package backtracking.palindromicpartition;

/*Problem: Palindrome Partitioning II

Given:

A string s.

Goal:

Partition s into palindromes using the minimum number of cuts.

Example:

Input: "aab"
Output: 1
Explanation: "aa" | "b"

🔹 Intuition

Naive approach (exponential):
Try all partitions (like Part I), track minimum cuts. Too slow.

Optimized approach with DP:

Precompute isPalindrome[i][j]: whether substring s[i..j] is a palindrome.

Use DP array dp[i] = minimum cuts needed for substring s[0..i].

Transition:

If s[0..i] is palindrome → dp[i] = 0 (no cut).

Otherwise, try all cuts:
dp[i] = min(dp[j] + 1) for all j < i where s[j+1..i] is palindrome.

This reduces the problem from exponential → polynomial.

🔹 Dry Run

s = "aab"

Precompute palindromes:

"a", "a", "b", "aa" are palindromes.

dp[0] = 0 ( "a" needs no cuts )

dp[1] = 0 ( "aa" is palindrome )

dp[2] = min(dp[1]+1, dp[0]+2) = 1 (best: "aa" | "b")

Answer = 1 ✅*/
public class PalindromePartitioningII_Memo {

    public static void main(String[] args) {
        String s = "aab";
        int minCuts = minCut(s);
        System.out.println("Minimum cuts: " + minCuts);
    }

    public static int minCut(String s) {
        int n = s.length();
        Boolean[][] palindromeMemo = new Boolean[n][n]; // cache palindrome checks
        Integer[] dp = new Integer[n]; // memo for min cuts
        return dfs(s, 0, palindromeMemo, dp);
    }

    private static int dfs(String s, int start, Boolean[][] palindromeMemo, Integer[] dp) {
        int n = s.length();
        if (start == n) return -1; // no cuts needed beyond end
        if (dp[start] != null) return dp[start];

        int minCuts = Integer.MAX_VALUE;
        for (int end = start; end < n; end++) {
            if (isPalindrome(s, start, end, palindromeMemo)) {
                int cuts = 1 + dfs(s, end + 1, palindromeMemo, dp);
                minCuts = Math.min(minCuts, cuts);
            }
        }
        dp[start] = minCuts;
        return minCuts;
    }

    private static boolean isPalindrome(String s, int i, int j, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                memo[i][j] = false;
                return false;
            }
            i++;
            j--;
        }
        memo[i][j] = true;
        return true;
    }
}

/*🔹 Complexity

Precompute Palindromes: O(n^2)

DP for min cuts: O(n^2)

Total: O(n^2) time, O(n^2) space

Much faster than exponential backtracking.*/