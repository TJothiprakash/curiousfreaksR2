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

/*
Question:
Convert s1 to s2 using minimum insertions and deletions.

Approach:
- Find LCS of s1 and s2
- deletions = s1.length - LCS
- insertions = s2.length - LCS
- Total = deletions + insertions

Time: O(m * n)
Space: O(m * n)
*/

 class MinInsertDeleteToConvert {

    public static int minOperations(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        Integer[][] memo = new Integer[m][n];
        int lcs = longestCommonSubseq(0, 0, s1, s2, memo);
        return (m - lcs) + (n - lcs);
    }

    // Classic LCS recursion with memo
    private static int longestCommonSubseq(int i, int j, String s1, String s2, Integer[][] memo) {
        if (i >= s1.length() || j >= s2.length()) return 0;
        if (memo[i][j] != null) return memo[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = 1 + longestCommonSubseq(i + 1, j + 1, s1, s2, memo);
        } else {
            int skipS1 = longestCommonSubseq(i + 1, j, s1, s2, memo);
            int skipS2 = longestCommonSubseq(i, j + 1, s1, s2, memo);
            memo[i][j] = Math.max(skipS1, skipS2);
        }

        return memo[i][j];
    }

    public static void main(String[] args) {
        System.out.println(minOperations("heap", "pea"));           // 3
        System.out.println(minOperations("geeksforgeeks", "geeks")); // 8
    }
}

/*
Time Complexity:
- O(m * n)

Space Complexity:
- O(m * n) for memo table
*/
