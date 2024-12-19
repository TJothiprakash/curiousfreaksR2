package dynamic_programming.dp_onstrings;

import java.util.HashMap;

public class LongestCommonSubstring {

    // Memoization table to store results of subproblems
    static HashMap<String, Integer> memo = new HashMap<>();

    // Helper function for recursion with memoization
    public static int longestCommonSubstringMemo(String s1, String s2, int i, int j) {
        // Base case: if either index is out of bounds
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }

        // Check if the result is already computed
        String key = i + "," + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // If characters match, we have a potential substring, continue recursively
        if (s1.charAt(i) == s2.charAt(j)) {
            int length = 1 + longestCommonSubstringMemo(s1, s2, i + 1, j + 1);
            memo.put(key, length);
            return length;
        } else {
            // No match, return 0
            memo.put(key, 0);
            return 0;
        }
    }

    // Function to find the length of the longest common substring
    public static int longestCommonSubstring(String s1, String s2) {
        int maxLength = 0;
        // Try all possible pairs of indices
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                maxLength = Math.max(maxLength, longestCommonSubstringMemo(s1, s2, i, j));
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s1 = "ABCDGH";
        String s2 = "ACDGHR";
        System.out.println("Length of Longest Common Substring: " + longestCommonSubstring(s1, s2));  // Output: 4

        String s1_2 = "abc";
        String s2_2 = "acb";
        System.out.println("Length of Longest Common Substring: " + longestCommonSubstring(s1_2, s2_2));  // Output: 1

        String s1_3 = "YZ";
        String s2_3 = "yz";
        System.out.println("Length of Longest Common Substring: " + longestCommonSubstring(s1_3, s2_3));  // Output: 0
    }
}
