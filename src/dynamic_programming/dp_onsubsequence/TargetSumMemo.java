package dynamic_programming.dp_onsubsequence;
/*✅ Target Sum — Recursive + Memoization (Java)
        🧠 Reminder:
        We need to count the number of ways to assign + or - to each number
         in nums[] so that their sum becomes target.

        ✅ Approach:
        At each index, you have two choices:

        Add the current number (+nums[i])

        Subtract the current number (-nums[i])

        We'll explore both recursively and memoize the results to avoid recomputation.

        💻 Java Code:
        java
        Copy
        Edit
import java.sql.Time;*/

import java.util.HashMap;
import java.util.Map;

public class TargetSumMemo {
    public int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> memo = new HashMap<>();
        return countWays(nums, 0, target, memo);
    }

    private int countWays(int[] nums, int index, int target, Map<String, Integer> memo) {
        // Base case
        if (index == nums.length) {
            return target == 0 ? 1 : 0;
        }

        String key = index + "," + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Try adding and subtracting current element
        int add = countWays(nums, index + 1, target - nums[index], memo);
        int subtract = countWays(nums, index + 1, target + nums[index], memo);

        memo.put(key, add + subtract);
        return add + subtract;
    }

    public static void main(String[] args) {
        TargetSumMemo sol = new TargetSumMemo();
        System.out.println(sol.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3)); // 5
        System.out.println(sol.findTargetSumWays(new int[]{1}, 1));             // 1
    }
}/*
🧠 Explanation:
At every index, you choose whether to +nums[i] or -nums[i].

Use a key in the form index,target to cache previously computed subproblems.

Return the total number of valid combinations.

⏱ Time and Space Complexity:
Part	Complexity
Time O(n * sum) — where sum is max absolute value target can reach
Space	O(n * sum) — for memoization map

*/