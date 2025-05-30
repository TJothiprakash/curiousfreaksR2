package backtracking;

import java.util.*;

public class StringPermutation {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<String> result = new HashSet<>();
        permute(nums, 0, result);

        List<List<Integer>> ans = new ArrayList<>();
        for (String perm : result) {
            List<Integer> temp = new ArrayList<>();
            for (char ch : perm.toCharArray()) {
                temp.add(ch - '0');  // Convert char digit to int
            }
            ans.add(temp);
        }

        return ans;
    }

    private void permute(int[] nums, int index, Set<String> result) {
        if (index == nums.length) {
            // Convert current permutation to a string key
            StringBuilder sb = new StringBuilder();
            for (int num : nums) {
                sb.append(num);
            }
            result.add(sb.toString());
            return;
        }

        Set<Integer> seen = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (seen.contains(nums[i])) continue;
            seen.add(nums[i]);

            swap(nums, index, i);
            permute(nums, index + 1, result);
            swap(nums, index, i); // backtrack
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

class UniqueCombinationII {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        permute(nums, 0, result);

        return new ArrayList<>(result);
    }

    private static void permute(int[] nums, int index, Set<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }

        Set<Integer> seen = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (seen.contains(nums[i])) continue;
            seen.add(nums[i]);

            swap(nums, index, i);
            permute(nums, index + 1, result);
            swap(nums, index, i); // backtrack
        }
    }

    private static void  swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Optional: for testing
    public static void main(String[] args) {
        UniqueCombinationII sol = new UniqueCombinationII();
        System.out.println(sol.permuteUnique(new int[]{1, 1, 2}));
        System.out.println(sol.permuteUnique(new int[]{11, 22, 11}));
    }
}


class UniquePermutations {

    public static List<String> getUniquePermutations(String s) {
        Set<String> result = new HashSet<>();
        char[] chars = s.toCharArray();
        permute(chars, 0, result);
        return new ArrayList<>(result);
    }

    private static void permute(char[] chars, int index, Set<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        Set<Character> seen = new HashSet<>(); // Local set to avoid duplicate swaps at this level
        for (int i = index; i < chars.length; i++) {
            if (seen.contains(chars[i])) continue;
            seen.add(chars[i]);

            swap(chars, index, i);
            permute(chars, index + 1, result);
            swap(chars, index, i); // backtrack
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // Example usage
    public static void main(String[] args) {
        System.out.println(getUniquePermutations("ABC"));  // [ABC, ACB, BAC, BCA, CAB, CBA]
        System.out.println(getUniquePermutations("AAA"));  // [AAA]
        System.out.println(getUniquePermutations("ABSG")); // 24 unique permutations
    }
}
