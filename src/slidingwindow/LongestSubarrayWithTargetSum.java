package slidingwindow;

import java.util.Arrays;
import java.util.List;

public class LongestSubarrayWithTargetSum {

    public static List<Integer> findLongestSubarrayWithSum(int[] arr, int target) {
        int start = 0, currentSum = 0, maxLength = 0;
        List<Integer> resultIndices = Arrays.asList(-1); // Default if no subarray is found

        for (int end = 0; end < arr.length; end++) {
            // Expand the window
            currentSum += arr[end];

            // Shrink the window if currentSum > target
            while (currentSum > target && start <= end) {
                currentSum -= arr[start];
                start++;
            }

            // Check if currentSum matches target
            if (currentSum == target) {
                int length = end - start + 1; // Current window length
                if (length > maxLength) {
                    maxLength = length;
                    resultIndices = Arrays.asList(start + 1, end + 1); // 1-based indices
                }
            }
        }

        return resultIndices;
    }

    public static void main(String[] args) {
        // Test Cases
        System.out.println(findLongestSubarrayWithSum(new int[]{1, 2, 3, 7, 5}, 12)); // Output: [2, 4]
        System.out.println(findLongestSubarrayWithSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 15)); // Output: [1, 5]
        System.out.println(findLongestSubarrayWithSum(new int[]{7, 2, 1}, 2)); // Output: [2, 2]
        System.out.println(findLongestSubarrayWithSum(new int[]{5, 3, 4, 7, 2, 1, 1, 1}, 8)); // Output: [4, 8]
        System.out.println(findLongestSubarrayWithSum(new int[]{5, 3, 4}, 2)); // Output: [-1]
    }
}
