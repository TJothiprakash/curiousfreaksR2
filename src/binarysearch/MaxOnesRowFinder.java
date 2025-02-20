package binarysearch;

public class MaxOnesRowFinder {
    public static int[] findMaxOnesRow(int[][] mat, int N) {
        int maxRowIndex = -1;
        int maxCount = 0;
        int j = N - 1;  // Start from the last column (top-right corner)

        for (int i = 0; i < N; i++) {  // Iterate through each row
            while (j >= 0 && mat[i][j] == 1) {  // Move left while encountering 1s
                j--;
                maxRowIndex = i; // Update row index with max 1s
                maxCount = N - (j + 1); // Count of 1s in the row
            }
        }

        return new int[]{maxRowIndex, maxCount}; // Return row index & count
    }

    public static void main(String[] args) {
        int[][] mat = {
                {0, 0, 1},
                {0, 1, 1},
                {0, 0, 0}
        };
        int N = mat.length;

        int[] result = findMaxOnesRow(mat, N);
        System.out.println("Row number = " + result[0]);
        System.out.println("MaxCount = " + result[1]);
    }
}
