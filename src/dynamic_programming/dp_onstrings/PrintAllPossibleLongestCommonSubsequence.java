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

    public static void main(String[] args) {
        String s1 = "ABCDGH";
        String s2 = "ACDGHR";
        System.out.println("s1 is " + s1 + " and s2 is " + s2 + ".\n");
        PrintAllPossibleLongestCommonSubsequence solution = new PrintAllPossibleLongestCommonSubsequence();
        System.out.println("All Longest Common Subsequences: " + solution.all_longest_common_subsequences(s1, s2));
    }
}


/*
Question:
Print all distinct LCS (Longest Common Subsequences) in lexicographical order.

Intuition:
1. Use DP to compute the LCS length (top-down with memoization)
2. Reconstruct all possible LCS strings using backtracking
3. Use a Set (TreeSet) to ensure uniqueness + lexicographical order

Time: O(2^(m+n)) worst-case for reconstruction (but pruned with memo)
Space: O(m*n + number of subsequences)
*/


 class AllLCS {

    static int[][] dp;
    static Set<String>[][] memoSet;

    // Step 1: Compute LCS length
    static int lcs(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        dp = new int[m + 1][n + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    // Step 2: Reconstruct all LCS strings from dp[0][0]
    static Set<String> buildAllLCS(int i, int j, String s1, String s2) {
        if (i >= s1.length() || j >= s2.length()) {
            Set<String> base = new HashSet<>();
            base.add("");
            return base;
        }

        if (memoSet[i][j] != null) return memoSet[i][j];

        Set<String> result = new TreeSet<>();

        if (s1.charAt(i) == s2.charAt(j)) {
            Set<String> temp = buildAllLCS(i + 1, j + 1, s1, s2);
            for (String str : temp) {
                result.add(s1.charAt(i) + str);
            }
        } else {
            if (dp[i + 1][j] >= dp[i][j + 1]) {
                result.addAll(buildAllLCS(i + 1, j, s1, s2));
            }
            if (dp[i][j + 1] >= dp[i + 1][j]) {
                result.addAll(buildAllLCS(i, j + 1, s1, s2));
            }
        }

        memoSet[i][j] = result;
        return result;
    }

    // Driver method
    public static List<String> allDistinctLCS(String s1, String s2) {
        lcs(s1, s2);
        memoSet = new HashSet[s1.length() + 1][s2.length() + 1];
        Set<String> result = buildAllLCS(0, 0, s1, s2);
        return new ArrayList<>(result); // lexicographically sorted via TreeSet
    }

    public static void main(String[] args) {
        String s1 = "abaaa", s2 = "baabaca";
        System.out.println("Output 1: " + allDistinctLCS(s1, s2)); // [aaaa, abaa, baaa]

        s1 = "aaa"; s2 = "a";
        System.out.println("Output 2: " + allDistinctLCS(s1, s2)); // [a]
    }
}

/*
Time Complexity:
- O(m*n) for LCS length
- Up to O(2^(m+n)) subsequences in worst case
- But pruned by memoSet and TreeSet

Space Complexity:
- O(m*n) for dp
- O(m*n) for memoSet
- O(k * l): k = number of subsequences, l = length of each
*/
