package backtracking;

import java.util.*;
/*

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.



Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
        [
        [1,1,6],
        [1,2,5],
        [1,7],
        [2,6]
        ]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output:
        [
        [1,2,2],
        [5]
        ]


Constraints:

        1 <= candidates.length <= 100
        1 <= candidates[i] <= 50
        1 <= target <= 30
*/


public class UniqueCombination {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Important to handle duplicates
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int start, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            if (candidates[i] > target) break; // Optimization

            current.add(candidates[i]);
            backtrack(candidates, i + 1, target - candidates[i], current, result);
            current.remove(current.size() - 1);
        }
    }
}
