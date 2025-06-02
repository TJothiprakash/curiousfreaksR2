package strings;

public class MinimumBracketReversals {
    public static int countReversals(String s) {

        if (s.length() % 2 == 1) {
            return -1;
        }

        int opening_count = 0, closing_count = 0; // opening_count;
        for (char c : s.toCharArray()) {
            if (c == '{') {
                opening_count++;
            } else {
                if (opening_count > 0) {
                    // Match with an unmatched opening bracket
                    opening_count--;
                } else {
                    // Unmatched closing bracket
                    closing_count++;
                }
            }
        }

        // Calculate the number of reversals

        return (opening_count + 1) / 2 + (closing_count + 1) / 2;
    }

    public static void main(String[] args) {
        String s = "}{{}}{{{";
        System.out.println(countReversals(s));
    }
}
