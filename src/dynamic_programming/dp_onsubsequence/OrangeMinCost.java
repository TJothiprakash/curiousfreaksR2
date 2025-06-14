package dynamic_programming.dp_onsubsequence;
/*
✅ Problem Breakdown:
        You’re given an array cost[] (1-based) of size n.

        You can use any number of available packets (infinite supply).

        cost[i] = -1 means packet of size i+1 is unavailable.

        Goal: Get exactly w kg of oranges with minimum cost, or return -1 if impossible.

        🔁 Recursive Formula:
        Let f(w) = minimum cost to buy exactly w kg.

        Then:

        pgsql
        Copy
        Edit
        f(w) = min(f(w - i) + cost[i - 1]) for all i from 1 to n, where cost[i - 1] != -1 and w - i >= 0
        Use memoization to cache f(w) to avoid recomputation.

        ✅ Java Code (Recursion with Memoization):
        java
        Copy
        Edit
import java.sql.Time;
*/

import java.util.Arrays;

public class OrangeMinCost {

    public int minimumCost(int n, int w, int[] cost) {
        int[] memo = new int[w + 1];
        Arrays.fill(memo, -2); // -2 means not yet computed

        int result = helper(w, cost, memo, n);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int helper(int w, int[] cost, int[] memo, int n) {
        if (w == 0) return 0; // Base case: 0 cost for 0 kg
        if (w < 0) return Integer.MAX_VALUE; // Not possible

        if (memo[w] != -2) return memo[w]; // Already computed

        int minCost = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            if (cost[i - 1] != -1 && w - i >= 0) {
                int subCost = helper(w - i, cost, memo, n);
                if (subCost != Integer.MAX_VALUE) {
                    minCost = Math.min(minCost, subCost + cost[i - 1]);
                }
            }
        }

        memo[w] = minCost;
        return memo[w];
    }

    // Example usage
    public static void main(String[] args) {
        OrangeMinCost solver = new OrangeMinCost();
        int[] cost1 = {20, 10, 4, 50, 100};
        System.out.println(solver.minimumCost(5, 5, cost1)); // Output: 14

        int[] cost2 = {-1, -1, 4, 3, -1};
        System.out.println(solver.minimumCost(5, 5, cost2)); // Output: -1
    }
}/*
📌 Notes:
memo[w] = -2 is used to detect if a state has been computed.

Integer.MAX_VALUE is used to represent impossible states (like w = -1).

Time Complexity: O(n * w)

Space Complexity: O(w) for memoization array + recursion stack.

*/