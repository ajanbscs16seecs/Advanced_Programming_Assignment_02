import Jobs.Counter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Arif
 * Date: 9/30/2018
 */

public class CountManager {


     Map<Character,Integer> counts = new HashMap<>();
    private final Lock lock = new ReentrantLock();


    File file;
    ExecutorService executorService;
    int chunkSize;
    boolean noThreads;

    public CountManager(File file,int numberOfThreads,int chunkSize) throws FileNotFoundException {

        this.file = file;
        this.chunkSize = chunkSize;

        if(numberOfThreads == 0){
            noThreads = true;
        }
        else {
            executorService = Executors.newFixedThreadPool(numberOfThreads);
        }

    }


//    void setMap(Map<Character,Integer> a){
//        synchronized (lock){
//            this.counts= a;
//        }
//    }
//    Map<Character,Integer> getCounts(){
//        synchronized (lock){
//            return this.counts;
//        }
//    }
    void mergeCounts(Map<Character,Integer> map){

        try {
            lock.lock();


            for (char c :
                    map.keySet()) {
                counts.put(c, (counts.getOrDefault(c, 0)) + map.get(c));

            }


        } finally {
            lock.unlock();
        }






    }

    void init() throws Exception {

        FileReader fileReader = new FileReader(file);
        long time = new Date().getTime();
        int id=1;
        char[] c = new char[this.chunkSize];


        while(fileReader.read(c)!=-1){
            Counter counter = new Counter(id,c, (id1,map)->{
                synchronized (lock){
                    this.mergeCounts(map);
                }
//                System.out.println("Result of chuck: "+id1);
            });

            if(noThreads){
                // do it ourselves... no thread...
                counter.count();
            }
            else{
                // do this with service

                executorService.submit(counter);
//                System.out.println("Submitted chuck: "+id);
            }
            id++;
        }

        fileReader.close();

        if(!noThreads){
            executorService.shutdown();
            try {
                executorService.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counts.toString());
        System.out.println("Time: "+(new Date().getTime()-time)+"ms");

    }
}
