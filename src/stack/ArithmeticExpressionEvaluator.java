package stack;

import java.util.*;

public class ArithmeticExpressionEvaluator {
    public static void main(String[] args) {
        String[] expressions = {
                "(2)",
                "((2+3)*(5/2))",
                "(121+(101+0))",
                "(3*(5+2)*(10-7))"
        };

        for (String expr : expressions) {
            System.out.println(evaluate(expr));
        }
    }

    public static int evaluate(String expression) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int n = expression.length();

        for (int i = 0; i < n; i++) {
            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) continue;

            if (ch == '(') {
                ops.push(ch);
            } else if (Character.isDigit(ch)) {
                int num = 0;
                while (i < n && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--; // to counter extra i++ from while
                nums.push(num);
            } else if (ch == ')') {
                while (ops.peek() != '(') {
                    nums.push(applyOp(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.pop(); // pop '('
            } else if (isOperator(ch)) {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(ch)) {
                    nums.push(applyOp(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(ch);
            }
        }

        while (!ops.isEmpty()) {
            nums.push(applyOp(ops.pop(), nums.pop(), nums.pop()));
        }

        return nums.pop();
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // integer division (floor)
        }
        return 0;
    }
}
