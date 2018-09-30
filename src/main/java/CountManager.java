import Jobs.Counter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: Arif
 * Date: 9/30/2018
 */

public class CountManager {


    volatile Map<Character,Integer> counts = new HashMap<>();


    FileReader file;
    ExecutorService executorService;

    public CountManager(File file,int numberOfThreads) throws FileNotFoundException {
        this.file = new FileReader(file);

         executorService = Executors.newFixedThreadPool(numberOfThreads);
    }


    synchronized void mergeCounts(Map<Character,Integer> map){
        for (char c :
                map.keySet()) {

            counts.put(c,(counts.getOrDefault(c, 0))+map.get(c));

        }
    }

    void init() throws IOException {

        char[] c = new char[1000];
        while(file.read(c)==1){
            executorService.submit(new Counter(c, this::mergeCounts));
        }
        file.close();
        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS);

            System.out.println(counts.toString());



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
