package practicesessions.revisions.striver_sde_sheet;


public class Arrays {
// set zero matrix

    /*Problem Statement: Given a matrix if an element in the
     matrix is 0 then you will have to set its entire column and row to 0 and then return the matrix.*/
    public int[][] setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // First pass: Mark rows and columns with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    // Mark the entire row with -1 (if not already 0)
                    for (int k = 0; k < m; k++) {
                        if (matrix[i][k] != 0) {
                            matrix[i][k] = -1;
                        }
                    }
                    // Mark the entire column with -1 (if not already 0)
                    for (int k = 0; k < n; k++) {
                        if (matrix[k][j] != 0) {
                            matrix[k][j] = -1;
                        }
                    }
                }
            }
        }

        // Second pass: Convert all -1 markers to 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        }

        return matrix;
    }



}
