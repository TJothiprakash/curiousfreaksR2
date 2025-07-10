package slidingwindow;

public class MaximumSubarraySum {

//     O(n)O(1)
    public int maxSubArraySum(int[] arr)  throws IllegalArgumentException {
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
