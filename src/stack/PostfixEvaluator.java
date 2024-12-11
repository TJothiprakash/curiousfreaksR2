package stack;

import java.util.Stack;

public class PostfixEvaluator {
    public static int evaluatePostfix(String s) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the character is an operand, push it to the stack
            if (Character.isDigit(c)) {
                stack.push(c - '0'); // Convert char to int
            }
            // If the character is an operator, pop two elements and perform the operation
            else {
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                int result = 0;

                switch (c) {
                    case '+':
                        result = operand2 + operand1;
                        break;
                    case '-':
                        result = operand2 - operand1;
                        break;
                    case '*':
                        result = operand2 * operand1;
                        break;
                    case '/':
                        if (operand1 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = operand2 / operand1;
                        break;
                }
                // Push the result back onto the stack
                stack.push(result);
            }
        }

        // The final result will be at the top of the stack
        return stack.pop();
    }

    public static void main(String[] args) {
        String expression1 = "231*+9-";
        String expression2 = "123+*8-";

        System.out.println("Result of \"" + expression1 + "\": " + evaluatePostfix(expression1));
        System.out.println("Result of \"" + expression2 + "\": " + evaluatePostfix(expression2));
    }
}

