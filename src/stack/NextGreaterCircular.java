package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGreaterCircular {

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // default to -1
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 2 * n; i++) {
            int currIndex = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[currIndex]) {
                int index = stack.pop();
                result[index] = nums[currIndex];
            }
            if (i < n) {
                stack.push(currIndex);
            }
        }

        return result;
    }

    // Utility to print array
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 2, 1};
        int[] result1 = nextGreaterElements(nums1);
        System.out.print("Input: [1, 2, 1] → Output: ");
        printArray(result1); // Expected: [2, -1, 2]

        // Test Case 2
        int[] nums2 = {1, 2, 3, 4, 3};
        int[] result2 = nextGreaterElements(nums2);
        System.out.print("Input: [1, 2, 3, 4, 3] → Output: ");
        printArray(result2); // Expected: [2, 3, 4, -1, 4]

        // Test Case 3 (all same elements)
        int[] nums3 = {5, 5, 5};
        int[] result3 = nextGreaterElements(nums3);
        System.out.print("Input: [5, 5, 5] → Output: ");
        printArray(result3); // Expected: [-1, -1, -1]

        // Test Case 4 (decreasing order)
        int[] nums4 = {5, 4, 3, 2, 1};
        int[] result4 = nextGreaterElements(nums4);
        System.out.print("Input: [5, 4, 3, 2, 1] → Output: ");
        printArray(result4); // Expected: [-1, 5, 5, 5, 5]

        // Test Case 5 (single element)
        int[] nums5 = {42};
        int[] result5 = nextGreaterElements(nums5);
        System.out.print("Input: [42] → Output: ");
        printArray(result5); // Expected: [-1]
    }
}

class NextGreaterCircularBruteForce {

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // default to -1

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int nextIndex = (i + j) % n;
                if (nums[nextIndex] > nums[i]) {
                    result[i] = nums[nextIndex];
                    break;
                }
            }
        }

        return result;
    }

    // Utility method to print the array
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 2, 1};
        System.out.print("Input: [1, 2, 1] → Output: ");
        printArray(nextGreaterElements(nums1)); // Expected: [2, -1, 2]

        // Test Case 2
        int[] nums2 = {1, 2, 3, 4, 3};
        System.out.print("Input: [1, 2, 3, 4, 3] → Output: ");
        printArray(nextGreaterElements(nums2)); // Expected: [2, 3, 4, -1, 4]

        // Test Case 3: All same elements
        int[] nums3 = {7, 7, 7};
        System.out.print("Input: [7, 7, 7] → Output: ");
        printArray(nextGreaterElements(nums3)); // Expected: [-1, -1, -1]

        // Test Case 4: Strictly decreasing
        int[] nums4 = {5, 4, 3, 2, 1};
        System.out.print("Input: [5, 4, 3, 2, 1] → Output: ");
        printArray(nextGreaterElements(nums4)); // Expected: [-1, 5, 5, 5, 5]

        // Test Case 5: Single element
        int[] nums5 = {42};
        System.out.print("Input: [42] → Output: ");
    }
}