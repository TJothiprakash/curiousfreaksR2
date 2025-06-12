package stack;

import java.util.Stack;

public class InfixToPostfix {
    // Function to return precedence of operators
    private static int precedence(char ch) {
        switch (ch) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }

    // Function to convert infix to postfix
    public static String infixToPostfix(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If character is an operand, add it to output
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // If character is '(', push it to stack
            else if (c == '(') {
                stack.push(c);
            }
            // If character is ')', pop until '(' is found
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            }
            // If an operator is encountered
            else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop all remaining operators from the stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        //String infix1 = "a+b*(c^d-e)^(f+g*h)-i";
        //String infix2 = "A*(B+C)/D";
        String infix3 = "(a+b)*(c+d)";

//        System.out.println("Postfix of \"" + infix1 + "\": " + infixToPostfix(infix1));
//        System.out.println("Postfix of \"" + infix2 + "\": " + infixToPostfix(infix2));
        System.out.println("Postfix of \"" + infix3 + "\": " + infixToPostfix(infix3));
    }
}

