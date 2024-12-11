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
