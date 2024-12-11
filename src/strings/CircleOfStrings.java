package strings;

import java.util.HashSet;
import java.util.Set;

public class CircleOfStrings {

    public int canFormCircle(String[] arr) {
        int[] startCount = new int[26]; // Count of strings starting with each character
        int[] endCount = new int[26];   // Count of strings ending with each character
        Set<Character> uniqueChars = new HashSet<>(); // To track all unique characters

        // Build counts and track characters
        for (String word : arr) {
            char start = word.charAt(0);
            char end = word.charAt(word.length() - 1);

            startCount[start - 'a']++;
            endCount[end - 'a']++;
            uniqueChars.add(start);
            uniqueChars.add(end);
        }

        // Check in-degree == out-degree for all characters
        for (int i = 0; i < 26; i++) {
            if (startCount[i] != endCount[i]) {
                return 0; // Mismatch in start and end counts
            }
        }

        // Ensure all unique characters form a single component
        // This is true if all characters belong to the same set of letters
        if (uniqueChars.size() < arr.length) {
            return 0; // Not all characters are connected
        }

        return 1; // Circle can be formed
    }
}

