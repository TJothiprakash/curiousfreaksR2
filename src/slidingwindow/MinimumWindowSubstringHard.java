package slidingwindow;
/*Given two strings s and t of lengths m and n respectively, return the minimum window
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?

 We are given two strings s and t. We need to find the minimum window in s that contains all characters of t, including duplicates. If no such window exists, we return an empty string.

Approach:
Sliding Window:

We will use a sliding window approach to explore all potential substrings of s that contain the characters of t.
The sliding window will be represented by two pointers: left and right. These pointers will help us expand and shrink the window.
Character Frequency:

We'll keep track of the frequency of characters in t and in the current window of s.
The idea is to expand the window by moving the right pointer and contract the window by moving the left pointer to find the smallest window that contains all characters of t.
Valid Window:

A window is valid when the count of characters in the window matches or exceeds the count of characters in t. Once we find a valid window, we attempt to shrink it by moving the left pointer to the right while keeping the window valid.
Optimization:

We can find the minimum window in O(m + n) time by processing each character of s and t at most twice (once for expanding and once for contracting the window).
Steps:
Character Frequency Count for t:

Create a frequency map for t to know how many times each character appears in t.
Sliding Window on s:

Use a window frequency map to keep track of the characters in the current window.
Expand the window by moving the right pointer and contract it by moving the left pointer when a valid window is found.
Update the Result:

Track the smallest window that contains all characters from t.
Code I*/

import java.util.HashMap;

public class MinimumWindowSubstringHard {
//    O(n)O(1)
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Frequency map of characters in t
        HashMap<Character, Integer> tFreq = new HashMap<>();
        for (char c : t.toCharArray()) {
            tFreq.put(c, tFreq.getOrDefault(c, 0) + 1);
        }

        // Variables for the sliding window
        int left = 0, right = 0;
        int required = tFreq.size();  // Number of unique characters in t that need to be matched
        int formed = 0;  // Number of characters in the current window that match the required frequency
        HashMap<Character, Integer> windowFreq = new HashMap<>();

        int[] result = {-1, 0, 0};  // {length, left, right} of the minimum window

        // Expand the window by moving the right pointer
        while (right < s.length()) {
            char c = s.charAt(right);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);

            // If the current character's frequency matches the target frequency in t
            if (tFreq.containsKey(c) && windowFreq.get(c).intValue() == tFreq.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window until it's no longer valid
            while (left <= right && formed == required) {
                c = s.charAt(left);

                // Update the result if the current window is smaller
                if (result[0] == -1 || right - left + 1 < result[0]) {
                    result[0] = right - left + 1;
                    result[1] = left;
                    result[2] = right;
                }

                // Shrink the window from the left
                windowFreq.put(c, windowFreq.get(c) - 1);
                if (tFreq.containsKey(c) && windowFreq.get(c).intValue() < tFreq.get(c).intValue()) {
                    formed--;
                }
                left++;
            }

            // Expand the window to the right
            right++;
        }

        // Return the minimum window substring
        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }
}
