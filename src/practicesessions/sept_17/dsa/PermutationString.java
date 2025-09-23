package practicesessions.sept_17.dsa;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class PermutationString {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        // frequency for s1
        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }

        int window = s1.length();

        // first window in s2
        for (int i = 0; i < window; i++) {
            count2[s2.charAt(i) - 'a']++;
        }

        if (matches(count1, count2)) return true;

        // slide the window
        for (int i = window; i < s2.length(); i++) {
            count2[s2.charAt(i) - 'a']++;                   // add new char
            count2[s2.charAt(i - window) - 'a']--;          // remove old char
            if (matches(count1, count2)) return true;
        }

        return false;
    }

    private boolean matches(int[] count1, int[] count2) {
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) return false;
        }
        return true;
    }

}

