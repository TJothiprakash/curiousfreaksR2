package matrix;

import org.jetbrains.annotations.Contract;

public class MinOnes {
//    O(n * n) O( 1)
    @Contract(pure = true)
    public static int rowWithMinimumOnes(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int minOnes = Integer.MAX_VALUE;
        int rowIndex = -1;

        for (int i = 0; i < n; i++) {
            int ones = 0;
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) ones++;
            }

            // Only update if we found a row with fewer 1s
            if (ones < minOnes) {
                minOnes = ones;
                rowIndex = i;  // save the first (smallest) such row
            }
        }

        return rowIndex + 1; // 1-based index
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        int ans = rowWithMinimumOnes(mat);
        System.out.println("Minimum number of 1s required in the matrix is: " + ans);
    }
}