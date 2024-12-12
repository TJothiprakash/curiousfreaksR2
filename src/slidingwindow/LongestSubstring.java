package slidingwindow;

import java.util.HashSet;


/*Sliding Window: We will maintain two pointers (left and right) that represent the current window of characters in the string.

The right pointer will expand the window by moving one step at a time.
The left pointer will shrink the window to ensure there are no duplicate characters in the window.
Character Set: To check for duplicates, we can use a set or a hashmap. The set will store characters in the current window, and as we move the right pointer, we check if the current character exists in the set. If it does, we move the left pointer to the right until the duplicate character is removed.

Track Maximum Length: Throughout the process, we will keep track of the maximum length of the window where there are no repeated characters.

Steps:
Initialize two pointers (left and right) to the start of the string.
Use a set or hash map to store the characters in the current window.
Expand the window by moving the right pointer, adding characters to the set.
If a character is already in the set, move the left pointer rightward to remove the duplicate and maintain the uniqueness of characters in the window.
Track and update the length of the longest valid substring during the process.*/
public class LongestSubstring {
    public static void main(String[] args) {
        System.out.println(longestSubstring("geeksforgeeks"));  // Output: 7
        System.out.println(longestSubstring("abdefgabef"));     // Output: 6
        System.out.println(longestSubstring("aaaaa"));          // Output: 1
    }

    public static int longestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int maxLength = 0;

        while (right < s.length()) {
            // Expand the window by moving the right pointer
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1); // Update the maximum length
                right++; // Move the right pointer
            } else {
                // Shrink the window from the left to remove the duplicate
                set.remove(s.charAt(left));
                left++; // Move the left pointer to the right
            }
        }

        return maxLength;
    }
}

