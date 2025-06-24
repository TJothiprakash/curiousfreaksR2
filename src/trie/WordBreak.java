package trie;

import java.util.List;

/**
 * ❓ Problem:
 * Given a string A and a dictionary B of n words, determine if A can be segmented
 * into a space-separated sequence of one or more dictionary words.
 * <p>
 * 🔸 Example 1:
 * Input: A = "ilike", B = ["i", "like", "sam", "sung", ...]
 * Output: 1 (because "i like" is valid)
 * <p>
 * 🔸 Example 2:
 * Input: A = "ilikesamsung", B = ["i", "like", "sam", "sung", "samsung", ...]
 * Output: 1 (because "i like samsung" or "i like sam sung")
 */

/**
 * 💡 Intuition:
 * - We are checking whether a string A can be split into words that exist in the dictionary B.
 * - Insert all words of B into a Trie for O(1) prefix lookup.
 * - Use dynamic programming:
 *   - dp[i] = true if A[0..i-1] can be segmented.
 *   - For each index i, check all j < i. If dp[j] is true and A[j..i-1] exists in Trie,
 *     then set dp[i] = true.
 */


public class WordBreak {
    public int wordBreak(int n, List<String> B, String A) {
        // Step 1: Build Trie from dictionary
        Trie trie = new Trie();
        for (String word : B) {
            trie.insert(word);
        }

        // Step 2: Create DP array
        int len = A.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true; // Empty string is segmentable

        // Step 3: Fill DP table
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && trie.search(A, j, i)) {
                    dp[i] = true;
                    break; // No need to check further if true
                }
            }
        }

        return dp[len] ? 1 : 0;
    }

    static class TrieNode {
        trie.TrieNode[] children = new trie.TrieNode[26];
        boolean isEnd = false;
    }

    static class Trie {
        trie.TrieNode root = new trie.TrieNode();

        // Insert a word into the Trie
        public void insert(String word) {
            trie.TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new trie.TrieNode();
                }
                curr = curr.children[idx];
            }
            curr.isEnd = true;
        }

        // Search if s[start..end) is a valid word in Trie
        public boolean search(String s, int start, int end) {
            trie.TrieNode curr = root;
            for (int i = start; i < end; i++) {
                int idx = s.charAt(i) - 'a';
                if (curr.children[idx] == null) return false;
                curr = curr.children[idx];
            }
            return curr.isEnd;
        }
    }

}

/**
 * ⏱️ Time Complexity:
 * - Insert dictionary into Trie: O(n * l), where l is average word length
 * - DP: O(|A|^2 * l) in worst-case due to substring checks and Trie traversal
 * - Total: O(n * l + |A|^2 * l)
 *
 * 📦 Space Complexity:
 * - Trie: O(n * l)
 * - DP array: O(|A|)
 * - Total: O(n * l + |A|)
 */
