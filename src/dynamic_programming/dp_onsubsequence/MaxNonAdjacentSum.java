package dynamic_programming.dp_onsubsequence;
import java.util.Arrays;

public class MaxNonAdjacentSum {

    public static int maxSubsetSumNoAdjacent(int[] arr) {
        int n = arr.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return helper(arr, n - 1, memo);
    }

    private static int helper(int[] arr, int i, int[] memo) {
        // Base cases
        if (i < 0) return 0;
        if (i == 0) return arr[0];

        // Check memo
        if (memo[i] != -1) return memo[i];

        // Recurrence
        int pick = arr[i] + helper(arr, i - 2, memo);
        int notPick = helper(arr, i - 1, memo);

        return memo[i] = Math.max(pick, notPick);
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 5, 10, 100, 10, 5};
        int[] arr2 = {3, 2, 7, 10};
        int[] arr3 = {9, 1, 6, 10};

        System.out.println(maxSubsetSumNoAdjacent(arr1)); // 110
        System.out.println(maxSubsetSumNoAdjacent(arr2)); // 13
        System.out.println(maxSubsetSumNoAdjacent(arr3)); // 19
    }
}
