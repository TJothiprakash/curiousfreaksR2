package dynamic_programming.dp_onstrings;
/*
Question:
Minimum operations to convert s1 to s2 using Insert/Delete/Replace.

Approach:
- Use recursion + memoization
- f(i, j) = edit distance between s1[0..i] and s2[0..j]

Time: O(m * n)
Space: O(m * n)
*/

import java.util.*;

public class EditDistance {

    public static int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        Integer[][] memo = new Integer[m + 1][n + 1];
        return helper(s1, s2, m, n, memo);
    }

    private static int helper(String s1, String s2, int i, int j, Integer[][] memo) {
        if (i == 0) return j; // Insert all characters of s2
        if (j == 0) return i; // Remove all characters of s1

        if (memo[i][j] != null) return memo[i][j];

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            memo[i][j] = helper(s1, s2, i - 1, j - 1, memo);
        } else {
            int insertOp = helper(s1, s2, i, j - 1, memo);
            int deleteOp = helper(s1, s2, i - 1, j, memo);
            int replaceOp = helper(s1, s2, i - 1, j - 1, memo);
            memo[i][j] = 1 + Math.min(insertOp, Math.min(deleteOp, replaceOp));
        }

        return memo[i][j];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("geek", "gesek")); // 1
        System.out.println(minDistance("gfg", "gfg"));    // 0
        System.out.println(minDistance("abcd", "bcfe"));  // 3
    }
}

