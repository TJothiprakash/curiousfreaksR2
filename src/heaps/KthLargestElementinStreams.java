package heaps;

import java.util.*;

public class KthLargestElementinStreams {
    static List<Integer> kthLargestinStreams(int k, int[] arr, int n) {
        // Min-heap to maintain the k largest elements
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // List to store the result after each insertion
        List<Integer> result = new ArrayList<>();

        // Iterate through the array
        for (int num : arr) {
            // Add the current number to the heap
            pq.add(num);

            // If the heap size exceeds k, remove the smallest element
            if (pq.size() > k) {
                pq.poll();
            }

            // If we have fewer than k elements, the k-th largest doesn't exist
            if (pq.size() < k) {
                result.add(-1);
            } else {
                // The k-th largest element is the root of the min-heap
                result.add(pq.peek());
            }
        }

        return result;
    }


}
