package binarysearch.june_6;

public class MatrixSearch {
    /**
     * Searches for a target value in a strictly sorted 2D matrix.
     *
     * @param mat The 2D matrix where each row and each column is strictly increasing,
     *            and the first element of each row is greater than the last of the previous.
     * @param x   The target value to search for.
     * @return True if x is found in the matrix, false otherwise.
     */
    public static boolean searchMatrix(int[][] mat, int x) {
        int n = mat.length;
        int m = mat[0].length;

        int low = 0, high = n * m - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int row = mid / m;
            int col = mid % m;

            if (mat[row][col] == x) {
                return true;
            } else if (mat[row][col] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    // Example usage
    public static void main(String[] args) {
        int[][] mat1 = {
                {1, 5, 9},
                {14, 20, 21},
                {30, 34, 43}
        };

        int[][] mat2 = {
                {1, 5, 9, 11},
                {14, 20, 21, 26},
                {30, 34, 43, 50}
        };

        System.out.println(searchMatrix(mat1, 14)); // true
        System.out.println(searchMatrix(mat2, 42)); // false
    }
}
