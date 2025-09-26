package practicesessions.sept_26.streams;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamTasks {

    // -------------------- Easy: Normalize and select --------------------
    static class NormalizeAndSelect {
        public static List<String> process(List<String> words) {
            return words.stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(w -> w.startsWith("a") || w.startsWith("b"))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }

        public static void main(String[] args) {
            List<String> words = Arrays.asList(" Apple ", "banana", "BANANA", "Cherry", " apple", "cherry ");
            System.out.println(process(words)); // Output: [apple, banana]
        }
    }

    // -------------------- Medium 1: Frequency by first letter --------------------
    static class FrequencyByFirstLetter {
        public static Map<String, Long> process(List<String> names) {
            return names.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(s -> s.substring(0,1),
                            TreeMap::new, Collectors.counting())); // TreeMap for sorted keys
        }

        public static void main(String[] args) {
            List<String> names = Arrays.asList("Arun", "ajay", "Bala", "aravind", "Babu", "baskar", "Anand");
            System.out.println(process(names)); // Output: {a=4, b=3}
        }
    }

    // -------------------- Medium 2: Top-k by aggregate metric --------------------
    static class TopKByAvg {
        static class Product {
            String cat;
            double price;
            Product(String cat, double price){ this.cat=cat; this.price=price;}
        }

        public static List<String> process(List<Product> products, int k) {
            Map<String, Double> avg = products.stream()
                    .collect(Collectors.groupingBy(p -> p.cat, Collectors.averagingDouble(p -> p.price)));

            return avg.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }

        public static void main(String[] args) {
            List<Product> products = Arrays.asList(
                    new Product("mobiles", 12000),
                    new Product("laptops", 70000),
                    new Product("mobiles", 18000),
                    new Product("audio", 3000),
                    new Product("laptops", 90000)
            );
            System.out.println(process(products, 2)); // Output: [laptops, mobiles]
        }
    }

    // -------------------- Medium 3: Flatten, dedupe, sort with tie-break --------------------
    static class FlattenDedupeSort {
        public static List<String> process(List<List<String>> tagsPerPost) {
            return tagsPerPost.stream()
                    .flatMap(List::stream)
                    .distinct()
                    .sorted((a,b) -> {
                        if(a.equals("java")) return -1;
                        if(b.equals("java")) return 1;
                        return a.compareTo(b);
                    })
                    .collect(Collectors.toList());
        }

        public static void main(String[] args) {
            List<List<String>> tags = Arrays.asList(
                    Arrays.asList("java","stream","api"),
                    Arrays.asList("sql","db","java"),
                    Arrays.asList("api","design","rest")
            );
            System.out.println(process(tags)); // Output: [java, api, db, design, rest, sql, stream]
        }
    }

    // -------------------- Hard: Sliding-window anomaly grouping --------------------
    static class SlidingWindowAnomaly {

        static class Event {
            String user;
            long timestamp; // in ms
            Event(String u, long t){ this.user=u; this.timestamp=t;}
        }

        public static Map<Long, List<String>> process(List<Event> events, int threshold) {
            // Group events by 1-second buckets
            Map<Long, List<Event>> bucketed = events.stream()
                    .collect(Collectors.groupingBy(e -> e.timestamp/1000));

            Map<Long, List<String>> result = new TreeMap<>();

            bucketed.forEach((bucket, bucketEvents) -> {
                Map<String, List<Long>> userTimestamps = bucketEvents.stream()
                        .collect(Collectors.groupingBy(e -> e.user,
                                Collectors.mapping(e -> e.timestamp, Collectors.toList())));

                List<String> violators = userTimestamps.entrySet().stream()
                        .filter(entry -> hasThresholdViolation(entry.getValue(), threshold))
                        .map(Map.Entry::getKey)
                        .sorted()
                        .collect(Collectors.toList());

                if(!violators.isEmpty()) {
                    result.put(bucket, violators);
                }
            });

            return result;
        }

        private static boolean hasThresholdViolation(List<Long> times, int threshold) {
            // sort timestamps
            List<Long> sorted = times.stream().sorted().collect(Collectors.toList());
            for(int i=0; i<=sorted.size()-threshold; i++) {
                long window = sorted.get(i+threshold-1) - sorted.get(i);
                if(window <= 200) return true; // within 200ms
            }
            return false;
        }

        public static void main(String[] args) {
            List<Event> events = Arrays.asList(
                    new Event("u1", 1000),
                    new Event("u2", 1050),
                    new Event("u1", 1600),
                    new Event("u3", 1700),
                    new Event("u2", 4000),
                    new Event("u1", 4050)
            );
            Map<Long, List<String>> res = process(events, 2);
            System.out.println(res); // Output: {1=[u1], 4=[u1, u2]}
        }
    }
}
