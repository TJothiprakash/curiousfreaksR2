package stack.july_13;

import java.util.Stack;

public class StockSpan {
    public void stockSpan(int arr[]) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] span = new int[n];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            span[i] = stack.isEmpty() ? i + 1 : (i - stack.peek());
            stack.push(i);
        }

    }
}
