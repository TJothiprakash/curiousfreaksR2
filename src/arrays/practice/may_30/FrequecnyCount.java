package arrays.practice.may_30;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FrequecnyCount {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 6, 6, 6};
        countFrequency(arr);
        countFrequency2(arr);
    }

    static void countFrequency(int[] arr) {

        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);

        }
        System.out.println(freq);

    }

    //using hasharray
    static void countFrequency2(int[] arr) {
        int[] hashArray = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            hashArray[arr[i]]++;
        }
        System.out.println(Arrays.toString(hashArray));
    }
}
