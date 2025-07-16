package backtracking;

import java.util.ArrayList;
import java.util.List;

/*Given a string s, you can transform every letter
 individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create.
Return the output in any order.



Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2: Input: s = "3z4" Output: ["3z4","3Z4"]


Constraints:

1 <= s.length <= 12
s consists of lowercase English letters, uppercase English letters, and digits.
Accepted
==================================
Key Idea
At each character:

If it's a digit, keep it as is.

If it's a letter, you have two choices:

Use it as lowercase

Use it as uppercase

Recurse on the rest of the string.

*/
public class LetterCasePermutation {
    public static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(char[] s, int index, StringBuilder path, List<String> result) {
        if (index == s.length) {
            result.add(path.toString());
            return;
        }

        char currentChar = s[index];

        if (Character.isLetter(currentChar)) {
            // Choose lowercase
            path.append(Character.toLowerCase(currentChar));
            backtrack(s, index + 1, path, result);
            path.deleteCharAt(path.length() - 1); // backtrack

            // Choose uppercase
            path.append(Character.toUpperCase(currentChar));
            backtrack(s, index + 1, path, result);
            path.deleteCharAt(path.length() - 1); // backtrack
        } else {
            // Just append digit and move on
            path.append(currentChar);
            backtrack(s, index + 1, path, result);
            path.deleteCharAt(path.length() - 1); // backtrack
        }
    }

    // Driver
    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2")); // [a1b2, a1B2, A1b2, A1B2]
        System.out.println(letterCasePermutation("3z4"));  // [3z4, 3Z4]
        System.out.println(letterCasePermutation("123"));  // [123]
    }
}

