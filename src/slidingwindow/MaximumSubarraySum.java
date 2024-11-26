package slidingwindow;

public class MaximumSubarraySum {
    public int maxSubArraySum(int[] arr) {


        if (arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements.");
        }

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length - 1; i++) {
            int sum = arr[i] + arr[i + 1];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }
}