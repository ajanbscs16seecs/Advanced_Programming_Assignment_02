package Jobs;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Arif
 * Date: 9/30/2018
 */

public class Counter implements Runnable{



    char[] chars;
    OnJobDoneListener onJobDoneListener;

    Map<Character,Integer> counted;


    public Counter(char[] chars, OnJobDoneListener onJobDoneListener) {
        this.chars = chars;
        this.onJobDoneListener = onJobDoneListener;
    }

    @Override
    public void run() {

        counted = new HashMap<>();


        int length = chars.length;
        for(int i = 0 ; i<length; i++){
            if(counted.containsKey(chars[i])){
                counted.put(chars[i],counted.get(chars[i]) + 1);
            }
            else{
                counted.put(chars[i],0);
            }
        }

        onJobDoneListener.onJobDone(counted);

    }




}
