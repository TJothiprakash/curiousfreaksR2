package concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordFrequencyCounter {

    // Shared thread-safe map
//    private static final ConcurrentHashMap<String, Integer> wordCountMap = new ConcurrentHashMap<>();
private  static final Map<String, Integer> wordCountMap = new HashMap<>();
    public static void main(String[] args) throws InterruptedException {
        // Sample input split among threads
        String text1 = "hello world hello";
        String text2 = "world of concurrency";

        // Create two threads to process these texts
        Thread t1 = new Thread(() -> processText(text1));
        Thread t2 = new Thread(() -> processText(text2));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final word counts:");
        wordCountMap.forEach((word, count) -> System.out.println(word + " : " + count));
    }

    private static void processText(String text) {
        String[] words = text.split("\\s+");
        for (String word : words) {
            // Atomic merge operation in ConcurrentHashMap
            wordCountMap.merge(word, 1, Integer::sum);
        }
    }
}
