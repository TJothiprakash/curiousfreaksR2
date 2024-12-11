package strings;

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        // Edge case: if the string is empty or has only one character
        if (s == null || s.length() <= 1) {
            return s;
        }

        // Reverse the string
        String rev = new StringBuilder(s).reverse().toString();

        // Try to find the longest matching prefix of the original string
        // and the suffix of the reversed string
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(0, s.length() - i).equals(rev.substring(i))) {
                // Add the remaining part of the reversed string in front
                return rev.substring(0, i) + s;
            }
        }

        return ""; // This will never be reached
    }
}

// TC O(n^2)
// SC O(n)