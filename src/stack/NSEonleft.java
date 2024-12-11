package stack;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NSEonleft {
    public static void main(String[] args) {
        int[] arr = {4, 8, 5, 3, 2, 25};
        System.out.println(nsOnLeft(arr.length, arr));
    }

    public static List<Integer> nsOnLeft(int N, int[] arr) {
        List<Integer> result = new ArrayList<>();
        // Initialize result list with -1
        for (int i = 0; i < N; i++) {
            result.add(-1);
        }

        // Stack to store elements of the array
        Stack<Integer> stack = new Stack<>();

        // Traverse the array from left to right
        for (int i = 0; i < N; i++) {
            // Find the first smaller element on the left
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            // If stack is not empty, the top element is the NSE on the left
            if (!stack.isEmpty()) {
                result.set(i, stack.peek());
            }

            // Push the current element to the stack
            stack.push(arr[i]);
        }

        return result;
    }
}

