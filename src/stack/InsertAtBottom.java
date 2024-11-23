package stack;

import java.util.Stack;

public class InsertAtBottom {

    public static Stack<Integer> insertAtBottom(int data, Stack<Integer> stack) {

        helper(data, stack);
        return stack;
    }

    private static void helper(int data, Stack<Integer> stack) {

        if (stack.isEmpty()) {
            stack.push(data);
            return;
        }
        // Step 1: Pop the top element and store it
        int temp = stack.pop();

        // Step 2: Recursively call helper to insert `data` at the bottom
        helper(data, stack);

        // Step 3: Push the stored element back
        stack.push(temp);
    }

}
