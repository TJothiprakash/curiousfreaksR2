package dynamic_programming.dp_onsubsequence;

import java.util.ArrayList;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        List<String> ans = generateSubsequences("aaab");
        System.out.println(ans);
        System.out.println(ans.size() == (int)Math.pow(2, "aab".length())); // Should print true
        System.out.println(ans.size());
    }

    static List<String> generateSubsequences(String s) {
        List<String> allSubsequences = new ArrayList<>();
        helper(allSubsequences, 0, s, "");
        return allSubsequences;
    }

    static void helper(List<String> allSubsequences, int index, String s, String current) {
        if (index == s.length()) {
            allSubsequences.add(current); // ✅ Add built subsequence
            return;
        }
        // Include current character
        helper(allSubsequences, index + 1, s, current + s.charAt(index));
        // Exclude current character
        helper(allSubsequences, index + 1, s, current);
    }
}
