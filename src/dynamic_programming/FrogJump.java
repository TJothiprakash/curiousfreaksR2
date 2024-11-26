package dynamic_programming;

public class FrogJump {
// order does matter
    static long countWays(int n) {
        // add your code here
        long ans = helpertofindNoofWays(n);

        return ans;
    }

    private static long helpertofindNoofWays(int n) {
        // Base cases
        if (n == 0) return 1; // 1 way to stay at ground
        if (n < 0) return 0;  // No way to reach negative steps

        // Recursive calls for 1-step, 2-steps, and 3-steps
        return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
    }

   // order does not matter
    public static long countWays2(int n) {
        int count = 0;

        // Loop through the number of 2-steps from 0 to maximum possible
        for (int twos = 0; twos <= n / 2; twos++) {
            // Remaining steps to be filled with 1-steps
            int ones = n - 2 * twos;

            // Each combination of 'twos' and 'ones' is a unique way
            count++;
        }

        return count;

    }
}
