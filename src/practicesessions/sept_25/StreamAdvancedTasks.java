package practicesessions.sept_25;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamAdvancedTasks {

    // -------------------- Easy: Normalize and dedupe emails --------------------
    static class NormalizeEmails {
        public static List<String> process(List<String> emails) {
            return emails.stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }

        public static void main(String[] args) {
            List<String> emails = Arrays.asList(
                    " Ram@EXAMPLE.com ",
                    "ram@example.com",
                    "meera@Example.COM",
                    "Asha@example.com "
            );
            System.out.println(process(emails));
            // Output: [asha@example.com, meera@example.com, ram@example.com]
        }
    }

    // -------------------- Medium: Group words by anagram key --------------------
    static class AnagramGroups {
        public static List<List<String>> process(List<String> words) {
            return words.stream()
                    .collect(Collectors.groupingBy(
                            w -> w.chars().sorted()
                                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                    .toString(),
                            Collectors.toList()
                    ))
                    .values().stream()
                    .filter(g -> g.size() >= 2)
                    .sorted((a, b) -> Integer.compare(b.size(), a.size()))
                    .collect(Collectors.toList());
        }

        public static void main(String[] args) {
            List<String> words = Arrays.asList("eat", "tea", "ate", "tan", "nat", "bat", "tab");
            System.out.println(process(words));
            // Output: [[eat, tea, ate], [bat, tab], [tan, nat]]
        }
    }

    // -------------------- Medium: Sessionize clickstream --------------------
    static class ClickstreamSessionizer {
        static class Event {
            String userId;
            long timestamp;
            String action;

            Event(String u, long t, String a) {
                userId = u;
                timestamp = t;
                action = a;
            }
        }

        public static Map<String, List<List<String>>> process(List<String> lines, long sessionGap) {
            List<Event> events = lines.stream()
                    .map(l -> {
                        String[] parts = l.split(",");
                        return new Event(parts[0], Long.parseLong(parts[1]), parts[2]);
                    })
                    .collect(Collectors.toList());

            Map<String, List<Event>> perUser = events.stream()
                    .collect(Collectors.groupingBy(e -> e.userId));

            Map<String, List<List<String>>> result = new HashMap<>();
            perUser.forEach((user, userEvents) -> {
                List<Event> sorted = userEvents.stream()
                        .sorted(Comparator.comparingLong(e -> e.timestamp))
                        .collect(Collectors.toList());

                List<List<String>> sessions = new ArrayList<>();
                List<String> session = new ArrayList<>();
                long lastTs = -1;
                for (Event e : sorted) {
                    if (lastTs == -1 || e.timestamp - lastTs <= sessionGap) {
                        session.add(e.action);
                    } else {
                        sessions.add(session);
                        session = new ArrayList<>();
                        session.add(e.action);
                    }
                    lastTs = e.timestamp;
                }
                if (!session.isEmpty()) sessions.add(session);
                result.put(user, sessions);
            });

            return result;
        }

        public static void main(String[] args) {
            List<String> lines = Arrays.asList(
                    "u1,100,view",
                    "u1,130,click",
                    "u2,105,view",
                    "u1,301,click",
                    "u2,400,view"
            );
            Map<String, List<List<String>>> res = process(lines, 180);
            System.out.println(res);
            // Example output: u1 -> [[view, click],[click]], u2 -> [[view],[view]]
        }
    }

    // -------------------- Medium: Top-K categories per user --------------------
    static class TopKCategories {
        static class Transaction {
            String user;
            String category;
            int amount;

            Transaction(String u, String c, int a) {
                user = u;
                category = c;
                amount = a;
            }
        }

        public static Map<String, List<Map.Entry<String, Integer>>> process(List<Transaction> txs, int k) {
            // Group by user -> category -> sum amount
            Map<String, Map<String, Integer>> userCatSum = txs.stream()
                    .collect(Collectors.groupingBy(t -> t.user,
                            Collectors.groupingBy(t -> t.category,
                                    Collectors.summingInt(t -> t.amount)
                            )
                    ));

            // For each user, get top-k categories
            Map<String, List<Map.Entry<String, Integer>>> result = new HashMap<>();
            userCatSum.forEach((user, catMap) -> {
                List<Map.Entry<String, Integer>> topK = catMap.entrySet().stream()
                        .sorted((a, b) -> {
                            int cmp = b.getValue().compareTo(a.getValue());
                            if (cmp == 0) return a.getKey().compareTo(b.getKey());
                            return cmp;
                        })
                        .limit(k)
                        .collect(Collectors.toList());
                result.put(user, topK);
            });
            return result;
        }

        public static void main(String[] args) {
            List<Transaction> txs = Arrays.asList(
                    new Transaction("ravi", "grocery", 120),
                    new Transaction("ravi", "fuel", 900),
                    new Transaction("ravi", "grocery", 80),
                    new Transaction("asha", "fuel", 200),
                    new Transaction("asha", "books", 500),
                    new Transaction("asha", "books", 50)
            );
            Map<String, List<Map.Entry<String, Integer>>> res = process(txs, 2);
            System.out.println(res);
            // Expected: {ravi=[(fuel,900),(grocery,200)], asha=[(books,550),(fuel,200)]}
        }
    }

    // -------------------- Hard: Rolling 95th percentile latency --------------------
    static class RollingPercentile {
        public static List<Integer> process(List<Integer> latencies, int windowSize) {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i <= latencies.size() - windowSize; i++) {
                List<Integer> window = latencies.subList(i, i + windowSize);
                List<Integer> sorted = window.stream().sorted().collect(Collectors.toList());
                int idx = (int) Math.ceil(0.95 * windowSize) - 1;
                result.add(sorted.get(idx));
            }
            return result;
        }

        public static void main(String[] args) {
            List<Integer> latencies = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
            List<Integer> res = process(latencies, 5);
            System.out.println(res);
            // Example: [50,60,70,80,90,100] for rolling windows of size 5, 95th percentile
        }
    }
}
