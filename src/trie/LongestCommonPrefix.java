package trie;
/******************************************************************************
 *  Problem: Longest Common Prefix
 *
 *  Write a function that returns the longest common prefix (LCP) among an
 *  array of lowercase strings. If there is no common prefix, return "".
 *
 *  Constraints:
 *      • 1 ≤ strs.length ≤ 200
 *      • 0 ≤ strs[i].length ≤ 200
 *      • strs[i] contains only lowercase English letters if non‑empty
 *
 *  --------------------------------------------------------------------------
 *  Intuition
 *  --------------------------------------------------------------------------
 *  ❶  The *shortest* word bounds the maximum possible prefix length.
 *  ❷  A simple and robust way is **vertical scanning**:
 *      • Compare characters column‑by‑column across all strings.
 *      • Stop at the first mismatch or at the end of any word.
 *  ❸  An alternative O(n log m) trick is to sort the array and only compare
 *      the first and last strings—they are the most different lexicographically.
 *      The common prefix of those two is the LCP of the whole set.
 *
 *  Here we implement the **vertical scan** (clear and O(n·m) in worst case),
 *  plus an optional optimized variant using sort.
 *
 *  --------------------------------------------------------------------------
 *  Dry‑Run (Vertical Scan) – strs = ["flower","flow","flight"]
 *  --------------------------------------------------------------------------
 *      index 0: 'f' == 'f' == 'f' → keep scanning
 *      index 1: 'l' == 'l' == 'l' → keep scanning
 *      index 2: 'o' vs 'i' (mismatch) → stop. LCP = "fl"
 *
 *  --------------------------------------------------------------------------
 *  Code
 *  --------------------------------------------------------------------------
 */

import java.util.Arrays;

public class LongestCommonPrefix {

    /**
     * Returns the longest common prefix using vertical scan (O(N·M)).
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int col = 0; col < strs[0].length(); col++) {
            char ch = strs[0].charAt(col);
            for (int row = 1; row < strs.length; row++) {
                // End of word OR mismatch → return prefix up to col
                if (col == strs[row].length() || strs[row].charAt(col) != ch) {
                    return strs[0].substring(0, col);
                }
            }
        }
        // Entire first string is a prefix
        return strs[0];
    }

    /**
     * Optional O(N log N + M) version: compare min & max after sorting.
     */
    public static String longestCommonPrefixSorted(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs);
        String first = strs[0], last = strs[strs.length - 1];
        int i = 0, maxLen = Math.min(first.length(), last.length());
        while (i < maxLen && first.charAt(i) == last.charAt(i)) i++;
        return first.substring(0, i);
    }

    /* ----------------------- Demo Driver ----------------------- */
    public static void main(String[] args) {
        String[][] tests = {
                {"flower", "flow", "flight"},
                {"dog", "racecar", "car"},
                {"", "", ""},
                {"interview", "internet", "internal"},
                {"a"}
        };

        for (String[] t : tests) {
            System.out.println("Input  : " + Arrays.toString(t));
            System.out.println("Output : \"" + longestCommonPrefix(t) + "\"\n");
        }
    }
}

/* --------------------------------------------------------------------------
 *  Complexity
 *  --------------------------------------------------------------------------
 *  • Vertical Scan
 *        Time   : O(N · M)
 *                 N = number of strings, M = length of shortest string
 *        Space  : O(1)  (no auxiliary structures apart from loop vars)
 *
 *  • Sorted Trick
 *        Time   : O(N log N + M)   (dominated by Arrays.sort)
 *        Space  : O(1) (in‑place sort for arrays of references)
 *
 *  Both meet the problem’s constraints comfortably (N, M ≤ 200).
 * ------------------------------------------------------------------------ */
