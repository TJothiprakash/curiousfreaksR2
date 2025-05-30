package backtracking;

import java.util.*;

public class UniqueSubsets {
    public static List<List<Integer>> AllSubsets(int[] arr, int N) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr); // Sort for lex order and duplicate handling
        backtrack(arr, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] arr, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // Add current subset

        for (int i = start; i < arr.length; i++) {
            // Skip duplicates
            if (i > start && arr[i] == arr[i - 1]) continue;

            current.add(arr[i]);
            backtrack(arr, i + 1, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }

    // Helper for printing
    public static void main(String[] args) {
        int[] arr1 = {2, 1, 2};
        List<List<Integer>> subsets1 = AllSubsets(arr1, arr1.length);
        for (List<Integer> subset : subsets1) {
            System.out.println(subset);
        }
        System.out.println("total number of combos are "+subsets1.size());

        System.out.println("------");

        int[] arr2 = {1, 2, 3, 3};
        List<List<Integer>> subsets2 = AllSubsets(arr2, arr2.length);
        for (List<Integer> subset : subsets2) {
            System.out.println(subset);
        }
        System.out.println("total number of combos are "+subsets2.size());
    }
}
