package practicesessions.sept_22.stream;
import java.util.*;
import java.util.stream.*;

public class TopKWords {
    public static void main(String[] args) {
        List<String> sentences = List.of(
                "Time and tide wait for none",
                "Time flies like an arrow",
                "Fruit flies like a banana"
        );

        int k = 2; // top 2 words

        List<String> topK = sentences.stream()
                .flatMap(s -> Arrays.stream(s.split("\\s+")))        // split into words
                .map(String::toLowerCase)                             // normalize case
                .filter(w -> !w.isEmpty())                            // remove empty tokens
                .collect(Collectors.groupingBy(w -> w, Collectors.counting())) // word -> count
                .entrySet().stream()
                .sorted(Comparator
                        .comparing(Map.Entry<String, Long>::getValue, Comparator.reverseOrder()) // count desc
                        .thenComparing(Map.Entry::getKey)                                         // word asc for tie
                )
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(topK);
    }
}
