package stack;

import java.util.Stack;

public class TrappingRainWaterStack {
    //    O(n)O(n)
    public static int trapWater(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0; // No water can be trapped if there are fewer than 3 blocks

        Stack<Integer> stack = new Stack<>();
        int waterTrapped = 0;

        for (int i = 0; i < n; i++) {
            // Process the current bar
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                // Pop the top of the stack
                int top = stack.pop();

                // If the stack is empty after popping, break
                if (stack.isEmpty()) break;

                // Calculate the distance (width) between the current bar and the next bar in the stack
                int distance = i - stack.peek() - 1;

                // Calculate the bounded height
                int boundedHeight = Math.min(arr[i], arr[stack.peek()]) - arr[top];

                // Add the trapped water for this segment
                waterTrapped += distance * boundedHeight;
            }

            // Push the current index onto the stack
            stack.push(i);
        }

        return waterTrapped;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 0, 0, 2, 0, 4};
        int[] arr2 = {7, 4, 0, 9};
        int[] arr3 = {6, 9, 9};

        System.out.println(trapWater(arr1)); // Output: 10
        System.out.println(trapWater(arr2)); // Output: 10
        System.out.println(trapWater(arr3)); // Output: 0
    }
}

