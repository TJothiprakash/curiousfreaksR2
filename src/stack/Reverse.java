package stack;

import java.util.Stack;

public class Reverse {
    // Reverse the stack in O(1) additional space
    public static Stack<Integer> reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return stack; // Base case: If stack is empty, it's already reversed
        }

        // Pop the top element
        int data = stack.pop();

        // Recursively reverse the remaining stack
        reverse(stack);

        // Insert the popped element at the bottom of the stack
        insertAtBottom(stack, data);

        return stack;
    }

    // Helper method to insert an element at the bottom of the stack
    private static void insertAtBottom(Stack<Integer> stack, int data) {
        if (stack.isEmpty()) {
            stack.push(data);
            return; // Base case: If stack is empty, push the data
        }

        // Pop the top element
        int temp = stack.pop();

        // Recursively insert `data` at the bottom
        insertAtBottom(stack, data);

        // Push the stored element back
        stack.push(temp);
    }
    public static Stack<Integer> reverseonspace(Stack<Integer> stack) {
        Stack<Integer> auxiliaryStack = new Stack<>();

        // Step 1: Pop all elements from the original stack into the auxiliary stack
        while (!stack.isEmpty()) {
            int temp = stack.pop();
            System.out.println(temp);
            auxiliaryStack.push(temp);
        }

      /*  // Step 2: Transfer elements back from the auxiliary stack to the original stack
        while (!auxiliaryStack.isEmpty()) {
            stack.push(auxiliaryStack.pop());
        }*/
        System.out.println(auxiliaryStack);
        return auxiliaryStack;
    }
}

