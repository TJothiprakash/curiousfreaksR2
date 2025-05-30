package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public static void main(String[] args) {
        CombinationSumIII sol = new CombinationSumIII();
        List<List<Integer>> result = sol.combinationSum3(3, 9);
        System.out.println(result);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int k, int target, List<Integer> current, List<List<Integer>> result) {
        if (k == 0 && target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i > target) break; // Optimization: prune the search

            current.add(i);
            backtrack(i + 1, k - 1, target - i, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }
}
