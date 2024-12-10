package strings;

import java.util.ArrayList;

public class BoyerMoore {

    public static void main(String[] args) {
        BoyerMoore bm = new BoyerMoore();
        String text = "ababcababcababc";
        String pattern = "abc";

        ArrayList<Integer> result = bm.search(text, pattern);
        System.out.println(result); // Output: [2, 7, 12]
    }

    // Preprocessing the pattern to create the bad character rule table
    private int[] badCharacterHeuristic(String pattern) {
        int[] badChar = new int[256]; // Assuming ASCII
        for (int i = 0; i < 256; i++) {
            badChar[i] = -1; // Initialize with -1 for all characters
        }

        for (int i = 0; i < pattern.length(); i++) {
            badChar[pattern.charAt(i)] = i; // Set the last occurrence of each character in the pattern
        }

        return badChar;
    }

    // Boyer-Moore pattern matching algorithm
    public ArrayList<Integer> search(String text, String pattern) {
        ArrayList<Integer> result = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();

        int[] badChar = badCharacterHeuristic(pattern); // Get the bad character heuristic table

        int s = 0; // Shift of the pattern over the text
        while (s <= (n - m)) {
            int j = m - 1;

            // Start comparing from the end of the pattern
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }

            if (j < 0) {
                // Match found at index 's'
                result.add(s);
                // Shift the pattern using the bad character heuristic
                s += (s + m < n) ? m - badChar[text.charAt(s + m)] : 1;
            } else {
                // Use the bad character rule to shift the pattern
                s += Math.max(1, j - badChar[text.charAt(s + j)]);
            }
        }

        return result;
    }
}

