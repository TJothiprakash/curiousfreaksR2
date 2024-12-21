package dynamic_programming.dp_onlongestincreasingsubsequence;

import java.util.Arrays;
// bitonic : increaisng then decreasing or
// fully increasing
//or fully decreasing
// approach compute LIS from the both ends start and back
// then combine

import java.util.Arrays;

public class BitonicSubsequence {
    public int longestBitonicSequence(int[] nums) {
        int n = nums.length;

        // Memoization arrays for LIS and LDS
        int[][] lisMemo = new int[n][n + 1];
        int[][] ldsMemo = new int[n][n + 1];

        for (int[] row : lisMemo) Arrays.fill(row, -1);
        for (int[] row : ldsMemo) Arrays.fill(row, -1);

        int maxBitonicLength = 0;

        for (int i = 0; i < n; i++) {
            int lisLength = findLIS(nums, i, -1, lisMemo);
            int ldsLength = findLDS(nums, i, -1, ldsMemo);

            if (lisLength > 1 && ldsLength > 1) { // Both parts must exist
                maxBitonicLength = Math.max(maxBitonicLength, lisLength + ldsLength - 1);
            }
        }

        return maxBitonicLength;
    }

    private int findLIS(int[] nums, int currentIndex, int prevIndex, int[][] memo) {
        if (currentIndex == nums.length) return 0;

        if (memo[currentIndex][prevIndex + 1] != -1) {
            return memo[currentIndex][prevIndex + 1];
        }

        // Option 1: Skip the current element
        int length = findLIS(nums, currentIndex + 1, prevIndex, memo);

        // Option 2: Include the current element if it forms an increasing subsequence
        if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
            length = Math.max(length, 1 + findLIS(nums, currentIndex + 1, currentIndex, memo));
        }

        memo[currentIndex][prevIndex + 1] = length;
        return length;
    }

    private int findLDS(int[] nums, int currentIndex, int nextIndex, int[][] memo) {
        if (currentIndex < 0) return 0;

        if (memo[currentIndex][nextIndex + 1] != -1) {
            return memo[currentIndex][nextIndex + 1];
        }

        // Option 1: Skip the current element
        int length = findLDS(nums, currentIndex - 1, nextIndex, memo);

        // Option 2: Include the current element if it forms a decreasing subsequence
        if (nextIndex == -1 || nums[currentIndex] > nums[nextIndex]) {
            length = Math.max(length, 1 + findLDS(nums, currentIndex - 1, currentIndex, memo));
        }

        memo[currentIndex][nextIndex + 1] = length;
        return length;
    }

    public static void main(String[] args) {
        BitonicSubsequence bs = new BitonicSubsequence();

        int[] nums1 = {1, 2, 5, 3, 2};
        System.out.println(bs.longestBitonicSequence(nums1)); // Output: 5

        int[] nums2 = {1, 11, 2, 10, 4, 5, 2, 1};
        System.out.println(bs.longestBitonicSequence(nums2)); // Output: 6

        int[] nums3 = {10, 20, 30};
        System.out.println(bs.longestBitonicSequence(nums3)); // Output: 0

        int[] nums4 = {10, 10, 10};
        System.out.println(bs.longestBitonicSequence(nums4)); // Output: 0
    }
}


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
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = arr.length;

        System.out.println("The length of the longest bitonic subsequence is " +
                longestBitonicSequence(arr, n));
    }
}