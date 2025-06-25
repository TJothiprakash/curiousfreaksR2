package dynamic_programming.dp_onstrings;

import java.util.List;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        LongestCommonSubsequence1 solution = new LongestCommonSubsequence1();

        // Test cases
        System.out.println(solution.longestCommonSubsequence("abcde", "ace")); // Expected: 3
//        System.out.println(solution.longestCommonSubsequence("abc", "abc"));  // Expected: 3
//        System.out.println(solution.longestCommonSubsequence("abc", "def"));  // Expected: 0
//        System.out.println(solution.longestCommonSubsequence("abcde", "ae")); // Expected: 2
        List<String> result = new PrintAllPossibleLongestCommonSubsequence().all_longest_common_subsequences("abaaa", "baabaca");
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


/*
Question:
Return the length of longest common subsequence between text1 and text2.
Subsequence means order must be preserved, but not necessarily contiguous.

Intuition:
- If characters match, move both i and j forward, add 1
- If not, try skipping either from text1 or text2
- Use recursion + memoization to avoid recomputing (i, j) pairs

Approach:
- Define helper(i, j)
- Memoize using a 2D array or map
- Base case: if i or j reaches end → return 0

Time: O(m * n)
Space: O(m * n)
*/


class LongestCommonSubsequence1 {

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        Integer[][] memo = new Integer[m][n];

        return lcs(0, 0, text1, text2, memo);
    }

    private static int lcs(int i, int j, String t1, String t2, Integer[][] memo) {
        if (i >= t1.length() || j >= t2.length()) return 0;

        if (memo[i][j] != null) return memo[i][j];

        if (t1.charAt(i) == t2.charAt(j)) {
            memo[i][j] = 1 + lcs(i + 1, j + 1, t1, t2, memo);
        } else {
            int skipText1 = lcs(i + 1, j, t1, t2, memo);
            int skipText2 = lcs(i, j + 1, t1, t2, memo);
            memo[i][j] = Math.max(skipText1, skipText2);
        }

        return memo[i][j];
    }

    // Test
    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        System.out.println("LCS Length (1): " + longestCommonSubsequence(text1, text2)); // 3

        String t3 = "abc", t4 = "abc";
        System.out.println("LCS Length (2): " + longestCommonSubsequence(t3, t4)); // 3

        String t5 = "abc", t6 = "def";
        System.out.println("LCS Length (3): " + longestCommonSubsequence(t5, t6)); // 0
    }
}

/*
Time Complexity:
- O(m * n): each (i, j) is computed once

Space Complexity:
- O(m * n): for memoization table
- O(m + n): recursion stack
*/

