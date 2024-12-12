package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class SubstringsWithKMinusOneDistinctChars {
    public static int countSubstrings(String s, int k) {
        if (s == null || s.length() < k) return 0;

        Map<Character, Integer> charCountMap = new HashMap<>();
        int count = 0;

        // Sliding window logic
        for (int i = 0; i < s.length(); i++) {
            // Add the current character to the map
            char currentChar = s.charAt(i);
            charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1);

            // If the window size exceeds K, remove the leftmost character
            if (i >= k) {
                char charToRemove = s.charAt(i - k);
                charCountMap.put(charToRemove, charCountMap.get(charToRemove) - 1);
                if (charCountMap.get(charToRemove) == 0) {
                    charCountMap.remove(charToRemove);
                }
            }

            // Check if the current window has exactly K-1 distinct characters
            if (i >= k - 1 && charCountMap.size() == k - 1) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String s1 = "abcc";
        int k1 = 2;
        System.out.println(countSubstrings(s1, k1)); // Output: 1

        String s2 = "aabab";
        int k2 = 3;
        System.out.println(countSubstrings(s2, k2)); // Output: 3
    }
}
