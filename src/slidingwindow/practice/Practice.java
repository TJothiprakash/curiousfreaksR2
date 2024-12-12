package slidingwindow.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Practice {
    public static void main(String[] args) {
        System.out.println(new Practice().maximumSumSubarray(new int[]{1, 2, 3, 4, 5}, 3));
    }
    public int maximumSumSubarray(int[] arr, int k) {

        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0, map.getOrDefault(0,0)+1);
        map.get(0);

        if(map.get(1)==0){
            System.out.println("element not found");
        }
        return -1;
/*
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        // Edge case: If the array length is less than k
        if (arr.length < k) {
            return -1; // Or throw an exception, depending on the requirement
        }

        int sum = 0;
        int maxsum = Integer.MIN_VALUE;

        // Calculate the initial sum of the first 'k' elements
        for (int i = 0; i < k; i++) {
            set.add(arr[i]);
            //sum += arr[i];
        }
        for(int num :set){
            sum+=num;
        }

        maxsum = sum;

        // Sliding window
        for (int j = k; j < arr.length; j++) {

            sum = sum + arr[j] - arr[j - k];
            maxsum = Math.max(maxsum, sum);
        }

        return maxsum;
   */ }
}
