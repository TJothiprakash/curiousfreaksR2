package practicesessions.aug_13;
/*
 * Problem: Make Costs of Paths Equal in a Perfect Binary Tree
 * -----------------------------------------------------------
 * You are given n nodes (1..n) forming a perfect binary tree where:
 *   - root is 1
 *   - left child of i is 2*i, right child is 2*i+1
 * Each node i has cost[i-1]. You may increment any node's cost by 1 any number of times.
 * Return the minimum number of increments to make the sum of costs along every root-to-leaf path equal.
 *
 * -----------------------------------------------------------
 * Intuition (Why no simple global formula works):
 * - Paths overlap: raising an ancestor adds to many paths at once. The true minimum depends on
 *   how imbalances are distributed across depths, not just total sums.
 * - The correct approach is to equalize *sibling subtrees bottom-up*.
 *   At each internal node:
 *     let L = max path sum of left subtree (after balancing it),
 *         R = max path sum of right subtree (after balancing it).
 *     We must add |L - R| increments to the smaller side to match the bigger side.
 *     The node's contribution upward becomes cost[i] + max(L, R).
 * - The total answer is the sum of |L - R| over all internal nodes.
 *
 * -----------------------------------------------------------
 * Step-by-step Plan (Iterative bottom-up DP on indices 1..n):
 * 1) Create dp[1..n] where dp[i] will store the (balanced) max path sum from node i to any leaf
 *    within its subtree (including its own cost).
 * 2) Initialize leaves: for i in (n/2 + 1 .. n), dp[i] = cost[i-1].
 * 3) Process internal nodes in reverse index order: for i = n/2 down to 1:
 *      L = dp[2*i], R = dp[2*i + 1]
 *      answer += |L - R|          // increments to balance this split
 *      dp[i] = cost[i-1] + max(L, R)
 * 4) Return answer.
 *
 * -----------------------------------------------------------
 * Dry Run (Example 1):
 * n = 7, cost = [1,5,2,2,3,3,1]
 * Leaves: i=4..7 → dp[4]=2, dp[5]=3, dp[6]=3, dp[7]=1
 * i=3: L=dp[6]=3, R=dp[7]=1 → add |3-1|=2, dp[3]=cost[2]+max(3,1)=2+3=5
 * i=2: L=dp[4]=2, R=dp[5]=3 → add |2-3|=1, dp[2]=cost[1]+max(2,3)=5+3=8
 * i=1: L=dp[2]=8, R=dp[3]=5 → add |8-5|=3, dp[1]=cost[0]+max(8,5)=1+8=9
 * Total increments = 2 + 1 + 3 = 6  ✅
 *
 * -----------------------------------------------------------
 * Complexity:
 * - Time:  O(n) — each node processed once.
 * - Space: O(n) for the dp array (or O(h) with a recursive DFS alternative).
 */

import java.util.*;

public class EqualizePathCosts {

    // Main API as on LeetCode (n, cost[]) -> minimum increments
    public int minIncrements(int n, int[] cost) {
        long[] dp = new long[n + 2]; // 1-based indexing; +2 guards 2*i+1 when i=n/2
        long answer = 0;

        // 1) Leaves
        for (int i = n / 2 + 1; i <= n; i++) {
            dp[i] = cost[i - 1];
        }

        // 2) Internal nodes bottom-up
        for (int i = n / 2; i >= 1; i--) {
            long left = dp[2 * i];
            long right = dp[2 * i + 1];
            answer += Math.abs(left - right);
            dp[i] = cost[i - 1] + Math.max(left, right);
        }

        return (int) answer; // fits in int under constraints; using long internally for safety
    }

    // ----- Simple demo -----
    public static void main(String[] args) {
        EqualizePathCosts solver = new EqualizePathCosts();

        int n1 = 7;
        int[] cost1 = {1, 5, 2, 2, 3, 3, 1};
        System.out.println(solver.minIncrements(n1, cost1)); // Expected: 6

        int n2 = 3;
        int[] cost2 = {5, 3, 3};
        System.out.println(solver.minIncrements(n2, cost2)); // Expected: 0
    }
}

/*
 * Notes:
 * - If you prefer recursion, a DFS(i) that returns the same dp[i] and accumulates |L - R| in a field
 *   produces identical results with O(h) extra stack space.
 */
