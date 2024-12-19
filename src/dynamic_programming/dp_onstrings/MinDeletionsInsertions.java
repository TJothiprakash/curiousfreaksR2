package dynamic_programming.dp_onstrings;

import java.util.Arrays;

public class MinDeletionsInsertions {
    public static void main(String[] args) {
        MinDeletionsInsertions solution = new MinDeletionsInsertions();
        String s1 = "heap";
        String s2 = "pea";
        solution.minOperations(s1, s2); // Output: Deletions: 2, Insertions: 1
    }

    public int lcs(String s1, String s2, int m, int n, int[][] memo) {
        // Base cases
        if (m == 0 || n == 0) return 0;

        // Check memo
        if (memo[m][n] != -1) return memo[m][n];

        // Recursive case
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1, memo);
        } else {
            memo[m][n] = Math.max(lcs(s1, s2, m - 1, n, memo), lcs(s1, s2, m, n - 1, memo));
        }

        return memo[m][n];
    }

    public void minOperations(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // Initialize memo table
        }

        // Find LCS length
        int lcsLength = lcs(s1, s2, m, n, memo);

        // Calculate minimum deletions and insertions
        int deletions = m - lcsLength;
        int insertions = n - lcsLength;

        System.out.println("Min Deletions: " + deletions);
        System.out.println("Min Insertions: " + insertions);
    }
}
