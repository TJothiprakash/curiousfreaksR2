package strings;
// longest prefix also sufffix

public class LongestPrefix {
}
// Java program to find the length of longest proper prefix
// that is also suffix by comparing each prefix with suffix

class GfG {
    static int longestPrefixSuffix(String s) {
        int res = 0;
        int n = s.length();

        // Iterating over all possible lengths
        for (int len = 1; len < n; len++) {

            // Starting index of proper prefix
            int i = 0;

            // Starting index of suffix
            int j = s.length() - len;

            boolean flag = true;

            // Comparing proper prefix with suffix of length 'len'
            for (int k = 0; k < len; k++) {
                if (s.charAt(i + k) != s.charAt(j + k)) {
                    flag = false;
                    break;
                }
            }

            // If they match, update the result
            if (flag)
                res = len;
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "ababab";
        System.out.println(longestPrefixSuffix(s));
    }
}


class LongestPrefixSuffix {
    public static int longestPrefixSuffix(String s) {
        int n = s.length();
        int[] lps = new int[n];

        int len = 0; // length of the previous longest prefix suffix
        int i = 1;

        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
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

        return lps[n - 1]; // Final LPS value (not the full string, so proper prefix)
    }

    public static void main(String[] args) {
        System.out.println(longestPrefixSuffix("aabcdaabc")); // 4
        System.out.println(longestPrefixSuffix("ababab"));     // 4
        System.out.println(longestPrefixSuffix("aaaa"));       // 3
        System.out.println(longestPrefixSuffix("abcdabc"));       // 0
    }
}
