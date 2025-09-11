package collections_practice.streams.sept_11;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class TopKCollectorDemo {
/*/*Write a custom Collector that computes the top-K most frequent elements from a Stream<T>
in one pass with bounded memory, and justify characteristics such as UNORDERED, CONCURRENT,
 and IDENTITY_FINISH for the collector.
 */
    static class Book {
        int id;
        String title;
        String author;

        public Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
        }

        @Override
        public String toString() {
            return "Book{id=" + id + ", title='" + title + "', author='" + author + "'}";
        }
    }

    public static <T> Collector<T, ?, List<T>> topKFrequent(int k) {
        return Collector.of(
                // Supplier: accumulator is a HashMap<T, Long>
                HashMap<T, Long>::new,

                // Accumulator: count frequencies
                (map, elem) -> map.merge(elem, 1L, Long::sum),

                // Combiner: merge two maps
                (map1, map2) -> {
                    for (var e : map2.entrySet()) {
                        map1.merge(e.getKey(), e.getValue(), Long::sum);
                    }
                    return map1;
                },

                // Finisher: extract top-k using min-heap
                (map) -> {
                    PriorityQueue<Map.Entry<T, Long>> pq =
                            new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));

                    for (var e : map.entrySet()) {
                        pq.offer(e);
                        if (pq.size() > k) pq.poll(); // maintain only top-k
                    }

                    List<T> result = new ArrayList<>();
                    while (!pq.isEmpty()) {
                        result.add(pq.poll().getKey());
                    }
                    Collections.reverse(result); // highest first
                    return result;
                },

                // Characteristics
                Collector.Characteristics.UNORDERED
        );
    }

    public static void main(String[] args) {
        List<Book> objects = List.of(
                new Book(1, "something", "sydney sheldon"),
                new Book(2, "what if tomorrow comes", "robert kiyosaki"),
                new Book(1, "something", "sydney sheldon"),
                new Book(2, "what if tomorrow comes", "robert kiyosaki"),
                new Book(3, "i love sex", "jothiprakash"),
                new Book(3, "i love sex", "jothiprakash"),
                new Book(3, "i love sex", "jothiprakash")
        );

        // Collect top-2 frequent books by ID
        List<Book> topK = objects.stream()
                .collect(topKFrequent(2));

        System.out.println(topK);
    }
}
