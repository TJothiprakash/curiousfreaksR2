package arrays;

import java.util.HashMap;

 class SubArraySumWithK {
    public static void main(String[] args) {
        int[] arr = {10, 5, 2, 7, 1, 9};
        int target = 15;
        System.out.println("Maximum Length of Subarray with Target Sum: " + subArraySum(arr, target));
    }

    private static int subArraySum(int[] arr, int target) {
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0;
        int maxLength = 0;

        // Add initial prefixSum = 0 at index -1
        prefixSumMap.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];

            // Check if prefixSum - target exists
            if (prefixSumMap.containsKey(prefixSum - target)) {
                int length = i - prefixSumMap.get(prefixSum - target);
                maxLength = Math.max(maxLength, length);
            }

            // Store the first occurrence of prefixSum
            prefixSumMap.putIfAbsent(prefixSum, i);
        }
        return maxLength;
    }
}
