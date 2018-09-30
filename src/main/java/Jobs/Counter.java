package Jobs;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Arif
 * Date: 9/30/2018
 */

public class Counter implements Runnable{



    int id;
    char[] chars;
    OnJobDoneListener onJobDoneListener;

    Map<Character,Integer> counted;


    public Counter(int id,char[] chars, OnJobDoneListener onJobDoneListener) {
        this.chars = chars;
        this.id = id;
        this.onJobDoneListener = onJobDoneListener;
    }

    @Override
    public void run() {
        count();
    }

    public void count(){
        counted = new HashMap<>();

        for (char aChar : chars) {
            if(aChar!=0){
                counted.put(aChar, counted.getOrDefault(aChar,0) + 1);

            }
        }

        onJobDoneListener.onJobDone(id,counted);
    }




}
