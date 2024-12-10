package strings;
// convert string number to integer

public class Atoi {
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        int sign = 1; // Default sign is positive
        long result = 0; // Use long for intermediate overflow detection

        // Step 1: Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: Check for the sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Step 3: Process digits and construct the number
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            int digit = s.charAt(i) - '0'; // Convert char to int manually
            result = result * 10 + digit;

            // Step 4: Check for overflow
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        // Step 5: Return final result with sign
        return (int) (sign * result);
    }
}
