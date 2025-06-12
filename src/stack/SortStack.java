package stack;

import java.util.Stack;

public class SortStack {

    // Function to sort the stack
    public static void sort(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int top = stack.pop();
            sort(stack); // sort remaining stack
            sortedInsert(stack, top); // insert top element back in sorted position
        }
    }

    // Helper function to insert an element into a sorted stack
    private static void sortedInsert(Stack<Integer> stack, int element) {
        // Base case: either stack is empty or top is smaller than element
        if (stack.isEmpty() || stack.peek() < element) {
            stack.push(element);
            return;
        }

        int temp = stack.pop(); // pop and hold
        sortedInsert(stack, element); // recursive call
        stack.push(temp); // push back held element
    }

    // Driver code to test
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(11);
        stack.push(2);
        stack.push(32);
        stack.push(3);
        stack.push(41);

        sort(stack); // sort the stack

        // Print sorted stack (top to bottom)
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
