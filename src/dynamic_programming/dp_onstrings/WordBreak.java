package dynamic_programming.dp_onstrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    // Memoization cache
    private static HashMap<Integer, Boolean> memo = new HashMap<>();

    public static int wordBreak(String s, HashSet<String> dictionary) {
        return wordBreakHelper(s, dictionary, 0) ? 1 : 0;
    }

    private static boolean wordBreakHelper(String s, HashSet<String> dictionary, int start) {
        // Base case: if we've reached the end of the string, return true
        if (start == s.length()) {
            return true;
        }

        // Check memoized results to avoid redundant calculations
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        // Try all possible substrings starting from `start`
        for (int end = start + 1; end <= s.length(); end++) {
            String currentSubstring = s.substring(start, end);
            // If the substring exists in the dictionary and the rest of the string can be segmented
            if (dictionary.contains(currentSubstring) && wordBreakHelper(s, dictionary, end)) {
                memo.put(start, true);
                return true;
            }
        }

        // If no valid segmentation found, memoize and return false
        memo.put(start, false);
        return false;
    }

    public static void main(String[] args) {
        // Example 1
        HashSet<String> dictionary1 = new HashSet<>();
        dictionary1.add("i");
        dictionary1.add("like");
        dictionary1.add("sam");
        dictionary1.add("sung");
        dictionary1.add("samsung");
        dictionary1.add("mobile");

        System.out.println(wordBreak("ilike", dictionary1)); // Output: 1

        // Example 2
        HashSet<String> dictionary2 = new HashSet<>();
        dictionary2.add("i");
        dictionary2.add("like");
        dictionary2.add("sam");
        dictionary2.add("sung");
        dictionary2.add("samsung");
        dictionary2.add("mobile");

        System.out.println(wordBreak("ilikesamsung", dictionary2)); // Output: 1
    }
}
/*You are given a string s and a list dictionary[] of words. Your task is to determine whether the string s can be formed by concatenating one or more words from the dictionary[].

Note: From dictionary[], any word can be taken any number of times and in any order.

Examples :

Input: s = "ilike", dictionary[] = ["i", "like", "gfg"]
Output: true
Explanation: s can be breakdown as "i like".
Input: s = "ilikegfg", dictionary[] = ["i", "like", "man", "india", "gfg"]
Output: true
Explanation: s can be breakdown as "i like gfg".
Input: s = "ilikemangoes", dictionary[] = ["i", "like", "man", "india", "gfg"]
Output: false
Explanation: s cannot be formed using dictionary[] words.
Constraints:
1 ≤ s.size() ≤ 3000
1 ≤ dictionary.size() ≤ 1000
1 ≤ dictionary[i].size() ≤ 100

Expected Complexities
*/
/*

✅ Expected Approach (with your preferred style)
We’ll use Recursion + Memoization.

🔁 Recursion Definition
Define a function:

java
        Copy
Edit
boolean canBreakFrom(int i)
i is the starting index in string s

It returns true if we can break s[i:] using the dictionary

🧠 Recurrence:
At each index i:

Try each word in the dictionary

If s.startsWith(word, i):

Recursively check canBreakFrom(i + word.length())

If any recursive call returns true, we memoize and return true

        ✅ Java Code (Recursion + Memoization)
java
        Copy
Edit
*/

class WordBreakWithRecursion {

    public boolean wordBreak(String s, List<String> dictionary) {
        Set<String> wordSet = new HashSet<>(dictionary); // O(1) lookups
        Boolean[] dp = new Boolean[s.length()];
        return canBreak(s, 0, wordSet, dp);
    }

    private boolean canBreak(String s, int i, Set<String> wordSet, Boolean[] dp) {
        if (i == s.length()) return true;
        if (dp[i] != null) return dp[i];

        for (String word : wordSet) {
            if (i + word.length() <= s.length() && s.startsWith(word, i)) {
                if (canBreak(s, i + word.length(), wordSet, dp)) {
                    dp[i] = true;
                    return true;
                }
            }
        }

        dp[i] = false;
        return false;
    }

    public static void main(String[] args) {
        WordBreakWithRecursion solution = new WordBreakWithRecursion();

        System.out.println(solution.wordBreak("ilike", List.of("i", "like", "gfg"))); // true
        System.out.println(solution.wordBreak("ilikegfg", List.of("i", "like", "man", "india", "gfg"))); // true
        System.out.println(solution.wordBreak("ilikemangoes", List.of("i", "like", "man", "india", "gfg"))); // false
    }
}/*
⏱ Time & Space Complexity:
Let:

n = s.length()

k = max word length in dictionary

        d = number of dictionary words

Time:
O(n × k × d) worst-case (but much faster in practice with pruning via memo)

Space:
O(n) for memo array

O(d × avg_word_len) for dictionary set

*/