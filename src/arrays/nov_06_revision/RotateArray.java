package arrays.nov_06_revision;

public class RotateArray {
    // rotate array by 1 space
    public static int[] rotateArray(int[] arr) {
        int n = arr.length;
        int temp = arr[n - 1];
        for (int i = 1; i < n; i++) {
            arr[i + 1] = arr[i];
        }
        arr[0] = temp;

        return arr;
    }

    //rotate array by k spaces
    public static int[] rotateArray(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        int[] temp = new int[k];
        // store k elements in temp array
        for (int i = 0; i < k; i++) {
            temp[i] = arr[n - k + i];
        }
        // shift remaining elements
        for (int i = n - 1; i >= k; i--) {
            arr[i] = arr[i - k];
        }
// place temp elements
        for (int i = 0; i < k; i++) {
            arr[i] = temp[i];

        }
        return arr;
    }
    // rotate array by k space using reverse function

    public static int[] rotateArrayUsingReverse(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        reverseArray(arr, 0, n - 1);
        reverseArray(arr, 0, k - 1);
        reverseArray(arr, k, n - 1);
        return arr;
    }

    static void reverseArray(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
