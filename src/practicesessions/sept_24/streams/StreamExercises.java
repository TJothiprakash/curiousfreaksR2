package practicesessions.sept_24.streams;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * StreamExercises.java
 *
 * Contains solutions for:
 * 1) Easy — case-insensitive prefix filter + map + sort
 * 2) Medium — anagram grouping
 * 3) Medium — sliding window average (size 3)
 * 4) Medium — CSV reduce to department totals
 * 5) Hard — custom Collector for top-K by frequency
 *
 * Each task includes a Problem, Intuition, Dry run, Code (method), and Complexity.
 */
public class StreamExercises {

    // --------------------------
    // Task 1 — EASY
    // Case-insensitive prefix filter and map to uppercase, then sorted
    // --------------------------
    /*
     * Problem:
     * Input: ["ajay","Vijay","arjun","bala","anita","Aarthi","abi"]
     * Output: uppercase names starting with 'a' ignoring case, sorted lexicographically:
     * ["AARTHI","ABI","AJAY","ANITA","ARJUN"]
     *
     * Intuition:
     * Filter using toLowerCase().startsWith("a"), map toUpperCase, then sorted() and collect.
     *
     * Dry run:
     * "ajay" -> startsWith 'a' true -> "AJAY"
     * "Vijay" -> false
     * "Aarthi" -> true -> "AARTHI"
     * After mapping and sorting -> expected order as above.
     *
     * Complexity:
     * Time: O(n log n) for sorting (n = number of matches)
     * Space: O(n)
     */
    public static List<String> easyPrefixFilterAndMap(List<String> names) {
        return names.stream()
                .filter(s -> s != null && s.toLowerCase().startsWith("a"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    // --------------------------
    // Task 2 — MEDIUM
    // Anagram grouping
    // --------------------------
    /*
     * Problem:
     * Input: ["eat","tea","tan","ate","nat","bat"]
     * Group anagrams together, using key = sorted characters.
     *
     * Intuition:
     * Compute normalized key by sorting characters, then Collectors.groupingBy(keyFunc).
     * Use LinkedHashMap to preserve encounter-order of groups.
     *
     * Dry run:
     * "eat" -> key "aet" -> group ["eat"]
     * "tea" -> key "aet" -> group ["eat","tea"]
     * "tan" -> key "ant" -> group ["tan"]
     * ...
     *
     * Complexity:
     * Time: O(n * m log m) where m = avg word length (for sorting each word)
     * Space: O(n * m)
     */
    public static Collection<List<String>> groupAnagrams(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(
                        w -> {
                            char[] arr = w.toCharArray();
                            Arrays.sort(arr);
                            return new String(arr);
                        },
                        LinkedHashMap::new, // keep groups in encounter order
                        Collectors.toList()
                ))
                .values();
    }

    // --------------------------
    // Task 3 — MEDIUM
    // Sliding window average (size 3)
    // --------------------------
    /*
     * Problem:
     * Given list of numbers (integers), compute length-3 moving averages, no partial windows.
     * Example input chosen: [2,4,6,8,10] -> window averages: [4.0, 6.0, 8.0]
     *
     * Intuition:
     * Use IntStream of valid starting indices 0..n-windowSize and for each index compute average
     * from subList (or use mapToDouble with sum of 3 elements). Avoid shared mutable state.
     *
     * Dry run:
     * i=0 -> avg(2,4,6)=4.0
     * i=1 -> avg(4,6,8)=6.0
     * i=2 -> avg(6,8,10)=8.0
     *
     * Complexity:
     * Time: O(n * w) where w = window size (here w=3) -> effectively O(n)
     * Space: O(n) for result
     */
    public static List<Double> slidingWindowAverageSize3(List<Integer> nums) {
        final int w = 3;
        if (nums == null || nums.size() < w) return Collections.emptyList();

        int n = nums.size();
        return IntStream.rangeClosed(0, n - w)
                .mapToDouble(i -> (nums.get(i) + nums.get(i + 1) + nums.get(i + 2)) / 3.0)
                .boxed()
                .collect(Collectors.toList());
    }

    // --------------------------
    // Task 4 — MEDIUM
    // CSV reduce to department totals
    // --------------------------
    /*
     * Problem:
     * Input lines:
     * ["eng,ram,70000","eng,vidya,80000","ops,arun,50000","eng,kavi,75000","ops,lee,55000"]
     * Produce Map {eng=225000, ops=105000}
     *
     * Intuition:
     * Stream lines -> split by comma -> groupingBy(dept, summingInt(salary)).
     * Robust parsing: trim tokens, ignore malformed lines.
     *
     * Dry run:
     * "eng,ram,70000" -> dept=eng salary=70000 -> eng sum 70000
     * ...
     * final sums as expected.
     *
     * Complexity:
     * Time: O(n) where n = number of lines
     * Space: O(d) where d = number of departments
     */
    public static Map<String, Integer> csvDepartmentTotals(List<String> lines) {
        return lines.stream()
                .map(String::trim)
                .map(line -> line.split(",", -1))
                .filter(parts -> parts.length >= 3)
                .map(parts -> new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()})
                .filter(parts -> {
                    try {
                        Integer.parseInt(parts[2]);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.groupingBy(
                        parts -> parts[0],
                        Collectors.summingInt(parts -> Integer.parseInt(parts[2]))
                ));
    }

    // --------------------------
    // Task 5 — HARD
    // Custom collector for top-K by frequency
    // --------------------------
    /*
     * Problem:
     * Given a stream of Integers and K, produce a List<Integer> containing top-K keys
     * by descending frequency. Ties broken by natural order (smaller key preferred).
     *
     * Intuition:
     * Implement a Collector that accumulates counts into a Map<Integer,Long>.
     * In finisher, use a min-heap of size K to retain top-K entries:
     *  - Heap comparator orders by (frequency asc), and when frequency equal by key desc,
     *    so that when we need to evict, we evict the entry with smaller frequency,
     *    and among ties we evict the larger key leaving smaller key (natural order) in heap.
     * Finally, extract heap content and sort by (freq desc, key asc) for final result.
     *
     * Dry run (example):
     * Stream: [1,2,2,3,3,3,4,4] K=2 -> counts: {1:1,2:2,3:3,4:2}
     * Heap after processing should contain entries for 3 (3) and 2 (2) or 4 (2),
     * tie among 2 and 4 resolved by natural order -> 2 stays.
     * Final sorted list -> [3,2]
     *
     * Complexity:
     * Collector accumulation: O(n) time to count
     * Finisher: O(d log K) where d = distinct keys (use heap of size K)
     * Space: O(d)
     */
    public static Collector<Integer, ?, List<Integer>> topKCollector(int k) {
        return Collector.of(
                HashMap::new, // supplier: counts map
                (map, value) -> map.merge(value, 1L, Long::sum), // accumulator
                (m1, m2) -> { // combiner for parallel streams
                    m2.forEach((key, cnt) -> m1.merge(key, cnt, Long::sum));
                    return m1;
                },
                (Map<Integer, Long> counts) -> {
                    if (k <= 0) return Collections.emptyList();
                    // min-heap: smallest freq on top; tie: larger key on top (so it gets evicted)
                    Comparator<Map.Entry<Integer, Long>> heapCmp = (e1, e2) -> {
                        int cmp = Long.compare(e1.getValue(), e2.getValue());
                        if (cmp != 0) return cmp;
                        return Integer.compare(e2.getKey(), e1.getKey()); // reverse key for tie eviction
                    };
                    PriorityQueue<Map.Entry<Integer, Long>> heap = new PriorityQueue<>(heapCmp);

                    for (Map.Entry<Integer, Long> e : counts.entrySet()) {
                        heap.offer(e);
                        if (heap.size() > k) heap.poll();
                    }

                    // Extract into list and sort by freq desc, key asc (natural order)
                    List<Map.Entry<Integer, Long>> top = new ArrayList<>(heap);
                    top.sort((e1, e2) -> {
                        int cmp = Long.compare(e2.getValue(), e1.getValue()); // desc freq
                        if (cmp != 0) return cmp;
                        return Integer.compare(e1.getKey(), e2.getKey()); // asc key
                    });

                    return top.stream().map(Map.Entry::getKey).collect(Collectors.toList());
                }
        );
    }

    // Helper to run hard example
    public static List<Integer> topKByFrequency(List<Integer> nums, int k) {
        return nums.stream().collect(topKCollector(k));
    }

    // --------------------------
    // main — demonstrate all tasks with example inputs
    // --------------------------
    public static void main(String[] args) throws Exception {
        System.out.println("==== Task 1: Easy — prefix filter + uppercase + sort ====");
        List<String> names = List.of("ajay","Vijay","arjun","bala","anita","Aarthi","abi");
        List<String> task1 = easyPrefixFilterAndMap(names);
        System.out.println("Input: " + names);
        System.out.println("Output: " + task1);
        System.out.println("Expected: [AARTHI, ABI, AJAY, ANITA, ARJUN]");
        System.out.println();

        System.out.println("==== Task 2: Medium — anagram grouping ====");
        List<String> words = List.of("eat","tea","tan","ate","nat","bat");
        Collection<List<String>> groups = groupAnagrams(words);
        System.out.println("Input: " + words);
        System.out.println("Anagram groups (order of groups preserved): " + groups);
        System.out.println();

        System.out.println("==== Task 3: Medium — sliding window average (size 3) ====");
        List<Integer> nums = List.of(2,4,6,8,10);
        List<Double> avgs = slidingWindowAverageSize3(nums);
        System.out.println("Input: " + nums);
        System.out.println("Window size 3 averages: " + avgs);
        System.out.println("Expected: [4.0, 6.0, 8.0]");
        System.out.println();

        System.out.println("==== Task 4: Medium — CSV reduce to department totals ====");
        List<String> lines = List.of(
                "eng,ram,70000",
                "eng,vidya,80000",
                "ops,arun,50000",
                "eng,kavi,75000",
                "ops,lee,55000"
        );
        Map<String, Integer> totals = csvDepartmentTotals(lines);
        System.out.println("Input lines: " + lines);
        System.out.println("Department totals: " + totals);
        System.out.println("Expected: {eng=225000, ops=105000}");
        System.out.println();

        System.out.println("==== Task 5: Hard — custom collector for top-K by frequency ====");
        List<Integer> seq = Arrays.asList(1,2,2,3,3,3,4,4,5,5,5,5,2,3);
        int K = 2;
        List<Integer> topK = topKByFrequency(seq, K);
        System.out.println("Input sequence: " + seq);
        System.out.println("K = " + K);
        System.out.println("Top-K keys by frequency (desc freq, ties by natural order): " + topK);
        // Let's print counts for clarity
        Map<Integer, Long> counts = seq.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Counts = " + counts);
        System.out.println("Explanation: highest freq should come first; ties broken by smaller key.");
        System.out.println();

        // Extra quick random test to show collector works for small K without sorting all entries
        List<Integer> large = IntStream.range(0, 1000).mapToObj(i -> ThreadLocalRandom.current().nextInt(0, 50)).toList();
        List<Integer> top3 = topKByFrequency(large, 3);
        System.out.println("Random test top-3 keys: " + top3);
        System.out.println("All demos finished.");
    }
}
