package backtracking.basics;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SubsetBacktracking {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);

        // Print result
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    static void backtrack(int start, int @NotNull [] nums, List<Integer> current, @NotNull List<List<Integer>> result) {
        // ✅ Save current path (make a copy)
        result.add(new ArrayList<>(current));

        // 🔁 Loop over choices
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);                        // pick
            backtrack(i + 1, nums, current, result);     // explore
            current.remove(current.size() - 1);          // ⬅️ backtrack
        }
    }
}


/*🧠 Key Takeaways
Concept	Explanation
Backtracking	Undo the last choice after recursion
Path copying	Always add new ArrayList<>(current)
Loop inside recursion	To control which elements can be used next

*/