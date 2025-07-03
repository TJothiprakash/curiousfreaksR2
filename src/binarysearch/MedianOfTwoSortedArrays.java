package binarysearch;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] a = {-5, 3, 6, 12, 15};
        int[] b = {-12, -10, -6, -3, 4, 10};
        System.out.println(findMedianSortedArrays(a, b));  // Output: 3.0
    }

    //O (log n+m) O(1)
    public static double findMedianSortedArrays(int[] a, int[] b) {
        // Ensure a is the smaller array to optimize binary search
        if (a.length > b.length) {
            return findMedianSortedArrays(b, a);
        }

        int m = a.length;
        int n = b.length;
        int low = 0, high = m;

        while (low <= high) {
            int i = (low + high) / 2;
            int j = (m + n + 1) / 2 - i;

            int aLeft = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
            int aRight = (i == m) ? Integer.MAX_VALUE : a[i];
            int bLeft = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
            int bRight = (j == n) ? Integer.MAX_VALUE : b[j];

            if (aLeft <= bRight && bLeft <= aRight) {
                // Correct partition found
                if ((m + n) % 2 == 1) {
                    return Math.max(aLeft, bLeft);
                } else {
                    return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2.0;
                }
            } else if (aLeft > bRight) {
                // Move partition i to left
                high = i - 1;
            } else {
                // Move partition i to right
                low = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted or invalid.");
    }
}
