package stack;

import java.util.Stack;

public class LargestRectangleInHistogram {
//O(n)O(2n ~ n)
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // Arrays to store indices of Next Smaller Elements (NSE)
        int[] nsr = new int[n]; // Next Smaller to Right
        int[] nsl = new int[n]; // Next Smaller to Left

        // Compute NSR
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nsr[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // Clear the stack to reuse
        stack.clear();

        // Compute NSL
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nsl[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // Calculate the maximum area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = nsr[i] - nsl[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr1 = {60, 20, 50, 40, 10, 50, 60};
        int[] arr2 = {7, 2, 8, 9, 1, 3, 6, 5};
        int[] arr3 = {3};

        System.out.println(largestRectangleArea(arr1)); // Output: 100
        System.out.println(largestRectangleArea(arr2)); // Output: 16
        System.out.println(largestRectangleArea(arr3)); // Output: 3
    }
}

