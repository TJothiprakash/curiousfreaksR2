package arrays.nov_06_revision;

import java.util.HashMap;

public class CompareTwoArrays {


//    O(2n) O(n)
    public boolean compare(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            frequencyMap.put(arr1[i], frequencyMap.getOrDefault(arr1[i], 0) + 1);

        }

        for (int i = 0; i < arr2.length; i++) {
            if (!frequencyMap.containsKey(arr2[i]) || frequencyMap.get(arr2[i]) <= 0) {
                return false;
            } else {
                frequencyMap.put(arr2[i], frequencyMap.get(arr2[i]) - 1);
            }
        }
        return true;
    }
}
