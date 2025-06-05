package stack;

import java.util.*;

public class NextSmallerToLeft {

    /**
     * Brute Force (Linear):
     * For each element, scan leftward and pick the first smaller element.
     */
    public static int[] nextSmallerLeftLinearBrute(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    res[i] = nums[j];
                    break;
                }
            }
        }

        return res;
    }

    /**
     * Brute Force (Circular):
     * For each index, search to the left in circular fashion.
     */
    public static int[] nextSmallerLeftCircularBrute(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int idx = (i - j + n) % n;
                if (nums[idx] < nums[i]) {
                    res[i] = nums[idx];
                    break;
                }
            }
        }

        return res;
    }

    /**
     * Stack-based (Linear):
     * Traverse left to right. For each element, pop from stack until a smaller one is found.
     */
    public static int[] nextSmallerLeftLinearStack(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }

        return res;
    }

    /**
     * Stack-based (Circular):
     * Simulate circular behavior using reverse traversal and double length.
     */
    public static int[] nextSmallerLeftCircularStack(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (!stack.isEmpty() && stack.peek() >= nums[idx]) {
                stack.pop();
            }
            if (i >= n) {
                res[idx] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[idx]);
        }

        return res;
    }

    // Print helper
    public static void print(String label, int[] arr) {
        System.out.println(label + Arrays.toString(arr));
    }

    // Driver Code
    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 10, 8};

        System.out.println("Input: " + Arrays.toString(nums));

        // Brute Force
        print("Brute Force Linear     : ", nextSmallerLeftLinearBrute(nums));    // [-1, 4, -1, 2, 2]
        print("Brute Force Circular   : ", nextSmallerLeftCircularBrute(nums));  // [2, 4, -1, 2, 2]

        // Stack-based
        print("Stack Linear           : ", nextSmallerLeftLinearStack(nums));    // [-1, 4, -1, 2, 2]
        print("Stack Circular         : ", nextSmallerLeftCircularStack(nums));  // [2, 4, -1, 2, 2]

        // Extra Test
        int[] single = {1};
        print("\nSingle Element         : ", nextSmallerLeftLinearBrute(single));  // [-1]
    }
}
