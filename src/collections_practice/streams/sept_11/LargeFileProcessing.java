package collections_practice.streams.sept_11;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

/*Process a very large text file using Files.lines, parse JSON per line, filter and aggregate safely with try-with-resources to ensure the stream closes; discuss why IO-backed streams should not be parallelized by default.*/
public class LargeFileProcessing {

    static class Record {
        public String id;
        public int value;
    }

    public static void main(String[] args) throws Exception {
        Path filePath = Path.of("data.jsonl"); // large file, 1 JSON object per line
        ObjectMapper mapper = new ObjectMapper();

        // Use try-with-resources to safely close the stream
        try (Stream<String> lines = Files.lines(filePath)) {

            int sum = lines
                    .map(line -> {
                        try {
                            return mapper.readValue(line, Record.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)          // filter out parse failures
                    .filter(r -> r.value > 0)          // some business logic
                    .mapToInt(r -> r.value)            // map to int for aggregation
                    .sum();                             // aggregate safely

            System.out.println("Sum of positive values: " + sum);
        }
    }
}

/*2️⃣ Explanation line by line

Files.lines(filePath)

Lazily produces a stream of lines from the file.

Important: it keeps an open file handle.

try-with-resources

Ensures the stream and underlying file are automatically closed even if exceptions occur.

map(line -> mapper.readValue(...))

Parse each line from JSON into a Record object.

Wrap in try-catch to safely handle malformed lines.

filter(Objects::nonNull)

Skip any failed parses.

filter(r -> r.value > 0)

Example business rule: only keep positive values.

mapToInt(r -> r.value)

Map Record to an integer for aggregation.

sum()

Aggregate all remaining values.

3️⃣ Why IO-backed streams should not be parallelized by default

Files.lines is IO-bound, not CPU-bound.

Parallel streams work best for CPU-heavy tasks with splittable sources (arrays, lists).

Problems with parallelizing IO streams:

Single file handle bottleneck → reading from disk can’t be parallelized efficiently.

Thread contention → multiple threads trying to read from the same file doesn’t help.

Increased memory pressure → partial lines may be buffered unnecessarily.

Order-sensitive streams → if you want to preserve order, parallelism adds extra cost.

✅ Rule of thumb: keep IO streams sequential. Only parallelize CPU-heavy processing downstream, e.g., parsing/processing after reading.

4️⃣ If you really need parallelism

You could read sequentially but parallelize the CPU-heavy work:

try (Stream<String> lines = Files.lines(filePath)) {
    int sum = lines
        .map(line -> {
            try { return mapper.readValue(line, Record.class); }
            catch(Exception e) { return null; }
        })
        .filter(Objects::nonNull)
        .parallel()                       // parallelize CPU-heavy filtering/mapping
        .filter(r -> r.value > 0)
        .mapToInt(r -> r.value)
        .sum();
}


Reading remains sequential.

Only CPU-bound transformations run in parallel.

✅ Takeaways

Always use try-with-resources for Files.lines → prevents resource leaks.

Keep IO sequential by default. Parallelism can help only after data is in memory.

Filter, map, and aggregate safely using streams → clean and readable.

If memory is a concern with very large files, consider streaming + partial aggregation, or heap-bounded top-N structures.*/