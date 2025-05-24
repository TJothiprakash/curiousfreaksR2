package queue.may_24_2025;

import java.util.Stack;

public class QueueImplUsingStack {
    private Stack<Integer> stackin;
    private Stack<Integer> stackout;

    public QueueImplUsingStack() {
        this.stackin = new Stack<>();
        this.stackout = new Stack<>();
    }

    public void enqueue(int data) {
        stackin.push(data);
    }

    public int dequeue() {
        if (stackout.isEmpty()) {
            while (!stackin.isEmpty()) {
                stackout.push(stackin.pop());
            }
        }
        return stackout.pop();
    }

}

