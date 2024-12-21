package dynamic_programming.dp_onstrings;

import java.util.HashMap;
import java.util.HashSet;

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
