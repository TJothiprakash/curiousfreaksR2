package stack;

import java.util.Stack;

public class MinElement {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinElement() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public static void main(String[] args) {
        MinElement minElementStack = new MinElement();

        // Example 1
        minElementStack.push(2);
        minElementStack.push(3);
        minElementStack.push(1);
        minElementStack.push(4);

        System.out.println("Minimum Element: " + minElementStack.getMin()); // Output: 1

        // Example 2
        minElementStack.pop(); // Remove 4
        minElementStack.pop(); // Remove 1
        System.out.println("Minimum Element after pops: " + minElementStack.getMin()); // Output: 2
    }

    // Push an element onto the stack
    public void push(int value) {
        stack.push(value);

        // Insert the new minimum into the minStack if it's smaller or equal
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    // Pop an element from the stack
    public int pop() {
        if (stack.isEmpty()) {
            return -1; // Stack is empty
        }

        int poppedValue = stack.pop();

        // If the popped element is the minimum, pop from minStack too
        if (poppedValue == minStack.peek()) {
            minStack.pop();
        }

        return poppedValue;
    }

    // Get the minimum element from the stack in O(1) time
    public int getMin() {
        if (minStack.isEmpty()) {
            return -1; // Stack is empty
        }

        return minStack.peek();
    }
}/*package stack;

import java.util.Stack;

public class MinElement {
    private Stack<Integer> stack;
    private Stack<Integer> tempStack;

    public MinElement() {
        stack = new Stack<>();
        tempStack = new Stack<>();
    }

    // Push an element onto the stack in sorted order
    public void push(int value) {
        // Move elements from stack to tempStack if they are greater than the new element
        while (!stack.isEmpty() && stack.peek() > value) {
            tempStack.push(stack.pop());
        }

        // Push the new element onto the stack
        stack.push(value);

        // Move elements back to stack from tempStack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    // Pop an element from the stack
    public int pop() {
        if (stack.isEmpty()) {
            return -1; // Stack is empty
        }
        return stack.pop();
    }

    // Get the minimum element from the stack in O(1) time
    public int getMin() {
        if (stack.isEmpty()) {
            return -1; // Stack is empty
        }
        return stack.peek(); // The minimum will always be on top of the stack
    }

    public static void main(String[] args) {
        MinElement minElementStack = new MinElement();

        // Example 1
        minElementStack.push(2);
        minElementStack.push(3);
        minElementStack.push(1);
        minElementStack.push(4);

        System.out.println("Minimum Element: " + minElementStack.getMin()); // Output: 1

        // Example 2
        minElementStack.pop(); // Remove 4
        minElementStack.pop(); // Remove 1
        System.out.println("Minimum Element after pops: " + minElementStack.getMin()); // Output: 2
    }
}
*/
