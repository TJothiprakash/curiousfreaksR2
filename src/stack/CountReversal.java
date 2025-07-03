package stack;

public class CountReversal {
    //    O(n)O(1)
    public static int countRev(String S) {
        // If the length of the string is odd, return -1 (impossible to balance)
        if (S.length() % 2 != 0) {
            return -1;
        }

        // Stack to track unbalanced brackets
        int openCount = 0; // count of unmatched opening brackets
        int closeCount = 0; // count of unmatched closing brackets

        for (char c : S.toCharArray()) {
            if (c == '{') {
                openCount++; // increase open bracket count
            } else {
                if (openCount > 0) {
                    openCount--; // match an open bracket with a close bracket
                } else {
                    closeCount++; // unmatched closing bracket
                }
            }
        }

        // Now we need to fix openCount and closeCount
        // Number of reversals needed is ceil(openCount/2) + ceil(closeCount/2)
        return (openCount + 1) / 2 + (closeCount + 1) / 2;
    }

    public static void main(String[] args) {
        // Example 1:
        String s1 = "}{{}}{{{";
        System.out.println(countRev(s1)); // Output: 3

        // Example 2:
        String s2 = "{{}{{{}{{}}{{";
        System.out.println(countRev(s2)); // Output: -1
    }
}
