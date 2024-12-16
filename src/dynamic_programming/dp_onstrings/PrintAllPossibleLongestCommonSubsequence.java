package dynamic_programming.dp_onstrings;
import java.util.*;

public class PrintAllPossibleLongestCommonSubsequence {
    public List<String> all_longest_common_subsequences(String s, String t) {
        int m = s.length();
        int n = t.length();

        // Step 1: Compute the LCS length using DP
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Step 2: Backtrack to find all LCSs of maximum length
        Set<String> resultSet = new HashSet<>();
        backtrack(s, t, m, n, dp, new StringBuilder(), resultSet);

        // Step 3: Convert to a sorted list
        List<String> resultList = new ArrayList<>(resultSet);
        Collections.sort(resultList);
        return resultList;
    }

    private void backtrack(String s, String t, int i, int j, int[][] dp, StringBuilder current, Set<String> resultSet) {
        // Base case: If either string is fully traversed
        if (i == 0 || j == 0) {
            resultSet.add(current.reverse().toString());
            current.reverse(); // Reverse back for further exploration
            return;
        }

        // If characters match, include it in the current subsequence
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
            current.append(s.charAt(i - 1));
            backtrack(s, t, i - 1, j - 1, dp, current, resultSet);
            current.deleteCharAt(current.length() - 1); // Backtrack
        } else {
            // Otherwise, explore both possible directions
            if (dp[i - 1][j] == dp[i][j]) {
                backtrack(s, t, i - 1, j, dp, current, resultSet);
            }
            if (dp[i][j - 1] == dp[i][j]) {
                backtrack(s, t, i, j - 1, dp, current, resultSet);
            }
        }
    }
}