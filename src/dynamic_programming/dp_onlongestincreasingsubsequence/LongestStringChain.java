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


/*
    🔹 Problem: Longest String Chain

    You are given a list of words. A word A is a predecessor of word B
    if inserting exactly one character anywhere in A gives B
    (without changing the order of existing characters).

    A word chain is a sequence where each word is a predecessor of the next.

    ❓ Return the length of the longest possible word chain.

    🔸 Example 1:
        Input: ["a", "b", "ba", "bca", "bda", "bdca"]
        Output: 4
        Explanation: "a" → "ba" → "bda" → "bdca"

    🔸 Example 2:
        Input: ["abcd", "dbqca"]
        Output: 1

    ✅ Intuition:
        - Sort words by length.
        - Use DP (HashMap) to store the longest chain ending at each word.
        - For each word, try deleting one character at every position.
        - If the resulting string is in DP → it's a valid predecessor.
        - Update the chain length accordingly.

    ✅ Time Complexity: O(N * L^2), where N = number of words, L = max word length (≤ 16)
    ✅ Space Complexity: O(N) for the DP map
*/

 class LongestStringChain1 {
    public static void main(String[] args) {
        String[] words1 = {"a", "b", "ba", "bca", "bda", "bdca"};
        String[] words2 = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        String[] words3 = {"abcd", "dbqca"};

        System.out.println("Longest chain length: " + longestStrChain(words1)); // 4
        System.out.println("Longest chain length: " + longestStrChain(words2)); // 5
        System.out.println("Longest chain length: " + longestStrChain(words3)); // 1
    }

    public static int longestStrChain(String[] words) {
        // Step 1: Sort words by length (short to long)
        Arrays.sort(words, Comparator.comparingInt(String::length));

        // DP map: word -> longest chain ending at this word
        Map<String, Integer> dp = new HashMap<>();
        int maxChain = 1;

        for (String word : words) {
            int best = 1;
            System.out.println("\n🟡 Processing word: " + word);

            for (int i = 0; i < word.length(); i++) {
                // Remove one character at position i
                String prev = word.substring(0, i) + word.substring(i + 1);
                if (dp.containsKey(prev)) {
                    int candidate = dp.get(prev) + 1;
                    System.out.println("  🔹 Found predecessor: " + prev + " -> chain: " + candidate);
                    best = Math.max(best, candidate);
                }
            }

            dp.put(word, best);
            System.out.println("  ✅ Final chain length ending at '" + word + "': " + best);
            maxChain = Math.max(maxChain, best);
        }

        System.out.println("\n🧠 DP Map: " + dp);
        return maxChain;
    }
}
