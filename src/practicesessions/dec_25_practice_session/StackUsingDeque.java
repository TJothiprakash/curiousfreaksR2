package practicesessions.dec_25_practice_session;

import java.util.Deque;
import java.util.LinkedList;

public class StackUsingDeque {
    private Deque<Integer> deque = new LinkedList<>();

    public void push(int data) {
        deque.addFirst(data);
    }

    public int pop() throws Exception {
        if (deque.isEmpty()) {
            throw new Exception("Stack is empty. Cannot perform pop operation.");
        }
        return deque.removeFirst();
    }

    public int peek() throws Exception {
        if (deque.isEmpty()) {
            throw new Exception("Stack is empty. Cannot perform peek operation.");
        }
        return deque.peekFirst();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }
}

