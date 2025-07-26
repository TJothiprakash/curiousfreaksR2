package dynamic_programming.july_24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 2};

        int sum = 9;
        boolean result = isSubsetSum(arr, sum);
        System.out.println("Subset sum exists: " + result);
    }


    static int[][] memo;

    //minsubset sum difference using recursion with memo
    public static int minDifference(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        int n = nums.length;
        int target = totalSum / 2;

        memo = new int[n + 1][target + 1];
        for (int[] row : memo) Arrays.fill(row, -1);

        int maxSubsetSum = helper(nums, 0, 0, target);

        return totalSum - 2 * maxSubsetSum;
    }

    private static int helper(int[] nums, int i, int currSum, int target) {
        // Base case: all elements processed
        if (i == nums.length) return currSum;

        // Already computed
        if (memo[i][currSum] != -1) return memo[i][currSum];

        // Option 1: Exclude current element
        int exclude = helper(nums, i + 1, currSum, target);

        // Option 2: Include current element (only if it doesn't exceed target)
        int include = currSum;
        if (currSum + nums[i] <= target) {
            include = helper(nums, i + 1, currSum + nums[i], target);
        }

        // Take max sum ≤ target
        memo[i][currSum] = Math.max(include, exclude);
        return memo[i][currSum];
    }

    // min susbset sum partition
//     can divide the array into two parts ie) abs. sum of p1 - abs. sum p2 is lesser most possible
    public static int minsubsetSumPartition(int arr[]) {
        int sum = Arrays.stream(arr).sum();
        int left = 0;
        int right = 0;
        if (sum % 2 == 0) {
            return 0;
        } else {
            int mid = sum / 2;

            for (int i = mid; i >= 0; i--) {
                if (isSubsetSum(arr, i)) {
                    left = i;
                    break;
                }
            }
            for (int i = mid; i <= sum; i++) {
                if (isSubsetSum(arr, i)) {
                    right = i;
                    break;
                }
            }
        }

        return left - right;
    }


    //    can divide teh arr into two parts of equal sum?
    public static boolean subsetSumPartition(int arr[]) {
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 == 0) {
            return isSubsetSum(arr, (sum / 2));
        } else {
            System.out.println("we can't divide the thsi array into two subsets ");
        }

        return false;

    }

    public static boolean isSubsetSum(int[] arr, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        boolean exists = helper(arr, sum, 0, new ArrayList<>(), 0, res);
        System.out.println("Valid subsets: " + res);
        return exists;
    }

    private static boolean helper(int[] arr, int sum, int index, List<Integer> path, int currSum, List<List<Integer>> res) {
        if (currSum == sum) {
            res.add(new ArrayList<>(path));
            return true;
        }

        if (index >= arr.length) return false;

        // Exclude
        boolean exclude = helper(arr, sum, index + 1, path, currSum, res);

        // Include
        path.add(arr[index]);
        boolean include = helper(arr, sum, index + 1, path, currSum + arr[index], res);
        path.remove(path.size() - 1); // backtrack

        return exclude || include;
    }


}
