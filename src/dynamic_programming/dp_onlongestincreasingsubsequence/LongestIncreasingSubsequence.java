package dynamic_programming.dp_onlongestincreasingsubsequence;


import java.util.Arrays;

public class LongestIncreasingSubsequence {
    // Function to find the length of the longest increasing subsequence
    static int getAns(int[] arr, int n, int ind, int prev_index, int[][] dp) {
        // Base condition
        if (ind == n) {
            return 0;
        }

        if (dp[ind][prev_index + 1] != -1) {
            return dp[ind][prev_index + 1];
        }

        int notTake = 0 + getAns(arr, n, ind + 1, prev_index, dp);

        int take = 0;

        if (prev_index == -1 || arr[ind] > arr[prev_index]) {
            take = 1 + getAns(arr, n, ind + 1, ind, dp);
        }

        dp[ind][prev_index + 1] = Math.max(notTake, take);

        return dp[ind][prev_index + 1];
    }

    // Function to find the length of the longest increasing subsequence
    static int longestIncreasingSubsequence(int[] arr, int n) {
        int[][] dp = new int[n][n + 1];

        // Initialize dp array with -1 to mark states as not calculated yet
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return getAns(arr, n, 0, -1, dp);
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};

        int n = arr.length;

        System.out.println("The length of the longest increasing subsequence is " + longestIncreasingSubsequence(arr, n));
    }
}

/*public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // Memoization table: -1 means uncomputed
        int[][] memo = new int[n][n + 1];
        for (int[] row : memo) Arrays.fill(row, -1);

        // Start the recursion with index 0 and previous index as -1
        return helper(nums, 0, -1, memo);
    }

    private int helper(int[] nums, int currentIndex, int prevIndex, int[][] memo) {
        if (currentIndex == nums.length) {
            return 0; // Base case: No more elements
        }

        // Use memoized value if available
        if (memo[currentIndex][prevIndex + 1] != -1) {
            return memo[currentIndex][prevIndex + 1];
        }

        // Option 1: Skip the current element
        int length = helper(nums, currentIndex + 1, prevIndex, memo);

        // Option 2: Include the current element if it forms an increasing subsequence
        if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
            length = Math.max(length, 1 + helper(nums, currentIndex + 1, currentIndex, memo));
        }

        // Store the result in the memo table
        memo[currentIndex][prevIndex + 1] = length;
        return length;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};

        System.out.println(lis.lengthOfLIS(nums1)); // Output: 4
        System.out.println(lis.lengthOfLIS(nums2)); // Output: 4
        System.out.println(lis.lengthOfLIS(nums3)); // Output: 1
    }
}
*/