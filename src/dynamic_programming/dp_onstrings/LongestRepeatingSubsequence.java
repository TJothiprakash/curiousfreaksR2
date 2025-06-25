package dynamic_programming.dp_onstrings;

public class LongestRepeatingSubsequence {

    // Function to find the length of the longest repeating subsequence
    public static int longestRepeatingSubsequence(String s) {
        int n = s.length();

        // Create a DP table to store the lengths of longest repeating subsequences
        int[][] dp = new int[n + 1][n + 1];

        // Build the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match and indices are not the same
                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The length of the longest repeating subsequence will be in dp[n][n]
        return dp[n][n];
    }

    public static void main(String[] args) {
        String s1 = "axxzxy";
        String s2 = "axxxy";

        System.out.println(longestRepeatingSubsequence(s1)); // Output: 2 ("xx")
        System.out.println(longestRepeatingSubsequence(s2)); // Output: 2 ("xx")
    }
}
/*
Question:
Find length of longest repeating subsequence (same characters, different indices).

Approach:
- Treat as LCS(s, s) with condition: i ≠ j
- Use recursion + memoization (your preference)

Time: O(n^2)
Space: O(n^2)
*/


 class LongestRepeatingSubsequence1 {

    public static int longestRepeatingSubsequence(String s) {
        int n = s.length();
        Integer[][] memo = new Integer[n][n];
        return lrs(s, n - 1, n - 1, memo);
    }

    private static int lrs(String s, int i, int j, Integer[][] memo) {
        if (i < 0 || j < 0) return 0;

        if (memo[i][j] != null) return memo[i][j];

        if (s.charAt(i) == s.charAt(j) && i != j) {
            memo[i][j] = 1 + lrs(s, i - 1, j - 1, memo);
        } else {
            int skipI = lrs(s, i - 1, j, memo);
            int skipJ = lrs(s, i, j - 1, memo);
            memo[i][j] = Math.max(skipI, skipJ);
        }

        return memo[i][j];
    }

    public static void main(String[] args) {
        System.out.println(longestRepeatingSubsequence("axxzxy")); // Output: 2
        System.out.println(longestRepeatingSubsequence("axxxy"));  // Output: 2
        System.out.println(longestRepeatingSubsequence("aab"));    // Output: 1
    }
}
