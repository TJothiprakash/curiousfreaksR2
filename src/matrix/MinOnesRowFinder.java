package matrix;

import org.junit.jupiter.api.Order;

import java.sql.Time;

/*

import java.sql.Time;✅ Approach (Straightforward)
Initialize:

minCount = Integer.MAX_VALUE

        minRow = -1

Loop through each row:

Count number of 1’s in that row.

If the count is less than minCount, update minCount and minRow.

        If equal, keep the smaller row index (minRow) — this is automatically handled because you update only on count < minCount.

✅ Java Code:
java
        Copy
Edit
*/
public class MinOnesRowFinder {
    public static int rowWithMinimumOnes(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int minCount = Integer.MAX_VALUE;
        int minRow = -1;

        for (int i = 0; i < n; i++) {
            int ones = 0;
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    ones++;
                }
            }

            if (ones < minCount) {
                minCount = ones;
                minRow = i + 1; // convert to 1-based index
            }
        }

        return minRow;
    }

    public static void main(String[] args) {
        int[][] mat1 = {{1, 1, 1, 1}, {1, 1, 0, 0}, {0, 0, 1, 1}, {1, 1, 1, 1}};
        int[][] mat2 = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(rowWithMinimumOnes(mat1)); // Output: 2
        System.out.println(rowWithMinimumOnes(mat2)); // Output: 1
    }
}
/*

🧠 Time Complexity:
O(n * m) where n = number of rows, m = number of columns.

✅ Optimization (If Rows are Sorted in Decreasing Order of 1s)
You can use binary search per row to find the first 0 and compute 1's count in O(log m) — but only if rows are sorted like: 1 1 1 0 0.
*/
