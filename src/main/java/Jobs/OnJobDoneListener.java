package Jobs;

import java.util.Map;

/**
 * Author: Arif
 * Date: 9/30/2018
 */

public interface OnJobDoneListener {

    void onJobDone(Map<Character, Integer> counted);
}
