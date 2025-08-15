package practicesessions.aug_13;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIV {
    int res = 0;

    public int combinationSum4(int[] nums, int target) {
        Integer[] dp = new Integer[target + 1];
        generate(nums, 0, target, new ArrayList<>(), 0, dp);
        return res;
    }

    public void generate(int[] nums, int index, int target, List<Integer> curr, int sum, Integer[] dp) {

        if (sum == target) {
            res++;
            return;
        }
        if (sum > target) return;

        if (dp[sum] != null) {
            res += dp[sum];
            return;
        }
        int before = res;

        for (int i = 0; i < nums.length; i++) {
            curr.add(nums[i]);
            generate(nums, i, target, curr, sum + nums[i], dp);
            curr.remove(curr.size() - 1);
        }
        dp[sum] = res - before;

    }
}