package dynamic_programming.july_25;

import java.util.*;


/*✅ Problem: Word Break I
Statement:
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Example:

text
Copy
Edit
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
🧠 Intuition
At any index i in s, we can try all prefixes s[i:j] and check:

If that prefix is in the dictionary

If the rest of the string s[j:] can also be broken

We can use Top-down Dynamic Programming (with Memoization) to cache the results for each starting index and avoid recomputation.

🔍 Dry Run
Let's say:

java
Copy
Edit
s = "applepenapple"
dict = ["apple", "pen"]
We check:

"apple" ✅ in dict → check "penapple"

"pen" ✅ in dict → check "apple"

"apple" ✅ → rest is "" → ✅

So, result is true.

✅ Approach
Use a HashSet for quick dictionary lookup.

Use a recursive function canBreak(start) to check if we can break from index start to end.

Memoize the result of each start.
*/
public class WordBreakTopDown {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, Boolean> memo = new HashMap<>();
        return canBreak(0, s, dict, memo);
    }

    private boolean canBreak(int start, String s, Set<String> dict, Map<Integer, Boolean> memo) {
        if (start == s.length()) return true;

        if (memo.containsKey(start)) return memo.get(start);

        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);
            if (dict.contains(prefix) && canBreak(end, s, dict, memo)) {
                memo.put(start, true);
                return true;
            }
        }

        memo.put(start, false);
        return false;
    }

    // Driver
    public static void main(String[] args) {
        WordBreakTopDown wb = new WordBreakTopDown();
        String s = "applepenapple";
        List<String> dict = Arrays.asList("apple", "pen");
        System.out.println("Can break: " + wb.wordBreak(s, dict));  // Output: true
    }
}
