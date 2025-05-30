package backtracking;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SubsetsLexicographical {
    public List<List<Integer>> subsets(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);  // Ensure lexicographical order
        backtrack(0, arr, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int index, int @NotNull [] arr, List<Integer> current, @NotNull List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // Add a copy of the current subset

        for (int i = index; i < arr.length; i++) {
            current.add(arr[i]);
            backtrack(i + 1, arr, current, result);  // Recurse
            current.remove(current.size() - 1);      // Backtrack
        }
    }

    // Driver code
    public static void main(String[] args) {
        SubsetsLexicographical s = new SubsetsLexicographical();
        System.out.println(s.subsets(new int[]{1, 2, 3}));
        System.out.println(s.subsets(new int[]{1, 2}));
        System.out.println(s.subsets(new int[]{10}));
    }
}
