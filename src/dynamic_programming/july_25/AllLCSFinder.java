package dynamic_programming.july_25;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllLCSFinder {

    public List<String> printAllLCS(String s1, String s2) {
        List<String> result = new ArrayList<>();
        Set<String> unique = new HashSet<>(); // avoid duplicates
        int[] maxLength = new int[1]; // holder for max length

        backtrack(s1, s2, 0, 0, new StringBuilder(), result, maxLength, unique);
        return result;
    }

    private void backtrack(String s1, String s2, int i, int j, StringBuilder path,
                           List<String> result, int[] maxLength, Set<String> unique) {

        if (i == s1.length() || j == s2.length()) {
            String lcs = path.toString();

            if (lcs.length() > maxLength[0]) {
                result.clear();
                unique.clear();
                result.add(lcs);
                unique.add(lcs);
                maxLength[0] = lcs.length();
            } else if (lcs.length() == maxLength[0] && !unique.contains(lcs)) {
                result.add(lcs);
                unique.add(lcs);
            }
            return;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            path.append(s1.charAt(i));
            backtrack(s1, s2, i + 1, j + 1, path, result, maxLength, unique);
            path.deleteCharAt(path.length() - 1);
        } else {
            backtrack(s1, s2, i + 1, j, path, result, maxLength, unique);
            backtrack(s1, s2, i, j + 1, path, result, maxLength, unique);
        }
    }

    public static void main(String[] args) {
        AllLCSFinder finder = new AllLCSFinder();
        String s1 = "abcabcaa";
        String s2 = "acbacba";
        List<String> lcsList = finder.printAllLCS(s1, s2);
        System.out.println("All LCS:");
        for (String lcs : lcsList) {
            System.out.println(lcs);
        }
    }
}
