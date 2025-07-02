package arrays.nov_06_revision;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        RotateArray obj = new RotateArray();
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int ans[] = obj.rotateArray(arr);
        int[] ans = rotateArray(arr, 3);
        System.out.println(Arrays.toString(ans));


    }

    // rotate array by 1 space
//    O(n) O(1)
    public static int[] rotateArray(int[] arr) {
        int n = arr.length;
        int temp = arr[n - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }
        arr[0] = temp;

        return arr;
    }

    //rotate array by k spaces
//    O(n) O(k)
    public static int[] rotateArray(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        System.out.println("k is " + k);
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
//    O(2n) O(1)
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


//| Method                      | Your Comment | Verdict   | Explanation                 |
//| --------------------------- | ------------ | --------- | --------------------------- |
//| `rotateArray()`             | `O(n) O(1)`  | ✅ Correct | 1-place right shift         |
//| `rotateArray(arr, k)`       | `O(n) O(k)`  | ✅ Correct | Uses temp array of size `k` |
//| `rotateArrayUsingReverse()` | `O(2n) O(1)` | ✅ Correct | 3 reversals, no extra space |