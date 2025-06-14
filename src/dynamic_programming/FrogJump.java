package dynamic_programming;

public class FrogJump {
    // Order matters
    static long countWays(int n) {
        int[] memo = new int[n + 1]; // size should be n+1 to include index n
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return helperToFindNoOfWays(n, memo);
    }

    private static long helperToFindNoOfWays(int n, int[] memo) {
        // Base cases
        if (n == 0) return 1;  // 1 way to stay at ground
        if (n < 0) return 0;   // No way to reach negative steps
        if (memo[n] != -1) return memo[n];

        // Store result in memo
        memo[n] = (int) (
                helperToFindNoOfWays(n - 1, memo) +
                        helperToFindNoOfWays(n - 2, memo) +
                        helperToFindNoOfWays(n - 3, memo)
        );

        return memo[n];
    }

    // Order does not matter — still limited to 1 and 2 steps
    public static long countWays2(int n) {
        int count = 0;

        for (int twos = 0; twos <= n / 2; twos++) {
            int ones = n - 2 * twos;
            // For order not to matter, each (ones, twos) pair is 1 way
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int n =4;
        System.out.println(countWays(n));
        System.out.println(countWays2(n));
    }
}
