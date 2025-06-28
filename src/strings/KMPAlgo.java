package strings;

import org.jetbrains.annotations.NotNull;

public class KMPAlgo {
    static int kmp(String string, String pattern) {
        int i = 0, j = 0, m = pattern.length(), n = string.length();
        pattern = ' ' + pattern; //just shifting the pattern indices by 1
        int[] piTable = new int[m + 1];
        for (i = 2; i <= m; i++) {
            while (j <= m && pattern.charAt(j + 1) == pattern.charAt(i))
                piTable[i++] = ++j;
            j = 0;
        }
        j = 0;
        for (i = 0; i < n; i++) {
            if (pattern.charAt(j + 1) != string.charAt(i)) {
                while (j != 0 && pattern.charAt(j + 1) != string.charAt(i))
                    j = piTable[j];
            }
            j++;
            if (j == m) return i - m + 1;
        }
        return -1;

    }

    public static void main(String[] args) {
        String pattern = "aaaaaab", string = "aaaaaaaamaaaaaab";

        int index = kmp(string, pattern);
        if (index == -1) System.out.println("The pattern is not found");
        else System.out.println("The pattern " + pattern + " is found in the given string " + string + " at " + index);

    }
}


/*
============================================
📝 QUESTION: Pattern Search Using KMP Algorithm
============================================

Given:
- Pattern = "baobab"
- Text = "bess knew about baobab"

Task:
Find all occurrences of the pattern in the text using the **KMP algorithm**.

--------------------------------------------
💡 KMP handles mismatches efficiently by precomputing a "prefix-suffix" (LPS) array.
It avoids rechecking characters that are known to match.
*/

 class KMPPatternSearch {

    public static void main(String[] args) {
        String pattern = "baobab";
        String text = "bess knew about baobab";

        System.out.println("=== LPS Table ===");
        int[] lps = buildLPS(pattern);
        for (int i = 0; i < lps.length; i++) {
            System.out.println("lps[" + i + "] = " + lps[i]);
        }

        System.out.println("\n=== KMP Matches ===");
        KMPSearch(text, pattern, lps);
    }

    /*
    =============================
    🔧 Step 1: Build LPS Array (Longest Prefix Suffix)
    =============================
    LPS[i] tells the length of the longest proper prefix
    which is also a suffix in pattern[0..i]
    */
    static int @NotNull [] buildLPS(@NotNull String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0; // length of the previous longest prefix suffix
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // fallback
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /*
    =============================
    🚀 Step 2: KMP Pattern Search
    =============================
    Uses LPS array to skip unnecessary comparisons
    */
    static void KMPSearch(String text, String pattern, int[] lps) {
        int n = text.length();
        int m = pattern.length();

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1]; // look for next match
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1]; // fallback using LPS
                } else {
                    i++;
                }
            }
        }
    }

    /*
    =============================
    ⏱ TIME & SPACE COMPLEXITY
    =============================

    📌 Preprocessing (LPS table): O(m)
    📌 Pattern Search: O(n)
    📌 Total: O(n + m)

    📌 Space:
    - LPS array: O(m)

    =============================
    ✅ OUTPUT EXAMPLE
    =============================

    Pattern = "baobab"
    Text    = "bess knew about baobab"

    Output:
    Pattern found at index 17

    */
}
