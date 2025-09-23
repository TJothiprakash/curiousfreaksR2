package practicesessions.sept_19.dsa;

import java.util.*;

public class PartitionEqualSubsetSumRecursive {

    private Map<String, Boolean> memo;

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) return false; // odd sum can't be partitioned
        int target = sum / 2;

        memo = new HashMap<>();
        return dfs(nums, 0, target);
    }

    private boolean dfs(int[] nums, int index, int target) {
        if (target == 0) return true;      // found a valid subset
        if (index >= nums.length || target < 0) return false;

        String key = index + "," + target;
        if (memo.containsKey(key)) return memo.get(key);

        // Choose or skip
        boolean include = dfs(nums, index + 1, target - nums[index]);
        boolean exclude = dfs(nums, index + 1, target);

        boolean result = include || exclude;
        memo.put(key, result);

        return result;
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSumRecursive solver = new PartitionEqualSubsetSumRecursive();
        int[] nums1 = {1, 5, 11, 5};
        int[] nums2 = {1, 2, 3, 5};

        System.out.println(solver.canPartition(nums1)); // true
        System.out.println(solver.canPartition(nums2)); // false
    }
}

