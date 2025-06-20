package dynamic_programming.dp_onstrings;

import java.util.List;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        LongestCommonSubsequence solution = new LongestCommonSubsequence();

        // Test cases
        System.out.println(solution.longestCommonSubsequence("abcde", "ace")); // Expected: 3
//        System.out.println(solution.longestCommonSubsequence("abc", "abc"));  // Expected: 3
//        System.out.println(solution.longestCommonSubsequence("abc", "def"));  // Expected: 0
//        System.out.println(solution.longestCommonSubsequence("abcde", "ae")); // Expected: 2
        List<String> result =  new PrintAllPossibleLongestCommonSubsequence().all_longest_common_subsequences("abaaa", "baabaca");
        System.out.println(result);
        for (String s : result) {
            System.out.println(s);
        }
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // Memoization table
        Integer[][] memo = new Integer[m][n];

        // Recursive function with memoization
        return lcs(text1, text2, m - 1, n - 1, memo);
    }

    private int lcs(String text1, String text2, int i, int j, Integer[][] memo) {
        // Base case: if any string is empty
        if (i < 0 || j < 0) {
            return 0;
        }

        // Check if the result is already calculated
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        // Case 1: Characters match
        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + lcs(text1, text2, i - 1, j - 1, memo);
        } else {
            // Case 2: Characters don't match
            memo[i][j] = Math.max(lcs(text1, text2, i - 1, j, memo), lcs(text1, text2, i, j - 1, memo));
        }

        return memo[i][j];
    }
}

