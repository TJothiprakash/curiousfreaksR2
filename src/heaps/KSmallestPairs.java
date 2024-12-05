package heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KSmallestPairs {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Initialize the min-heap (priority queue)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> (a[0] + a[1]) - (b[0] + b[1]) // Compare by sum of the pair
        );

        // Result list to store the k smallest pairs
        List<int[]> result = new ArrayList<>();

        // Edge case: if either of the arrays is empty, return an empty result
        if (nums1.length == 0 || nums2.length == 0) {
            return result;
        }

        // Push the first row (first element from nums1 and all elements from nums2)
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0}); // [nums1[i], nums2[0], 0] format -> (nums1[i], nums2[0], index_in_nums2)
        }

        // Extract the smallest pairs and add them to the result
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            result.add(new int[]{curr[0], curr[1]});

            // If there is a next element in nums2 for the current nums1 element, add it to the heap
            if (curr[2] + 1 < nums2.length) {
                minHeap.offer(new int[]{curr[0], nums2[curr[2] + 1], curr[2] + 1});
            }
        }

        return result;
    }
}
