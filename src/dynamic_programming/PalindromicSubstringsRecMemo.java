package dynamic_programming;


/*Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.

*/

public class PalindromicSubstringsRecMemo {
    private String s;
    private Boolean[][] dp; // memo table
    private int n;

    public int countSubstrings(String s) {
        this.s = s;
        this.n = s.length();
        this.dp = new Boolean[n][n];
        int count = 0;

        // Check all substrings
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindrome(int i, int j) {
        // Base cases
        if (i >= j) return true;
        if (dp[i][j] != null) return dp[i][j];

        if (s.charAt(i) != s.charAt(j)) {
            return dp[i][j] = false;
        }

        // Recurse on inner substring
        return dp[i][j] = isPalindrome(i + 1, j - 1);
    }

    // Driver
    public static void main(String[] args) {
        PalindromicSubstringsRecMemo sol = new PalindromicSubstringsRecMemo();
        System.out.println(sol.countSubstrings("abc")); // 3
        System.out.println(sol.countSubstrings("aaa")); // 6
    }
}
