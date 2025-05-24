package queue.may_24_2025;

import java.util.Stack;

public class ReverseaQueue {
    private Stack<Integer> stack;
    private QueueImplUsingLL queue;

    public ReverseaQueue() {
        this.stack = new Stack<>();
        this.queue = new QueueImplUsingLL();
    }

    public void reverseQueue() throws Exception {
        while (queue.size() > 0) {
            stack.push(queue.dequeue());
        }
        while (stack.size() > 0) {
            queue.enqueue(stack.pop());
        }
        queue.display();
    }

    public void enqueue(int data) {
        queue.enqueue(data);
    }

    public int dequeue() throws Exception {
        return queue.dequeue();
    }

    public void display() {
        queue.display();
    }
}
