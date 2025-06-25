package dynamic_programming.dp_onlongestincreasingsubsequence;

import java.util.Arrays;

public class NumberOfLIS {
    public static void main(String[] args) {
        NumberOfLIS1 solution = new NumberOfLIS1();

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


/*
Question:
Given an array nums, return the number of Longest Increasing Subsequences.
The sequence must be strictly increasing.

Intuition:
Use dynamic programming:
- dp[i] = length of LIS ending at index i
- count[i] = number of LIS of length dp[i]

We update dp[i] and count[i] by looking at all previous indices j < i:
- If nums[i] > nums[j]
    - If dp[j] + 1 > dp[i]: update length and set count
    - If dp[j] + 1 == dp[i]: another way found, add count[j]

Finally, count all indices where dp[i] == maxLen

Time: O(n^2)
Space: O(n)
*/

class NumberOfLIS1 {

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];     // dp[i] = length of LIS ending at i
        int[] count = new int[n];  // count[i] = number of such LIS
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            System.out.println("Checking LIS ending at index " + i + " (value = " + nums[i] + ")");
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; // reset count
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j]; // add another way
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
            System.out.println("  → LIS length = " + dp[i] + ", count = " + count[i]);
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                total += count[i];
            }
        }

        System.out.println("Max Length = " + maxLen + ", Total LIS = " + total);
        return total;
    }

    // Test
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5, 4, 7};     // Output: 2
        int[] nums2 = {2, 2, 2, 2, 2};     // Output: 5
        int[] nums3 = {1, 2, 4, 3, 5, 4, 7, 2}; // Output: ?

        System.out.println("Result 1: " + findNumberOfLIS(nums1));
        System.out.println("Result 2: " + findNumberOfLIS(nums2));
        System.out.println("Result 3: " + findNumberOfLIS(nums3));
    }
}

/*
Time Complexity:
- O(n^2): for every i, compare with all j < i

Space Complexity:
- O(n) for dp[]
- O(n) for count[]
*/
