package dynamic_programming.july_23;
import java.util.*;

public class HouseRobberI {
    public static int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robHelper(nums, nums.length - 1, memo);
    }

    private static int robHelper(int[] nums, int i, int[] memo) {
        if (i < 0) return 0;
        if (memo[i] != -1) return memo[i];

        // Option 1: Skip current
        int skip = robHelper(nums, i - 1, memo);

        // Option 2: Rob current + result from i-2
        int rob = nums[i] + robHelper(nums, i - 2, memo);

        memo[i] = Math.max(skip, rob);
        return memo[i];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println("Max Robbed: " + rob(nums)); // Output: 12
    }
}
