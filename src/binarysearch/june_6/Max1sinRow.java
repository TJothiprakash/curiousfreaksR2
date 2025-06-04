package binarysearch.june_6;

public class Max1sinRow {
    public static void main(String[] args) {
        int[][] arr = {
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 1, 1}
        };

        int[] result = findRowThatHasMaxNoOf1s(arr);
        System.out.println("Row with maximum 1s: " + result[0] + " and the no of 1s in that row is  " + result[1]);  // Should print 3
    }

    static int[] findRowThatHasMaxNoOf1s(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int maxRowIndex = -1;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            int firstOneIndex = findFirstOne(arr[i], 0, m - 1);
            if (firstOneIndex != -1) {
                int count = m - firstOneIndex;
                if (count > maxCount) {
                    maxCount = count;
                    maxRowIndex = i;
                }
            }
        }

        return new int[]{maxRowIndex, maxCount};
    }

    // Binary search to find first occurrence of 1
    static int findFirstOne(int[] row, int low, int high) {
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (row[mid] == 1) {
                result = mid;
                high = mid - 1; // Look for earlier 1 on the left
            } else {
                low = mid + 1;
            }
        }

        return result;
    }
}
