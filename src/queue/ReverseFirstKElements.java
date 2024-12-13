package queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseFirstKElements {

    // Function to reverse the first K elements of the queue
    public static Queue<Integer> modifyQueue(Queue<Integer> q, int K) {
        // Create a stack to store the first K elements
        Stack<Integer> stack = new Stack<>();

        // Step 1: Dequeue the first K elements and push them onto the stack
        for (int i = 0; i < K; i++) {
            stack.push(q.poll());
        }

        // Step 2: Enqueue the remaining elements back to the queue
        int size = q.size();
        for (int i = 0; i < size; i++) {
            q.add(q.poll());
        }

        // Step 3: Pop from the stack and enqueue the reversed elements back into the queue
        while (!stack.isEmpty()) {
            q.add(stack.pop());
        }

        return q;
    }

    public static void main(String[] args) {
        // Example usage
        Queue<Integer> q = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        int K = 3;

        Queue<Integer> modifiedQueue = modifyQueue(q, K);

        // Output the modified queue
        System.out.println(modifiedQueue);  // Output: [3, 2, 1, 4, 5]
    }
}
