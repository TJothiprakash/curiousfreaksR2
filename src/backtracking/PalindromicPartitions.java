package backtracking;
import java.util.*;
/*Given a String S, Find all possible Palindromic partitions of the given String.


Example 1:

Input:
S = "geeks"
Output:
g e e k s
g ee k s
Explanation:
All possible palindromic partitions
are printed.
Example 2:

Input:
S = "madam"
Output:
m a d a m
m ada m
madam

Your Task:
You don't need to read input or print anything.
 Your task is to complete the function allPalindromicPerms() which takes a String S
 as input parameter and returns a list of lists denoting all the possible palindromic partitions
 in the order of their appearance in the original string.



Expected Time Complexity: O(N*2N)
Expected Auxiliary Space: O(N2), where N is the length of the String



Constraints:
1 <= |S| <= 20

Company T*/
public class PalindromicPartitions {
    public static List<List<String>> allPalindromicPerms(String S) {
        List<List<String>> result = new ArrayList<>();
        backtrack(S, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, int start, List<String> current, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                current.add(s.substring(start, end + 1));
                backtrack(s, end + 1, current, result);
                current.remove(current.size() - 1); // Backtrack
            }
        }
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }

    // Test
    public static void main(String[] args) {
        System.out.println(allPalindromicPerms("madam")); // [[m, a, d, a, m], [m, ada, m], [madam]]
        System.out.println(allPalindromicPerms("geeks")); // [[g, e, e, k, s], [g, ee, k, s]]
    }
}
