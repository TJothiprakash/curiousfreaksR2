package dynamic_programming.dp_ongrids;

import java.util.Arrays;

/*Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
*/
public class MiniandMaxPathSum {

    // Memoization approach (top-down DP)
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Create a memoization table to store the minimum path sum
        int[][] dp = new int[m][n];

        // Initialize the dp table with -1 (indicating uncomputed)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        // Start from the top-left corner and compute the minimum path sum
        return helper(grid, m - 1, n - 1, dp);
    }

    private int helper(int[][] grid, int i, int j, int[][] dp) {
        // Base cases
        if (i == 0 && j == 0) return grid[0][0];  // Starting point
        if (i < 0 || j < 0) return Integer.MAX_VALUE;  // Out of bounds

        // If the value is already computed, return it
        if (dp[i][j] != -1) return dp[i][j];

        // Recursively compute the minimum path sum from top or left
        int fromTop = helper(grid, i - 1, j, dp);
        int fromLeft = helper(grid, i, j - 1, dp);

        // Store the result in the dp table and return the minimum path sum
        dp[i][j] = grid[i][j] + Math.min(fromTop, fromLeft);
        return dp[i][j];
    }


    // Memoization table to store the maximum path sums
    public int maxPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Memoization table to store results of subproblems
        int[][] memo = new int[m][n];

        // Initialize the memo table with -1 (meaning unvisited)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Start recursion from the top-left corner (0, 0)
        return maxPathSumRecursive(grid, m - 1, n - 1, memo);
    }

    // Helper function to calculate the maximum path sum recursively with memoization
    private int maxPathSumRecursive(int[][] grid, int i, int j, int[][] memo) {
        // Base case: if we reach (0, 0), return the value in grid[0][0]
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        // If the result is already calculated, return it from the memo table
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Calculate the maximum path sum from the top and left cells
        int fromTop = Integer.MIN_VALUE;
        if (i > 0) {
            fromTop = maxPathSumRecursive(grid, i - 1, j, memo);
        }

        int fromLeft = Integer.MIN_VALUE;
        if (j > 0) {
            fromLeft = maxPathSumRecursive(grid, i, j - 1, memo);
        }

        // The current cell's value is added to the maximum of the two possible paths
        memo[i][j] = grid[i][j] + Math.max(fromTop, fromLeft);

        // Return the result from the memo table
        return memo[i][j];
    }
}

