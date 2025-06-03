package matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixDiagonalTraversal {
    public static List<Integer> matrixDiagonally(int[][] mat) {
        int n = mat.length;
        List<Integer> result = new ArrayList<>();

        for (int d = 0; d < 2 * n - 1; d++) {
            int rowStart = d < n ? 0 : d - n + 1;
            int colStart = d < n ? d : n - 1;

            List<Integer> temp = new ArrayList<>();
            int r = rowStart, c = colStart;

            while (r < n && c >= 0) {
                temp.add(mat[r][c]);
                r++;
                c--;
            }

            // Reverse for even-numbered diagonals
            if (d % 2 == 0) {
                Collections.reverse(temp);
            }

            result.addAll(temp);
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(matrixDiagonally(mat));  // Output: [1, 2, 4, 7, 5, 3, 6, 8, 9]
    }
}
