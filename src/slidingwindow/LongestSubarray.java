package slidingwindow;

import java.util.HashMap;

/*The idea is to transform the array and treat the 0s as -1 and keep 1s as they are. This way, we are essentially trying to find a subarray whose sum is 0, as that would indicate an equal number of 0s and 1s.

Steps:
Transform the Array:

Convert every 0 to -1 and keep 1 as it is. Now, our task is to find a subarray with a sum of 0.
Prefix Sum:

Compute the prefix sum as we iterate through the array. The prefix sum will be the sum of elements from the start up to the current index.
If at two different indices, the prefix sum is the same, it means the subarray between these two indices has a sum of 0 (because the same sum indicates that the subarray between these indices has balanced the number of 1s and -1s).
Hash Map:

We use a hash map to store the first occurrence of each prefix sum. The key is the prefix sum,
 and the value is the index where this sum first occurred.
If the prefix sum is found again at a later index, we know that the subarray between
 the previous index and the current index has a sum of 0.
Track Maximum Length:

For each occurrence of a prefix sum, calculate the length of the subarray between the current index and the first occurrence of this prefix sum. Keep track of the maximum length.*/
public class LongestSubarray {
    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{0, 1, 0, 1}));  // Output: 4
        System.out.println(longestSubarray(new int[]{0, 0, 1, 0, 0}));  // Output: 2
        System.out.println(longestSubarray(new int[]{0}));  // Output: 0
    }

    public static int longestSubarray(int[] arr) {
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        int maxLength = 0;
        int prefixSum = 0;

        // Initialize the map with the prefix sum 0 at index -1 to handle the case where the subarray starts from index 0
        prefixSumMap.put(0, -1);

        // Iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // Transform 0 to -1
            prefixSum += (arr[i] == 0) ? -1 : 1;

            // If this prefix sum has been encountered before, it means the subarray between the previous index
            // and the current index has equal number of 0s and 1s.
            if (prefixSumMap.containsKey(prefixSum)) {
                int previousIndex = prefixSumMap.get(prefixSum);
                int length = i - previousIndex;
                maxLength = Math.max(maxLength, length);
            } else {
                // Otherwise, store the first occurrence of this prefix sum
                prefixSumMap.put(prefixSum, i);
            }
        }

        return maxLength;
    }
}
