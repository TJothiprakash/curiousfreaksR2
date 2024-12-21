package dynamic_programming.dp_onlongestincreasingsubsequence;

import java.util.Arrays;

public class NumberOfLIS {
    public static void main(String[] args) {
        NumberOfLIS solution = new NumberOfLIS();

        int[] nums1 = {1, 3, 5, 4, 7};
        System.out.println(solution.findNumberOfLIS(nums1)); // Output: 2

        int[] nums2 = {2, 2, 2, 2, 2};
        System.out.println(solution.findNumberOfLIS(nums2)); // Output: 5
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // Memoization arrays for LIS lengths and counts
        int[] lisLength = new int[n];
        int[] count = new int[n];
        Arrays.fill(lisLength, -1);
        Arrays.fill(count, -1);

        int maxLength = 0;
        int totalCount = 0;

        for (int i = 0; i < n; i++) {
            int length = calculateLIS(nums, i, -1, lisLength, count);
            maxLength = Math.max(maxLength, length);
        }

        // Count all LIS of max length
        for (int i = 0; i < n; i++) {
            if (lisLength[i] == maxLength) {
                totalCount += count[i];
            }
        }

        return totalCount;
    }

    private int calculateLIS(int[] nums, int current, int prevIndex, int[] lisLength, int[] count) {
        if (lisLength[current] != -1) {
            return lisLength[current];
        }

        int maxLen = 1; // Minimum length is 1
        int lisCount = 1; // There is at least one subsequence ending here

        for (int i = 0; i < current; i++) {
            if (nums[i] < nums[current]) {
                int length = calculateLIS(nums, i, -1, lisLength, count) + 1;

                if (length > maxLen) {
                    maxLen = length;
                    lisCount = count[i]; // Reset count for new LIS length
                } else if (length == maxLen) {
                    lisCount += count[i]; // Add count of LIS from `i`
                }
            }
        }

        lisLength[current] = maxLen;
        count[current] = lisCount;
        return maxLen;
    }
}
