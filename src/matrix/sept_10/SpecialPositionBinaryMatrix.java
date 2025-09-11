package matrix.sept_10;

import java.util.Arrays;

public class SpecialPositionBinaryMatrix {
    static class Solution {
        public int numSpecial(int[][] mat) {
            return numSpecialhelper(mat);
        }


        private int numSpecialhelper(int[][] mat) {
            int rows = mat.length;
            int cols = mat[0].length;
            int count = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (mat[r][c] == 1) {
                        count += isSpecial(mat, r, c);
                    }
                }

            }
            return count;
        }

        private static int isSpecial(int[][] mat, int r, int c) {
            int row = mat.length;
            int col = mat[0].length;
            for (int d = 0; d < col; d++) {
                if (d != c && mat[r][d] == 1) {
                    return 0;
                }
            }
            for (int d = 0; d < row; d++) {
                if (d != r && mat[d][c] == 1) {
                    return 0;
                }
            }
            return 1;
        }
    }
    private static int optimizedVersionisSpecial(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int [] row =  new int [rows];
        int [] col = new int [cols];
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 1) {
                    row[r]++;
                    col[c]++;
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 1 && row[r]==1 &&  col[c] == 1) {
                    count++;
                }
            }
        }
        return count;
    }


}
