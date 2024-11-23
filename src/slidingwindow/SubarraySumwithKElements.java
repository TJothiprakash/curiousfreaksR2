package slidingwindow;

public class SubarraySumwithKElements {
    public static int subarraySum(int[] nums, int k) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        maxSum = Math.max(maxSum, sum);
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k]; // Add new element and remove the oldest
            maxSum = Math.max(maxSum, sum);
        }


        return maxSum;
    }
}
