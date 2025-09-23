package practicesessions.sept_22.stream;
import java.util.*;
import java.util.stream.*;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

public class StreamExercises {

    // 1️⃣ Top 3 words by frequency
    public static void top3WordsExample() {
        List<String> sentences = List.of(
                "to be or not to be",
                "be the change you want to see",
                "see and be seen"
        );

        List<Map.Entry<String, Long>> top3 = sentences.stream()
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .map(String::toLowerCase)
                .filter(w -> !w.isEmpty())
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator
                        .comparing(Map.Entry<String, Long>::getValue, Comparator.reverseOrder())
                        .thenComparing(Map.Entry::getKey))
//                .limit(3)
                .toList();

        System.out.println("Top 3 words: " + top3);
    }

    // 2️⃣ Group and average price per category
    public static void averagePriceExample() {
        List<String> products = List.of(
                "fruit,apple,120",
                "fruit,banana,60",
                "veg,carrot,40",
                "veg,spinach,50",
                "grain,rice,70"
        );

        Map<String, Double> avgPrice = products.stream()
                .map(s -> s.split(","))
                .collect(Collectors.groupingBy(
                        arr -> arr[0],
                        Collectors.averagingInt(arr -> Integer.parseInt(arr[2]))
                ));

        // Round to 2 decimals
        avgPrice.replaceAll((k,v) -> Math.round(v*100.0)/100.0);

        System.out.println("Average prices: " + avgPrice);
    }

    // 3️⃣ Join-like transform: user events mapping
    public static void userEventsExample() {
        List<String> users = List.of("1,ram", "2,abi", "3,krish");
        List<String> events = List.of(
                "1,LOGIN,2025-09-21T06:01:00Z",
                "2,LOGIN,2025-09-21T06:02:00Z",
                "1,LOGOUT,2025-09-21T06:05:00Z"
        );

        Map<String, String> userIdToName = users.stream()
                .map(s -> s.split(","))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));
        System.out.println("userIdToName = " + userIdToName);
        Map<String, List<String>> userEvents = events.stream()
                .map(s -> s.split(","))
                .filter(arr -> userIdToName.containsKey(arr[0]))
                .sorted(Comparator.comparing(arr -> Instant.parse(arr[2])))
                .collect(Collectors.groupingBy(
                        arr -> userIdToName.get(arr[0]),
                        LinkedHashMap::new, // preserve insertion order
                        Collectors.mapping(arr -> arr[1], Collectors.toList())
                ));

        System.out.println("User events: " + userEvents);
    }

    // 4️⃣ Sliding window maximum
    public static List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < nums.length; i++) {
            // Remove indices outside window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Remove smaller values from the tail
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Record max for window
            if (i >= k - 1) {
                result.add(nums[deque.peekFirst()]);
            }
        }

        return result;
    }

    public static void slidingWindowExample() {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        List<Integer> maxima = maxSlidingWindow(nums, k);
        System.out.println("Sliding window maxima: " + maxima);
    }

    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Top 3 Words ===");
        top3WordsExample();
        System.out.println("\n=== Exercise 2: Average Prices ===");
        averagePriceExample();
        System.out.println("\n=== Exercise 3: User Events Mapping ===");
        userEventsExample();
        System.out.println("\n=== Exercise 4: Sliding Window Maximum ===");
        slidingWindowExample();
    }
}
