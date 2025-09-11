package collections_practice.streams.sept_11;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Daily_Tasks {

    static void main() {
        stream();
    }

    /*Given a List<Integer>, produce the sorted squares of distinct even numbers using
     a Stream pipeline and primitive streams to avoid boxing where possible (e.g., mapToInt),
     and explain the effect of distinct() on ordering.

    Given a List<String>, group by first character (case-insensitive) and return only groups
    of size ≥ 2 with stable encounter order, choosing appropriate Collectors
     (e.g., groupingBy with a downstream filter) and discussing stream encounter order guarantees.

    */
    public static void stream() {
        List<Integer> list = List.of(1, 2, 3, 4, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> firstqn = list.stream().sorted().mapToInt(a -> a * a).boxed().distinct().toList();
        System.out.println(firstqn);
// suggestion first filter then process it
        List<String> strings = List.of("arun", "gowtham", "thangaraj", "A", "Anbarasu", "logumani", "arvind", "testuser", "venugopal", "gopal");
        Map<Character, List<String>> secondqn = strings.stream().filter(a -> a.length() >= 2).map(a -> a.toLowerCase()).collect(Collectors.groupingBy(a -> a.charAt(0)));
        System.out.println("secondqn = " + secondqn);
        // need to maintain the encoutering order (given order) here all are sorted order use linkedhashmap and
// do groping and filtering in two separate processes

        /*Stream API medium-hard

Design a pipeline to parse large log lines into domain events, deduplicate by eventId, window by minute, and compute per-minute counts; discuss short-circuiting, ordering, and pitfalls of side effects and shared mutability in parallel streams.

Explain when to choose parallel streams, considering element cost, data size, spliterator characteristics (SIZED, ORDERED, DISTINCT), and common sources (collections vs. IO-bound streams), and outline a safe pattern to measure real speedups.

Flatten a List<Optional<User>> into distinct emails of active users using flatMap and Optional.stream, and explain how laziness and short-circuiting interact with operations like anyMatch and limit.

Use groupingBy + mapping + collectingAndThen to compute the top N transactions per account by amount with stable ordering, and discuss memory usage trade-offs versus streaming/heap-bound approaches.

Compare reduce versus collect for building complex results, focusing on associativity, identity, and mutability constraints; provide a case that fails under parallel reduce but succeeds with a proper Collector.

Process a very large text file using Files.lines, parse JSON per line, filter and aggregate safely with try-with-resources to ensure the stream closes; discuss why IO-backed streams should not be parallelized by default.

Implement a custom Spliterator that merges continuation lines (ending with a backslash) into a single logical record while advertising correct characteristics and enabling downstream parallelism where valid.

*/


    }
}
