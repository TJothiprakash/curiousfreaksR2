package dynamic_programming.dp_ongrids;

import java.util.Arrays;
/*Given an m x n binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.

Example 1:

Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],
["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
Example 2:


Input: matrix = [["0","1"],["1","0"]]
Output: 1
Example 3:

Input: matrix = [["0"]]
Output: 0


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.*/

public class MaximalAreaSquare {

    private int maxSide = 0;

    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] memo = new int[n][m];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                helper(matrix, i, j, memo);
            }
        }
        return maxSide * maxSide;
    }

    private int helper(char[][] matrix, int i, int j, int[][] memo) {
        // define basecase
        if (i >= matrix.length || j >= matrix[0].length) return 0;

        if (memo[i][j] != -1) return memo[i][j];


        int right = helper(matrix, i + 1, j, memo);
        int down = helper(matrix, i, j + 1, memo);
        int diag = helper(matrix, i + 1, j + 1, memo);


        // explore all possibilites
        // explore all possible path (left, down)
// return maximum square cell (1 + min(left, down, diagonal) )
        // do the stuff


        int curr = 0;
        if (matrix[i][j] == '1') {
            curr = 1 + Math.min(right, Math.min(down, diag));
            maxSide = Math.max(maxSide, curr);
        }

        memo[i][j] = curr;
        return memo[i][j];
        // return recursion
    }
}