package dec_25_practice_session;

public class SlidingWindowFixedSizeSum {
    public static void main(String[] args) {
        SlidingWindowFixedSizeSum obj = new SlidingWindowFixedSizeSum();
        int[] nums = {1, 2, 3, 7, 5};
        int target = 12;
        int windowSize = 3;
        System.out.println(obj.hasSubarraySum(nums, target, windowSize));  // Output: true
    }

    public boolean hasSubarraySum(int[] nums, int target, int windowSize) {
        int n = nums.length;

        // Edge case: if array is smaller than window size
        if (n < windowSize) return false;

        // Calculate the sum of the first window
        int sum = 0;
        for (int i = 0; i < windowSize; i++) {
            sum += nums[i];
        }

        // Check if the first window's sum matches the target
        if (sum == target) {
            return true;
        }

        // Slide the window
        for (int i = windowSize; i < n; i++) {
            // Slide the window by subtracting the element going out and adding the element coming in
            sum += nums[i] - nums[i - windowSize];

            // Check if the new window sum matches the target
            if (sum == target) {
                return true;
            }
        }

        // No subarray with the target sum
        return false;
    }
}
