package practicesessions.aug_13;/*
# Problem
Given two positive integers n and x, return the number of ways n can be expressed as the sum of the x-th power of **unique** positive integers; i.e., count sets {a1, a2, ..., ak} with
    n = a1^x + a2^x + ... + ak^x
Return the count modulo 1_000_000_007.

# Intuition
- This is a 0/1 subset-sum over the multiset of all powers p = i^x (for i >= 1) such that p <= n.
- Each base i can be used **at most once** (uniqueness), so we use 0/1 knapsack DP:
  Let dp[s] = number of ways to reach sum s with some subset of powers.
  Initialize dp[0] = 1. For each power p, update s from n down to p:
      dp[s] = (dp[s] + dp[s - p]) % MOD
- The main pitfall: do **not** use floating Math.pow and roots to bound bases (precision issues like floor(n^(1/x)) can be off, e.g., n=64, x=3).
  Instead, generate powers safely with integer multiplication and early stop when > n.

# Dry Run (n=64, x=3)
Powers <= 64: 1^3=1, 2^3=8, 3^3=27, 4^3=64  → [1, 8, 27, 64]
dp[0]=1 initially.
- Using 1: dp[1]+=dp[0] → dp[1]=1
- Using 8: dp[8]+=dp[0]=1; dp[9]+=dp[1]=1; ... (standard 0/1 updates descending)
- Using 27: contributes sums with +27
- Using 64: dp[64]+=dp[0]=1
No other combination reaches 64 with unique bases, so dp[64]=1. ✅

# Code
*/
import java.util.*;

public class Solution6 {
    private static final int MOD = 1_000_000_007;

    public int numberOfWays(int n, int x) {
        // Build the list of powers p = i^x <= n using safe integer multiplication
        List<Integer> powers = new ArrayList<>();
        for (int base = 1; ; base++) {
            long p = powLimit(base, x, n);
            if (p > n) break;
            powers.add((int) p);
        }

        // 0/1 subset-sum DP: dp[s] = #ways to get sum s using unique powers
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int p : powers) {
            for (int s = n; s >= p; s--) {
                dp[s] += dp[s - p];
                if (dp[s] >= MOD) dp[s] -= MOD;
            }
        }
        return dp[n];
    }

    // Compute base^exp with early exit if it exceeds 'limit' (prevents overflow & saves time)
    private long powLimit(int base, int exp, int limit) {
        long res = 1;
        for (int i = 0; i < exp; i++) {
            res *= base;
            if (res > limit) return res; // early stop if beyond n
        }
        return res;
    }

    // --- Optional quick tests ---
    public static void main(String[] args) {
        Solution6 sol = new Solution6();
        System.out.println(sol.numberOfWays(10, 2)); // 1  -> 3^2 + 1^2
        System.out.println(sol.numberOfWays(4, 1));  // 2  -> 4 or 3+1
        System.out.println(sol.numberOfWays(64, 3)); // 1  -> 4^3 only
        System.out.println(sol.numberOfWays(160, 3)); // e.g., 2^3+3^3+5^3 is one way (answer > 0)
    }
}

/*
# Complexity
- Let m = floor(n^(1/x)) (number of candidate bases/powers).
- Building powers: O(m * x) (small constants; x ≤ 5).
- DP: O(n * m).
- Space: O(n).

# Notes
- Avoid Math.pow / fractional roots for bounds; doubles can underflow the integer root (e.g., floor(64^(1/3)) → 3).
- The 1D descending DP loop enforces uniqueness (0/1 choice per power).
*/
