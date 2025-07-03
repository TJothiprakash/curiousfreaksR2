package stack;

import java.util.Stack;

public class BalancedBrackets {
//    O(n)O(n)
    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();

        // Traverse through the string
        for (char ch : s.toCharArray()) {
            // Push opening brackets onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Handle closing brackets
            else if (ch == ')' || ch == '}' || ch == ']') {
                // If stack is empty or the top of the stack doesn't match, return false
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                    return false;
                }
            }
        }

        // If stack is empty, all brackets were matched
        return stack.isEmpty();
    }

    // Helper method to check if the pair of brackets matches
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    public static void main(String[] args) {
        String s1 = "{([])}";
        String s2 = "()";
        String s3 = "([]";

        System.out.println(isBalanced(s1)); // Output: true
        System.out.println(isBalanced(s2)); // Output: true
        System.out.println(isBalanced(s3)); // Output: false
    }
}

