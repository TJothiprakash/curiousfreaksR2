package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SmallestWindowContainingAllChars {
    public static void main(String[] args) {
        String s = "AABBBCBBAC";
        System.out.println("Smallest window size: " + findSmallestWindow(s));
    }

    public static int findSmallestWindow(String s) {
        // Count all unique characters in the string
        Set<Character> uniqueChars = new HashSet<>();
        for (char c : s.toCharArray()) {
            uniqueChars.add(c);
        }
        int uniqueCount = uniqueChars.size();

        // Sliding window
        Map<Character, Integer> charCountMap = new HashMap<>();
        int left = 0, minLength = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCountMap.put(rightChar, charCountMap.getOrDefault(rightChar, 0) + 1);

            // Shrink the window if it contains all unique characters
            while (charCountMap.size() == uniqueCount) {
                minLength = Math.min(minLength, right - left + 1);

                // Remove the leftmost character from the window
                char leftChar = s.charAt(left);
                charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);
                if (charCountMap.get(leftChar) == 0) {
                    charCountMap.remove(leftChar);
                }
                left++;
            }
        }

        return minLength;
    }
}

