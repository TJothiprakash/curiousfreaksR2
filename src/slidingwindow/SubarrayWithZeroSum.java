package slidingwindow;

import java.util.HashSet;

public class SubarrayWithZeroSum {
    public static boolean hasZeroSumSubarray(int[] arr) {
        // Initialize a HashSet to store prefix sums
        HashSet<Integer> prefixSums = new HashSet<>();
        int prefixSum = 0;

        for (int num : arr) {
            prefixSum += num;

            // Check if the prefix sum is 0 or if it exists in the set
            if (prefixSum == 0 || prefixSums.contains(prefixSum)) {
                return true;
            }

            // Add the current prefix sum to the set
            prefixSums.add(prefixSum);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 2, -3, 1, 6};
        System.out.println(hasZeroSumSubarray(arr1)); // Output: true

        int[] arr2 = {4, 2, 0, 1, 6};
        System.out.println(hasZeroSumSubarray(arr2)); // Output: true

        int[] arr3 = {1, 2, -1};
        System.out.println(hasZeroSumSubarray(arr3)); // Output: false
    }
}
