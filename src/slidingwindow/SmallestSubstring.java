package slidingwindow;

import java.util.HashMap;

public class SmallestSubstring {
    public static void main(String[] args) {
        String s = "10212";
        System.out.println("Smallest window length: " + smallestSubstring(s));
    }
//O(n)O(1)
    public static int smallestSubstring(String s) {
        // Step 1: Initialize the map to store the frequency of '0', '1', '2'
        HashMap<Character, Integer> freqMap = new HashMap<>();
        int left = 0;
        int minLength = Integer.MAX_VALUE;

        // Step 2: Iterate through the string with the right pointer
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            freqMap.put(rightChar, freqMap.getOrDefault(rightChar, 0) + 1);

            // Step 3: Shrink the window when all three characters are present
            while (freqMap.size() == 3) {
                minLength = Math.min(minLength, right - left + 1);

                // Remove the character at the left of the window and shrink the window
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                if (freqMap.get(leftChar) == 0) {
                    freqMap.remove(leftChar);
                }
                left++;
            }
        }

        // If no valid window is found, return -1
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}

