package queue;
import java.util.*;

public class FirstNonRepeatingCharacter {
    public static String firstNonRepeating(String s) {
        // HashMap to store the frequency of each character
        HashMap<Character, Integer> freq = new HashMap<>();
        // Queue to store characters in the order of appearance
        Queue<Character> queue = new LinkedList<>();
        // StringBuilder to store the result
        StringBuilder result = new StringBuilder();

        // Process each character in the stream
        for (char c : s.toCharArray()) {
            // Increment the frequency of the character
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            // If the character appears for the first time, add it to the queue
            if (freq.get(c) == 1) {
                queue.offer(c);
            }

            // Remove all characters from the queue that have a frequency > 1
            while (!queue.isEmpty() && freq.get(queue.peek()) > 1) {
                queue.poll();
            }

            // If the queue is not empty, the front is the first non-repeating character
            if (!queue.isEmpty()) {
                result.append(queue.peek());
            } else {
                // If the queue is empty, append '#'
                result.append('#');
            }
        }

        return result.toString();
    }


}

