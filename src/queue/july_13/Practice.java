package queue.july_13;

import java.util.Queue;
import java.util.Stack;

public class Practice {
    public void reverseUsingStack(Queue<Integer> queue) {
        // reverse the queue
        // 1,2,3,4,5
//     intution put into stack then put back into queue
// next type : recursively go deep then add from last
// next type : im still thining any other effiient approach
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.add(queue.poll());
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

    }

    public void reverseQueueUsingRecursion(Queue<Integer> queue) {
        Queue<Integer> ans = helper(queue, queue.poll());
    }

    public static Queue<Integer> helper(Queue<Integer> queue, int temp) {

        if (queue.isEmpty()) {
            queue.add(temp);
        }
        helper(queue, queue.poll());
        queue.add(temp);
        return queue;
    }
}
