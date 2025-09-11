package matrix.sept_10;

import java.util.ArrayList;
import java.util.List;


/*2482. Difference Between Ones and Zeros in Row and Column
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given a 0-indexed m x n binary matrix grid.

A 0-indexed m x n difference matrix diff is created with the following procedure:

Let the number of ones in the ith row be onesRowi.
Let the number of ones in the jth column be onesColj.
Let the number of zeros in the ith row be zerosRowi.
Let the number of zeros in the jth column be zerosColj.
diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj
Return the difference matrix diff.



Example 1:


Input: grid = [[0,1,1],[1,0,1],[0,0,1]]
Output: [[0,0,4],[0,0,4],[-2,-2,2]]
Explanation:
- diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 2 + 1 - 1 - 2 = 0
- diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 2 + 1 - 1 - 2 = 0
- diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 2 + 3 - 1 - 0 = 4
- diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 2 + 1 - 1 - 2 = 0
- diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 2 + 1 - 1 - 2 = 0
- diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 2 + 3 - 1 - 0 = 4
- diff[2][0] = onesRow2 + onesCol0 - zerosRow2 - zerosCol0 = 1 + 1 - 2 - 2 = -2
- diff[2][1] = onesRow2 + onesCol1 - zerosRow2 - zerosCol1 = 1 + 1 - 2 - 2 = -2
- diff[2][2] = onesRow2 + onesCol2 - zerosRow2 - zerosCol2 = 1 + 3 - 2 - 0 = 2
Example 2:


Input: grid = [[1,1,1],[1,1,1]]
Output: [[5,5,5],[5,5,5]]
Explanation:
- diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 3 + 2 - 0 - 0 = 5
- diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 3 + 2 - 0 - 0 = 5
- diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 3 + 2 - 0 - 0 = 5
- diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 3 + 2 - 0 - 0 = 5
- diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 3 + 2 - 0 - 0 = 5
- diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 3 + 2 - 0 - 0 = 5


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 105
1 <= m * n <= 105
grid[i][j] is either 0 or 1.*/

public class Matrix01 {
    static void main() {

    }

    static class Row {
        int row;
        int zeros;
        int ones;

        Row(int row, int zeros, int ones) {
            this.row = row;
            this.zeros = zeros;
            this.ones = ones;
        }
    }

    static class Col {
        int col;
        int zeros;
        int ones;

        Col(int col, int zeros, int ones) {
            this.col = col;
            this.zeros = zeros;
            this.ones = ones;

        }
    }

    public int[][] onesMinusZeros(int[][] grid) {
        int[][] result = new int[grid.length][grid[0].length];

        result = helper(grid);

        return result;
    }

    public int[][] helper(int[][] grid) {
        List<Row> rows = new ArrayList<>();
        List<Col> cols = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            int rowZeros = 0, rowOnes = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    rowZeros++;
                } else {
                    rowOnes++;
                }
            }
            rows.add(new Row(i, rowZeros, rowOnes));

        }
        for (int j = 0; j < grid[0].length; j++) {
            int colZeros = 0, colOnes = 0;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 0) {
                    colZeros++;
                } else {
                    colOnes++;
                }
            }
            cols.add(new Col(j, colZeros, colOnes));
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = (rows.get(i).ones + cols.get(j).ones) - (rows.get(i).zeros + cols.get(j).zeros);
            }
        }

        return grid;
    }
}