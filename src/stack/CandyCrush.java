package stack;

import java.util.Stack;

public class CandyCrush {


//    O(n)O(n)
    public static String Reduced_String(int k, String s) {
        // Stack to store character and their frequencies
        StringBuilder result = new StringBuilder();
        Stack<int[]> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // If stack is empty or top element is not the same character
            if (stack.isEmpty() || stack.peek()[0] != c) {
                stack.push(new int[]{c, 1});
            } else {
                // Increment frequency if the same character is found
                int[] top = stack.peek();
                top[1]++;

                // If frequency reaches k, pop it
                if (top[1] == k) {
                    stack.pop();
                }
            }
        }

        // Reconstruct the string from the stack
        for (int[] entry : stack) {
            for (int i = 0; i < entry[1]; i++) {
                result.append((char) entry[0]);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int k1 = 2;
        String s1 = "geeksforgeeks";
        System.out.println(Reduced_String(k1, s1)); // Output: "gksforgks"

        int k2 = 2;
        String s2 = "geegsforgeeeks";
        System.out.println(Reduced_String(k2, s2)); // Output: "sforgeks"
    }
}

