package trie;

/***************************************************************
 * Problem: Count Matching Prefixes of Length K
 *
 * Given an array of strings and a query string `str`, count
 * how many of the array's strings match `str` on the first `k`
 * characters (i.e., their prefix of length `k`).
 *
 * ────────────────────────────────────────────────────────────
 * Intuition:
 * ────────────────────────────────────────────────────────────
 * - If `k > str.length()`, then no valid match is possible.
 * - Otherwise, extract the first `k` characters of `str` and
 *   compare it with the first `k` characters of each string in
 *   the array.
 *
 * ────────────────────────────────────────────────────────────
 * Dry Run Example 1:
 * ────────────────────────────────────────────────────────────
 * arr = ["abba", "abbb", "abbc", "abbd", "abaa", "abca"]
 * str = "abbg", k = 3
 * prefix = "abb"
 * Match: "abba", "abbb", "abbc", "abbd" → total = 4
 *
 * ────────────────────────────────────────────────────────────
 * Code with Two Approaches:
 * 1. Brute-force String Matching
 * 2. Trie-based Prefix Matching (optional if needed)
 ****************************************************************/

public class KLengthPrefixMatcher {

    // Approach 1: Brute Force
    public static int klengthpref(String[] arr, int n, String str, int k) {
        if (k > str.length()) return 0;
        String targetPrefix = str.substring(0, k);

        int count = 0;
        for (String word : arr) {
            if (word.length() >= k && word.substring(0, k).equals(targetPrefix)) {
                count++;
            }
        }
        return count;
    }

    // Optional Approach 2: Trie (overhead, not required unless multiple queries)
    // For now, skipping trie as brute-force is sufficient.

    // Test the function with provided examples
    public static void main(String[] args) {
        String[] arr1 = {"abba", "abbb", "abbc", "abbd", "abaa", "abca"};
        String str1 = "abbg";
        int k1 = 3;
        System.out.println("Output: " + klengthpref(arr1, arr1.length, str1, k1)); // 4

        String[] arr2 = {"geeks", "geeksforgeeks", "forgeeks"};
        String str2 = "ge";
        int k2 = 5;
        System.out.println("Output: " + klengthpref(arr2, arr2.length, str2, k2)); // 0
    }
}

/*──────────────────────────────────────────────────────────────
  Complexity Analysis
  ─────────────────────────────────────────────────────────────
  Let:
    n = number of words
    l = average length of word in arr
    k = length of prefix to match (≤ 1000)

  Time Complexity:  O(n × k)
       - We compare up to k characters per string.

  Space Complexity: O(k) for targetPrefix string.
──────────────────────────────────────────────────────────────*/

