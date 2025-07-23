package dynamic_programming.july_23;

import java.util.Arrays;

public class DynamicProgramming1D {
    public static void main(String[] args) {
        int n = 6;
        int ans = countWaysMemoize(6);
        System.out.println("ans = " + ans);
    }

    //    frog jumps
    static int countWays(int n) {
        if (n == 0) return 1;
        if (n < 1) return 0;
        // add your code here
        return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
    }

    static int countWays(int n, int[] memo) {
        if (n == 0) return 1;
        if (n < 1) return 0;
        if (memo[n] != -1) return memo[n];
        // add your code here
        memo[n] = countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
        return memo[n];
    }

// only combination
    public int nthStair(int n) {
        int[] possibleSteps = {1, 2};
        return helper(n, possibleSteps, 0);
    }

    int helper(int m, int[] arr, int start) {
        if (m == 0) return 1;
        if (m < 0) return 0;

        int ways = 0;
        for (int i = start; i < arr.length; i++) {
            ways += helper(m - arr[i], arr, i); // avoid permutations
        }

        return ways;
    }


    static int countWaysMemoize(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        // add your code here
        return countWays(n, memo);
    }


    int stairCase(int n) {
        // your code here
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return staircaseHelper(n, memo);

    }

    int staircaseHelper(int n, int[] memo) {
        if (n == 0) return 1;
        if (n < 1) return 0;
        if (memo[n] != -1) return memo[n];
        // add your code here
        memo[n] = staircaseHelper(n - 1, memo) + staircaseHelper(n - 2, memo);
        return memo[n];
    }

    //     staircase order does not matter
    int stairCaseII(int n) {
        // your code here
        int[] possibleSteps = {1, 2};

        return staircaseHelperII(n, possibleSteps);

    }

    private int staircaseHelperII(int n, int[] possibleSteps) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        int ways = 0;
        for (int i = 0; i < possibleSteps.length; i++) {
            ways += staircaseHelperII(n - possibleSteps[i], possibleSteps);
        }
        return 0;
    }

    int houserRobberI(int []homes){
        return 0;
    }
    int houserRobberII(int []homes){
        return 0;
    }

}
