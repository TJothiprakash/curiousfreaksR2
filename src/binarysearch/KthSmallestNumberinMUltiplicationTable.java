package binarysearch;

public class KthSmallestNumberinMUltiplicationTable {
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countLessEqual(mid, m, n) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int countLessEqual(int x, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(x / i, n); // count how many elements in row i are <= x
        }
        return count;
    }

}