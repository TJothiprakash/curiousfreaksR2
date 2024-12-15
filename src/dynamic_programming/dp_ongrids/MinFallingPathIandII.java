package dynamic_programming.dp_ongrids;

import java.util.Arrays;

public class MinFallingPathIandII {

    // Main function to initialize and call the helper function
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] memo = new int[n][n]; // Memoization array

        // Initialize the memoization array with -1 (indicating uncalculated)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int minSum = Integer.MAX_VALUE;

        // Try every element in the first row as starting point
        for (int j = 0; j < n; j++) {
            minSum = Math.min(minSum, minPath(grid, memo, 0, j));
        }

        return minSum;
    }

    // Helper function to calculate the minimum sum path for cell (i, j)
    private int minPath(int[][] grid, int[][] memo, int i, int j) {
        int n = grid.length;

        // Base case: If we are at the last row, just return the value at grid[i][j]
        if (i == n - 1) {
            return grid[i][j];
        }

        // If the result is already computed, return it from memo
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Calculate the possible next positions (down, left-down, right-down)
        int down = minPath(grid, memo, i + 1, j);
        int leftDown = (j - 1 >= 0) ? minPath(grid, memo, i + 1, j - 1) : Integer.MAX_VALUE;
        int rightDown = (j + 1 < n) ? minPath(grid, memo, i + 1, j + 1) : Integer.MAX_VALUE;

        // Store the result in memo and return the minimum sum path
        memo[i][j] = grid[i][j] + Math.min(down, Math.min(leftDown, rightDown));
        return memo[i][j];
    }


}

class MinFallingPathSumII {
    // Main function to initialize and call the helper function
    public int minFallingPathSumNonZeroShifts(int[][] grid) {
        int n = grid.length;
        int[][] memo = new int[n][n]; // Memoization array

        // Initialize the memoization array with -1 (indicating uncalculated)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int minSum = Integer.MAX_VALUE;

        // Try every element in the first row as starting point
        for (int j = 0; j < n; j++) {
            minSum = Math.min(minSum, minPath(grid, memo, 0, j));
        }

        return minSum;
    }

    // Helper function to calculate the minimum sum path for cell (i, j) with non-zero shifts
    private int minPath(int[][] grid, int[][] memo, int i, int j) {
        int n = grid.length;

        // Base case: If we are at the last row, just return the value at grid[i][j]
        if (i == n - 1) {
            return grid[i][j];
        }

        // If the result is already computed, return it from memo
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Calculate the possible next positions (down, left-down, right-down) with restriction on the column
        int down = Integer.MAX_VALUE;
        if (j > 0) down = Math.min(down, minPath(grid, memo, i + 1, j - 1)); // Move diagonally left
        down = Math.min(down, minPath(grid, memo, i + 1, j)); // Move down directly
        if (j < n - 1) down = Math.min(down, minPath(grid, memo, i + 1, j + 1)); // Move diagonally right

        // Store the result in memo and return the minimum sum path
        memo[i][j] = grid[i][j] + down;
        return memo[i][j];
    }
}
