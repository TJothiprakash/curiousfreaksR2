package practicesessions.sept_18.streams;

import javax.sql.rowset.spi.TransactionalWriter;
import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Practice {
    public static void main(String[] args) {
        practice();
    }

    private static void practice() {
//        Task: Trim, lowercase, capitalize first letter, then sort ascendin
        List<String> names = new ArrayList<>(List.of(" anNa ", "bob", " CAtH ", "dan"));
        List<String> resultList = names.stream().map(s -> {
            s = s.trim().toLowerCase();
            s = s.substring(0, 1).toUpperCase() + s.substring(1);
            return s;
        }).sorted().toList();

        System.out.println("resultList = " + resultList);

//Medium: Word frequency (top 3) ignoring case and punctuation.
//Input: ["Java streams are great!", "Streams in Java can be parallel.", "java JAVA JaVa"].
//Task: Split on non-letters, normalize to lower, count frequencies,
// return top 3 as List<Map.Entry<String,Integer>> ordered by count desc then word asc.
//Expected (entries textual): [(java,6), (streams,2), (are,1)] noting
// ties resolved lexicographically for remaining words
        List<String> sentenceList = new ArrayList<>(List.of("Java streams are great!", "Streams in Java can be parallel.", "java JAVA JaVa"));
        List<Map.Entry<String, Integer>> resultset = sentenceList.stream()
                .flatMap(s -> Arrays.stream(s.toLowerCase().split("[^a-z]+")))
                .filter(e -> !e.isEmpty())
                .collect(Collectors.groupingBy(e -> e, Collectors.summingInt(e -> 1)))
                .entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(e -> -e.getValue())
                        .thenComparing(Map.Entry::getKey))
                .limit(3)
                .toList();

        System.out.println("resultset = " + resultset);

        List<Map.Entry<String, Integer>> resultset2 = sentenceList.stream()
                // flatten all words
                .flatMap(s -> Arrays.stream(s.toLowerCase().split("[^a-z]+")))
                .filter(e -> !e.isEmpty())
                // group and count
                .collect(Collectors.groupingBy(e -> e, Collectors.summingInt(e -> 1)))
                .entrySet().stream()
                // sort: count desc, word asc
                .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(e -> -e.getValue())
                        .thenComparing(Map.Entry::getKey))
                .limit(3)
                .toList();
        System.out.println("resultset2 = " + resultset2);


//        Medium: Group transactions by user and sum.
//Input (List<Transaction>):
//T(userId=1, amount=100), T(userId=2, amount=50), T(userId=1, amount=30), T(userId=3, amount=80), T(userId=2, amount=20).
//Task: Use groupingBy userId with summingInt to produce Map<Integer,Integer>.
//Expected: {1=130, 2=70, 3=80}

        List<Transaction> transactionList = new
                ArrayList<>(List.of(
                new Transaction("abcd1", 100),
                new Transaction("abcd2", 300),
                new Transaction("abcd3", 400),
                new Transaction("abcd1", 150),
                new Transaction("abcd2", 150)

        ));
        Map<String, Integer> transactionMapByUser = transactionList.stream()
                .collect(Collectors.groupingBy(t -> t.id, Collectors.summingInt(t -> t.amount)));
        System.out.println("transactionMapByUser = " + transactionMapByUser);


//        Medium: Stable dedupe while preserving first occurrence.
//Input: ["A","B","A","C","B","D","A"].
//Task: Produce ["A","B","C","D"] keeping first positions; avoid Set that loses order of first-seen.
//Expected: ["A","B","C","D"]

        List<String> input = List.of("A", "B", "A", "C", "B", "D", "A");

        List<String> result = input.stream()
                .distinct()   // keeps first occurrence while streaming
                .toList();

        System.out.println(result);  // [A, B, C, D]


        List<Integer> data = Arrays.asList(5, 9, 11, 7, 8, 11, 16, 10, 11);
        int windowSize = 3;
        int threshold = 10;

//        Hard: Sliding window anomaly detection.
//        Input: List<Integer> data = , windowSize=3, threshold=10.
//        Task: Compute the average for each consecutive window and return indices where avg > threshold; windows are =8, =8.33, =10.67, =7.67, =8.67, =10.67, =12.33.
//                Expected indices (0-based window start): because 10.67, 10.67, and 12.33 exceed 10.
        List<Integer> anomalyIndices =
                IntStream.range(0, data.size() - windowSize + 1)  // indices of window starts
                        .mapToObj(i -> {
                            double avg = data.subList(i, i + windowSize).stream()
                                    .mapToInt(Integer::intValue)
                                    .average()
                                    .orElse(0.0);
                            return Map.entry(i, avg); // pair: (startIndex, average)
                        })
                        .filter(entry -> entry.getValue() > threshold) // keep anomalies
                        .map(Map.Entry::getKey) // extract indices
                        .toList();

        System.out.println("Anomaly window start indices = " + anomalyIndices);

    }

    static class Transaction {
        String id;
        int amount;

        public Transaction(String id, int amount) {
            this.id = id;
            this.amount = amount;
        }
    }
}
