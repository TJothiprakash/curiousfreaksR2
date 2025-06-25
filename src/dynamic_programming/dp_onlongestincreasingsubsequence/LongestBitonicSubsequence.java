package dynamic_programming.dp_onlongestincreasingsubsequence;
/*
Question:
Given an array of positive integers. Find the maximum length of Bitonic subsequence.
A subsequence is Bitonic if it is first strictly increasing then strictly decreasing.
Strictly increasing or strictly decreasing alone is NOT considered bitonic.

Examples:
Input: [1, 2, 5, 3, 2]           Output: 5
Input: [1, 11, 2, 10, 4, 5, 2, 1] Output: 6
Input: [10, 20, 30]              Output: 0
Input: [10, 10, 10]              Output: 0

Constraints:
1 ≤ arr.length ≤ 1000
1 ≤ arr[i] ≤ 10^4

--------------------------------------------------

Intuition:
To form a bitonic subsequence, we need to find:
1. The Longest Increasing Subsequence (LIS) ending at each index `i`
2. The Longest Decreasing Subsequence (LDS) starting at each index `i`

A bitonic sequence "peaks" at index `i` and has:
length = LIS[i] + LDS[i] - 1
(because the peak element is counted in both)

We use recursion + memoization for both LIS and LDS.

--------------------------------------------------

Approach:
- Define two DP memo tables: `dpLIS[i]`, `dpLDS[i]`
- Use top-down recursion to fill them
- For each `i`, compute the LIS ending at `i` and LDS starting from `i`
- Finally, combine LIS and LDS to get the max bitonic length

*/

import java.util.Arrays;

public class LongestBitonicSubsequence {

    // Memo tables
    static int[] dpLIS;
    static int[] dpLDS;

    public static int longestBitonicSubsequence(int[] nums) {
        int n = nums.length;
        dpLIS = new int[n];
        dpLDS = new int[n];
        Arrays.fill(dpLIS, -1);
        Arrays.fill(dpLDS, -1);

        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int lis = lisEndingAt(i, nums);
            int lds = ldsStartingAt(i, nums);

            if (lis > 1 && lds > 1) { // strictly increasing then strictly decreasing
                int bitonicLen = lis + lds - 1;
                System.out.println("Peak at index " + i + " → LIS: " + lis + ", LDS: " + lds + ", BitonicLen: " + bitonicLen);
                maxLen = Math.max(maxLen, bitonicLen);
            }
        }

        return maxLen;
    }

    // Recursively find LIS ending at index i
    private static int lisEndingAt(int i, int[] nums) {
        if (dpLIS[i] != -1) return dpLIS[i];

        int max = 1;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                max = Math.max(max, lisEndingAt(j, nums) + 1);
            }
        }
        dpLIS[i] = max;
        return max;
    }

    // Recursively find LDS starting at index i
    private static int ldsStartingAt(int i, int[] nums) {
        if (dpLDS[i] != -1) return dpLDS[i];

        int max = 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] < nums[i]) {
                max = Math.max(max, ldsStartingAt(j, nums) + 1);
            }
        }
        dpLDS[i] = max;
        return max;
    }

    // Driver code for testing
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 3, 2};
        int[] nums2 = {1, 11, 2, 10, 4, 5, 2, 1};
        int[] nums3 = {10, 20, 30};
        int[] nums4 = {10, 10, 10};

        System.out.println("Result 1: " + longestBitonicSubsequence(nums1)); // Expected: 5
        System.out.println("Result 2: " + longestBitonicSubsequence(nums2)); // Expected: 6
        System.out.println("Result 3: " + longestBitonicSubsequence(nums3)); // Expected: 0
        System.out.println("Result 4: " + longestBitonicSubsequence(nums4)); // Expected: 0
    }
}

/*
--------------------------------------------------

🧠 Time Complexity:
- LIS and LDS for each element: O(n^2) with memoization (each pair is visited once)
- Final result: O(n^2)

🧠 Space Complexity:
- dpLIS and dpLDS: O(n)
- Call stack for recursion: O(n)

--------------------------------------------------
*/


class Bitonic {
    // Function to find the length of the longest bitonic subsequence
    static int longestBitonicSequence(int[] arr, int n) {
        // Arrays to store lengths of increasing and decreasing subsequences
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        // Initialize both arrays with 1, as each element itself is a subsequence of length 1
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        // Calculate the lengths of increasing subsequences
        for (int i = 0; i < n; i++) {
            for (int prevIndex = 0; prevIndex < i; prevIndex++) {
                if (arr[prevIndex] < arr[i]) {
                    dp1[i] = Math.max(dp1[i], 1 + dp1[prevIndex]);
                }
            }
        }

        // Reverse the direction of nested loops and calculate the lengths of decreasing subsequences
        for (int i = n - 1; i >= 0; i--) {
            for (int prevIndex = n - 1; prevIndex > i; prevIndex--) {
                if (arr[prevIndex] < arr[i]) {
                    dp2[i] = Math.max(dp2[i], 1 + dp2[prevIndex]);
                }
            }
        }

        int maxi = -1;

        // Calculate the length of the longest bitonic subsequence
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
        }

        return maxi;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = arr.length;

        System.out.println("The length of the longest bitonic subsequence is " +
                longestBitonicSequence(arr, n));
    }
}

