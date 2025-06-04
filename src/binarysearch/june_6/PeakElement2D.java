package binarysearch.june_6;

public class PeakElement2D {
    public static void main(String[] args) {
        int[][] mat = {
                {10, 20, 15},
                {21, 30,46},
                {7, 16, 32}
        };

        int[] peak = findPeakGrid(mat);
        System.out.println("Peak found at: [" + peak[0] + "," + peak[1] + "]");
    }

    public static int[] findPeakGrid(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int left = 0;
        int right = cols - 1;

        while (left <= right) {
            int midCol = left + (right - left) / 2;

            // Find the global maximum in this column
            int maxRow = 0;
            for (int row = 0; row < rows; row++) {
                if (mat[row][midCol] > mat[maxRow][midCol]) {
                    maxRow = row;
                }
            }

            int leftNeighbor = midCol > 0 ? mat[maxRow][midCol - 1] : -1;
            int rightNeighbor = midCol < cols - 1 ? mat[maxRow][midCol + 1] : -1;
            int current = mat[maxRow][midCol];

            if (current > leftNeighbor && current > rightNeighbor) {
                return new int[]{maxRow, midCol};
            } else if (rightNeighbor > current) {
                left = midCol + 1;
            } else {
                right = midCol - 1;
            }
        }

        return new int[]{-1, -1}; // Should never happen due to problem constraints
    }
}
