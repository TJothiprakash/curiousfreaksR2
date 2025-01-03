package practicesessions.dec_25_practice_session;

import java.util.Stack;

public class CustomQueueUsingTwoStacks {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public CustomQueueUsingTwoStacks() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    // Enqueue operation: Push elements onto stack1
    public void enqueue(int data) {
        stack1.push(data);
    }

    // Dequeue operation: Pop from stack2, transfer if empty
    public int dequeue() {
        transferIfNeeded();
        if (stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty, cannot dequeue");
        }
        return stack2.pop();
    }

    // Peek operation: Look at the front element
    public int peek() {
        transferIfNeeded();
        if (stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty, cannot peek");
        }
        return stack2.peek();
    }

    // Helper method to transfer elements from stack1 to stack2 if stack2 is empty
    private void transferIfNeeded() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
