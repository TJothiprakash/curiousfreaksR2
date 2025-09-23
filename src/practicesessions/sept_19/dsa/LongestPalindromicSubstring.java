package practicesessions.sept_19.dsa;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring obj = new LongestPalindromicSubstring();
        System.out.println(obj.longestPalindrome("babad")); // bab or aba
        System.out.println(obj.longestPalindrome("cbbd"));  // bb
    }

    private String s;
    private Map<String, Boolean> memo;

    public String longestPalindrome(String s) {
        this.s = s;
        this.memo = new HashMap<>();
        int n = s.length();

        int start = 0, maxLen = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j)) {
                    int len = j - i + 1;
                    if (len > maxLen) {
                        maxLen = len;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    private boolean isPalindrome(int i, int j) {
        if (i >= j) return true;

        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);

        boolean res;
        if (s.charAt(i) != s.charAt(j)) {
            res = false;
        } else {
            res = isPalindrome(i + 1, j - 1);
        }

        memo.put(key, res);
        return res;
    }
}

/*  private Boolean[][] memo;

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) return false;
        int target = sum / 2;
        memo = new Boolean[nums.length][target + 1];

        return dfs(nums, 0, target);
    }

    private boolean dfs(int[] nums, int index, int target) {
        if (target == 0) return true;
        if (index >= nums.length || target < 0) return false;

        if (memo[index][target] != null) return memo[index][target];

        boolean include = dfs(nums, index + 1, target - nums[index]);
        boolean exclude = dfs(nums, index + 1, target);

        return memo[index][target] = (include || exclude);
    }*/
