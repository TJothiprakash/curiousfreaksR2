package heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopFrequentCharacters {

    public String frequencySort(String s) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char c : s.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // Use a max heap (priority queue) to sort characters by frequency
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> charFrequency.get(b) - charFrequency.get(a));
        maxHeap.addAll(charFrequency.keySet());

        // Build the sorted string based on frequency
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char currentChar = maxHeap.poll();
            int frequency = charFrequency.get(currentChar);
            for (int i = 0; i < frequency; i++) {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
}