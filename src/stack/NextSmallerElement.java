package stack;

import java.util.*;

public class NextSmallerElement {

    /**
     * Brute Force (Linear):
     * For each element, check all elements to its right.
     * First smaller element found is the answer.
     * Time: O(n^2)
     */
    public static int[] nextSmallerLinearBruteForce(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
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
     * For each element, check the next n-1 elements circularly using (i + j) % n.
     * First smaller element found is the answer.
     * Time: O(n^2)
     */
    public static int[] nextSmallerCircularBruteForce(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int nextIdx = (i + j) % n;
                if (nums[nextIdx] < nums[i]) {
                    res[i] = nums[nextIdx];
                    break;
                }
            }
        }

        return res;
    }

    /**
     * Stack-based (Linear):
     * Traverse from right to left.
     * Use stack to store potential candidates.
     * Pop from stack until you find a smaller element or it's empty.
     * Time: O(n), Space: O(n)
     */
    public static int[] nextSmallerLinearStack(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
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
     * Traverse 2n times from right to left to simulate circular behavior.
     * Use modulo to wrap around.
     * Only store results in first n iterations.
     * Time: O(n), Space: O(n)
     */
    public static int[] nextSmallerCircularStack(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            int idx = i % n;
            while (!stack.isEmpty() && stack.peek() >= nums[idx]) {
                stack.pop();
            }
            if (i < n) {
                res[idx] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[idx]);
        }

        return res;
    }

    public static void print(String label, int[] arr) {
        System.out.println(label + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 10, 8};

        System.out.println("Input: " + Arrays.toString(nums));

        // Brute Force Approaches
        print("BruteForce Linear     : ", nextSmallerLinearBruteForce(nums));   // [2, 2, -1, 8, -1]
        print("BruteForce Circular   : ", nextSmallerCircularBruteForce(nums)); // [2, 2, -1, 8, 4]

        // Stack Approaches
        print("Stack Linear          : ", nextSmallerLinearStack(nums));        // [2, 2, -1, 8, -1]
        print("Stack Circular        : ", nextSmallerCircularStack(nums));      // [2, 2, -1, 8, 4]

        // Edge Case: All same
        int[] same = {3, 3, 3};
        System.out.println("\nEdge Case: Same Elements");
        print("Stack Linear          : ", nextSmallerLinearStack(same));        // [-1, -1, -1]
        print("Stack Circular        : ", nextSmallerCircularStack(same));      // [-1, -1, -1]

        // Edge Case: Descending
        int[] desc = {5, 4, 3, 2, 1};
        System.out.println("\nEdge Case: Strictly Descending");
        print("Stack Linear          : ", nextSmallerLinearStack(desc));        // [4, 3, 2, 1, -1]
        print("Stack Circular        : ", nextSmallerCircularStack(desc));      // [4, 3, 2, 1, 5]

        // Edge Case: Single element
        int[] single = {9};
        System.out.println("\nEdge Case: Single Element");
        print("Stack Linear          : ", nextSmallerLinearStack(single));      // [-1]
        print("Stack Circular        : ", nextSmallerCircularStack(single));    // [-1]
    }
}
