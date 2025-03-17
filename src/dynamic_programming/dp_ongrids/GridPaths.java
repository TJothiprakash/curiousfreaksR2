package dynamic_programming.dp_ongrids;

import java.util.Arrays;

public class GridPaths {
    // Function to count the number of ways to reach cell (i, j)
    static int countWaysUtil(int i, int j, int[][] dp) {
        // If we reach the starting cell (0, 0), there's one way to reach it.
        if (i == 0 && j == 0)
            return 1;

        // If we go out of bounds, there's no way to reach the cell.
        if (i < 0 || j < 0)
            return 0;

        // If the value for this cell is already computed, return it.
        if (dp[i][j] != -1)
            return dp[i][j];

        // Calculate the number of ways by moving up and moving left.
        int up = countWaysUtil(i - 1, j, dp);
        int left = countWaysUtil(i, j - 1, dp);

        // Store the result in the DP array and return it.
        return dp[i][j] = up + left;
    }

    // Function to count the number of ways to reach cell (m-1, n-1)
    static int countWays(int m, int n) {
        // Create a 2D DP array to store the results
        int[][] dp = new int[m][n];

        // Initialize the DP array with -1 to indicate uncomputed values
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Start the recursive calculation from the bottom-right cell (m-1, n-1)
        return countWaysUtil(m - 1, n - 1, dp);
    }

    public static void main(String[] args) {
        int m = 100;
        int n = 67;

        // Call the countWays function and print the result
        System.out.println(countWays(m, n));
    }
}


