package dynamic_programming.dp_onlongestincreasingsubsequence;

import java.util.Arrays;

public class LIS {
}


class TUF {
    static int longestIncreasingSubsequence(int[] arr, int n) {

        int[] next = new int[n + 1];
        int[] cur = new int[n + 1];

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int prev_index = ind - 1; prev_index >= -1; prev_index--) {

                int notTake = 0 + next[prev_index + 1];

                int take = 0;

                if (prev_index == -1 || arr[ind] > arr[prev_index]) {

                    take = 1 + next[ind + 1];
                }

                cur[prev_index + 1] = Math.max(notTake, take);
            }
            next = cur.clone();
        }

        return cur[0];
    }

    public static void main(String[] args) {

        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};

        int n = arr.length;

        System.out.println("The length of the longest increasing subsequence is " + longestIncreasingSubsequence(arr, n));

    }
}


class NumberOfLISRecursive {
    static int[] nums;
    static int[][] dpLen;
    static int[][] dpCount;
    static int n;

    // Get the length of LIS starting at index i with previous index prev
    public static int getLength(int i, int prev) {
        if (i == n) return 0;
        if (dpLen[i][prev + 1] != -1) return dpLen[i][prev + 1];

        int skip = getLength(i + 1, prev);
        int take = 0;
        if (prev == -1 || nums[i] > nums[prev]) {
            take = 1 + getLength(i + 1, i);
        }

        dpLen[i][prev + 1] = Math.max(skip, take);
        return dpLen[i][prev + 1];
    }

    // Count number of ways to reach LIS length maxLen
    public static int countLIS(int i, int prev, int maxLen) {
        if (i == n) return maxLen == 0 ? 1 : 0;
        if (dpCount[i][prev + 1] != -1) return dpCount[i][prev + 1];

        int total = countLIS(i + 1, prev, maxLen); // skip

        if (prev == -1 || nums[i] > nums[prev]) {
            total += countLIS(i + 1, i, maxLen - 1); // take
        }

        dpCount[i][prev + 1] = total;
        return total;
    }

    public static int findNumberOfLIS(int[] input) {
        nums = input;
        n = nums.length;

        dpLen = new int[n][n + 1];
        for (int[] row : dpLen) Arrays.fill(row, -1);

        int maxLen = getLength(0, -1);

        dpCount = new int[n][n + 1];
        for (int[] row : dpCount) Arrays.fill(row, -1);

        return countLIS(0, -1, maxLen);
    }

    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1, 3, 5, 4, 7})); // Output: 2
        System.out.println(findNumberOfLIS(new int[]{2, 2, 2, 2, 2})); // Output: 5
    }
}

