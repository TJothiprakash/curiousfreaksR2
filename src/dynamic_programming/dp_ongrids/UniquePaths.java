package dynamic_programming.dp_ongrids;

import java.util.Arrays;

/*The task is to count all the possible paths from top left to bottom right of a m X n matrix with
 the constraints that from each cell you can either move only to right or down.

Example 1:

Input: m = 2, n = 2
Output: 2
Explanation: Two possible ways are
RD and DR.

Example 2:

Input: m = 3, n = 3
Output: 6
Explanation: Six possible ways are
RRDD, DDRR, RDDR, DRRD, RDRD, DRDR.

Your Task:
You dont need to read input or print anything.
Complete the function numberOfPaths() which takes m and n as input parameter and returns
 count all the possible paths.The answer may be very large, compute the answer modulo 109 + 7.


Expected Time Complexity: O(m*n)
Expected Auxiliary Space: O(m*n)

Constraints:
1 <= m <=100
1 <= n <=100*/
public class UniquePaths {

    public static int numberOfPaths(int m, int n) {
        int MOD = 1000000007;

        // Memoization table
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // Initialize all cells as -1 (indicating uncomputed)
        }

        return countPaths(m - 1, n - 1, memo);
    }

    // Recursive function to calculate the number of paths
    private static int countPaths(int i, int j, int[][] memo) {
        // Base cases
        if (i < 0 || j < 0) {
            return 0; // Out of bounds
        }
        if (i == 0 && j == 0) {
            return 1; // Reached the start point
        }

        // If the result is already computed, return it from memo
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Recursive call for top and left cells
        int up = countPaths(i - 1, j, memo);
        int left = countPaths(i, j - 1, memo);

        // Store the result in the memoization table and return it
        memo[i][j] = (up + left) % 1000000007;
        return memo[i][j];
    }

    public static void main(String[] args) {
        // Example test cases
        System.out.println(numberOfPaths(2, 2)); // Output: 2
        System.out.println(numberOfPaths(3, 3)); // Output: 6
    }
}
