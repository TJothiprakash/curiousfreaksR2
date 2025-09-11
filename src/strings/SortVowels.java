package strings;

import java.util.*;

public class SortVowels {
    static class Solution {
        public String sortVowels(String s) {
            Set<Character> vowels = new HashSet<>(Arrays.asList(
                    'a','e','i','o','u','A','E','I','O','U'
            ));

            // Collect vowels
            List<Character> vowelList = new ArrayList<>();
            for (char c : s.toCharArray()) {
                if (vowels.contains(c)) {
                    vowelList.add(c);
                }
            }

            // Sort vowels
            Collections.sort(vowelList);

            // Replace vowels in original order
            StringBuilder sb = new StringBuilder(s);
            int idx = 0;
            for (int i = 0; i < sb.length(); i++) {
                if (vowels.contains(sb.charAt(i))) {
                    sb.setCharAt(i, vowelList.get(idx++));
                }
            }

            return sb.toString();
        }
    }

    // Quick test
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.sortVowels("leetcode")); // Output: "leetcede"
        System.out.println(sol.sortVowels("hello"));    // Output: "holle"
    }
}
