package dynamic_programming.dp_onstrings;

/*minOps(3,3): 'c' vs 'c' => match ✅
|
└── minOps(2,2): 'b' vs 'd' => mismatch ❌
     |
     ├── Insert  → minOps(2,1): 'b' vs 'c' => mismatch ❌
     |     |
     |     ├── Insert  → minOps(2,0): 'b' vs ''  => return 2 (base case)
     |     ├── Remove  → minOps(1,1): 'a' vs 'a' => match ✅
     |     |     └── minOps(0,0): '' vs ''      => return 0
     |     └── Replace → minOps(1,0): 'a' vs ''  => return 1 (base case)
     |
     ├── Remove → minOps(1,2): 'a' vs 'd' => mismatch ❌
     |     |
     |     ├── Insert  → minOps(1,1): 'a' vs 'a' => match ✅
     |     |     └── minOps(0,0): '' vs ''      => return 0
     |     ├── Remove  → minOps(0,2): '' vs 'd'  => return 2 (base case)
     |     └── Replace → minOps(0,1): '' vs 'a'  => return 1 (base case)
     |
     └── Replace → minOps(1,1): 'a' vs 'a' => match ✅
           └── minOps(0,0): '' vs '' => return 0


           minOps(3,3): 'c' vs 'c' → match ✅
→ minOps(2,2): 'b' vs 'd' → mismatch ❌
    → try all 3 options:
    ┌──────────────┬──────────────┬──────────────┐
    │ Insert       │ Remove       │ Replace      │
    │ minOps(2,1)  │ minOps(1,2)  │ minOps(1,1)  │
    │ 'b' vs 'c'   │ 'a' vs 'd'   │ 'a' vs 'a'   │
    └──────┬───────┘ └──────┬──────┘ └──────┬──────┘
           │                │                │
           │                │                └─ minOps(0,0) → 0
           │                │                   → return 0
           │                │                   → Replace cost = 1 + 0 = 1
           │                │
           │                ├─ minOps(1,1): 'a' vs 'a' → match ✅
           │                │     └─ minOps(0,0) → 0
           │                │     → return 0
           │                ├─ minOps(0,2): '' vs 'd' → base → return 2
           │                ├─ minOps(0,1): '' vs 'a' → base → return 1
           │                → Remove cost = min(0,2,1) + 1 = 1 + 0 = 1
           │
           ├─ minOps(2,0): 'b' vs '' → base → return 2
           ├─ minOps(1,1): 'a' vs 'a' → match ✅
           │     └─ minOps(0,0) → 0
           │     → return 0
           ├─ minOps(1,0): 'a' vs '' → base → return 1
           → Insert cost = min(2,0,1) + 1 = 1
           → All options give cost 1
→ So minOps(2,2) = 1 + min(1,1,1) = 1 + 1 = 2

Final:
minOps(3,3) = (match) → return minOps(2,2) = 2
→ So final result = 2

*/
public class MinEditDistance {

    public static void main(String[] args) {
        MinEditDistance solution = new MinEditDistance();

        System.out.println(solution.minOperations("geek", "gesek")); // Output: 1
        System.out.println(solution.minOperations("gfg", "gfg"));   // Output: 0
        System.out.println(solution.minOperations("abc", "def"));   // Output: 3
    }

    public int minOperations(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // DP table for memoization
        int[][] dp = new int[m + 1][n + 1];

        // Initialize the DP array with -1 (indicating uncomputed states)
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        // Start the recursive function with memoization
        return minOperationsRec(s1, s2, m, n, dp);
    }

    // Helper recursive function with memoization
    private int minOperationsRec(String s1, String s2, int m, int n, int[][] dp) {
        // Base cases
        if (m == 0) return n; // If s1 is empty, insert all characters of s2
        if (n == 0) return m; // If s2 is empty, remove all characters of s1

        // Check if the result for this subproblem is already computed
        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        // If characters match, no operation is needed
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            dp[m][n] = minOperationsRec(s1, s2, m - 1, n - 1, dp);
        } else {
            // If characters don't match, we consider all three operations
            int insertOp = minOperationsRec(s1, s2, m, n - 1, dp);  // Insert character from s2
            int removeOp = minOperationsRec(s1, s2, m - 1, n, dp);  // Remove character from s1
            int replaceOp = minOperationsRec(s1, s2, m - 1, n - 1, dp);  // Replace character in s1 with s2

            // Take the minimum of the three operations
            dp[m][n] = 1 + Math.min(insertOp, Math.min(removeOp, replaceOp));
        }

        return dp[m][n];
    }
}
