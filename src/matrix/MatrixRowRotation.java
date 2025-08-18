package matrix;

public class MatrixRowRotation {

    static int[][] rotateMatrix(int[][] mat, int k) {
        int n = mat.length;        // number of rows
        int m = mat[0].length;     // number of cols
        int[][] result = new int[n][m];

        // normalize k to avoid extra work
        k = k % m;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // element at position j in row i moves to (j-k+m)%m
                result[i][j] = mat[i][(j + k) % m];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int k = 2;

        int[][] rotated = rotateMatrix(mat, k);

        for (int[] row : rotated) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
