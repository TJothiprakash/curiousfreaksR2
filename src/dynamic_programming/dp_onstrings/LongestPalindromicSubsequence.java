package dynamic_programming.dp_onstrings;

import java.util.Arrays;
// 1. find longest palindromic subsequence
// 2. make palindrome with minimal insertions
public class LongestPalindromicSubsequence {
    int[][] dp = new int[503][503];

    public static void main(String[] args) {
        LongestPalindromicSubsequence solution = new LongestPalindromicSubsequence();
        String s1 = "bbbab";
        String s2 = "cbbd";
        System.out.println("Longest Palindromic Subsequence Length (bbbab): " + solution.longestPalindromeSubseq(s1)); // Output: 4
        System.out.println("Longest Palindromic Subsequence Length (cbbd): " + solution.longestPalindromeSubseq(s2)); // Output: 2
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // Initialize memo table with -1
        }
        return dfs(s, 0, n - 1, memo);
    }

    // form a palindrome with minimal insertions

    private int dfs(String s, int i, int j, int[][] memo) {
        // Base cases
        if (i > j) return 0; // Invalid substring
        if (i == j) return 1; // Single character is a palindrome

        // Check memo
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Recursive case
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = dfs(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(dfs(s, i + 1, j, memo), dfs(s, i, j - 1, memo));
        }

        return memo[i][j];
    }

    public int findPalindromeWithMinInsertions(String s, String p, int i, int j) {
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        char a = s.charAt(i);
        char b = p.charAt(j);

        if (a == b)
            dp[i][j] = 1 + findPalindromeWithMinInsertions(s, p, i - 1, j - 1);
        else
            dp[i][j] = (int) Math.max(findPalindromeWithMinInsertions(s, p, i - 1, j), findPalindromeWithMinInsertions(s, p, i, j - 1));

        return dp[i][j];
    }

    public int minInsertions(String s) {
        String p = "";
        for (char c : s.toCharArray())
            p = c + p;
        for (int i = 0; i <= s.length(); i++)
            Arrays.fill(dp[i], -1);
        return s.length() - findPalindromeWithMinInsertions(s, p, s.length() - 1, p.length() - 1);
    }

}

/*
Question:
Return the length of the longest palindromic subsequence in string s.

Approach:
- Use recursion + memoization.
- If s[i] == s[j] → 2 + recurse inward
- Else → max(skip i, skip j)
- Memoize using dp[i][j]

Time: O(n^2)
Space: O(n^2)
*/


 class LongestPalindromicSubsequence7 {

    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        Integer[][] memo = new Integer[n][n];
        return lps(0, n - 1, s, memo);
    }

    // Recursive helper with memo
    private static int lps(int i, int j, String s, Integer[][] memo) {
        if (i > j) return 0;          // Invalid range
        if (i == j) return 1;         // Single char is a palindrome

        if (memo[i][j] != null) return memo[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = 2 + lps(i + 1, j - 1, s, memo);
        } else {
            int skipLeft = lps(i + 1, j, s, memo);
            int skipRight = lps(i, j - 1, s, memo);
            memo[i][j] = Math.max(skipLeft, skipRight);
        }

        return memo[i][j];
    }

    // Driver
    public static void main(String[] args) {
        String s1 = "bbbab";
        System.out.println("Longest Palindromic Subsequence Length (1): " + longestPalindromeSubseq(s1)); // 4

        String s2 = "cbbd";
        System.out.println("Longest Palindromic Subsequence Length (2): " + longestPalindromeSubseq(s2)); // 2
    }
}

/*
Time Complexity:
- O(n^2): each (i, j) pair is computed once

Space Complexity:
- O(n^2): for memoization table
- O(n): recursion stack depth
*/
