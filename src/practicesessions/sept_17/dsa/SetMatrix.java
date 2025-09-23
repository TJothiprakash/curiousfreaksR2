package practicesessions.sept_17.dsa;

public class SetMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[4][4];
        int n = matrix.length, m = matrix[0].length;

        // Fill matrix with incremental numbers
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = k++;
            }
        }

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        setZeroes(matrix);

        System.out.println("\nMatrix After setZeroes:");
        printMatrix(matrix);

        System.out.println("\nTraversal Example (3x5):");
        varyingLengthAndWidth();
    }

    // ------------------------------
    // Core Algorithm: Set Matrix Zero
    // ------------------------------
    public static void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Track if first row or col should be zero
        boolean firstRow = false, firstCol = false;

        // Check first row
        for (int j = 0; j < m; j++) {
            if (matrix[0][j] == 1) {
                firstRow = true;
                break;
            }
        }

        // Check first col
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 1) {
                firstCol = true;
                break;
            }
        }

        // Mark rows/cols to be zeroed
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][0] = 1; // mark row
                    matrix[0][j] = 1; // mark col
                }
            }
        }

        // Zero marked rows
        for (int i = 1; i < n; i++) {
            if (matrix[i][0] == 1) {
                makeRowZero(matrix, i);
            }
        }

        // Zero marked cols
        for (int j = 1; j < m; j++) {
            if (matrix[0][j] == 1) {
                makeColZero(matrix, j);
            }
        }

        // Handle first row/col separately
        if (firstRow) makeRowZero(matrix, 0);
        if (firstCol) makeColZero(matrix, 0);
    }

    // ------------------------------
    // Helpers
    // ------------------------------
    private static void makeRowZero(int[][] mat, int row) {
        for (int j = 0; j < mat[0].length; j++) {
            mat[row][j] = 0;
        }
    }

    private static void makeColZero(int[][] mat, int col) {
        for (int i = 0; i < mat.length; i++) {
            mat[i][col] = 0;
        }
    }

    private static void printMatrix(int[][] mat) {
        for (int[] row : mat) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // ------------------------------
    // Traversal Demo (3x5 matrix)
    // ------------------------------
    private static void varyingLengthAndWidth() {
        int[][] mat = new int[3][5];
        int k = 0;

        // Fill
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = k++;
            }
        }

        System.out.println("Row-wise traversal:");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Column-wise traversal:");
        for (int j = 0; j < mat[0].length; j++) {
            for (int i = 0; i < mat.length; i++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
