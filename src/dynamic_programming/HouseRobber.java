package dynamic_programming;

// I & II problem
public class HouseRobber {


    private static int robHelper(int[] nums, int i) {
        // Base cases
        if (i < 0) return 0; // No houses left to rob
        if (i == 0) return nums[0]; // Only one house to rob

        // Rob current house or skip it
        int robCurrent = nums[i] + robHelper(nums, i - 2); // Rob this house
        int skipCurrent = robHelper(nums, i - 1);          // Skip this house

        // Return the maximum of both choices
        return Math.max(robCurrent, skipCurrent);
    }

    public int rob(int[] nums) {
        // Start recursion from the last house
        return robHelper(nums, nums.length - 1);
    }


    public int robCircularHouses(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        // Rob houses excluding the last house
        int maxRobExcludingLast = robLinear(nums, 0, nums.length - 2);
        // Rob houses excluding the first house
        int maxRobExcludingFirst = robLinear(nums, 1, nums.length - 1);

        // Return the maximum of the two
        return Math.max(maxRobExcludingLast, maxRobExcludingFirst);
    }

    private int robLinear(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;

        for (int i = start; i <= end; i++) {
            int current = Math.max(prev2 + nums[i], prev1);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
