package binarysearch;

import java.util.Arrays;

public class KthSmallestPairDistance {
    public static void main(String[] args) {

    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        int low = 0, high = nums[n - 1] - nums[0];
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (check(mid, nums) >= k) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;
    }

    public int check(int mid, int arr[]) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            int val = arrayFloor(arr, v + mid);
            if (val == Integer.MIN_VALUE) continue;
            count += (val - i);
        }
        return count;
    }

    private int arrayFloor(int[] arr, int val) {
        int lo = 0;
        int hi = arr.length - 1;
        int max = Integer.MIN_VALUE;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= val) {
                max = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return max;
    }
}