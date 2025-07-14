package stack.july_13;

import java.util.Stack;

public class ParenthesisValidator {


    public static void main(String[] args) {

    }


    // 1st variant check all braces are matched 2 open and 2 closed withapporpirate places not cloe and open
    /*only open close is the correct placement
     * retur true or false in the end */
    // 2nd variant  find true or false if false then find the correct order / no of braces removed/ added to correct the string

    public boolean validate(Character[] arr) {
        Stack<Character> stack = new Stack<>();

        for (Character ch : arr) {
            if (ch == '{' || ch == '(' || ch == '[') {
                stack.push(ch);
            } else if (ch == '}' || ch == ')' || ch == ']') {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if (!isMatching(top, ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private boolean isMatching(char open, char close) {
        return (open == '{' && close == '}') ||
                (open == '(' && close == ')') ||
                (open == '[' && close == ']');
    }

    public int validateAndCount(Character[] arr) {
        Stack<Character> stack = new Stack<>();
        int mismatchCount = 0;

        for (Character ch : arr) {
            if (ch == '{' || ch == '(' || ch == '[') {
                stack.push(ch);
            } else if (ch == '}' || ch == ')' || ch == ']') {
                if (!stack.isEmpty() && isMatching(stack.peek(), ch)) {
                    stack.pop();
                } else {
                    // unmatched closing bracket
                    mismatchCount++;
                }
            }
        }

        // leftover openings are also mismatches
        mismatchCount += stack.size();

        return mismatchCount;
    }




}

