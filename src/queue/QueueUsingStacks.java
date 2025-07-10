package queue;

import java.util.Stack;

public class QueueUsingStacks<T> {
    private Stack<T> stack1 = new Stack<>(); // Stack for insert
    private Stack<T> stack2 = new Stack<>(); // Stack for remove

    // Insert an element into the queue
//    O(1)O(1)
    public boolean insert(T item) {
        stack1.push(item);
        return true;
    }

    // Remove and return the front element of the queue
//    O(n)O(n)
    public T remove() throws Exception {
        if (stack2.isEmpty()) { // If stack2 is empty, transfer from stack1
            if (stack1.isEmpty()) { // If both stacks are empty, queue is empty
                throw new Exception("Queue is empty");
            }
            // Move all elements from stack1 to stack2
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop(); // Remove and return the front element
    }

    // Display the queue elements without modifying their order
//    O(n)O(1)
    public void display() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.print("Front -> ");

        // Display elements in stack2 (FIFO order)
        for (int i = stack2.size() - 1; i >= 0; i--) {
            System.out.print(stack2.get(i) + " <- ");
        }

        // Display elements in stack1 (reverse order)
        for (int i = 0; i < stack1.size(); i++) {
            System.out.print(stack1.get(i) + " <- ");
        }

        System.out.println("END");
    }
}
