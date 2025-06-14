package dynamic_programming.dp_onsubsequence;


/*
🔍 Problem Summary:
You’re given a rod of length n.

You can cut the rod into any number of pieces.

You’re also given a price[] array where price[i] is the price of a rod piece of length i + 1.

Goal: Maximize the total value by cutting and selling.

✅ Approach 1: Recursion + Memoization (Top-down DP)
💡 Key:
At every length n, we have a choice:

Cut the rod at length i + 1 and recurse for remaining n - (i + 1)

Try all possible lengths and take the maximum


🧠 Intuition:
You try all cut lengths from 1 to n, and for each cut i, add price[i-1] + best
 result for remaining length.

🔢 Time and Space Complexity:
Approach	Time Complexity	Space Complexity
Recursion + Memo	O(n²)	O(n)
Tabulation (DP)	O(n²)	O(n)*/
public class RodCutting {

    public static int cutRod(int[] price, int n) {
        Integer[] memo = new Integer[n + 1];
        return helper(price, n, memo);
    }

    private static int helper(int[] price, int n, Integer[] memo) {
        if (n == 0) return 0;
        if (memo[n] != null) return memo[n];

        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            int cutLen = i + 1;
            if (cutLen <= n) {
                maxVal = Math.max(maxVal, price[i] + helper(price, n - cutLen, memo));
            }
        }

        return memo[n] = maxVal;
    }

    public static void main(String[] args) {
        System.out.println(cutRod(new int[]{1, 5, 8, 9, 10, 17, 17, 20}, 8)); // 22
        System.out.println(cutRod(new int[]{3, 5, 8, 9, 10, 17, 17, 20}, 8)); // 24
        System.out.println(cutRod(new int[]{3}, 1)); // 3
    }
}
