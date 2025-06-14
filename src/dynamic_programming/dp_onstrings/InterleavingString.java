package dynamic_programming.dp_onstrings;
/*Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
Interleaving of two strings s1 and s2 is a way to mix their characters to form a new string s3, while maintaining the relative order of characters from s1 and s2. Conditions for interleaving:

Characters from s1 must appear in the same order in s3 as they are in s1.
Characters from s2 must appear in the same order in s3 as they are in s2.
The length of s3 must be equal to the combined length of s1 and s2.
Examples :

Input: s1 = "AAB", s2 = "AAC", s3 = "AAAABC",
Output: true
Explanation: The string "AAAABC" has all characters of the other two strings and in the same order.
Input: s1 = "AB", s2 = "C", s3 = "ACB",
Output: true
Explanation: s3 has all characters of s1 and s2 and retains order of characters of s1.
Input: s1 = "YX", s2 = "X", s3 = "XXY"
Output: false
Explanation: "XXY " is not interleaved of "YX" and "X". The strings that can be formed are YXX and XYX
Constraints:
1 ≤ s1.length, s2.length ≤ 300
1 ≤ s3.length ≤ 600

*//*
✅ Problem Summary
        You're given three strings s1, s2, s3. Determine if s3 is a valid interleaving of s1 and s2.

        Conditions:
        Order of characters in s1 and s2 must be preserved in s3.

        All characters of s1 and s2 must be used.

        Length condition: s3.length() == s1.length() + s2.length()

        🧠 Recursive Idea
        Define:

        java
        Copy
        Edit
        boolean isInterleave(i, j)
        Where:

        i is current index in s1

        j is current index in s2

        k = i + j is current index in s3

        At each step:
        Try to match s1[i] with s3[k] → recurse on (i + 1, j)

        Try to match s2[j] with s3[k] → recurse on (i, j + 1)

        Use memoization to avoid recomputation.

        ✅ Java Code (Recursion + Memo)
        java
        Copy
        Edit*/

public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        Boolean[][] dp = new Boolean[s1.length() + 1][s2.length() + 1];
        return helper(s1, s2, s3, 0, 0, dp);
    }

    private boolean helper(String s1, String s2, String s3, int i, int j, Boolean[][] dp) {
        if (i + j == s3.length()) return true;
        if (dp[i][j] != null) return dp[i][j];

        boolean fromS1 = false, fromS2 = false;

        if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j)) {
            fromS1 = helper(s1, s2, s3, i + 1, j, dp);
        }

        if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j)) {
            fromS2 = helper(s1, s2, s3, i, j + 1, dp);
        }

        return dp[i][j] = fromS1 || fromS2;
    }

    public static void main(String[] args) {
        InterleavingString solver = new InterleavingString();

        System.out.println(solver.isInterleave("AAB", "AAC", "AAAABC")); // true
        System.out.println(solver.isInterleave("AB", "C", "ACB"));       // true
        System.out.println(solver.isInterleave("YX", "X", "XXY"));       // false
    }
}/*
🔍 Example Breakdown: s1 = "AB", s2 = "C", s3 = "ACB"
Start at (0, 0): s3[0] == s1[0] → move to (1, 0)

At (1, 0): s3[1] == s2[0] → move to (1, 1)

At (1, 1): s3[2] == s1[1] → move to (2, 1)

Done. All characters used. ✅

        ⏱ Time and Space Complexity
        Let m = s1.length(), n = s2.length()

Time: O(m * n) due to memoization

Space: O(m * n) for dp table + recursion stack*/