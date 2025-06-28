package strings;
/*
    ✅ Question:
    Given a string s, find the length of the longest proper prefix which is also a suffix.
    A proper prefix does not include the entire string.

    🔸 Examples:
    Input: "aabcdaabc" → Output: 4
    Input: "ababab"    → Output: 4
    Input: "aaaa"      → Output: 3
*/

/*
    ✅ Intuition:
    This is the same as building the LPS array in the KMP pattern matching algorithm.

    The LPS array stores, for every index i, the length of the longest proper prefix
    which is also a suffix for the substring s[0..i].

    The last value in the LPS array gives the result.
*/

/*
    ✅ Time & Space Complexity:
    Time: O(n)      → We build the LPS array in linear time.
    Space: O(n)     → For the LPS array.
*/

public class LongestPrefixSuffixLPS {

    // ✅ Function to compute the longest proper prefix which is also suffix
    public static int longestPrefixSuffix(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0; // length of previous longest prefix suffix

        int i = 1;
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                System.out.println("Match: i=" + i + ", len=" + len + ", lps=" + lps[i]);
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // try previous prefix-suffix length
                    System.out.println("Mismatch: len updated to " + len);
                } else {
                    lps[i] = 0;
                    System.out.println("Mismatch: i=" + i + ", lps=" + lps[i]);
                    i++;
                }
            }
        }

        // Debug: print full LPS array
        System.out.print("LPS Array: ");
        for (int v : lps) System.out.print(v + " ");
        System.out.println();

        return lps[n - 1]; // last value = longest proper prefix also suffix
    }

    // ✅ Driver code
    public static void main(String[] args) {
        System.out.println("=== Longest Prefix Suffix LPS ===");
        String s = "ababaca";
        System.out.println("Input : "+ s+" -> Output: " + longestPrefixSuffix(s));
//        String s1 = "aabcdaabc";
//        String s2 = "ababab";
//        String s3 = "aaaa";
//        String s4 = "abcdabc";
//
//        System.out.println("Input: " + s1 + " → Output: " + longestPrefixSuffix(s1)); // 4
//        System.out.println("Input: " + s2 + " → Output: " + longestPrefixSuffix(s2)); // 4
//        System.out.println("Input: " + s3 + " → Output: " + longestPrefixSuffix(s3)); // 3
//        System.out.println("Input: " + s4 + " → Output: " + longestPrefixSuffix(s4)); // 3
    }
}

/*
    ✅ Output Example (for debug):
    Match: i=6, len=1, lps=1
    Match: i=7, len=2, lps=2
    Match: i=8, len=3, lps=3
    Match: i=9, len=4, lps=4
    LPS Array: 0 1 0 0 0 1 2 3 4
    Input: aabcdaabc → Output: 4
*/
/*| i | s\[i] | len | s\[len] | s\[i] == s\[len]? | Explanation                                  | lps\[i] |
s = a b a b a c a
    0 1 2 3 4 5 6

| - | ----- | --- | ------- | ----------------- | -------------------------------------------- | ------- |
| 1 | b     | 0   | a       | ❌                 | No match → lps\[1] = 0                       | 0       |
| 2 | a     | 0   | a       | ✅                 | Match → len = 1, lps\[2] = 1                 | 1       |
| 3 | b     | 1   | b       | ✅                 | Match → len = 2, lps\[3] = 2                 | 2       |
| 4 | a     | 2   | a       | ✅                 | Match → len = 3, lps\[4] = 3                 | 3       |
| 5 | c     | 3   | b       | ❌                 | Mismatch → fallback: len = lps\[2] = 1       |         |
|   | c     | 1   | b       | ❌                 | Still mismatch → fallback: len = lps\[0] = 0 |         |
|   | c     | 0   | a       | ❌                 | Final fallback → lps\[5] = 0                 | 0       |
| 6 | a     | 0   | a       | ✅                 | Match → len = 1, lps\[6] = 1                 | 1       |


Index:  0 1 2 3 4 5 6
Char :  a b a b a c a
LPS  :  0 0 1 2 3 0 1
*/