package dynamic_programming.dp_onstrings;
/*Given an array arr[], where arr[i] denotes the number of characters in one word. Let k be the limit on the number of characters that can be put in one line (line width). Put line breaks in the given sequence such that the lines are printed neatly.
Assume that the length of each word is smaller than the line width. When line breaks are inserted there is a possibility that extra spaces are present in each line. The extra spaces include spaces put at the end of every line except the last one.

You have to minimize the following total cost where total cost = Sum of cost of all lines, where cost of line is = (Number of extra spaces in the line)2.

Examples:

Input: arr[] = [3,2,2,5], k = 6
Output: 10
Explanation: Given a line can have 6 characters,
Line number 1: From word no. 1 to 1
Line number 2: From word no. 2 to 3
Line number 3: From word no. 4 to 4
So total cost = (6-3)2 + (6-2-2-1)2 = 32+12 = 10. As in the first line word length = 3 thus extra spaces = 6 - 3 = 3 and in the second line there are two word of length 2 and there already 1 space between two word thus extra spaces = 6 - 2 -2 -1 = 1. As mentioned in the problem description there will be no extra spaces in the last line. Placing first and second word in first line and third word on second line would take a cost of 02 + 42 = 16 (zero spaces on first line and 6-2 = 4 spaces on second), which isn't the minimum possible cost.
Input: arr[] = [3,2,2], k = 4
Output: 5
Explanation: Given a line can have 4 characters,
Line number 1: From word no. 1 to 1
Line number 2: From word no. 2 to 2
Line number 3: From word no. 3 to 3
Same explaination as above total cost = (4 - 3)2 + (4 - 2)2 = 5.
Constraints:
1 ≤ arr.size() ≤ 500
1 ≤ arr[i] ≤ 1000
max(arr[i]) ≤ k ≤ 2000

*/
/*
🧠 Problem Breakdown:
        You’re given:

        arr[] = array of word lengths

        k = max characters per line (line width)

        You need to break the array into lines such that:

        Each line (except last) has minimum cost = (extra spaces)²

        The last line’s cost is always 0

        You return the minimum total cost

        ✅ Recursive Function Definition
        We define a function:

        java
        Copy
        Edit
        int solve(int i)
        Where:

        i is the starting word index (0-based)

        It returns the minimum total cost of wrapping words from i to end

        📌 At each i, we try:
        Include words from i to j (as long as line length ≤ k)

        Compute cost for line i to j (except if it's the last line → 0)

        Add the cost from the recursive call solve(j + 1)

        Take min across all such j

        ✅ Java Code (Recursion + Memoization)
        java
        Copy
        Edit
import designpatterns.Square;

import java.sql.Time;
import java.util.*;
*/

public class WordWrap {

    public int solveWordWrap(int[] words, int k) {
        int n = words.length;
        Integer[] dp = new Integer[n];
        return helper(0, words, k, dp);
    }

    private int helper(int i, int[] words, int k, Integer[] dp) {
        if (i == words.length) return 0;
        if (dp[i] != null) return dp[i];

        int minCost = Integer.MAX_VALUE;
        int lineLen = 0;

        for (int j = i; j < words.length; j++) {
            lineLen += words[j];
            if (j > i) lineLen += 1; // add space between words

            if (lineLen > k) break;

            int cost = (j == words.length - 1) ? 0 : (k - lineLen) * (k - lineLen);
            int nextCost = helper(j + 1, words, k, dp);
            minCost = Math.min(minCost, cost + nextCost);
        }

        return dp[i] = minCost;
    }

    public static void main(String[] args) {
        WordWrap ww = new WordWrap();

        System.out.println(ww.solveWordWrap(new int[]{3, 2, 2, 5}, 6)); // Output: 10
        System.out.println(ww.solveWordWrap(new int[]{3, 2, 2}, 4));    // Output: 5
    }
}
/*
🔍 How It Works:
For each i, we try forming lines like:

words[i]

words[i] + words[i+1]

words[i] + words[i+1] + words[i+2]

        … until line length exceeds k

We:

Compute extra spaces

Square them if it’s not the last line

Recursively solve for the rest

Use memo to avoid recomputation

⏱ Time Complexity:
Let n = number of words

Time: O(n²) — due to nested loop with memoization

Space: O(n) for dp[] + recursion stack

*/
