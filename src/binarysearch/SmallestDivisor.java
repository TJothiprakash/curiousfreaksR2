package binarysearch;

public class SmallestDivisor {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 9};
        int threshold1 = 6;
        System.out.println(smallestDivisor(nums1, threshold1));  // Output: 5

        int[] nums2 = {44, 22, 33, 11, 1};
        int threshold2 = 5;
        System.out.println(smallestDivisor(nums2, threshold2));  // Output: 44
    }

    public static int smallestDivisor(int[] nums, int threshold) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int low = 1;
        int high = max;
        int result = max;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(nums, threshold, mid)) {
                result = mid;  // valid divisor, try smaller
                high = mid - 1;
            } else {
                low = mid + 1;  // invalid, try bigger divisor
            }
        }
        return result;
    }

    private static boolean isValid(int[] nums, int threshold, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor; // equivalent to ceil(num/divisor)
            if (sum > threshold) return false;
        }
        return true;
    }
}
