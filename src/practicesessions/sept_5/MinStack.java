package practicesessions.sept_5;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack1;
    Stack<Integer> minStack;

    public MinStack() {
        this.stack1 = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int val) {
        stack1.push(val);
        if (minStack.isEmpty() || val < minStack.peek()) {
            minStack.push(val);
        }

    }

    public void pop() {
        int temp = stack1.pop();
        if (temp == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        int temp = minStack.peek();
        return temp;
    }

    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */