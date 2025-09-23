package practicesessions.sept_17.dsa;

public class ValidParenthesisString {

    public static boolean checkValidString(String s) {
        int low = 0;   // minimum possible open '('
        int high = 0;  // maximum possible open '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low = Math.max(low - 1, 0);
                high--;
            } else if (c == '*') {
                // '*' can be '(', ')' or empty
                low = Math.max(low - 1, 0);
                high++;
            }

            // If high < 0, too many ')', invalid
            if (high < 0) return false;
        }

        // If low == 0, there exists a valid assignment
        return low == 0;
    }

    public static void main(String[] args) {
        System.out.println(checkValidString("()"));      // true
        System.out.println(checkValidString("(*)"));     // true
        System.out.println(checkValidString("(*))"));    // true
        System.out.println(checkValidString("(*()"));    // false
    }
}
