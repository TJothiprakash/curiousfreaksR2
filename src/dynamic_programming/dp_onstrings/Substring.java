package dynamic_programming.dp_onstrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Substring {
    // Recursive function to find the length of the Longest Common Subsequence (LCS)
    static int lcsUtil(String s1, String s2, int ind1, int ind2, int[][] dp) {
        // Base case: If either of the strings reaches the end, return 0
        if (ind1 < 0 || ind2 < 0)
            return 0;

        // If the result for this subproblem has already been calculated, return it
        if (dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        // If the characters at the current indices are the same, increment the LCS length
        if (s1.charAt(ind1) == s2.charAt(ind2))
            return dp[ind1][ind2] = 1 + lcsUtil(s1, s2, ind1 - 1, ind2 - 1, dp);

            // If the characters are different, choose the maximum LCS length by either
            // skipping a character in s1 or skipping a character in s2
        else
            return dp[ind1][ind2] = Math.max(lcsUtil(s1, s2, ind1, ind2 - 1, dp),
                    lcsUtil(s1, s2, ind1 - 1, ind2, dp));
    }

    // Function to find the length of the Longest Common Subsequence (LCS)
    static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Create a 2D array to store results of subproblems
        int[][] dp = new int[n][m];

        // Initialize the dp array with -1 to indicate that subproblems are not solved yet
        for (int[] rows : dp)
            Arrays.fill(rows, -1);

        // Call the recursive function to find the LCS length
        return lcsUtil(s1, s2, n - 1, m - 1, dp);
    }

    public static void main(String[] args) {
        String s1 = "acde";
        String s2 = "cede";

        // Call the lcs function and print the result
        System.out.println("The Length of Longest Common Subsequence is " + lcs(s1, s2));
    }
}

/*
Question:
Find the length of the longest common substring between s1 and s2 using recursion + memoization.

Approach:
- Use 3D memoization (i, j, count)
- count tracks current matching substring length
- If chars match → count++
- Else → reset count and explore both sides

Time: O(m * n * min(m,n))
Space: O(m * n * min(m,n))
*/


class LongestCommonSubstringRec {

    static int maxLen = 0;

    public static int longestCommonSubstring(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        Map<String, Integer> memo = new HashMap<>();
        return lcs(s1, s2, 0, 0, 0, memo);
    }

    private static int lcs(String s1, String s2, int i, int j, int count, Map<String, Integer> memo) {
        if (i == s1.length() || j == s2.length()) return count;

        String key = i + "|" + j + "|" + count;
        if (memo.containsKey(key)) return memo.get(key);

        int currentCount = count;

        // If characters match, continue the streak
        if (s1.charAt(i) == s2.charAt(j)) {
            currentCount = lcs(s1, s2, i + 1, j + 1, count + 1, memo);
        }

        // Else try other branches (break streak)
        int moveS1 = lcs(s1, s2, i + 1, j, 0, memo);
        int moveS2 = lcs(s1, s2, i, j + 1, 0, memo);

        int max = Math.max(currentCount, Math.max(moveS1, moveS2));
        memo.put(key, max);
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("ABCDGH", "ACDGHR")); // 4
        System.out.println(longestCommonSubstring("abc", "acb"));       // 1
        System.out.println(longestCommonSubstring("YZ", "yz"));         // 0
    }
}



