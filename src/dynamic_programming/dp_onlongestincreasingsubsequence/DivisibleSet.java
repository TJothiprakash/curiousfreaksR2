package dynamic_programming.dp_onlongestincreasingsubsequence;

import java.util.*;

public class DivisibleSet {
    // Function to find the longest divisible subset
    static List<Integer> divisibleSet(List<Integer> arr) {
        int n = arr.size();

        // Sort the array
        Collections.sort(arr);

        List<Integer> dp = new ArrayList<>(Collections.nCopies(n, 1));
        List<Integer> hash = new ArrayList<>(Collections.nCopies(n, 0));

        for (int i = 0; i < n; i++) {
            hash.set(i, i); // Initializing with current index
            for (int prev_index = 0; prev_index <= i - 1; prev_index++) {
                if (arr.get(i) % arr.get(prev_index) == 0 && 1 + dp.get(prev_index) > dp.get(i)) {
                    dp.set(i, 1 + dp.get(prev_index));
                    hash.set(i, prev_index);
                }
            }
        }

        int ans = -1;
        int lastIndex = -1;

        for (int i = 0; i < n; i++) {
            if (dp.get(i) > ans) {
                ans = dp.get(i);
                lastIndex = i;
            }
        }

        List<Integer> temp = new ArrayList<>();
        temp.add(arr.get(lastIndex));

        while (hash.get(lastIndex) != lastIndex) {
            lastIndex = hash.get(lastIndex);
            temp.add(arr.get(lastIndex));
        }

        // Reverse the array
        Collections.reverse(temp);

        return temp;
    }

    public static void main(String[] args) {

        List<Integer> arr = Arrays.asList(1, 16, 7, 8, 4);

        List<Integer> ans = divisibleSet(arr);

        System.out.print("The longest divisible subset elements are: ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}


class LargestDivisibleSubsetRecursive {
    static int[] nums;
    static Map<String, List<Integer>> memo;

    public static List<Integer> largestDivisibleSubset(int[] input) {
        Arrays.sort(input); // Sort for easier divisibility
        nums = input;
        memo = new HashMap<>();

        List<Integer> bestSubset = new ArrayList<>();

        // Try starting from every index
        for (int i = 0; i < nums.length; i++) {
            List<Integer> subset = dfs(i, -1);
            if (subset.size() > bestSubset.size()) {
                bestSubset = subset;
            }
        }

        return bestSubset;
    }

    // Recursive function to build subset
    private static List<Integer> dfs(int curr, int prev) {
        String key = curr + "|" + prev;
        if (memo.containsKey(key)) return memo.get(key);

        List<Integer> include = new ArrayList<>();
        if (prev == -1 || nums[curr] % nums[prev] == 0) {
            include.add(nums[curr]);
            List<Integer> nextBest = new ArrayList<>();
            for (int next = curr + 1; next < nums.length; next++) {
                List<Integer> candidate = dfs(next, curr);
                if (candidate.size() > nextBest.size()) {
                    nextBest = candidate;
                }
            }
            include.addAll(nextBest);
        }

        // Try skipping current
        List<Integer> exclude = dfs(curr + 1, prev);

        List<Integer> result = include.size() > exclude.size() ? include : exclude;
        memo.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(largestDivisibleSubset(new int[]{1, 2, 3}));     // Output: [1, 2] or [1, 3]
        System.out.println(largestDivisibleSubset(new int[]{1, 2, 4, 8})); // Output: [1, 2, 4, 8]
    }
}

