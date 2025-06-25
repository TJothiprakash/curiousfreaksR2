package dynamic_programming.dp_onstrings;

public class LongestPalindromicSubstring {

    // Helper function to expand around center and return the longest palindrome
    public static String expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the longest palindromic substring found in this expansion
        return s.substring(left + 1, right);
    }

    // Function to find the longest palindromic substring
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String longest = "";

        // Iterate through each character and treat it as a center for palindromes
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome (single center)
            String oddPalindrome = expandAroundCenter(s, i, i);
            if (oddPalindrome.length() > longest.length()) {
                longest = oddPalindrome;
            }

            // Even length palindrome (two center characters)
            String evenPalindrome = expandAroundCenter(s, i, i + 1);
            if (evenPalindrome.length() > longest.length()) {
                longest = evenPalindrome;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        String s1 = "aaaabbaa";
        String s2 = "abc";
        String s3 = "abacdfgdcaba";

        System.out.println(longestPalindrome(s1)); // Output: "aabbaa"
        System.out.println(longestPalindrome(s2)); // Output: "a"
        System.out.println(longestPalindrome(s3)); // Output: "aba"
    }
}


/*
Question:
Find minimum insertions to make the string a palindrome.

Approach:
- Find Longest Palindromic Subsequence (LPS)
- Minimum insertions = s.length() - LPS length

Time: O(n^2)
Space: O(n^2)
*/

 class MinInsertionsPalindrome {

    public static int minInsertions(String s) {
        int n = s.length();
        Integer[][] memo = new Integer[n][n];
        int lpsLength = longestPalindromeSubseq(0, n - 1, s, memo);
        return n - lpsLength;
    }

    private static int longestPalindromeSubseq(int i, int j, String s, Integer[][] memo) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (memo[i][j] != null) return memo[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = 2 + longestPalindromeSubseq(i + 1, j - 1, s, memo);
        } else {
            memo[i][j] = Math.max(
                    longestPalindromeSubseq(i + 1, j, s, memo),
                    longestPalindromeSubseq(i, j - 1, s, memo)
            );
        }

        return memo[i][j];
    }

    public static void main(String[] args) {
        System.out.println(minInsertions("zzazz"));    // 0
        System.out.println(minInsertions("mbadm"));    // 2
        System.out.println(minInsertions("leetcode")); // 5
    }
}


/*
Question:
Find the longest palindromic substring (not subsequence).

Approach:
- Recursively check all substrings s[i..j]
- Use memoization to avoid recomputation
- Track longest length and its position
- Base cases: i >= j (single char or empty) is palindrome

Time: O(n^2)
Space: O(n^2)
*/


 class LongestPalindromicSubstring1 {

    static int maxLen = 0;
    static int startIndex = 0;
    static Boolean[][] memo;

    public static String longestPalindrome(String s) {
        int n = s.length();
        memo = new Boolean[n][n];
        maxLen = 0;
        startIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j)) {
                    int len = j - i + 1;
                    if (len > maxLen) {
                        maxLen = len;
                        startIndex = i;
                    }
                }
            }
        }

        return s.substring(startIndex, startIndex + maxLen);
    }

    private static boolean isPalindrome(String s, int i, int j) {
        if (i >= j) return true;

        if (memo[i][j] != null) return memo[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = isPalindrome(s, i + 1, j - 1);
        } else {
            memo[i][j] = false;
        }

        return memo[i][j];
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("forgeeksskeegfor")); // "geeksskeeg"
        System.out.println(longestPalindrome("Geeks"));            // "ee"
        System.out.println(longestPalindrome("abc"));              // "a"
    }
}
