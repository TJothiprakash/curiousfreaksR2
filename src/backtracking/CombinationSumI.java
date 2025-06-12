package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Given an array arr[] and a target, your task is to find all unique combinations
in the array where the sum is equal to target. The same number may be chosen from the array
any number of times to make target.

You can return your answer in any order.

Examples:

Input: arr[] = [2, 4, 6, 8], target = 8
Output: [[2 2 2 2] [2 2 4] [2 6] [4 4] [8]]
Explanation: Total number of possible combinations are 5.
Input: arr[] = [2, 7, 6, 5], target = 16
Output: [[2 2 2 2 2 2 2 2] [2 2 2 2 2 6] [2 2 2 5 5] [2 2 5 7] [2 2 6 6] [2 7 7] [5 5 6]]
Explanation: Total number of possible combinations are 7.
Input: arr[] = [6, 5, 7], target = 8
Output: []
Explanation: There are no possible combinantions such that target sum is 8.
Constraints:
1 <= arr.size() <= 30
2 <= arr[i] <= 40
2 <= target <= 40
All arr[i] are distinct.

Expected Complexities
Co*/
public class CombinationSumI {

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8};
        int target = 8;
        CombinationSumI sol = new CombinationSumI();
        List<List<Integer>> result = sol.combinationSum(arr, target);
        System.out.println(result);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Optional for pruning
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] arr, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            if (arr[i] > target) break; // Optimization

            current.add(arr[i]);
            backtrack(arr, target - arr[i], i, current, result); // Reuse allowed => i (not i + 1)
            current.remove(current.size() - 1); // Backtrack
        }
    }
}
