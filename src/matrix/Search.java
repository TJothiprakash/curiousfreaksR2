package matrix;

public class Search {
    int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {71, 8, 9}};
        int target = 7;
        System.out.println(searchElement(arr, target));
    }

    //O(log m * n) O(1)
    private static boolean searchElement(int[][] arr, int target) {
        int m = arr.length;
        int n = arr[0].length;
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / n;
            int col = mid % n;

            if (arr[row][col] == target) {
                return true;
            } else if (arr[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;

    }
}
