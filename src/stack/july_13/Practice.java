package stack.july_13;

import java.util.Stack;

public class Practice {


    /*| Type                        | Traverse From | Stack Pattern    |
| --------------------------- | ------------- | ---------------- |
| Next Greater on Right (NGR) | Right → Left  | Decreasing stack |
| Next Greater on Left (NGL)  | Left → Right  | Decreasing stack |
| Next Smaller on Right (NSR) | Right → Left  | Increasing stack |
| Next Smaller on Left (NSL)  | Left → Right  | Increasing stack |
*/
    public void nextGreaterElement(int arr[]) {
//        arr[arr.length - 1] = -1;
        Stack<Integer> stack = new Stack<>();
        int result[] = new int[arr.length];

        for (int i = arr.length - 1; i > 0; i--) {

            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }


    public void nextGreaterElementonLeft(int arr[]) {
//        arr[arr.length - 1] = -1;
        Stack<Integer> stack = new Stack<>();
        int result[] = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = arr.length - 1 == i ? -1 : stack.peek();

            stack.push(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }


    public void nextSmallerElementFromLeft(int arr[]) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
    }


    public void nextSmallerElementFromRight(int arr[]) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        System.arraycopy(result, 0, arr, 0, n); // Optional: copy to original array
    }


    public void trappingRainWater(int arr[]) {
        int n = arr.length;
        int[] ngel = new int[n];
        int[] nger = new int[n];
        ngel[0] = arr[0];
        for (int i = 1; i < n; i++) {
            ngel[i] = Math.max(arr[i], ngel[i - 1]);
        }
        nger[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            nger[i] = Math.max(arr[i], nger[i + 1]);
        }
        int total = 0;
        // Calculate water trapped
        for (int i = 0; i < n; i++) {
            int waterAtI = Math.min(ngel[i], nger[i]) - arr[i];
            if (waterAtI > 0) total += waterAtI;
        }
        System.out.println("total water can be trapped is " + total);
    }


    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];  // Nearest Smaller to Left
        int[] right = new int[n]; // Nearest Smaller to Right

        Stack<Integer> stack = new Stack<>();

        // Fill left[]: index of nearest smaller to left
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // Clear and reuse stack
        stack.clear();

        // Fill right[]: index of nearest smaller to right
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // Calculate max area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        reverseStack();
    }

    public static void reverseStack() {
//        Stack stack = new Stack();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(7);
        stack.push(4);
        stack.push(3);
        stack.push(5);
        System.out.println(stack);
//        Stack<Integer> stack1 = new Stack<>();
//        while (!stack.isEmpty()) {
//            stack1.push(stack.pop());
//        }
//        System.out.println(stack);
//        System.out.println(stack1);
        sortStackElements(stack);
        System.out.println(stack);

    }

    //    [1,7,4,3,5] -> [1,3,4,5,7]
    public static void sortStackElements(Stack<Integer> input) {
        Stack<Integer> tempStack = new Stack<>();

        while (!input.isEmpty()) {
            int temp = input.pop();

            // Move elements from tempStack back to input if they're greater than temp
            while (!tempStack.isEmpty() && tempStack.peek() > temp) {
                input.push(tempStack.pop());//1.7.4.3.5.5
            }

            tempStack.push(temp);// 3,5,
        }

        // Final result will be in tempStack (sorted in descending order)
        while (!tempStack.isEmpty()) {
            input.push(tempStack.pop()); // now input is sorted in ascending order (bottom to top)
        }
        System.out.println(input);
    }

    public static void sortStackAscendingPop(Stack<Integer> input) {
        Stack<Integer> tempStack = new Stack<>();

        while (!input.isEmpty()) {
            int temp = input.pop();

            // Reverse comparison: Put smaller elements below
            while (!tempStack.isEmpty() && tempStack.peek() < temp) {
                input.push(tempStack.pop());
            }

            tempStack.push(temp);
        }

        // Put back into input so that smallest is on top
        while (!tempStack.isEmpty()) {
            input.push(tempStack.pop());
        }
    }

}

