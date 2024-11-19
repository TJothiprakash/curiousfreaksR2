package recursion;

public class RecursiveATOI {
    public static void main(String[] args) {
        String s1 = "  -0012gfg4";
        String s2 = " 1231231231311133";
        String s3 = "-999999999999";
        String s4 = "42";
        String s5="ad56";

        System.out.println(atoi(s5));
        System.out.println(atoi(s1)); // Output: -12
        System.out.println(atoi(s2)); // Output: 2147483647
        System.out.println(atoi(s3)); // Output: -2147483648
        System.out.println(atoi(s4)); // Output: 42
    }

    public static int atoi(String s) {
        // Mutable index to track the current character during recursion
        int[] index = {0};
        return parseNumber(s.trim(), index, 0, 1);
    }

    private static int parseNumber(String s, int[] index, int result, int sign) {
        // Base case: If index is out of bounds or a non-digit is encountered
        if (index[0] >= s.length()) {
            return result;
        }

        char c = s.charAt(index[0]);

        // Handle the sign at the start
        if (index[0] == 0 && (c == '+' || c == '-')) {
            sign = (c == '-') ? -1 : 1;
            index[0]++;
            return parseNumber(s, index, result, sign);
        }

        // If character is not a digit, stop parsing
        if (c < '0' || c > '9') {
            return result * sign;
        }

        // Add current digit to the result
        int digit = c - '0';
        if (result > (Integer.MAX_VALUE - digit) / 10) {
            // Check for overflow and return appropriate limit
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        result = result * 10 + digit;

        // Increment index and continue parsing
        index[0]++;
        return parseNumber(s, index, result, sign);
    }
}
