package strings;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HP_BM {
}

/*
============================================
📝 QUESTION: Pattern Search Using Horspool & Boyer-Moore
============================================

Given:
- Pattern = "baobab"
- Text = "bess knew about baobab"

Task:
Find all occurrences of the pattern in the text using:
1. Horspool Algorithm
2. Boyer-Moore Algorithm (Bad Character + Good Suffix)

Constraint:
Use the same Bad Character Table (based on your custom right-to-left format) in both algorithms.

--------------------------------------------
🎯 CUSTOM BAD CHARACTER TABLE FORMAT:

For pattern "baobab" (length = 6), assign:
- b -> 0 (rightmost index = 5)
- a -> 1 (index = 4)
- o -> 2 (index = 3)
- others -> 6 (pattern length)

This table helps determine how far to shift the pattern on mismatch.

*/


class PatternSearchBM_Horspool {

    public static void main(String[] args) {
        String pattern = "baobab";
        String text = "bess knew about baobab";

        // Step 1: Build bad character table (your standard)
        Map<Character, Integer> badCharTable = buildBadCharTable(pattern);

        // Step 2: Build good suffix table for BM
        int[] goodSuffixTable = buildGoodSuffixTable(pattern);

        // Log tables
        System.out.println("=== Bad Character Table (Your Format) ===");
        for (char c = 'a'; c <= 'z'; c++) {
            System.out.println(c + " -> " + badCharTable.getOrDefault(c, pattern.length()));
        }

        System.out.println("\n=== Good Suffix Table ===");
        System.out.println(Arrays.toString(goodSuffixTable));

        // Step 3: Search using Horspool
        System.out.println("\n== Horspool Matches ==");
        horspoolSearch(text, pattern, badCharTable);

        // Step 4: Search using Full Boyer-Moore
        System.out.println("\n== Boyer-Moore Matches ==");
        boyerMooreSearch(text, pattern, badCharTable, goodSuffixTable);
    }

    /*
    =============================
    🔧 Step 1: Build Bad Char Table (Your Format)
    =============================
    For each character in pattern (right to left),
    store distance from rightmost position.
    */
    static Map<Character, Integer> buildBadCharTable(String pattern) {
        Map<Character, Integer> table = new HashMap<>();
        int m = pattern.length();
        for (int i = m - 1; i >= 0; i--) {
            char ch = pattern.charAt(i);
            table.putIfAbsent(ch, m - 1 - i);
        }
        return table;
    }

    /*
    =============================
    🔧 Step 2: Build Good Suffix Table for BM
    =============================
    Helps BM skip using suffixes matched previously.
    */
    static int[] buildGoodSuffixTable(String pattern) {
        int m = pattern.length();
        int[] shift = new int[m];
        int[] border = new int[m + 1];
        int i = m, j = m + 1;
        border[i] = j;

        while (i > 0) {
            while (j <= m && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (shift[j] == 0) {
                    shift[j] = j - i;
                }
                j = border[j];
            }
            i--;
            j--;
            border[i] = j;
        }

        j = border[0];
        for (i = 0; i <= m; i++) {
            if (shift[i] == 0) shift[i] = j;
            if (i == j) j = border[j];
        }

        return Arrays.copyOfRange(shift, 1, m + 1);
    }

    /*
    =============================
    🚀 Step 3: Horspool Algorithm
    =============================
    Matches pattern right to left, shifts by last char only.
    */
    static void horspoolSearch(String text, String pattern, Map<Character, Integer> badCharTable) {
        int n = text.length(), m = pattern.length();
        int i = 0;

        while (i <= n - m) {
            int j = m - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(i + j)) {
                j--;
            }

            if (j < 0) {
                System.out.println("Pattern found at index " + i);
                i++;
            } else {
                char badChar = text.charAt(i + m - 1);
                int shift = badCharTable.getOrDefault(badChar, m);
                i += shift;
            }
        }
    }

    /*
    =============================
    🚀 Step 4: Full Boyer-Moore Algorithm
    =============================
    Uses both Bad Char and Good Suffix heuristics.
    */
    static void boyerMooreSearch(@NotNull String text, @NotNull String pattern, Map<Character, Integer> badCharTable, int[] goodSuffixTable) {
        int n = text.length(), m = pattern.length();
        int i = 0;

        while (i <= n - m) {
            int j = m - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(i + j)) {
                j--;
            }

            if (j < 0) {
                System.out.println("Pattern found at index " + i);
                i += goodSuffixTable[0];
            } else {
                char badChar = text.charAt(i + j);
                int badCharShift = j - badCharTable.getOrDefault(badChar, m);
                int goodSuffixShift = goodSuffixTable[m - 1 - j];
                i += Math.max(1, Math.max(badCharShift, goodSuffixShift));
            }
        }
    }

    /*
    =============================
    ⏱ TIME & SPACE COMPLEXITY
    =============================

    📌 Preprocessing:
    - Bad Char Table:   O(m)
    - Good Suffix Table:O(m)

    📌 Searching:
    - Best: O(n/m) — large jumps
    - Worst: O(n * m) — rare with good heuristics
    - Avg: O(n)

    📌 Space:
    - Bad Char Table: O(1) — for fixed alphabet
    - Good Suffix Table: O(m)

    =============================
    ✅ OUTPUT EXAMPLE
    =============================
    Pattern = "baobab"
    Text    = "bess knew about baobab"

    Output:
    Pattern found at index 17
    (in both Horspool and Boyer-Moore)

    */
}
