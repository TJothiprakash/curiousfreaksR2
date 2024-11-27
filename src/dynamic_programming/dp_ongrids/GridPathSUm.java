package dynamic_programming.dp_ongrids;

import java.util.Arrays;

public class GridPathSUm {
    // Helper function to calculate the minimum sum path in the matrix
    static int minSumPathUtil(int i, int j, int[][] matrix, int[][] dp) {
        // Base cases
        if (i == 0 && j == 0)
            return matrix[0][0]; // If we're at the top-left cell, return its value
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE; // If we're out of bounds, return a very large value

        if (dp[i][j] != -1)
            return dp[i][j]; // If we've already calculated this cell, return the stored result

        // Calculate the sum of the current cell plus the minimum sum path from above and from the left
        int up = minSumPathUtil(i - 1, j, matrix, dp);
        int left = minSumPathUtil(i, j - 1, matrix, dp);

        // Store the result for the current cell
        return dp[i][j] = matrix[i][j] + Math.min(up, left);
    }

    // Main function to calculate the minimum sum path in the matrix
    static int minSumPath(int n, int m, int[][] matrix) {
        int dp[][] = new int[n][m];

        // Initialize the DP matrix with -1
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Call the helper function starting from the bottom-right cell
        return minSumPathUtil(n - 1, m - 1, matrix, dp);
    }

    public static void main(String[] args) {
        // Define the matrix
        int[][] matrix = {
                {5, 9, 6},
                {11, 5, 2}
        };

        int n = matrix.length;
        int m = matrix[0].length;

        // Calculate and print the minimum sum path in the matrix
        System.out.println(minSumPath(n, m, matrix)); // Output: 22
    }
}
