package slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubarrayWithTargetSum {
//O(n)O(1)
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

            /*   for (int end = 0; end < arr.length; end++) {
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
*/
        }

        return resultIndices;
    }

    public static void main(String[] args) {
        // Test Cases
//        System.out.println(findLongestSubarrayWithSum(new int[]{1, 2, 3, 7, 5}, 12)); // Output: [2, 4]
//        System.out.println(findLongestSubarrayWithSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 15)); // Output: [1, 5]
        System.out.println(findLongestSubarrayWithSumHandlingNegatives(new int[]{10, 5, 2, 7, 1, -10}, 15)); // Output: [2, 2]
//        System.out.println(findLongestSubarrayWithSum(new int[]{5, 3, 4, 7, 2, 1, 1, 1}, 8)); // Output: [4, 8]
//        System.out.println(findLongestSubarrayWithSum(new int[]{5, 3, 4}, 2)); // Output: [-1]
    }

    public static int findLongestSubarrayWithSumHandlingNegatives(int[] arr, int k) {
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        sumIndexMap.put(0, -1); // This handles the case where the sum itself equals k
        int currentSum = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            if (sumIndexMap.containsKey(currentSum - k)) {
                int length = i - sumIndexMap.get(currentSum - k);
                if (length > maxLength) {
                    maxLength = length;
                }
            }

            // Store the first occurrence of the current sum
            if (!sumIndexMap.containsKey(currentSum)) {
                sumIndexMap.put(currentSum, i);
            }
        }

        return maxLength;
    }


}
