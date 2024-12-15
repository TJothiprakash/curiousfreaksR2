package dynamic_programming.dp_onsubsequence;

import java.util.HashMap;

/*Given an array arr[]  containing non-negative integers, the task is to divide it into two sets set1 and set2 such that the absolute difference between their sums is minimum and find the minimum difference.

Examples:

Input: arr[] = [1, 6, 11, 5]
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11
Hence, minimum difference is 1.
Input: arr[] = [1, 4]
Output: 3
Explanation:
Subset1 = {1}, sum of Subset1 = 1
Subset2 = {4}, sum of Subset2 = 4
Hence, minimum difference is 3.
Input: arr[] = [1]
Output: 1
Explanation:
Subset1 = {1}, sum of Subset1 = 1
Subset2 = {}, sum of Subset2 = 0
Hence, minimum difference is 1.
Constraints:
1 ≤ arr.size()*|sum of array elements| ≤ 105
1 <= arr[i] <= 105*/
public class MinimumDifferenceSubset {

    // Memoization table to store previously calculated results
    static HashMap<String, Integer> memo = new HashMap<>();

    public static int minDifference(int[] arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        return findMinDifference(arr, 0, 0, totalSum);
    }

    // Recursive function with memoization
    private static int findMinDifference(int[] arr, int i, int sum1, int totalSum) {
        // Base case: If we've considered all elements
        if (i == arr.length) {
            int sum2 = totalSum - sum1;
            return Math.abs(sum2 - sum1);
        }

        // Memoization key
        String key = i + "," + sum1;

        // If the result is already computed for this state, return it
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Include current element in the first subset
        int include = findMinDifference(arr, i + 1, sum1 + arr[i], totalSum);

        // Exclude current element from the first subset (i.e., include in the second subset)
        int exclude = findMinDifference(arr, i + 1, sum1, totalSum);

        // Take the minimum of including or excluding the current element
        int result = Math.min(include, exclude);

        // Store the result in the memoization table
        memo.put(key, result);

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 6, 11, 5};
        System.out.println("Minimum difference: " + minDifference(arr1)); // Output: 1

        int[] arr2 = {1, 4};
        System.out.println("Minimum difference: " + minDifference(arr2)); // Output: 3

        int[] arr3 = {1};
        System.out.println("Minimum difference: " + minDifference(arr3)); // Output: 1
    }
}

