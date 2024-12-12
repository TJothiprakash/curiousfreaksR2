package slidingwindow;

import java.util.Arrays;
import java.util.List;


public class SubarrayWithTargetSum {

    public static List<Integer> findSubarrayWithSum(int[] arr, int target) {
        int start = 0, currentSum = 0;
            //1, 2, 3, 7, 5}, 12
        for (int end = 0; end < arr.length; end++) {
            // Add the current element to the sum
            currentSum += arr[end];

            // Shrink the window as long as currentSum > target
            while (currentSum > target && start <= end) {
                currentSum -= arr[start];
                start++;
            }

            // Check if we have found the target sum
            if (currentSum == target) {
                return Arrays.asList(start + 1, end + 1); // Return 1-based indices
            }
        }

        // No subarray found
        return Arrays.asList(-1);
    }

    public static void main(String[] args) {
        // Test Cases
        System.out.println(findSubarrayWithSum(new int[]{1, 2, 3, 7, 5}, 12)); // Output: [2, 4]
        System.out.println(findSubarrayWithSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 15)); // Output: [1, 5]
        System.out.println(findSubarrayWithSum(new int[]{7, 2, 1}, 2)); // Output: [2, 2]
        System.out.println(findSubarrayWithSum(new int[]{5, 3, 4}, 2)); // Output: [-1]
    }
}
