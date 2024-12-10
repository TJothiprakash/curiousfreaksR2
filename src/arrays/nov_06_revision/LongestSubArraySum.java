package arrays.nov_06_revision;

import java.util.HashMap;

class LongestSubArraySum {
    public void generateSubarrays(int[] arr, int target) {
        int n = arr.length;
        int sum = 0;
        int count = 0, maxcount = 0;
        // Outer loop for the starting index of subarrays
        for (int i = 0; i < n; i++) {
            // Inner loop for the ending index of subarrays
            for (int j = i; j < n; j++) {
                // Print or process the subarray arr[i..j]
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k] + " ");
                    sum += arr[k];
                    count++;
                    if (sum == target) {
                        maxcount = Math.max(maxcount, count);
                    }
                }
                sum = 0;
                System.out.println(); // Print a new line after each subarray
            }
        }
    }


    public int longestSubarraySum(int[] arr, int target) {
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        int runningSum = 0;
        int maxLength = 0;

        // Initialize the map with the base case: sum 0 at index -1
        prefixSumMap.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            runningSum += arr[i]; // Update running sum

            // Check if there is a prefix sum that satisfies the condition
            if (prefixSumMap.containsKey(runningSum - target)) {
                int prevIndex = prefixSumMap.get(runningSum - target);
                int subArrayLength = i - prevIndex; // Length of the subarray
                maxLength = Math.max(maxLength, subArrayLength);
            }

            // Store the current running sum with its index in the map
            prefixSumMap.putIfAbsent(runningSum, i);
        }

        return maxLength;
    }


}

// using two pointer approach
class Solution {
    public int lenOfLongestSubarr(int[] arr, int k) {
        // code here
        int n = arr.length;
        int sum = 0;
        int maxCount = 0;   // To keep track of the maximum length of the subarray
        int left = 0;       // Left pointer

        // Traverse through the array using the right pointer
        for (int right = 0; right < n; right++) {
            sum += arr[right];  // Add the current element to the sum

            // If the sum exceeds the target, move the left pointer to reduce the sum
            while (sum > k && left <= right) {
                sum -= arr[left];  // Subtract the element at the left pointer
                left++;            // Move the left pointer forward
            }

            // If the sum matches the target, calculate the length of the subarray
            if (sum == k) {
                maxCount = Math.max(maxCount, right - left + 1);  // Update max length
            }
        }

        return maxCount;  // Return the length of the longest subarray
    }
}


/*import java.util.HashMap;

class Solution {
    public int lenOfLongestSubarr(int[] arr, int k) {
        // Create a hashmap to store the cumulative sum and its corresponding index
        HashMap<Integer, Integer> sumMap = new HashMap<>();

        // Variables to track the cumulative sum, maximum length, and current sum
        int sum = 0;
        int maxLength = 0;

        // Traverse the array
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];  // Update the cumulative sum

            // If the sum is equal to k, we have a subarray from the start to the current index
            if (sum == k) {
                maxLength = i + 1;  // Length from the beginning to the current index
            }

            // If the difference between sum and k is already in the hashmap, we have a subarray with sum = k
            if (sumMap.containsKey(sum - k)) {
                // Calculate the length of the subarray and update maxLength if necessary
                maxLength = Math.max(maxLength, i - sumMap.get(sum - k));
            }

            // Store the cumulative sum and its index in the hashmap if not already present
            if (!sumMap.containsKey(sum)) {
                sumMap.put(sum, i);
            }
        }

        return maxLength;  // Return the length of the longest subarray
    }
}
*/