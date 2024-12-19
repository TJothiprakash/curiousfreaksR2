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
