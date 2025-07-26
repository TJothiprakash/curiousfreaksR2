package dynamic_programming.july_25;

public class WildcardMatcher {

    public static void main(String[] args) {
        System.out.println(isMatch("acccccd", "a*b"));      // false
        System.out.println(isMatch("aaabjk", "a*b*d"));     // false
        System.out.println(isMatch("abcde", "a*?e"));       // true
        System.out.println(isMatch("abc", "*"));            // true
        System.out.println(isMatch("abc", "a*c"));          // true
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        Boolean[][] dp = new Boolean[m + 1][n + 1];
        return match(0, 0, s, p, dp);
    }

    private static boolean match(int i, int j, String s, String p, Boolean[][] dp) {
        if (dp[i][j] != null) return dp[i][j];

        if (j == p.length()) {
            return dp[i][j] = (i == s.length());
        }

        if (i == s.length()) {
            // Remaining pattern should all be '*'
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*') return dp[i][j] = false;
            }
            return dp[i][j] = true;
        }

        char pc = p.charAt(j), sc = s.charAt(i);
        boolean ans;

        if (pc == sc || pc == '?') {
            ans = match(i + 1, j + 1, s, p, dp);
        } else if (pc == '*') {
            // Try 0 chars: match(i, j+1), or try 1 char: match(i+1, j)
            ans = match(i, j + 1, s, p, dp) || match(i + 1, j, s, p, dp);
        } else {
            ans = false;
        }

        return dp[i][j] = ans;
    }
}
