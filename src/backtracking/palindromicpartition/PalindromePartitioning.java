package backtracking.palindromicpartition;
import java.util.*;

/*🔹 Problem: Palindrome Partitioning

Given:

A string s.

Goal:

Partition s into all possible sets of substrings such that each substring is a palindrome.

Example:

Input: "aab"
Output: [["a","a","b"], ["aa","b"]]

🔹 Intuition

Use backtracking on string indices.

Start at index 0:

Try every possible end index for a substring.

Check if the substring s[start:end] is a palindrome.

If yes → add to current path → recurse from end+1.

Once start reaches end of string → collect the path as one valid partition.

Backtrack by removing last substring and try the next end index.

Pattern takeaway: choice of substring + validity check (palindrome) + recursion/backtrack

🔹 Dry Run

String = "aab"

Start at index 0:

Substring "a" → palindrome ✅ → path = ["a"] → recurse from index 1

Substring "a" → palindrome ✅ → path = ["a","a"] → recurse from index 2

Substring "b" → palindrome ✅ → path = ["a","a","b"] → start==end → collect

Backtrack → try "ab" → not palindrome ❌

Substring "aa" → palindrome ✅ → path = ["aa"] → recurse from index 2

Substring "b" → palindrome ✅ → path = ["aa","b"] → collect

Substring "aab" → not palindrome ❌

Collected partitions: [["a","a","b"], ["aa","b"]] ✅*/

public class PalindromePartitioning {

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> result = partition(s);
        System.out.println("Palindrome partitions: " + result);
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, int start, List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path)); // found valid partition
            return;
        }

        for (int end = start; end < s.length(); end++) {
            String sub = s.substring(start, end + 1);
            if (isPalindrome(sub)) {
                path.add(sub);
                backtrack(s, end + 1, path, result);
                path.remove(path.size() - 1); // backtrack
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
//🔹 Complexity
//
//Time: Exponential O(2^n)
//
//Every substring choice splits recursion → worst-case all partitions considered
//
//Space:
//
//O(n) recursion stack + O(n) path storage per recursion → total depends on number of partitions
//
//This pattern is string partition + validity check backtracking — completely different from grids and Trie.