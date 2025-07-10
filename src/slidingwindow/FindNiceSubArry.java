package slidingwindow;
/*Given an array of integers nums and an integer k. A continuous
subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.



Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16


Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
*/

import java.util.HashMap;

public class FindNiceSubArry {
    //O(n)O(n)
    public int numberOfSubarrays(int[] nums, int k) {
        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // To handle the case where the subarray starts from index 0
        int count = 0;
        int prefixSum = 0;

        // Traverse the array
        for (int num : nums) {
            // If the number is odd, increment the prefix sum
            if (num % 2 == 1) {
                prefixSum++;
            }

            // Check if (prefixSum - k) is present in the hashmap
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }

            // Update the hashmap with the current prefix sum count
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
