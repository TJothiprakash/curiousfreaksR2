package backtracking.july_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test1 {
/*    @Test
    void testPermuteString() {
        Test t = new Test();
        List<String> out = t.permuteString("abc");
        assertEquals(Set.of("abc","acb","bac","bca","cab","cba"),
                new HashSet<>(out));
    }

    @Test
    void testPermuteUnique() {
        Test t = new Test();
        List<List<Integer>> out = t.permuteUnique(new int[]{1,1,2});
        assertEquals(3, out.size());
    }*/

    public static void main(String[] args) {

    }

    private void permute(char[] chars, boolean[] used, StringBuilder sb, List<String> out) {
        if (sb.length() == chars.length) {
            out.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            sb.append(chars[i]);
            permute(chars, used, sb, out);
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }

    public List<String> permuteString(String s) {
        List<String> res = new ArrayList<>();
        permute(s.toCharArray(), new boolean[s.length()], new StringBuilder(), res);
        return res;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);                       // needed for duplicate skipping
        List<List<Integer>> out = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), out);
        return out;
    }

    private void backtrack(int[] nums, boolean[] used,
                           List<Integer> path, List<List<Integer>> out) {

        if (path.size() == nums.length) {
            out.add(new ArrayList<>(path));      // deep copy
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            // Skip duplicates: only use first instance at this depth
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            path.add(nums[i]);

            backtrack(nums, used, path, out);

            path.remove(path.size() - 1);
            used[i] = false;
        }
    }


    public List<List<Integer>> combinationSUmII(int candidates[], int target) {


        return null;
    }

}
