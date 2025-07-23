package dynamic_programming.july_23;

public class HouseRobberII {

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        Integer[] memo1 = new Integer[nums.length];
        Integer[] memo2 = new Integer[nums.length];

        int option1 = helper(nums, 0, nums.length - 2, memo1); // rob from 0 to n-2
        int option2 = helper(nums, 1, nums.length - 1, memo2); // rob from 1 to n-1

        return Math.max(option1, option2);
    }

    private int helper(int[] nums, int start, int end, Integer[] memo) {
        if (start > end) return 0;
        if (memo[end] != null) return memo[end];

        int skip = helper(nums, start, end - 1, memo);
        int rob = nums[end] + helper(nums, start, end - 2, memo);

        memo[end] = Math.max(skip, rob);
        return memo[end];
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 2};
        System.out.println(new HouseRobberII().rob(nums)); // Output: 3
    }
}
