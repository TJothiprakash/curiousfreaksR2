package dynamic_programming.dp_onstrings;

/*

Given a string s and a dictionary dict[] of valid words,
you need to return all possible ways to break the string s into a sentence
such that each word in the sentence is a valid dictionary word.
You are allowed to use a valid word multiple times in the sentence.

        Examples:

Input: s = "likegfg", dict[] = ["lik", "like", "egfg", "gfg"]
Output:
        "lik egfg"
        "like gfg"
Explanation: All the words in the given sentences are present in the dictionary.
Input: s = "geeksforgeeks", dict[] = ["for", "geeks"]
Output: "geeks for geeks"
Explanation: The string "geeksforgeeks" can be broken into valid words from the
 dictionary in one way.
Constraints:
        1 ≤ dict.size() ≤ 20
        1 ≤ dict[i] ≤ 15
        1 ≤ s.size() ≤ 500

*/
/*
✅ Preferred Style: Recursion + Memoization
        We'll use:

        Recursion to explore all combinations

        Memoization to avoid re-computing results for the same index

        🧠 Recursive Function
        java
        Copy
        Edit
        List<String> breakFrom(int start)
        Returns all valid sentences that can be formed from s.substring(start)

        At each index, try every word in the dictionary:

        If s.startsWith(word, start), recurse from start + word.length()

        ✅ Java Code (Recursive with Memoization)
        java
        Copy
        Edit
import org.junit.platform.commons.function.Try;

import java.sql.Time;
import java.util.*;*/

import java.util.*;

public class WordBreakAll {

    public List<String> wordBreak(String s, List<String> dict) {
        Set<String> wordSet = new HashSet<>(dict); // Faster lookup
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, wordSet, memo);
    }

    private List<String> dfs(String s, int start, Set<String> wordSet, Map<Integer,
            List<String>> memo) {
        if (memo.containsKey(start)) return memo.get(start);
        List<String> result = new ArrayList<>();

        if (start == s.length()) {
            result.add(""); // Add empty sentence to build up
            return result;
        }

        for (String word : wordSet) {
            if (s.startsWith(word, start)) {
                List<String> subSentences = dfs(s, start + word.length(), wordSet, memo);
                for (String sub : subSentences) {
                    String space = sub.isEmpty() ? "" : " ";
                    result.add(word + space + sub);
                }
            }
        }

        memo.put(start, result);
        return result;
    }

    public static void main(String[] args) {
        WordBreakAll solution = new WordBreakAll();

        System.out.println(solution.wordBreak("likegfg", List.of("lik", "like", "egfg",
                "gfg")));
        // Output: ["lik egfg", "like gfg"]

        System.out.println(solution.wordBreak("geeksforgeeks", List.of("for", "geeks")));
        // Output: ["geeks for geeks"]
    }
}/*
🧠 How It Works
Start at index 0

Try all matching words at that position

Recurse forward and build sentences

Memoization avoids recomputing the same suffix

⏱ Time and Space Complexity
Let:

n = s.length()

d = dict.size()

k = max word length in dict

Time: O(n × d × k) in practice due to pruning with memoization
Space:
O(n) memo map entries

O(#sentences × avg_sentence_length) for output


*/

