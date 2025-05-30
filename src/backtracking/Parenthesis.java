package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Parenthesis {
    public static void main(String[] args) {
        Parenthesis sol = new Parenthesis();
        System.out.println(sol.generateParenthesis(3));
        System.out.println(sol.generateParenthesis(1));
        System.out.println(sol.generateParenthesis(4));
        System.out.println(sol.generateParenthesis(5));
        System.out.println(sol.generateParenthesis(6));
    }
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n % 2 != 0) return result; // odd-length strings cannot be balanced
        backtrack(result, "", 0, 0, n / 2);
        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // Base case: when the length of current string is 2 * max
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // If we can add an opening bracket
        if (open < max)
            backtrack(result, current + "(", open + 1, close, max);

        // If we can add a closing bracket
        if (close < open)
            backtrack(result, current + ")", open, close + 1, max);
    }
}
