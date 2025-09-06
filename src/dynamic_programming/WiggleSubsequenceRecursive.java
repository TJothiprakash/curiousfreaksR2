package dynamic_programming;

import java.util.Arrays;

public class WiggleSubsequenceRecursive {
    public static int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        // Memo table: dp[i][prevIndex+1][2] (-1 -> index 0)
        int[][][] dp = new int[n][n + 1][2];
        for (int[][] layer : dp) {
            for (int[] row : layer) Arrays.fill(row, -1);
        }
        return Math.max(helper(nums, 0, -1, true, dp),
                helper(nums, 0, -1, false, dp));
    }

    private static int helper(int[] nums, int index, int prevIndex, boolean isUp, int[][][] dp) {
        if (index == nums.length) return 0;

        int prev = prevIndex == -1 ? 0 : nums[prevIndex];
        int memoIndex = prevIndex + 1;
        int isUpIndex = isUp ? 1 : 0;

        if (dp[index][memoIndex][isUpIndex] != -1) return dp[index][memoIndex][isUpIndex];

        // Option 1: skip current
        int length = helper(nums, index + 1, prevIndex, isUp, dp);

        // Option 2: take current if it forms a wiggle
        if (prevIndex == -1 ||
                (isUp && nums[index] > nums[prevIndex]) ||
                (!isUp && nums[index] < nums[prevIndex])) {
            length = Math.max(length, 1 + helper(nums, index + 1, index, !isUp, dp));
        }

        return dp[index][memoIndex][isUpIndex] = length;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,7,4,9,2,5};
        int[] nums2 = {1,17,5,10,13,15,10,5,16,8};
        int[] nums3 = {1,2,3,4,5,6,7,8,9};

        System.out.println(wiggleMaxLength(nums1)); // 6
        System.out.println(wiggleMaxLength(nums2)); // 7
        System.out.println(wiggleMaxLength(nums3)); // 2
    }
}
