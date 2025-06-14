package dynamic_programming.dp_onstrings;

/*🔁 Problem Recap:
Given txt and pat, count distinct subsequences of pat in txt.

✅ Recursion with Memoization Strategy
Let’s define the recursive function:

java
Copy
Edit
count(i, j)
i: current index in txt

j: current index in pat

It returns the number of ways to form pat[j:] from txt[i:]

🔁 Recurrence:
Base Case 1: If j == pat.length() → whole pattern matched ⇒ return 1

Base Case 2: If i == txt.length() but j < pat.length() ⇒ no more txt ⇒ return 0

Memoization Check: If dp[i][j] != -1, return cached result

Logic:
If txt[i] == pat[j], we can either:

Match it → count(i+1, j+1)

Skip it → count(i+1, j)

Else:

Only skip txt[i] → count(i+1, j)*/
public class DistinctSubsequences {

    public int countDistinctSubsequences(String txt, String pat) {
        int[][] dp = new int[txt.length()][pat.length()];

        // Initialize with -1 to indicate uncomputed states
        for (int i = 0; i < txt.length(); i++) {
            for (int j = 0; j < pat.length(); j++) {
                dp[i][j] = -1;
            }
        }

        return count(txt, pat, 0, 0, dp);
    }

    private int count(String txt, String pat, int i, int j, int[][] dp) {
        // Base case: entire pat matched
        if (j == pat.length()) return 1;

        // Base case: txt exhausted before pattern
        if (i == txt.length()) return 0;

        // Memo check
        if (dp[i][j] != -1) return dp[i][j];

        int ways = 0;
        if (txt.charAt(i) == pat.charAt(j)) {
            // Use txt[i] OR skip txt[i]
            ways = count(txt, pat, i + 1, j + 1, dp) + count(txt, pat, i + 1, j, dp);
        } else {
            // Only skip txt[i]
            ways = count(txt, pat, i + 1, j, dp);
        }

        dp[i][j] = ways;
        return ways;
    }

    public static void main(String[] args) {
        DistinctSubsequences solution = new DistinctSubsequences();

        System.out.println(solution.countDistinctSubsequences("abba", "aba"));    // Output: 2
        System.out.println(solution.countDistinctSubsequences("banana", "ban"));  // Output: 3
    }
}
/// *🧠 Time & Space:
/// Time: O(m × n)
///
/// Space: O(m × n) for memo + O(m + n) recursion stack*/


