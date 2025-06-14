package dynamic_programming.dp_ongrids;

/*You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The testcases are generated so that the answer will be less than or equal to 2 * 109.



Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1


Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
*/

import java.util.Arrays;

public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        UniquePathsWithObstacles solution = new UniquePathsWithObstacles();

        int[][] obstacleGrid1 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(solution.uniquePathsWithObstacles(obstacleGrid1)); // Output: 2

        int[][] obstacleGrid2 = {
                {0, 1},
                {0, 0}
        };
        System.out.println(solution.uniquePathsWithObstacles(obstacleGrid2)); // Output: 1
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // If the start or end is blocked, no paths are possible
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        // Memoization table
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return findPaths(m - 1, n - 1, obstacleGrid, memo);
    }

    private int findPaths(int i, int j, int[][] grid, int[][] memo) {
        // Base cases
        if (i < 0 || j < 0 || grid[i][j] == 1) {
            return 0; // Out of bounds or obstacle
        }
        if (i == 0 && j == 0) {
            return 1; // Reached the start cell
        }

        // Check memo table
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Recursive calls for top and left cells
        int top = findPaths(i - 1, j, grid, memo);
        int left = findPaths(i, j - 1, grid, memo);

        // Store result in memo table
        memo[i][j] = top + left;

        return memo[i][j];
    }
}


/*The task is to count all the possible paths from top left to bottom right of a m X n matrix with the constraints that from each cell you can either move only to right or down.

Examples :

Input: m = 2, n = 2
Output: 2
Explanation: Two possible ways are RD and DR.
Input: m = 3, n = 3
Output: 6
Explanation: Six possible ways are RRDD, DDRR, RDDR, DRRD, RDRD, DRDR.
Constraints:
1 <= m <= 17
1 <= n <=17

*/


class NumPaths {
    void countPaths(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return;
        }
        int m = grid.length - 1;
        int n = grid[0].length - 1;
        int[][] dp = new int[m][n];
        int ans = helper(dp, m, n, grid);
    }

    static int helper(int[][] memo, int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || grid[i][j] == 1) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 1;
        }
        int right = helper(memo, i + 1, j, grid);
        int left = helper(memo, i, j - 1, grid);
        memo[i][j] = right + left;
        return memo[i][j];
    }

}
