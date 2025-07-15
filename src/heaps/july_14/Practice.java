package heaps.july_14;

import java.util.HashMap;
import java.util.Map;

public class Practice {
    public static void main(String[] args) {

    }

    //
    public void topKfrequentWords(String[] words) {

        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);

        }


    }
}
