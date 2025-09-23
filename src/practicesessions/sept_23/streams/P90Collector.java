package practicesessions.sept_23.streams;

import java.util.*;
import java.util.stream.Collector;

public class P90Collector {

    public static Collector<Integer, ?, Double> percentileCollector(double percentile) {
        return Collector.of(
                ArrayList<Integer>::new,      // Supplier: creates a new list of Integers
                List::add,                    // Accumulator: add element to list
                (left, right) -> {            // Combiner: merge two lists
                    left.addAll(right);
                    return left;
                },
                list -> {                     // Finisher: compute percentile
                    if (list.isEmpty()) return 0.0;
                    Collections.sort(list);   // works because list is List<Integer>
                    int N = list.size();
                    int k = (int) Math.ceil(percentile * N);
                    return (double) list.get(k - 1); // nearest-rank
                }
        );
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        double p90 = numbers.stream()
                .collect(percentileCollector(0.9));

        System.out.println("P90: " + p90); // Output: 9
    }
}
