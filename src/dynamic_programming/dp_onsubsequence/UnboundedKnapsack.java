package dynamic_programming.dp_onsubsequence;
/*

This is the classic Unbounded Knapsack Problem, where:

        You can pick any item multiple times.

        Goal: Maximize the total value without exceeding the knapsack capacity.

        ✅ Approach: Recursion with Memoization (Java)
        🔁 Recursive Relation:
        Let f(cap) be the maximum profit for a given capacity cap.

        Then:

        java
        Copy
        Edit
        f(cap) = max(f(cap - wt[i]) + val[i]) for all i where wt[i] <= cap
        We use memoization to avoid recomputation.

        ✅ Java Code: Recursion + Memoization
        java
        Copy
        Edit
import java.sql.Time;
*/

import java.util.Arrays;

public class UnboundedKnapsack {

    public int knapsack(int[] val, int[] wt, int capacity) {
        int[] memo = new int[capacity + 1];
        Arrays.fill(memo, -1); // -1 means not yet computed
        return helper(val, wt, capacity, memo);
    }

    private int helper(int[] val, int[] wt, int capacity, int[] memo) {
        if (capacity == 0) return 0; // Base case
        if (memo[capacity] != -1) return memo[capacity];

        int maxProfit = 0;
        for (int i = 0; i < wt.length; i++) {
            if (wt[i] <= capacity) {
                int sub = helper(val, wt, capacity - wt[i], memo);
                if (sub != Integer.MIN_VALUE) {
                    maxProfit = Math.max(maxProfit, sub + val[i]);
                }
            }
        }
        memo[capacity] = maxProfit;
        return maxProfit;
    }

    public static void main(String[] args) {
        UnboundedKnapsack solver = new UnboundedKnapsack();

        int[] val1 = {1, 1}, wt1 = {2, 1};
        System.out.println(solver.knapsack(val1, wt1, 3)); // Output: 3

        int[] val2 = {6, 1, 7, 7}, wt2 = {1, 3, 4, 5};
        System.out.println(solver.knapsack(val2, wt2, 8)); // Output: 48

        int[] val3 = {6, 8, 7, 100}, wt3 = {2, 3, 4, 5};
        System.out.println(solver.knapsack(val3, wt3, 1)); // Output: 0
    }
}/*
📈 Time and Space Complexity:
Time: O(n * capacity) → Each subproblem is solved once.

        Space: O(capacity) for memoization + recursion stack.

        ✅ Notes:
This approach supports n up to 1000 and capacity up to 1000 as per constraints.

        Perfect for unbounded (infinite items) knapsack problems.*/