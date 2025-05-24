package queue.may_24_2025;

import java.util.LinkedList;
import java.util.Queue;

public class StackImplUsingQueue {
    private Queue<Integer> queue;
    private int size;

    public StackImplUsingQueue() {
        this.queue = new LinkedList<>();
        this.size = 0;
    }

    public void push(int data) {
        queue.add(data);
        size++;
    }

    public int pop() {
        if (queue.isEmpty()) throw new RuntimeException("Stack is empty");

        // Rotate elements to bring the last inserted to the front
        for (int i = 0; i < size - 1; i++) {
            queue.add(queue.poll());
        }

        size--;
        return queue.poll(); // Remove and return the "top" element
    }

    public int peek() {
        if (queue.isEmpty()) throw new RuntimeException("Stack is empty");

        for (int i = 0; i < size - 1; i++) {
            queue.add(queue.poll());
        }

        int top = queue.peek(); // Get top
        queue.add(queue.poll()); // Restore order
        return top;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
