package dynamic_programming.dp_onsubsequence;
/*Given an array arr of non-negative integers and an integer target, the task is to count all subsets of the array whose sum is equal to the given target.

Note: It is guaranteed that the product of the length of arr and target will not exceed 106

Examples:

Input: arr[] = [5, 2, 3, 10, 6, 8], target = 10
Output: 3
Explanation: The subsets {5, 2, 3}, {2, 8}, and {10} sum up to the target 10.
Input: arr[] = [2, 5, 1, 4, 3], target = 10
Output: 3
Explanation: The subsets {2, 1, 4, 3}, {5, 1, 4}, and {2, 5, 3} sum up to the target 10.
Input: arr[] = [5, 7, 8], target = 3
Output: 0
Explanation: There are no subsets of the array that sum up to the target 3.
Input: arr[] = [35, 2, 8, 22], target = 0
Output: 1
Explanation: The empty subset is the only subset with a sum of 0.
Constraints:
1 ≤ arr.size() ≤ 103
0 ≤ arr[i] ≤ 103
0 ≤ target ≤ 103*/
public class SubsetSumCount {
    public static int countSubsets(int[] arr, int target) {
        int n = arr.length;
        Integer[][] dp = new Integer[n + 1][target + 1];

        return countSubsetsHelper(arr, n, target, dp);
    }

    private static int countSubsetsHelper(int[] arr, int n, int target, Integer[][] dp) {
        // Base cases
        if (target == 0) return 1; // The empty subset satisfies this
        if (n == 0) return 0;      // No elements left, no subsets possible

        // Check memoization table
        if (dp[n][target] != null) return dp[n][target];

        // Exclude the current element
        int exclude = countSubsetsHelper(arr, n - 1, target, dp);

        // Include the current element if it doesn't exceed the target
        int include = (arr[n - 1] <= target)
                ? countSubsetsHelper(arr, n - 1, target - arr[n - 1], dp)
                : 0;

        // Store the result in the memo table
        dp[n][target] = exclude + include;

        return dp[n][target];
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 2, 3, 10, 6, 8};
        int target1 = 10;
        System.out.println(countSubsets(arr1, target1)); // Output: 3

        int[] arr2 = {2, 5, 1, 4, 3};
        int target2 = 10;
        System.out.println(countSubsets(arr2, target2)); // Output: 3

        int[] arr3 = {5, 7, 8};
        int target3 = 3;
        System.out.println(countSubsets(arr3, target3)); // Output: 0

        int[] arr4 = {35, 2, 8, 22};
        int target4 = 0;
        System.out.println(countSubsets(arr4, target4)); // Output: 1
    }
}
