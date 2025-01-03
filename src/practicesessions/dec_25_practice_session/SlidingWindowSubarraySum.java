package practicesessions.dec_25_practice_session;

public class SlidingWindowSubarraySum {
    public static void main(String[] args) {
        SlidingWindowSubarraySum obj = new SlidingWindowSubarraySum();
        int[] nums = {1, 2, 3, 7, 5};
        int target = 12;
        System.out.println(obj.hasSubarraySum(nums, target));  // Output: true
    }

    public boolean hasSubarraySum(int[] nums, int target) {
        int n = nums.length;

        // Edge case: empty array or array with only one element
        if (n == 0) return false;

        int left = 0;
        int sum = 0;

        // Iterate through the array with the right pointer
        for (int right = 0; right < n; right++) {
            // Add the current element to the window's sum
            sum += nums[right];

            // If the sum exceeds the target, move the left pointer to reduce the sum
            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }

            // If the sum equals the target, return true
            if (sum == target) {
                return true;
            }
        }

        // No subarray with the target sum found
        return false;
    }
}
