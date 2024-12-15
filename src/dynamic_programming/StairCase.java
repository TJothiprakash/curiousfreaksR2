package dynamic_programming;

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

