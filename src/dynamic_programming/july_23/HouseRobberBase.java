package dynamic_programming.july_23;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberBase {
    protected int robRecursively(int[] nums, Map<Integer, Integer> memo, int start, int end) {
        if (start > end) return 0;
        if (memo.containsKey(start)) return memo.get(start);

        int skip = robRecursively(nums, memo, start + 1, end);
        int rob = nums[start] + robRecursively(nums, memo, start + 2, end);
        int result = Math.max(skip, rob);

        memo.put(start, result);
        return result;
    }
}

class HOUSERobberI extends HouseRobberBase {
    public int rob(int[] nums) {
        Map<Integer, Integer> memo = new HashMap<>();
        return robRecursively(nums, memo, 0, nums.length - 1);
    }
}

class HOUSERobberII extends HouseRobberBase {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // Case 1: Rob from 0 to n-2 (exclude last)
        Map<Integer, Integer> memo1 = new HashMap<>();
        int case1 = robRecursively(nums, memo1, 0, n - 2);

        // Case 2: Rob from 1 to n-1 (exclude first)
        Map<Integer, Integer> memo2 = new HashMap<>();
        int case2 = robRecursively(nums, memo2, 1, n - 1);

        return Math.max(case1, case2);
    }
}

class Main {
    public static void main(String[] args) {
        // main goes here

    }
}
