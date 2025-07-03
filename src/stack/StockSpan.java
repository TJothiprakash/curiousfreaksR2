package stack;

import java.util.Stack;

public class StockSpan {
//O(n)O(n)
    public static int[] calculateSpan(int[] arr) {
        int n = arr.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Traverse through all prices
        for (int i = 0; i < n; i++) {
            // Calculate span for arr[i]
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            // If stack is empty, it means arr[i] is greater than all previous elements
            if (stack.isEmpty()) {
                span[i] = i + 1;  // span is i+1 because we include the current day itself
            } else {
                span[i] = i - stack.peek();  // span is the difference between current index and index of last higher price
            }

            // Push the current index to stack
            stack.push(i);
        }

        return span;
    }


}
