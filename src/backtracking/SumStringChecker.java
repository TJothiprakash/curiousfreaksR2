package backtracking;

/*Given a string s consisting of digits, determine whether it can be classified as a sum-string.

A sum-string is a string that can be split into more than two non-empty substrings such that:


The rightmost substring is equal to the sum of the two substrings immediately before it (interpreted as integers).

This condition must apply recursively to the substrings before it.

The rightmost substring (and any number in the sum) must not contain leading zeroes, unless the number is exactly '0'.

Examples:

Input: s = "12243660"
Output: true
Explanation: The string can be split as {"12", "24", "36", "60"} where each number is the sum of the two before it:
36 = 12 + 24, and 60 = 24 + 36. Hence, it is a sum-string.
Input: s = "1111112223"
Output: true
Explanation: Split the string as {"1", "111", "112", "223"}, where:
112 = 1 + 111 and 223 = 111 + 112. Hence, it follows the sum-string rule.
Input: s = "123456"
Output: false
Explanation: There is no valid split of the string such that each part satisfies the sum-string property recursively.
Constraints:
1 ≤ s.size() ≤ 100
String consists of characters from '0' to '9'.


 Key Observations
We split the string into numbers a, b, and check if the next part of the string starts with sum(a + b).

Recursively continue checking: b, a + b, b + (a + b)...

*/
public class SumStringChecker {

    public static boolean isSumString(String s) {
        int n = s.length();

        // Try all possible first and second number lengths
        for (int i = 1; i <= n / 2; i++) {
            for (int j = 1; j <= (n - i) / 2; j++) {
                String s1 = s.substring(0, i);
                String s2 = s.substring(i, i + j);

                // Skip if any number has leading zeros (except "0" itself)
                if ((s1.length() > 1 && s1.startsWith("0")) || (s2.length() > 1 && s2.startsWith("0")))
                    continue;

                if (checkSumString(s1, s2, s.substring(i + j)))
                    return true;
            }
        }

        return false;
    }

    // Recursive function to check the sum-string property
    private static boolean checkSumString(String a, String b, String remaining) {
        String sum = addStrings(a, b);
        if (!remaining.startsWith(sum))
            return false;

        if (remaining.equals(sum)) // Base case: matched exactly
            return true;

        return checkSumString(b, sum, remaining.substring(sum.length()));
    }

    // Add two numeric strings and return the result as string
    private static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();

        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int d1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int d2 = j >= 0 ? num2.charAt(j--) - '0' : 0;

            int sum = d1 + d2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }

        return sb.reverse().toString();
    }

    // Driver
    public static void main(String[] args) {
        System.out.println(isSumString("12243660"));     // true
        System.out.println(isSumString("1111112223"));   // true
        System.out.println(isSumString("123456"));       // false
        System.out.println(isSumString("199100199"));    // true => 199 + 100 = 299
    }
}

