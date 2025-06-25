package dynamic_programming.dp_onstrings;

public class SmallestCommonSupersequence {

    // Function to compute the LCS length
    public static int lcs(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    // Function to compute the length of the SCS
    public static int shortestCommonSupersequence(String s1, String s2) {
        int lcsLength = lcs(s1, s2);
        return s1.length() + s2.length() - lcsLength;
    }

    public static void main(String[] args) {
        String s1 = "geek";
        String s2 = "eke";
        System.out.println("Length of SCS: " + shortestCommonSupersequence(s1, s2));  // Output: 5
    }
}


/*
Question:
Find length of the shortest string that has both s1 and s2 as subsequences.

Approach:
- Compute Longest Common Subsequence (LCS)
- SCS length = s1.length + s2.length - LCS

Time: O(m * n)
Space: O(m * n)
*/

 class ShortestCommonSupersequence {

    public static int shortestCommonSupersequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        Integer[][] memo = new Integer[m][n];
        int lcsLen = lcs(0, 0, s1, s2, memo);
        return m + n - lcsLen;
    }

    private static int lcs(int i, int j, String s1, String s2, Integer[][] memo) {
        if (i >= s1.length() || j >= s2.length()) return 0;
        if (memo[i][j] != null) return memo[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = 1 + lcs(i + 1, j + 1, s1, s2, memo);
        } else {
            memo[i][j] = Math.max(
                    lcs(i + 1, j, s1, s2, memo),
                    lcs(i, j + 1, s1, s2, memo)
            );
        }

        return memo[i][j];
    }

    public static void main(String[] args) {
        System.out.println(shortestCommonSupersequence("geek", "eke"));         // 5
        System.out.println(shortestCommonSupersequence("geek", "ek"));          // 4
        System.out.println(shortestCommonSupersequence("AGGTAB", "GXTXAYB"));   // 9
    }
}

/*
Time: O(m * n)
Space: O(m * n)
*/
