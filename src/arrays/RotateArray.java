package arrays;

public class RotateArray {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] ans = rotateArray(arr, 7);
        for (int i : ans) {
            System.out.print(i + " ");

        }
    }

    // O(n) O(1)
    private static int[] rotateArray(int[] arr) {
        int temp = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }
        arr[0] = temp;

        return arr;
    }

    // rotate the array by k places
//    O(n) O(1)
    private static int[] rotateArray(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Normalize k
        reverse(nums, 0, k - 1);    // Reverse first part
        reverse(nums, k, n - 1);    // Reverse second part
        reverse(nums, 0, n - 1);    // Reverse the entire array

        return nums;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
