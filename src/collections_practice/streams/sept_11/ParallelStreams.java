package collections_practice.streams.sept_11;


/*Explain when to choose parallel streams, considering element cost, data size,
spliterator characteristics (SIZED, ORDERED, DISTINCT),
and common sources (collections vs. IO-bound streams), and outline a safe pattern to measure real speedups.

*/


/*🔹 When to choose parallel streams?

Java streams can run in two modes:

Sequential (stream())

Parallel (parallelStream() or stream().parallel())

Whether parallel helps depends on work per element, data size, and source characteristics.

1. Element processing cost

If each element is cheap (like summing numbers or mapping a field), parallel streams often hurt performance due to thread-splitting and synchronization overhead.

If each element is expensive (e.g., heavy CPU calculations like image processing, cryptography, or complex transforms), parallel streams can shine because multiple cores share the work.

👉 Rule of thumb:

Per-element cost should be at least 10–100 µs before parallelism pays off.

2. Data size

Small datasets (hundreds, thousands of elements) rarely benefit from parallelism — overhead dominates.

Large datasets (millions of elements) often benefit because splitting work across threads reduces total wall-clock time.

3. Spliterator characteristics

Stream sources define Spliterator characteristics that affect parallelism:

SIZED: If the spliterator knows size (like ArrayList), work can be divided evenly.

ORDERED: Preserving order (e.g., List) adds overhead in parallel mode. If order doesn’t matter, use unordered().

DISTINCT: Helps optimizations for deduplication.

👉 Example:

ArrayList → SIZED + ORDERED, splits efficiently.

LinkedList → poor splitting (each split requires traversing nodes).

Streams from IO (like reading from a socket) → often not splittable, so parallel is useless.

4. Source type

Good candidates:

ArrayList, arrays, IntStream.range() — can split evenly.

Bad candidates:

LinkedList, BufferedReader.lines() — costly to split.

IO-bound sources: Don’t benefit from parallel streams, since they’re limited by external IO rather than CPU.

🔹 Safe pattern to measure speedups

Never assume parallel is faster — measure it.

Pattern:

long start = System.nanoTime();

result = data.parallelStream()
             .map(...)
             .reduce(...);

long end = System.nanoTime();

System.out.println("Elapsed: " + (end - start)/1_000_000 + " ms");


✅ Tips:

Warm up the JVM (run once before timing).

Run multiple iterations and average.

Compare sequential vs parallel on the same dataset.

Use System.nanoTime() (not currentTimeMillis()) for accurate measurement.*/
import java.util.List;
import java.util.stream.IntStream;

public class ParallelStreams {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.range(0, 10_000_000).boxed().toList();

        // Sequential
        long startSeq = System.nanoTime();
        long sumSeq = numbers.stream()
                .mapToLong(i -> slowSquare(i))
                .sum();
        long endSeq = System.nanoTime();
        System.out.println("Sequential: " + (endSeq - startSeq)/1_000_000 + " ms");

        // Parallel
        long startPar = System.nanoTime();
        long sumPar = numbers.parallelStream()
                .mapToLong(i -> slowSquare(i))
                .sum();
        long endPar = System.nanoTime();
        System.out.println("Parallel: " + (endPar - startPar)/1_000_000 + " ms");
    }

    // Simulates heavy per-element work
    static long slowSquare(int n) {
        try { Thread.sleep(1); } catch (InterruptedException ignored) {}
        return (long) n * n;
    }
}
