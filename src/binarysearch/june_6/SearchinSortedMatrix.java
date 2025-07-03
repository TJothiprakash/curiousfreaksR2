package binarysearch.june_6;

public class SearchinSortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int x = 11;
        int result = findX(x, matrix);
        System.out.println("Index of " + x + " is: " + result);

        SearchInSorted2DMatrix searchInSorted2DMatrix = new SearchInSorted2DMatrix();
        searchInSorted2DMatrix.main(args);
    }

    static int findX(int x, int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        int left = 0;
        int right = n * m - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int row = mid / m;
            int col = mid % m;

            if (arr[row][col] == x) {
                return mid;  // You can also return row, col if needed
            } else if (arr[row][col] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Not found
    }

    static class SearchInSorted2DMatrix {
        public static void main(String[] args) {
            int[][] matrix = {
                    {1, 4, 7, 11, 15},
                    {2, 5, 8, 12, 19},
                    {3, 6, 9, 16, 22},
                    {10, 13, 14, 17, 24},
                    {18, 21, 23, 26, 30}
            };

            int target = 5;
            System.out.println("Target found? " + searchMatrix(matrix, target)); // true

            target = 23;
            System.out.println("Target found? " + searchMatrix(matrix, target)); // false
        }

        //O( log n + m) O (1)
// staricase search diff from binary search
        static boolean searchMatrix(int[][] matrix, int target) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            int row = 0;
            int col = cols - 1; // Start at top-right corner

            while (row < rows && col >= 0) {
                int val = matrix[row][col];

                if (val == target) {
                    return true;
                } else if (val > target) {
                    col--; // move left
                } else {
                    row++; // move down
                }
            }

            return false; // not found
        }
    }

}
