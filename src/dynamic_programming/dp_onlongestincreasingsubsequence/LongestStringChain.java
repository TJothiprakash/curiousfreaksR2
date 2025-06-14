package dynamic_programming.dp_onlongestincreasingsubsequence;

import java.util.*;

/*💡 Problem Summary
We are given an array of words, and we can go from wordA to wordB only if:

wordB is formed by adding exactly one letter to wordA, keeping the order.

We need to find the maximum length of such a chain.

📌 Example:
Input:

text
Copy
Edit
["a","b","ba","bca","bda","bdca"]
Valid chain: "a" -> "ba" -> "bda" -> "bdca" → Length = 4 ✅

✅ Strategy
Step 1: Sort words by length
We build chains from short to long words.

Step 2: For each word, try removing one letter at every position and
check if the resulting word is in the list. If yes, it can be a predecessor.*/
public class LongestStringChain {
    static Set<String> wordSet;
    static Map<String, Integer> memo;

    public static int longestStrChain(String[] words) {
        wordSet = new HashSet<>(Arrays.asList(words));
        memo = new HashMap<>();
        int maxLen = 0;

        for (String word : words) {
            maxLen = Math.max(maxLen, dfs(word));
        }

        return maxLen;
    }

    private static int dfs(String word) {
        if (memo.containsKey(word)) return memo.get(word);

        int maxLength = 1; // At least the word itself
        for (int i = 0; i < word.length(); i++) {
            String prev = word.substring(0, i) + word.substring(i + 1); // Remove one char
            if (wordSet.contains(prev)) {
                maxLength = Math.max(maxLength, 1 + dfs(prev));
            }
        }

        memo.put(word, maxLength);
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"})); // 4
        System.out.println(longestStrChain(new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"})); // 5
        System.out.println(longestStrChain(new String[]{"abcd", "dbqca"})); // 1
    }
}
