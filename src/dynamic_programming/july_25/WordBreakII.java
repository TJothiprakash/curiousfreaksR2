package dynamic_programming.july_25;
/*

📘 Problem Statement:
        Given a string s and a list of strings wordDict, return all possible
        sentences where the words form a valid segmentation of s. You may return the answer in any order.

        🧪 Example:
        text
        Copy
        Edit
        Input:
        s = "catsanddog"
        wordDict = ["cat", "cats", "and", "sand", "dog"]

        Output:
        [
        "cats and dog",
        "cat sand dog"
        ]
        🧠 Intuition
        This is an extension of Word Break I, but instead of
        returning a boolean, we return all valid combinations of words.

        So at each index, instead of asking “can I break?”, we ask “what are all
         the sentences I can form from here?”

        🗺️ Thought Process
        Use top-down recursion + memoization.

        At each index, try all valid prefixes.

        If prefix is in dict → recursively solve for remaining.

        Combine the current word with the result of the remaining.

        🔍 Dry Run
        For s = "catsanddog":

        Try: "cat" → is in dict → check "sanddog"

        "sand" → in dict → check "dog"

        "dog" → in dict → ✅ → "cat sand dog"

        Try: "cats" → is in dict → check "anddog"

        "and" → in dict → check "dog" → ✅ → "cats and dog"

        ✅ Both sentences are valid.
*/

import java.util.*;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(0, s, dict, memo);
    }

    private List<String> dfs(int start, String s, Set<String> dict, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) return memo.get(start);
        List<String> results = new ArrayList<>();

        if (start == s.length()) {
            results.add("");  // base case: end of string
            return results;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);
            if (dict.contains(prefix)) {
                List<String> suffixWays = dfs(end, s, dict, memo);
                for (String suffix : suffixWays) {
                    String sentence = prefix + (suffix.isEmpty() ? "" : " " + suffix);
                    results.add(sentence);
                }
            }
        }

        memo.put(start, results);
        return results;
    }

    // Driver
    public static void main(String[] args) {
        WordBreakII wb = new WordBreakII();
        String s = "catsanddog";
        List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> res = wb.wordBreak(s, dict);
        System.out.println("All sentences: ");
        for (String str : res) {
            System.out.println(str);
        }
    }
}/*
📊 Complexity Analysis
Time Complexity: O(n^2 * k)
where n is the length of s, and k is the number of valid combinations.

Space Complexity: O(n * k) for memo + result list.

*/