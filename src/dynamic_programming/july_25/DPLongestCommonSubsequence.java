package dynamic_programming.july_25;

import java.util.*;

public class DPLongestCommonSubsequence {
    public static void main(String[] args) {
        DPLongestCommonSubsequence dp = new DPLongestCommonSubsequence();
        String s1 = "abcde";
        String s2 = "ace";
        int lcs = dp.longestCommonSubsequence("agbdba",new StringBuilder("agbdba").reverse().toString() );
        int lcs1 = dp.longestCommonSubsequence(s1, s2);
        int lcs2 = dp.longestCommonSubsequence(s1, s2);
        int lcs3 = dp.longestCommonSubsequence(s1, s2);
        int lcs4 = dp.longestCommonSubsequence(s1, s2);
        int lcs5 = dp.longestCommonSubsequence(s1, s2);
        int lcs6 = dp.longestCommonSubsequence(s1, s2);
        System.out.println("LCS length: " + lcs); // Expected: 3
    }


    public int longestCommonSubsequence(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();
        int[][] memo = new int[n][m];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        int result = findlcs(0, 0, s1, s2, memo);
        return result;
    }

    private int findlcs(int index1, int index2, String s1, String s2, int[][] memo) {
        if (index1 >= s1.length() || index2 >= s2.length()) {
            return 0;
        }

        if (memo[index1][index2] != -1) {
            return memo[index1][index2];
        }

        // cases 1)if both equal move both else move either one

        if (s1.charAt(index1) == s2.charAt(index2)) {
            memo[index1][index2] = 1 + findlcs(index1 + 1, index2 + 1, s1, s2, memo);
            return memo[index1][index2];
        }

        if (s1.charAt(index1) != s2.charAt(index2)) {
            memo[index1][index2] = Math.max(findlcs(index1 + 1, index2, s1, s2, memo), findlcs(index1, index2 + 1, s1, s2, memo));
        }
        return memo[index1][index2];

    }
    public List<String> printAllLCS(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        // Fill DP table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        // Use Set to avoid duplicate LCS strings
        Set<String> result = new HashSet<>();
        backtrack(s1, s2, 0, 0, dp, new StringBuilder(), result);

        return new ArrayList<>(result);
    }

    private void backtrack(String s1, String s2, int i, int j, int[][] dp, StringBuilder path, Set<String> result) {
        if (i == s1.length() || j == s2.length()) {
            result.add(path.toString());
            return;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            path.append(s1.charAt(i));
            backtrack(s1, s2, i + 1, j + 1, dp, path, result);
            path.deleteCharAt(path.length() - 1); // backtrack
        } else {
            if (dp[i + 1][j] >= dp[i][j + 1]) {
                backtrack(s1, s2, i + 1, j, dp, path, result);
            }
            if (dp[i][j + 1] >= dp[i + 1][j]) {
                backtrack(s1, s2, i, j + 1, dp, path, result);
            }
        }
    }
        public List<String> pringLongestCommonSubsequence(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();
            int memo[][] = new int[n][m];
            List<String> res = new ArrayList<>();
            helper(0, 0, s1, s2, memo, res, new StringBuilder());
            return null;
        }

        private void helper(int index1, int index2, String s1, String s2, int[][] memo, List<String> res, StringBuilder stringBuilder) {

            if (index1 >= s1.length() || index2 >= s2.length()) {
                return;
            }

            if (memo[index1][index2] != -1) {
    //            return memo[index1][index2];

            }

            // cases 1)if both equal move both else move either one

            if (s1.charAt(index1) == s2.charAt(index2)) {
                stringBuilder.append(s1.charAt(index1));
                helper(index1 + 1, index2 + 1, s1, s2, memo, res, stringBuilder);
    //    return memo[index1][index2];
                res.add(stringBuilder.toString());

            }

            if (s1.charAt(index1) != s2.charAt(index2)) {

                memo[index1][index2] = Math.max(findlcs(index1 + 1, index2, s1, s2, memo), findlcs(index1, index2 + 1, s1, s2, memo));
            }
    //        return memo[index1][index2];
        }
    }
