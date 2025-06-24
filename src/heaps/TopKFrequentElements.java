package heaps;

import java.util.*;
/*Given an integer array nums and an integer k,
 return the k most frequent elements.
You may return the answer in any order.



Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.


Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

*/

public class TopKFrequentElements {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        // Step 1: Count the frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a Min-Heap to find the top k frequent elements
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the least frequent element
            }
        }

        // Step 3: Extract the elements from the heap
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        // Step 4: Reverse the list to return in decreasing order of frequency
        Collections.reverse(result);
        int[] ans =new int[result.size()];
        int i=0;
        for(int num:result){
            ans[i++]=num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 1;
        System.out.println(topKFrequent(nums1, k1)); // Output: [1, 2]

        int[] nums2 = {1,2,3,3,3,4,5,5};
        int k2 = 1;
        System.out.println(topKFrequent(nums2, k2)); // Output: [1]

        int[] nums3 = {4, 4, 4,4,4 , 6, 6, 8, 8, 8, 8};
        int k3 = 1;
        System.out.println(topKFrequent(nums3, k3)); // Output: [8, 4]
    }
}


/*import java.util.*;

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // Step 1: Count the frequency of each element
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a min-heap (priority queue) to keep the top k frequent elements
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // Add all entries to the heap, keeping only the k most frequent elements
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 3: Extract the k most frequent elements from the heap
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        // Return the result (can be in any order)
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        List<Integer> result = sol.topKFrequent(nums, k);
        System.out.println(result);  // Output: [1, 2] (or any order)
    }
}
*/