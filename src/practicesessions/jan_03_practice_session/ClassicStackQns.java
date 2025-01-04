package practicesessions.jan_03_practice_session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ClassicStackQns {
    public static List<Integer> ngeOnLeft(int arr[]) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {

            // Pop elements smaller or equal to current
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            // If stack is empty, no greater element to the left
            if (stack.isEmpty()) {
                result.add(-1);
            } else {
                result.add(stack.peek());
            }
            // Push current element onto the stack
            stack.push(arr[i]);

        }

        return result;
    }

    public static List<Integer> ngeOnRight(int[] arr) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        // Iterate from the end of the array
        for (int i = arr.length - 1; i >= 0; i--) {
            // Remove elements smaller or equal to the current element
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            // If stack is empty, no greater element on the right
            if (stack.isEmpty()) {
                result.add(-1);
            } else {
                result.add(stack.peek());
            }
            // Push current element onto the stack
            stack.push(arr[i]);
        }

        // Reverse the result to maintain correct order
        Collections.reverse(result);
        return result;
    }

    public static List<Integer> nseOnLeft(int arr[]) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result.add(-1);
            } else {
                result.add(stack.peek());
            }
            stack.push(arr[i]);
        }
        return result;
    }

    public static List<Integer> nseOnRight(int arr[]) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result.add(-1);
            } else {
                result.add(stack.peek());
            }
            stack.push(arr[i]);
        }
        Collections.reverse(result);
        return result;
    }


}
