package slidingwindow;

import java.util.HashMap;

public class SmallestWindow {
    public static void main(String[] args) {
        System.out.println(smallestWindow("timetopractice", "toc")); // "toprac"
        System.out.println(smallestWindow("zoomlazapzo", "oza")); // "apzo"
        System.out.println(smallestWindow("zoom", "zooe")); // ""
    }
//O(n)O(1)
    public static String smallestWindow(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return "";
        }

        // Step 1: Build frequency map for characters in s2
        HashMap<Character, Integer> s2_map = new HashMap<>();
        for (char c : s2.toCharArray()) {
            s2_map.put(c, s2_map.getOrDefault(c, 0) + 1);
        }

        // Step 2: Sliding window approach
        HashMap<Character, Integer> window_map = new HashMap<>();
        int left = 0, right = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0; // To track the start index of the smallest window

        int required = s2_map.size(); // Number of unique characters in s2
        int formed = 0; // Number of unique characters in the window that meet the required frequency

        while (right < s1.length()) {
            // Add the current character to the window_map
            char rightChar = s1.charAt(right);
            window_map.put(rightChar, window_map.getOrDefault(rightChar, 0) + 1);

            // If the current character's frequency matches the required frequency in s2
            if (s2_map.containsKey(rightChar) && window_map.get(rightChar).intValue() == s2_map.get(rightChar).intValue()) {
                formed++;
            }

            // Try and shrink the window until it's no longer valid
            while (left <= right && formed == required) {
                char leftChar = s1.charAt(left);
                int windowLength = right - left + 1;

                if (windowLength < minLength) {
                    minLength = windowLength;
                    start = left;
                }

                // Remove the left character from the window
                window_map.put(leftChar, window_map.get(leftChar) - 1);
                if (s2_map.containsKey(leftChar) && window_map.get(leftChar).intValue() < s2_map.get(leftChar).intValue()) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        // If no window found, return ""
        return minLength == Integer.MAX_VALUE ? "" : s1.substring(start, start + minLength);
    }
}


class SmallestWindowContainingAllCharss {

    public static String minWindow(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) return "";

        // Step 1: Build frequency map for s2
        HashMap<Character, Integer> targetFreq = new HashMap<>();
        for (char c : s2.toCharArray()) {
            targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);
        }

        // Step 2: Sliding window setup
        HashMap<Character, Integer> windowFreq = new HashMap<>();
        int left = 0, minLen = Integer.MAX_VALUE, startIndex = 0;
        int matchCount = 0;

        for (int right = 0; right < s1.length(); right++) {
            char ch = s1.charAt(right);
            windowFreq.put(ch, windowFreq.getOrDefault(ch, 0) + 1);

            // If character is in target and count matches, increment match count
            if (targetFreq.containsKey(ch) && windowFreq.get(ch).intValue() <= targetFreq.get(ch).intValue()) {
                matchCount++;
            }

            // Step 3: Try to shrink the window if all characters are matched
            while (matchCount == s2.length()) {
                // Update result if this window is smaller
                if ((right - left + 1) < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }

                // Try to remove from left
                char leftChar = s1.charAt(left);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);

                if (targetFreq.containsKey(leftChar) && windowFreq.get(leftChar) < targetFreq.get(leftChar)) {
                    matchCount--;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s1.substring(startIndex, startIndex + minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("timetopractice", "toc"));    // Output: "toprac"
        System.out.println(minWindow("zoomlazapzo", "oza"));       // Output: "apzo"
        System.out.println(minWindow("zoom", "zooe"));             // Output: ""
    }
}

