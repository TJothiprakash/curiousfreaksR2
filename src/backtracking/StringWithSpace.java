package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringWithSpace {

    public static void main(String[] args) {
        StringWithSpace sol = new StringWithSpace();
//        System.out.println(sol.permutation("abc"));
//        System.out.println(sol.permutation("ab"));
//        System.out.println(sol.permutation(""));
//        System.out.println(sol.permutation("a"));
        System.out.println(sol.permutation("aa"));
//        System.out.println(sol.permutation("aaa"));
//
//
//
//        System.out.println(sol.permutation("a b c"));
    }
    public List<String> permutation(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;

        backtrack(result, "", s, 1);  // Start from index 1 since we always include first char
        Collections.sort(result);    // Sort the result lexicographically
        return result;
    }

    private void backtrack(List<String> result, String current, String s, int index) {
        // Base case: reached the end of the string
        if (index == s.length()) {
            result.add(current + s.charAt(index - 1));  // Append last character
            return;
        }

        // Choice 1: add current character with a space
        backtrack(result, current + s.charAt(index - 1) + " ", s, index + 1);

        // Choice 2: add current character without space
        backtrack(result, current + s.charAt(index - 1), s, index + 1);
    }
}
