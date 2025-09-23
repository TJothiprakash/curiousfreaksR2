package practicesessions.sept_17.streams;

import java.util.*;
import java.util.stream.*;
import java.util.Map.Entry;

public class StreamPractice {

    public static void main(String[] args) {
        vowelNamesExample();
        highValueTransactionsExample();
        flattenLogsExample();
        topWordsExample();
        categoryStatsExample();
    }

    // --------------------------
    // Problem 1 — Vowel Names Unique and Sorted
    // --------------------------
    public static void vowelNamesExample() {
        System.out.println("=== Vowel Names Unique & Sorted ===");
        List<String> names = List.of("Arun","bala","Ajith","Kumar","anand","uma","Siva","ibrahim","Eeshwar","Vijay");

        Set<Character> vowels = Set.of('a','e','i','o','u');

        List<String> result = names.stream()
                .filter(n -> vowels.contains(Character.toLowerCase(n.charAt(0))))
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(result);
        // Expected: [ajith, anand, arun, eeshwar, ibrahim, uma]
    }

    // --------------------------
    // Problem 2 — Group and Sum High-Value Transactions
    // --------------------------
    static class Transaction {
        String userId;
        long amount;
        Transaction(String u, long a) { userId = u; amount = a; }
    }

    public static void highValueTransactionsExample() {
        System.out.println("\n=== High-Value Transactions Sum ===");
        List<Transaction> txs = List.of(
                new Transaction("u1",500),
                new Transaction("u2",1500),
                new Transaction("u1",1200),
                new Transaction("u3",700),
                new Transaction("u2",400),
                new Transaction("u1",3000),
                new Transaction("u3",2000),
                new Transaction("u2",50)
        );

        Map<String, Long> result = txs.stream()
                .filter(tx -> tx.amount > 1000)
                .collect(Collectors.groupingBy(tx -> tx.userId, Collectors.summingLong(tx -> tx.amount)));

        System.out.println(result);
        // Expected: {u1=4200, u2=1500, u3=2000}
    }

    // --------------------------
    // Problem 3 — Flatten Logs and Rank Events
    // --------------------------
    public static void flattenLogsExample() {
        System.out.println("\n=== Flatten Logs & Rank Events ===");
        List<List<String>> logs = List.of(
                List.of("u1:login","u2:login","u1:view","u1:logout"),
                List.of("u3:login","u2:view","u2:view","u2:logout"),
                List.of("u1:view","u3:view","u3:logout")
        );

        List<String> result = logs.stream()
                .flatMap(List::stream)
                .map(s -> s.split(":")[1])
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(result);
        // Expected: [view, login, logout]
    }

    // --------------------------
    // Problem 4 — Top 3 Words by Frequency
    // --------------------------
    public static void topWordsExample() {
        System.out.println("\n=== Top 3 Words by Frequency ===");
        List<String> sentences = List.of(
                "Streams make data processing easy and efficient.",
                "Processing data with streams avoids manual loops and mutable state.",
                "Efficient stream pipelines map filter and reduce data."
        );

        List<String> result = sentences.stream()
                .flatMap(s -> Arrays.stream(s.toLowerCase().split("\\W+")))
                .filter(w -> w.length() > 3)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(result);
        // Expected: [data, efficient, processing]
    }

    // --------------------------
    // Problem 5 — Custom Collector for Category Stats with Percentile
    // --------------------------
    static class Item {
        String category;
        int value;
        Item(String c, int v) { category = c; value = v; }
    }

    static class Stats {
        int count;
        long sum;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        List<Integer> values = new ArrayList<>();

        void add(int v) {
            count++;
            sum += v;
            min = Math.min(min, v);
            max = Math.max(max, v);
            values.add(v);
        }

        void combine(Stats other) {
            count += other.count;
            sum += other.sum;
            min = Math.min(min, other.min);
            max = Math.max(max, other.max);
            values.addAll(other.values);
        }

        double avg() { return count == 0 ? 0 : (double) sum / count; }

        int percentile(int p) {
            if (values.isEmpty()) return 0;
            Collections.sort(values);
            int idx = (int) Math.ceil(p / 100.0 * values.size()) - 1;
            return values.get(idx);
        }

        @Override
        public String toString() {
            return "{count=" + count + ",sum=" + sum + ",min=" + min + ",max=" + max +
                    ",avg=" + String.format("%.2f", avg()) + ",p90=" + percentile(90) + "}";
        }
    }

    public static void categoryStatsExample() {
        System.out.println("\n=== Category Stats with Percentile ===");
        List<Item> items = List.of(
                new Item("A",5), new Item("A",10), new Item("A",20),
                new Item("B",7), new Item("B",22), new Item("B",0), new Item("B",100),
                new Item("C",1), new Item("C",4), new Item("C",2), new Item("C",3)
        );

        Map<String, Stats> result = items.stream()
                .collect(Collectors.groupingBy(i -> i.category,
                        Collector.of(
                                Stats::new,
                                (stats, item) -> stats.add(item.value),
                                (s1, s2) -> { s1.combine(s2); return s1; },
                                stats -> stats
                        )));

        result.forEach((cat, stats) -> System.out.println(cat + " → " + stats));
        // Expected:
        // A → {count=3,sum=35,min=5,max=20,avg≈11.67,p90=20}
        // B → {count=4,sum=129,min=0,max=100,avg≈32.25,p90=100}
        // C → {count=4,sum=10,min=1,max=4,avg≈2.50,p90=4}
    }
}
