package stack;
import java.util.Stack;

public class InfixEvaluator {
    public static int evaluateInfix(String expression) {
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // If the current character is a digit
            if (Character.isDigit(ch)) {
                int num = 0;
                // Handle multi-digit numbers
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--; // Adjust for the extra increment in the loop
                operands.push(num);
            }
            // If the current character is '('
            else if (ch == '(') {
                operators.push(ch);
            }
            // If the current character is ')'
            else if (ch == ')') {
                while (operators.peek() != '(') {
                    evaluateTop(operands, operators);
                }
                operators.pop(); // Pop the '('
            }
            // If the current character is an operator
            else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                    evaluateTop(operands, operators);
                }
                operators.push(ch);
            }
        }

        // Evaluate any remaining operators
        while (!operators.isEmpty()) {
            evaluateTop(operands, operators);
        }

        // The result is the only value left in the operands stack
        return operands.pop();
    }

    // Helper function to evaluate the top operation
    private static void evaluateTop(Stack<Integer> operands, Stack<Character> operators) {
        int a = operands.pop();
        int b = operands.pop();
        char op = operators.pop();
        operands.push(applyOperation(b, a, op));
    }

    // Helper function to apply an operation
    private static int applyOperation(int a, int b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // Floor division
            default: throw new IllegalArgumentException("Invalid operator");
        }
    }

    // Helper function to determine precedence
    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0; // For '('
    }

    // Helper function to check if a character is an operator
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static void main(String[] args) {
        String[] testCases = {
                "(2)",
                "((2+3)*(5/2))",
                "(121+(101+0))",
                "(3*(5+2)*(10-7))"
        };

        for (String expression : testCases) {
            System.out.println("Expression: " + expression + ", Result: " + evaluateInfix(expression));
        }
    }
}

