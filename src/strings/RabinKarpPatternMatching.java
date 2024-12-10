package strings;

import java.util.ArrayList;
import java.util.List;

public class RabinKarpPatternMatching {

    private static final int PRIME = 101;
// follow kunals implementation for easy catch

    ArrayList<Integer> searchUsingBruteForce(String pattern, String text) {
        // your code here
        int textLength = text.length();
        List<Integer> res = new ArrayList<>();
        int patternLength = pattern.length();
        for (int i = 0; i < textLength - patternLength + 1; i++) {
            if (text.substring(i, i + patternLength).equals(pattern)) {
                res.add(i);
            }
        }
        return (ArrayList<Integer>) res;
    }




    ArrayList<Integer> searchUsingRabinKarp(String pattern, String text) {
        // your code here
        ArrayList<Integer> result = new ArrayList<>();
        int m = pattern.length();
        int n = text.length();

        // Compute the hash of the pattern and the first window of the text
        long patternHash = 0, textHash = 0, power = 1;

        // Compute p^(m-1)
        for (int i = 0; i < m - 1; i++) {
            power = (power * PRIME);
        }

        // Compute initial hash values
        for (int i = 0; i < m; i++) {
            patternHash = patternHash * PRIME + pattern.charAt(i);
            textHash = textHash * PRIME + text.charAt(i);
        }

        // Slide the window over the text
        for (int i = 0; i <= n - m; i++) {
            // If hashes match, compare the strings to confirm
            if (patternHash == textHash && text.substring(i, i + m).equals(pattern)) {
                result.add(i);
            }

            // Compute the hash for the next window
            if (i < n - m) {
                textHash = (textHash - text.charAt(i) * power) * PRIME + text.charAt(i + m);
            }
        }

        return result;

    }

}
