package heaps;
/*Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.



Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""


Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
Always pick the most frequent character, place it,
then pick the next most frequent (so we don’t place the same character adjacently), and alternate.

✅ Step-by-step Approach:
Count frequency of each character.

Use a max-heap (priority queue) to store characters sorted by frequency.

Build the result by:

Picking the character with the highest frequency.

Making sure it’s not the same as the previous character used.

Reinsert the previously used character (if it still has remaining frequency).

*/import java.util.*;

public class ReorganizeString {
    public static String reorganizeString(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray())
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
                (a, b) -> freqMap.get(b) - freqMap.get(a)
        );
        maxHeap.addAll(freqMap.keySet());

        StringBuilder result = new StringBuilder();
        Character prevChar = null;
        int prevFreq = 0;

        while (!maxHeap.isEmpty()) {
            char current = maxHeap.poll();
            result.append(current);
            freqMap.put(current, freqMap.get(current) - 1);

            // reinsert the previous character if it still has remaining frequency
            if (prevChar != null && prevFreq > 0) {
                maxHeap.offer(prevChar);
            }

            prevChar = current;
            prevFreq = freqMap.get(current);
        }

        return result.length() == s.length() ? result.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));   // Output: "aba"
        System.out.println(reorganizeString("aaab"));  // Output: ""
    }
}
