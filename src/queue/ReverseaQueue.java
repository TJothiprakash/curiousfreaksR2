package queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseaQueue {
}


class ReverseQueue {

    // Function to reverse the queue using recursion
//     O(n)O(n)->stack space
    public static void reverseQueue(Queue<Integer> q) {
        // Base case: if queue is empty, return
        if (q.isEmpty()) {
            return;
        }

        // Remove the front element
        int front = q.poll();

        // Recursively reverse the remaining queue
        reverseQueue(q);

        // Enqueue the front element back to the queue after reversing
        q.offer(front);
    }

    public static void main(String[] args) {
        // Test case 1
        Queue<Integer> q1 = new LinkedList<>(Arrays.asList(4, 3, 1, 10, 2, 6));
        reverseQueue(q1);
        System.out.println(q1); // Output: [6, 2, 10, 1, 3, 4]

        // Test case 2
        Queue<Integer> q2 = new LinkedList<>(Arrays.asList(4, 3, 2, 1));
        reverseQueue(q2);
        System.out.println(q2); // Output: [1, 2, 3, 4]

        // Test case 3
        Queue<Integer> q3 = new LinkedList<>(Arrays.asList(7, 9, 5, 12, 8));
        reverseQueue(q3);
        System.out.println(q3); // Output: [8, 12, 5, 9, 7]
    }

    // Function to reverse the queue using a stack
    public static void reverseQueueUsingStack(Queue<Integer> q) {
        Stack<Integer> stack = new Stack<>();

        // Step 1: Dequeue all elements and push them to the stack
        while (!q.isEmpty()) {
            stack.push(q.poll());
        }

        // Step 2: Pop elements from the stack and enqueue them back to the queue
        while (!stack.isEmpty()) {
            q.offer(stack.pop());
        }
    }

}
/// / Recursion-based reverse – O(n) time | O(n) space (due to call stack)
/// / Stack-based reverse – O(n) time | O(n) space (explicit stack)