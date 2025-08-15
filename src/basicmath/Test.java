package basicmath;

public class Test {
    public static void main(String[] args) {
        int n = 27;
        new Solution2().isPowerOfThree(n);
    }
}

class Solution2 {
    public boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}


/*1780. Check if Number is a Sum of Powers of Three
Solved
Medium
Topics
premium lock icon
Companies
Hint
Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.

An integer y is a power of three if there exists an integer x such that y == 3x.



Example 1:

Input: n = 12
Output: true
Explanation: 12 = 31 + 32
Example 2:

Input: n = 91
Output: true
Explanation: 91 = 30 + 32 + 34
Example 3:

Input: n = 21
Output: false


Constraints:

1 <= n <= 107*/
class Solution3 {

    public boolean checkPowersOfThree(int n) {
        return checkPowersOfThreeHelper(0, n);
    }

    private boolean checkPowersOfThreeHelper(int power, int n) {
        // Base case: if n becomes 0, we have successfully formed the sum
        if (n == 0) return true;

        // If the current power of 3 exceeds n, we can't use it, so return false
        if (Math.pow(3, power) > n) return false;

        // Option 1: Include the current power of 3 and subtract it from n
        boolean addPower = checkPowersOfThreeHelper(
                power + 1,
                n - (int) Math.pow(3, power)
        );

        // Option 2: Skip the current power of 3 and try with the next power
        boolean skipPower = checkPowersOfThreeHelper(power + 1, n);

        // Return true if either option leads to a valid solution
        return addPower || skipPower;
    }
}