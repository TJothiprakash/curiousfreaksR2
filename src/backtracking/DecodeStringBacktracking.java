package backtracking;
/*Given an encoded string s, the task is to decode it. The encoding rule is :

k[encodedString], where the encodedString inside the square brackets
is being repeated exactly k times. Note that k is guaranteed to be a positive integer,
 and encodedString contains only lowercase english alphabets.
Note: The test cases are generated so that the length of the output string will never exceed 105 .

Examples:

Input: s = "1[b]"
Output: "b"
Explanation: "b" is present only one time.
Input: s = "3[b2[ca]]"
Output: "bcacabcacabcaca"
Explanation:
1. Inner substring “2[ca]” breakdown into “caca”.
2. Now, new string becomes “3[bcaca]”
3. Similarly “3[bcaca]” becomes “bcacabcacabcaca ” which is final result.
Constraints:
1 ≤ |s| ≤ 105
1 <= k <= 100

Expected Complexities*/
public class DecodeStringBacktracking {
    static int index;

    public static String decodeString(String s) {
        index = 0;  // Start from the beginning
        return backtrack(s);
    }

    private static String backtrack(String s) {
        StringBuilder result = new StringBuilder();
        int n = s.length();

        while (index < n) {
            char ch = s.charAt(index);

            if (Character.isDigit(ch)) {
                // Parse the full repeat count
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count = count * 10 + (s.charAt(index) - '0');
                    index++;
                }

                index++; // Skip '['

                // Recurse to decode the substring inside the brackets
                String sub = backtrack(s);

                // Append 'sub' count times
                for (int i = 0; i < count; i++) {
                    result.append(sub);
                }

            } else if (ch == ']') {
                index++; // Move past ']'
                return result.toString(); // End of current backtracking level
            } else {
                result.append(ch); // Just add the character
                index++;
            }
        }

        return result.toString();
    }

    // Test
    public static void main(String[] args) {
        System.out.println(decodeString("1[b]"));         // Output: b
        System.out.println(decodeString("3[b2[ca]]"));    // Output: bcacabcacabcaca
        System.out.println(decodeString("2[a2[b]c]"));    // Output: abbcbabc
    }
}
