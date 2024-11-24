package slidingwindow;
import java.util.HashMap;

public class SubstringCounter {

    public static int countOfSubstrings(String S, int K) {
        // Edge case
        if (S == null || S.length() < K) return 0;

        int count = 0;
        HashMap<Character, Integer> charFrequency = new HashMap<>();

        // Process the first window
        for (int i = 0; i < K; i++) {
            char c = S.charAt(i);
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // Check the first window
        if (charFrequency.size() == K - 1) {
            count++;
        }

        // Process the remaining windows
        for (int i = K; i < S.length(); i++) {
            // Add the new character to the window
            char newChar = S.charAt(i);
            charFrequency.put(newChar, charFrequency.getOrDefault(newChar, 0) + 1);

            // Remove the character that's sliding out of the window
            char oldChar = S.charAt(i - K);
            if (charFrequency.get(oldChar) == 1) {
                charFrequency.remove(oldChar);
            } else {
                charFrequency.put(oldChar, charFrequency.get(oldChar) - 1);
            }

            // Check the current window
            if (charFrequency.size() == K - 1) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(countOfSubstrings("abcc", 2)); // Output: 1
        System.out.println(countOfSubstrings("aabab", 3)); // Output: 3
    }
}

