package backtracking;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SubsetsLexicographical {
    public static List<List<Integer>> subsets(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);  // Ensure lexicographical order
        backtrack(0, arr, new ArrayList<>(), result);

        return result;
    }

    private static void backtrack(int index, int @NotNull [] arr, List<Integer> current, @NotNull List<List<Integer>> result) {
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
        System.out.println(s.subsets(new int[]{1, 2, 3}));
        findUniqueSubsets(new int[]{1, 2, 3});
    }

    public static void findUniqueSubsets(int[] arr) {
        List<List<Integer>> result = subsets(arr);
        HashSet<List<Integer>> set = new HashSet<>(result);
        System.out.println(set.size() + " " + set);
    }

}
