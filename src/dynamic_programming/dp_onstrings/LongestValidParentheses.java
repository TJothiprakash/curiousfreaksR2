package dynamic_programming.dp_onstrings;

import java.util.Arrays;
/*

Given a string s consisting of opening and closing parenthesis '(' and ')'. Find the length of the longest valid parenthesis substring.

A parenthesis string is valid if:

For every opening parenthesis, there is a closing parenthesis.
The closing parenthesis must be after its opening parenthesis.
        Examples :

Input: s = "((()"
Output: 2
Explanation: The longest valid parenthesis substring is "()".
Input: s = ")()())"
Output: 4
Explanation: The longest valid parenthesis substring is "()()".
Input: s = "())()"
Output: 2
Explanation: The longest valid parenthesis substring is "()".
Constraints:
        1 ≤ s.size() ≤ 106
s consists of '(' and ')' only

Expected Complexities*/

/*

🧠 Problem Understanding
        We're given a string s of '(' and ')'. We want to find the length of the longest
         contiguous substring which is a valid parenthesis expression.

        ✅ Constraints:
        Length up to 1e6 ⇒ very large

        So pure recursion without memo will TLE

        We'll use recursion with memoization, but we also need to be cautious and efficient

        🔍 Observations
        The usual approaches are:

        Stack-based (O(n))

        DP with array (O(n))

        Two-pass greedy (O(n))

        But since you want recursive with memo, let's define:

        ✅ Recursion Strategy
        Let’s define:

        java
        Copy
        Edit
        int longestFrom(int i)
        Where:

        i is the starting index in the string s

        It returns the length of the longest valid parentheses substring starting at i

        We memoize the result for each i.

        🔁 Recursion Cases:
        At position i:

        If s[i] == '(', we try to find a matching ')' at some j > i

        We match the first () pair we can find and check recursively for the next position

        Only substrings that are contiguous and valid are considered

        We'll need to manage indices carefully to ensure continuity.

        ✅ Java Code (Recursion + Memoization)
        java
        Copy
        Edit

*/

public class LongestValidParentheses {

    public int longestValid(String s) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, helper(s, i, dp));
        }

        return maxLen;
    }

    private int helper(String s, int i, int[] dp) {
        if (i >= s.length()) return 0;
        if (dp[i] != -1) return dp[i];

        if (s.charAt(i) == ')') {
            dp[i] = 0;
            return 0;
        }

        // s.charAt(i) == '('
        int j = i + 1;
        int count = 1;

        while (j < s.length()) {
            int subLen = helper(s, j, dp); // check what's at j
            j += subLen;

            if (j < s.length() && s.charAt(j) == ')') {
                // Found matching ')'
                dp[i] = (j - i + 1);
                // Can extend further if valid after this pair
                if (j + 1 < s.length()) {
                    dp[i] += helper(s, j + 1, dp);
                }
                return dp[i];
            } else {
                break; // No valid match
            }
        }

        dp[i] = 0;
        return 0;
    }

    public static void main(String[] args) {
        LongestValidParentheses solution = new LongestValidParentheses();

        System.out.println(solution.longestValid("((()"));      // Output: 2
        System.out.println(solution.longestValid(")()())"));    // Output: 4
        System.out.println(solution.longestValid("())()"));     // Output: 2
    }
}/*
🧠 Explanation:
We try to match '(' with a future ')' using recursion.
We keep skipping forward using memoized results (dp[j]) to avoid recomputation.
This is the closest way to replicate stack or DP logic using recursion with memo.

⏱ Time and Space Complexity
Time: O(n), because each index is computed only once

Space: O(n) for memo + O(n) recursion stack

*/