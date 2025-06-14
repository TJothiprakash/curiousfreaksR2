package dynamic_programming;

import java.util.Arrays;

public class StairCase {

    // Order Matters
    public static int countWays(int n, int[] arr) {
        if (n == 0) {
            return 1; // Base case: 1 way to stay at step 0
        }
        if (n < 0) {
            return 0; // No way if n becomes negative
        }

        int ways = 0;
        for (int num : arr) {
            ways += countWays(n - num, arr); // Add ways by taking each step
        }
        return ways;
    }

    // Order Does Not Matter
    public static int countWaysOrderDoesNotMatter(int n, int[] arr) {
        return countWaysHelper(n, arr, 0);
    }

    private static int countWaysHelper(int n, int[] arr, int index) {
        if (n == 0) {
            return 1; // Base case: 1 way to stay at step 0
        }
        if (n < 0) {
            return 0; // No way if n becomes negative
        }

        int ways = 0;
        for (int i = index; i < arr.length; i++) {
            ways += countWaysHelper(n - arr[i], arr, i); // Process current and later steps only
        }
        return ways;
    }

    public static void main(String[] args) {
        int n = 3; // Target step
        int[] arr = {1, 2}; // Possible steps

        // Order matters
        System.out.println("Order Matters: " + countWays(n, arr));

        // Order does not matter
        System.out.println("Order Does Not Matter: " + countWaysOrderDoesNotMatter(n, arr));
    }


}
// 1,2,3,4 with order matter / doesn't

class FrogJumpCombinations {

    public static int countWaysOrderDoesNotMatter(int n) {
        int[] steps = {1, 2, 3, 4};  // allowed step sizes
        int[][] memo = new int[n + 1][steps.length + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return countCombinations(n, steps, 0, memo);
    }

    private static int countCombinations(int n, int[] steps, int index, int[][] memo) {
        if (n == 0) return 1;
        if (n < 0 || index >= steps.length) return 0;

        if (memo[n][index] != -1) return memo[n][index];

        // Choose the current step
        int take = countCombinations(n - steps[index], steps, index, memo);

        // Skip the current step (move to next larger step)
        int skip = countCombinations(n, steps, index + 1, memo);

        memo[n][index] = take + skip;
        return memo[n][index];
    }

    public static void main(String[] args) {
        System.out.println(countWaysOrderDoesNotMatter(5)); // Example: output combinations to reach 5
    }
}


