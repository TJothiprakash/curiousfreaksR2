package heaps;

import java.util.*;

public class TopKFrequentWords {
}

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // Step 1: Count frequencies
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Use a min-heap to keep track of k most frequent elements
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue()) ?
                        b.getKey().compareTo(a.getKey()) :
                        a.getValue() - b.getValue()
        );

        /*Comparator<Map.Entry<String,Integer>> cmp =
        Comparator.comparingInt(Map.Entry::getValue)      // ↑ freq asc
                  .thenComparing(Map.Entry::getKey,        // ↑ key desc
                                 Comparator.reverseOrder());

PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>(cmp);
*/
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 3: Extract elements from the heap and sort them
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        Collections.reverse(result); // Since we used a min-heap, reverse to get the correct order

        return result;
    }
}
