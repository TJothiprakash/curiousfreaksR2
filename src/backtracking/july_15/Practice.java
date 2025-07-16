package backtracking.july_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice {

    public static void main(String[] args) {

    }

    public List<List<Integer>> combinationSum(int arr[], int target) {
        Arrays.sort(arr);
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        int sum = 0;
        helper(0, target, arr, path, result);
        return result;
    }

    private void helper(int start, int remaining, int[] arr, List<Integer> path, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > remaining) break;
            path.add(arr[i]);
            helper(i, remaining - arr[i], arr, path, result);
            path.remove(path.size() - 1);
        }
    }




}