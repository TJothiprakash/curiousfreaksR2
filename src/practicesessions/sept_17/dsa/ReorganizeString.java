package practicesessions.sept_17.dsa;

import java.util.HashMap;
import java.util.PriorityQueue;

public class ReorganizeString {

    public static void main(String[] args) {
        ReorganizeString obj = new ReorganizeString();
        System.out.println(obj.reorganizeString("aab"));  // "aba"
        System.out.println(obj.reorganizeString("aaab")); // ""
    }

    public String reorganizeString(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>();

        // Step 1: Count frequencies
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            if (freqMap.get(c) > (s.length() + 1) / 2) {
                return ""; // impossible to reorganize
            }
        }

        // Step 2: Max heap based on frequencies
        PriorityQueue<Character> maxHeap =
                new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        maxHeap.addAll(freqMap.keySet());

        StringBuilder sb = new StringBuilder();

        // Step 3: Greedy selection
        while (maxHeap.size() > 1) {
            char first = maxHeap.poll();
            char second = maxHeap.poll();

            sb.append(first);
            sb.append(second);

            freqMap.put(first, freqMap.get(first) - 1);
            freqMap.put(second, freqMap.get(second) - 1);

            if (freqMap.get(first) > 0) maxHeap.add(first);
            if (freqMap.get(second) > 0) maxHeap.add(second);
        }

        // Step 4: If one char remains
        if (!maxHeap.isEmpty()) {
            sb.append(maxHeap.poll());
        }

        return sb.toString();
    }
}
