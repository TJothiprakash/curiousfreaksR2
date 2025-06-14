package dynamic_programming.dp_onsubsequence;

import java.util.Arrays;


/*✅ Problem Statement:
Given an array arr[], determine if it can be split into two subsets with equal sum.

🧠 Key Insight:
If total sum is odd → cannot split into two equal subsets.

If total sum is even → check whether a subset exists with sum = total / 2.

This reduces to:
Can we find a subset whose sum = target = totalSum / 2?

✅ Approach: Recursion + Memoization
💡 Recurrence:
At each index i, we either:

Pick the element (if it doesn't overshoot sum)

Skip the element

So:

java
Copy
Edit
boolean subsetExists(i, target) = subsetExists(i-1, target) || subsetExists(i-1, target - arr[i])
*/
public class PartitionEqualSubsetSum {

    public static boolean canPartition(int[] arr) {
        int total = Arrays.stream(arr).sum();

        // If total is odd, can't divide into two equal subsets
        if (total % 2 != 0) return false;

        int target = total / 2;
        int n = arr.length;
        Boolean[][] memo = new Boolean[n][target + 1];

        return helper(n - 1, target, arr, memo);
    }

    private static boolean helper(int i, int target, int[] arr, Boolean[][] memo) {
        if (target == 0) return true;
        if (i < 0 || target < 0) return false;

        if (memo[i][target] != null) return memo[i][target];

        // Either pick or not pick the current element
        boolean pick = false;
        if (arr[i] <= target)
            pick = helper(i - 1, target - arr[i], arr, memo);

        boolean notPick = helper(i - 1, target, arr, memo);

        return memo[i][target] = pick || notPick;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 5, 11, 5};
        int[] arr2 = {1, 3, 5};

        System.out.println(canPartition(arr1)); // true
        System.out.println(canPartition(arr2)); // false
    }
}
