package dynamic_programming;

public class F {
    public static void findFibonacci(int n) {
        int[] dp = new int[n + 1]; // Array to store results for memoization
        for (int i = 0; i <= n; i++) {
            dp[i] = -1; // Initialize all values to -1 (uncomputed state)
        }
        int ans = fibonacciOfN(n, dp);
        System.out.println("Fibonacci number is " + ans);
    }

    private static int fibonacciOfN(int n, int[] dp) {
        if (n == 0) {
            return 0; // Base case: F(0) = 0
        }
        if (n == 1) {
            return 1; // Base case: F(1) = 1
        }

        if (dp[n] != -1) {
            return dp[n]; // Return already computed value
        }

        // Compute and store the result in the dp array
        dp[n] = fibonacciOfN(n - 1, dp) + fibonacciOfN(n - 2, dp);
        return dp[n];
    }
}
