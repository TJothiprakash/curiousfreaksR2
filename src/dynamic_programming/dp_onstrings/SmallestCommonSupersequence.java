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
