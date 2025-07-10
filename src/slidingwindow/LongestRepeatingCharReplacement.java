package slidingwindow;
/*You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.


Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
To solve this problem, we can use the sliding window (or two-pointer) technique to find the length of the longest substring with at most k character changes, where all characters in the window are the same.

Approach:
We are allowed to change up to k characters to make a substring where all characters are the same.
 Our goal is to find the longest such substring.

Key Idea:
The idea is to maintain a sliding window that contains at most k characters that are not the same as the most frequent character in that window. By doing so, we can achieve a substring where all characters are the same after at most k replacements.
Steps:
Sliding Window:
Use two pointers (left and right) to maintain a window of characters.
Track the frequency of characters within the window.
Calculate the number of characters that need to be changed (i.e., the window size minus the count of the most frequent character).
If the number of characters that need to be changed exceeds k, move the left pointer to shrink the window until the number of changes is ≤ k.
Track Maximum Length:
Continuously update the maximum length of the window where we can achieve a uniform substring after changing at most k characters.
Code Implem*/

import java.util.HashMap;

public class LongestRepeatingCharReplacement {
//    O(n)O(n)
    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        int left = 0, maxLength = 0, maxFreq = 0;

        // Traverse the string with the right pointer
        for (int right = 0; right < s.length(); right++) {
            // Update frequency of the current character
            char rightChar = s.charAt(right);
            freqMap.put(rightChar, freqMap.getOrDefault(rightChar, 0) + 1);

            // Track the maximum frequency of any character in the window
            maxFreq = Math.max(maxFreq, freqMap.get(rightChar));

            // If the window size minus the max frequency is greater than k, shrink the window
            if (right - left + 1 - maxFreq > k) {
                // Shrink the window from the left
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                left++;
            }

            // Calculate the maximum length of the valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
