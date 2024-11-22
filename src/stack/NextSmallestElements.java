package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextSmallestElements {
    public static List<Integer> nsOnLeft(int N, int[] arr) {
        List<Integer> result = new ArrayList<>();
        // Initialize result list with -1
        for (int i = 0; i < N; i++) {
            result.add(-1);
        }

        // Stack to store indices of the array
        Stack<Integer> stack = new Stack<>();

        // Traverse the array
        for (int i = 0; i < N; i++) {
            // Find the first student with lesser marks
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                result.set(stack.pop(), arr[i]);
            }

            // Push the current student index to the stack
            stack.push(i);
        }

        return result;
    }
}
