package dynamic_programming.dp_onsubsequence;

import java.util.Arrays;


// subset sum with given target

public class SubsetSumMemoization {
    public static boolean isSubsetSum(int[] arr, int target) {
        int n = arr.length;
        Boolean[][] memo = new Boolean[n + 1][target + 1];

        // Fill memo table with null (uncomputed states)
        for (Boolean[] row : memo) {
            Arrays.fill(row, null);
        }

        return isSubsetSumHelper(arr, n, target, memo);
    }

    private static boolean isSubsetSumHelper(int[] arr, int n, int target, Boolean[][] memo) {
        // Base cases
        if (target == 0) return true; // A subset with sum 0 exists (empty subset)
        if (n == 0) return false; // No elements left and target > 0

        // Check if result is already computed
        if (memo[n][target] != null) return memo[n][target];

        // If the current element is greater than the target, exclude it
        if (arr[n - 1] > target) {
            memo[n][target] = isSubsetSumHelper(arr, n - 1, target, memo);
        } else {
            // Include or exclude the current element
            memo[n][target] = isSubsetSumHelper(arr, n - 1, target - arr[n - 1], memo)
                    || isSubsetSumHelper(arr, n - 1, target, memo);
        }

        return memo[n][target];
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 34, 4, 12, 5, 2};
        int target1 = 9;
        System.out.println(isSubsetSum(arr1, target1)); // Output: true

        int[] arr2 = {3, 34, 4, 12, 5, 2};
        int target2 = 30;
        System.out.println(isSubsetSum(arr2, target2)); // Output: false

        int[] arr3 = {1, 2, 3};
        int target3 = 6;
        System.out.println(isSubsetSum(arr3, target3)); // Output: true
    }
}

