package stack.july_13;

import java.util.Stack;

public class Practice {

    /*
    | Type                        | Traverse From | Stack Pattern    |
    | --------------------------- | ------------- | ---------------- |
    | Next Greater on Right (NGR) | Right → Left  | Decreasing stack |
    | Next Greater on Left (NGL)  | Left → Right  | Decreasing stack |
    | Next Smaller on Right (NSR) | Right → Left  | Increasing stack |
    | Next Smaller on Left (NSL)  | Left → Right  | Increasing stack |
    */

    // ✅ Next Greater Element on Right
    public static void nextGreaterElementOnRight(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        for (int val : result) System.out.print(val + " ");
        System.out.println();
    }

    // ✅ Next Greater Element on Left
    public static void nextGreaterElementOnLeft(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        for (int val : result) System.out.print(val + " ");
        System.out.println();
    }

    // ✅ Next Smaller Element on Left
    public static void nextSmallerElementOnLeft(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        for (int val : result) System.out.print(val + " ");
        System.out.println();
    }

    // ✅ Next Smaller Element on Right
    public static void nextSmallerElementOnRight(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        for (int val : result) System.out.print(val + " ");
        System.out.println();
    }

    // ✅ Trapping Rain Water
    public static void trappingRainWater(int[] arr) {
        int n = arr.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(arr[i], leftMax[i - 1]);
        }

        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(arr[i], rightMax[i + 1]);
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += Math.min(leftMax[i], rightMax[i]) - arr[i];
        }

        System.out.println("Total trapped water = " + total);
    }

    // ✅ Largest Rectangle in Histogram
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Left smaller
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Right smaller
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // ✅ Reverse a stack
    public static void reverseStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(7);
        stack.push(4);
        stack.push(3);
        stack.push(5);

        System.out.println("Original stack: " + stack);
        Stack<Integer> reversed = new Stack<>();
        while (!stack.isEmpty()) {
            reversed.push(stack.pop());
        }
        System.out.println("Reversed stack: " + reversed);
    }

    // ✅ Sort stack elements ascending
    public static void sortStackElements(Stack<Integer> input) {
        Stack<Integer> tempStack = new Stack<>();

        while (!input.isEmpty()) {
            int temp = input.pop();
            while (!tempStack.isEmpty() && tempStack.peek() > temp) {
                input.push(tempStack.pop());
            }
            tempStack.push(temp);
        }

        while (!tempStack.isEmpty()) {
            input.push(tempStack.pop());
        }

        System.out.println("Sorted stack: " + input);
    }

    // ✅ Main method
    public static void main(String[] args) {
        int[] arr = {1,3, 2, 4};
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
        System.out.println("Next Greater on Right:");
        nextGreaterElementOnRight(arr);

        System.out.println("Next Greater on Left:");
        nextGreaterElementOnLeft(arr);

        System.out.println("Next Smaller on Right:");
        nextSmallerElementOnRight(arr);

        System.out.println("Next Smaller on Left:");
        nextSmallerElementOnLeft(arr);

        int[] waterArr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        trappingRainWater(waterArr);

        int[] hist = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + largestRectangleArea(hist));

        reverseStack();

        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(5);
        stack.push(1);
        stack.push(7);
        stack.push(4);
        sortStackElements(stack);
    }
}
