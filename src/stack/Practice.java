package stack;

// stock span

import java.util.ArrayList;

public class Practice {
    public static ArrayList<Integer> calculateSpan(int[] arr) {
        /*  int n = arr.length;
        ArrayList<Integer> span = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Pop elements from the stack while the current price is higher
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            // If the stack is empty, all previous prices are smaller
            int prevGreater = stack.isEmpty() ? -1 : stack.peek();

            // Calculate the span
            span.add(i - prevGreater);

            // Push the current index onto the stack
            stack.push(i);
        }

        return span;*/
        int n = arr.length;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    count++;
                } else {
                    break;
                }
            }
            list.add(count);
        }
        return list;
    }
}
