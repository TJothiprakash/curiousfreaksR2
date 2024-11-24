package slidingwindow;

import java.util.Arrays;
import java.util.Stack;

public class MaxOfMinInWindows {

    public static int[] maxOfMin(int[] arr, int n) {
        int[] left = new int[n];
        int[] right = new int[n];
        int[] result = new int[n + 1]; // result[k] stores max of mins for window size k

        // Initialize left and right boundaries
        Stack<Integer> stack = new Stack<>();

        // Compute left boundaries
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // Clear the stack for right boundaries
        stack.clear();

        // Compute right boundaries
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // Fill result array using the range information
        for (int i = 0; i < n; i++) {
            // Calculate the window size where arr[i] is the minimum
            int windowSize = right[i] - left[i] - 1;

            // Update the result for this window size
            result[windowSize] = Math.max(result[windowSize], arr[i]);
        }

        // Propagate maximum values to smaller window sizes
        for (int i = n - 1; i >= 1; i--) {
            result[i] = Math.max(result[i], result[i + 1]);
        }

        // Copy results to the output array
        int[] output = new int[n];
        for (int i = 1; i <= n; i++) {
            output[i - 1] = result[i];
        }

        return output;
    }

    public static void main(String[] args) {
        // Example Test Cases
        int[] arr1 = {10, 20, 30, 50, 10, 70, 30};
        int n1 = arr1.length;
        System.out.println(Arrays.toString(maxOfMin(arr1, n1))); // Output: [70, 30, 20, 10, 10, 10, 10]

        int[] arr2 = {10, 20, 30};
        int n2 = arr2.length;
        System.out.println(Arrays.toString(maxOfMin(arr2, n2))); // Output: [30, 20, 10]
    }
}
