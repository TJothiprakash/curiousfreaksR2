/*
Problem:
You are given an m x n integer matrix `mat` where each row is sorted and each column is unsorted.
Your task is to find a peak element in the matrix. A peak is an element that is strictly greater than its left and right neighbors.
Return the position (row, col) of any such peak.

Constraints:
- You may assume there's at least one peak.
- Neighbors only mean left and right in the same row (not up and down).

Intuition:
We use binary search on the columns. For each mid-column, find the global max row.
Compare it with left and right neighbors in the same row.
Based on which neighbor is greater, move your search window accordingly.

Dry Run:
Input:
mat = {
    {10, 20, 15},
    {21, 30, 46},
    {7, 16, 32}
}
Step 1: midCol = 1, maxRow = 1 (30)
Left = 21, Right = 46 → Go right

Step 2: midCol = 2, maxRow = 1 (46)
Left = 30, Right = out of bounds → 46 > 30 → Peak found

Code:
*/
package binarysearch.june_6;

public class PeakElement2D {

    public static void main(String[] args) {
        int[][] mat = {
                {10, 20, 15},
                {21, 30, 46},
                {7, 16, 32}
        };

        int[] peak = findPeakGrid(mat);
        System.out.println("✅ Peak found at: [" + peak[0] + "," + peak[1] + "]");
    }

    // Time: O(n * log m), Space: O(1)
    public static int[] findPeakGrid(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        int left = 0;
        int right = cols - 1;

        while (left <= right) {
            int midCol = left + (right - left) / 2;
            System.out.println("🔍 Searching in column: " + midCol);

            // Find the max row in the mid column
            int maxRow = 0;
            for (int row = 0; row < rows; row++) {
                if (mat[row][midCol] > mat[maxRow][midCol]) {
                    maxRow = row;
                }
            }

            int current = mat[maxRow][midCol];
            int leftNeighbor = (midCol > 0) ? mat[maxRow][midCol - 1] : -1;
            int rightNeighbor = (midCol < cols - 1) ? mat[maxRow][midCol + 1] : -1;

            System.out.println("➡️ Max in column " + midCol + " is mat[" + maxRow + "][" + midCol + "] = " + current);
            System.out.println("   Left neighbor: " + leftNeighbor);
            System.out.println("   Right neighbor: " + rightNeighbor);

            // Check if it's a peak
            if (current > leftNeighbor && current > rightNeighbor) {
                System.out.println("🎯 Peak found at [" + maxRow + "," + midCol + "] with value " + current);
                return new int[]{maxRow, midCol};
            } else if (rightNeighbor > current) {
                System.out.println("➡️ Right neighbor is greater. Moving right.");
                left = midCol + 1;
            } else {
                System.out.println("⬅️ Left neighbor is greater. Moving left.");
                right = midCol - 1;
            }
        }

        System.out.println("❌ No peak found. (Should not reach here)");
        return new int[]{-1, -1}; // According to constraints, this won't happen
    }

    /*
    Complexity:
    Time: O(n * log m), where n is number of rows and m is number of columns
          - For each column (log m), you search all rows to find max (O(n)).
    Space: O(1), since no extra space is used.
    */
}
