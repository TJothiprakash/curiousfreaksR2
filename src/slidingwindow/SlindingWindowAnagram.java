package slidingwindow;

/*
Given a word pat and a text txt. Return the count of the occurrences of anagrams of the word in the text.

Example 1:

Input:
txt = forxxorfxdofr
pat = for
Output: 3
Explanation: for, orf and ofr appears
in the txt, hence answer is 3.
Example 2:

Input:
txt = aabaabaa
pat = aaba
Output: 4
Explanation: aaba is present 4 times
in txt.
Your Task:
Complete the function search() which takes two strings pat, txt, as input parameters and returns an integer denoting the answer.
You don't need to print answer or take inputs.

Expected Time Complexity: O(N)
Expected Auxiliary Space: O(26) or O(256)

Constraints:
1 <= |pat| <= |txt| <= 105
Both strings contain lowercase English letters.


Initialize frequency arrays:

One for the frequency of characters in pat.
One for the frequency of characters in the current window of txt.
Start with the first window:

Initialize the window size to be the same as the length of pat.
Count the characters in the first window and compare them to the frequency of characters in pat.
Slide the window across txt:

Slide the window one character at a time from left to right. At each step:
Remove the character that's no longer in the window (the character that's sliding out).
Add the new character that's entering the window.
Check if the frequencies of the characters in the window match those in pat.
Count the matches: Each time the frequencies match, increment the count of anagrams.

*/
public class SlindingWindowAnagram {
    public int search(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();

        // If pattern is longer than the text, no anagrams can be found
        if (m > n) {
            return 0;
        }

        // Frequency arrays for characters in pat and the current window in txt
        int[] patFreq = new int[26];
        int[] windowFreq = new int[26];

        // Count frequency of characters in the pattern
        for (int i = 0; i < m; i++) {
            patFreq[pat.charAt(i) - 'a']++;
        }

        // Count frequency of characters in the first window of txt
        for (int i = 0; i < m; i++) {
            windowFreq[txt.charAt(i) - 'a']++;
        }

        int count = 0;
        // If the frequencies of the pattern and window match, it's an anagram
        if (matches(patFreq, windowFreq)) {
            count++;
        }

        // Slide the window across the text
        for (int i = m; i < n; i++) {
            // Add the next character to the window
            windowFreq[txt.charAt(i) - 'a']++;
            // Remove the character that's sliding out of the window
            windowFreq[txt.charAt(i - m) - 'a']--;

            // If the frequencies match, increment the count
            if (matches(patFreq, windowFreq)) {
                count++;
            }
        }

        return count;
    }

    // Helper function to compare the frequency arrays
    private boolean matches(int[] patFreq, int[] windowFreq) {
        for (int i = 0; i < 26; i++) {
            if (patFreq[i] != windowFreq[i]) {
                return false;
            }
        }
        return true;
    }
}
