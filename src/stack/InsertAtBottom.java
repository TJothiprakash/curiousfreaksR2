package stack;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class InsertAtBottom {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        InsertAtBottom insertAtBottom = new InsertAtBottom();
        System.out.println(stack);
        insertAtBottom.insertAtBottom(6, stack);
        System.out.println(stack);
    }

    public static Stack<Integer> insertAtBottom(int data, Stack<Integer> stack) {

        helper(data, stack);
        return stack;
    }

    //O(n)O(n)
    private static void helper(int data, @NotNull Stack<Integer> stack) {

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
