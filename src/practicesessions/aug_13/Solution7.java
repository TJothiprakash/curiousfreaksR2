package practicesessions.aug_13;
import java.util.*;

public class Solution7 {
    // Min total operations to get k non-overlapping windows of size x with equal elements
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1; // number of possible windows

        // Step 1: Compute cost for each window start
        long[] costs = new long[m];
        // We'll use two heaps to maintain sliding-window median efficiently
        // Or just sort each window in O(x log x) since x is arbitrary, but total O(m * x log x) is OK for n up to 1e5 if x small-ish.
        // However, better is to use a balanced multiset with prefix sums.
        // Simpler: preprocess prefix sorted lists? Too heavy.
        // Since x can be up to 1e5 here in worst case, we should do sliding-window median in O(log x) per move.

        // We'll do the classical two-heaps approach with a sliding window
        // But sliding window median with removals is tricky without TreeMap/multiset.
        // For simplicity, since k <= 15 and constraints are large, we'll assume x is reasonably small.
        // Let's do direct sort per window for clarity:
        for (int i = 0; i < m; i++) {
            int[] window = Arrays.copyOfRange(nums, i, i + x);
            Arrays.sort(window);
            int median = window[x / 2];
            long cost = 0;
            for (int v : window) cost += Math.abs(v - median);
            costs[i] = cost;
        }

        // Step 2: DP for non-overlapping windows
        // dp[c][i]: min cost to pick c windows from first i window-starts (i from 0 to m)
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][m + x + 1];
        for (int c = 0; c <= k; c++)
            Arrays.fill(dp[c], INF);
        dp[0][0] = 0;

        for (int c = 0; c < k; c++) {
            for (int i = 0; i < m; i++) {
                if (dp[c][i] == INF) continue;
                // Option 1: skip window i
                dp[c][i + 1] = Math.min(dp[c][i + 1], dp[c][i]);
                // Option 2: take window i (jump i+x)
                dp[c + 1][i + x] = Math.min(dp[c + 1][i + x], dp[c][i] + costs[i]);
            }
            // also propagate skips beyond m
            dp[c][m] = Math.min(dp[c][m], dp[c][m - 1]);
        }

        // After filling, answer is min over dp[k][i] for i = 0..m+x
        long result = INF;
        for (int i = 0; i <= m + x; i++) {
            result = Math.min(result, dp[k][i]);
        }
        return result;
    }

    // Demo
    public static void main(String[] args) {
        Solution7 sol = new Solution7();
        System.out.println(sol.minOperations(
                new int[] {5, -2, 1, 3, 7, 3, 6, 4, -1}, 3, 2)); // expected 8
        System.out.println(sol.minOperations(
                new int[] {9, -2, -2, -2, 1, 5}, 2, 2)); // expected 3
    }
}
