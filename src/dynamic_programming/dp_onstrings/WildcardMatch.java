package dynamic_programming.dp_onstrings;

import org.jetbrains.annotations.NotNull;

/*
Given two strings pat and txt which may be of different sizes,
 You have to return true if the wildcard pattern i.e. pat, matches with txt else return false.

The wildcard pattern pat can include the characters '?' and '*'.

        '?' – matches any single character.
        '*' – matches any sequence of characters (including the empty sequence).
Note: The matching should cover the entire txt (not partial txt).

Examples:

Input: txt = "abcde", pat = "a?c*"
Output: true
Explanation: '?' matches with 'b' and '*' matches with "de".
Input: txt = "baaabab", pat = "a*ab"
Output: false
Explanation: Because in string pat character 'a' at first position, pat and txt can't be matched.
Input: txt = "abc", pat = "*"
Output: true
Explanation: '*' matches with whole text "abc".
Constraints:
        1 <= txt.size(),pat.size() <= 3000
*/
/*🎯 Pattern Rules Recap:
'?' matches any one character

'*' matches any sequence (even empty)

✅ Goal:
Determine if pat matches entire txt (not just a prefix or substring).

🔁 Recursion with Memoization Strategy
We'll define:

java
Copy
Edit
isMatch(i, j)
i: current index in pat

j: current index in txt

✅ Base Cases:
If both i and j are exhausted → ✅ match

If i is exhausted but j is not → ❌ no pattern left to match remaining text

If j is exhausted but i is not → only a chain of * can match empty string

🔁 Recurrence:
If pat[i] == txt[j] OR pat[i] == '?':

Match current char → recurse: isMatch(i+1, j+1)

If pat[i] == '*':

Two choices:

Match 0 chars → isMatch(i+1, j)

Match 1 or more → isMatch(i, j+1)

Else:

Characters do not match → return false*/
public class WildcardMatch {

    public boolean isMatch(@NotNull String txt, @NotNull String pat) {
        int m = pat.length();
        int n = txt.length();

        Boolean[][] dp = new Boolean[m + 1][n + 1];

        return match(pat, txt, 0, 0, dp);
    }

    private boolean match(String pat, String txt, int i, int j, Boolean[][] dp) {
        // Base Case: both exhausted
        if (i == pat.length() && j == txt.length()) return true;

        // Pattern exhausted, but txt remains
        if (i == pat.length()) return false;
        // Txt exhausted
        if (j == txt.length()) {
            // Remaining pattern must be all '*'
            for (int k = i; k < pat.length(); k++) {
                if (pat.charAt(k) != '*') return false;
            }
            return true;
        }

        if (dp[i][j] != null) return dp[i][j];

        boolean ans;

        if (pat.charAt(i) == txt.charAt(j) || pat.charAt(i) == '?') {
            ans = match(pat, txt, i + 1, j + 1, dp);
        } else if (pat.charAt(i) == '*') {
            // Match 0 characters or match 1 character
            ans = match(pat, txt, i + 1, j, dp) || match(pat, txt, i, j + 1, dp);
        } else {
            ans = false;
        }

        dp[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) {
        WildcardMatch solution = new WildcardMatch();

        System.out.println(solution.isMatch("abcde", "a?c*"));    // true
        System.out.println(solution.isMatch("baaabab", "a*ab"));  // false
        System.out.println(solution.isMatch("abc", "*"));         // true
    }
}

/*⏱ Time and Space:
Time: O(m × n) due to memoization

Space: O(m × n) for memo table + recursion stack*/