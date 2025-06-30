package backtracking.basics;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/*🧩 1. Permutations (Backtracking)
🔹 Problem:
Given nums = [1, 2, 3], generate all permutations
Output:

Copy
Edit
[1, 2, 3]
[1, 3, 2]
[2, 1, 3]
[2, 3, 1]
[3, 1, 2]
[3, 2, 1]
🧠 Intuition:
At each level, loop over all unused elements

Use a boolean[] visited array to track used ones

Backtrack: remove last, mark as unvisited

⏱️ Time & Space Complexity:
Time: O(n!) → total permutations

Space: O(n) recursion + O(n) visited[] = O(n)*/
public class PermutationBacktracking {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), visited, result);
        System.out.println(result);
        System.out.println(result.size() + " permutations");
//        for (List<Integer> perm : result) {
//            System.out.println(perm);
//        }
    }

    static void backtrack(int @NotNull [] nums, @NotNull List<Integer> path, boolean[] visited, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            path.add(nums[i]);
            backtrack(nums, path, visited, result);
            path.remove(path.size() - 1);  // 🔁 Backtrack
            visited[i] = false;
        }
    }
}

