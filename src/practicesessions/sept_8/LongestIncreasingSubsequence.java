package practicesessions.sept_8;
import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i][prev+1] stores LIS length starting at index i with prevIndex=prev
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(0, -1, nums, dp);
    }

    private int solve(int i, int prev, int[] nums, int[][] dp) {
        if (i == nums.length) return 0;

        if (dp[i][prev + 1] != -1) return dp[i][prev + 1];

        // Option 1: Skip nums[i]
        int notTake = solve(i + 1, prev, nums, dp);

        // Option 2: Take nums[i] if valid
        int take = 0;
        if (prev == -1 || nums[i] > nums[prev]) {
            take = 1 + solve(i + 1, i, nums, dp);
        }

        return dp[i][prev + 1] = Math.max(take, notTake);
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence sol = new LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(sol.lengthOfLIS(nums)); // Output: 4
    }
}
