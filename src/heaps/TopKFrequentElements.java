package heaps;

import java.util.*;

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
        int ans[]=new int[result.size()];
        int i=0;
        for(int num:result){
            ans[i++]=num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println(topKFrequent(nums1, k1)); // Output: [1, 2]

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println(topKFrequent(nums2, k2)); // Output: [1]

        int[] nums3 = {4, 4, 4, 6, 6, 8, 8, 8, 8};
        int k3 = 2;
        System.out.println(topKFrequent(nums3, k3)); // Output: [8, 4]
    }
}
