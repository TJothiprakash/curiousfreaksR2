package collections_practice.streams.sept_11;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/*Implement a custom Spliterator that merges continuation lines (ending with a backslash)
 into a single logical record while advertising correct characteristics and enabling downstream parallelism where valid.*/
public class ContinuationLineSpliterator implements Spliterator<String> {

    private final Iterator<String> source;
    private String nextLine; // buffer for merged line

    public ContinuationLineSpliterator(Iterator<String> source) {
        this.source = source;
    }

    @Override
    public boolean tryAdvance(Consumer<? super String> action) {
        if (!source.hasNext()) return false;

        StringBuilder sb = new StringBuilder();
        String line = (nextLine != null) ? nextLine : source.next();
        nextLine = null;

        sb.append(line.endsWith("\\") ? line.substring(0, line.length() - 1) : line);

        while (line.endsWith("\\") && source.hasNext()) {
            line = source.next();
            sb.append(line.endsWith("\\") ? line.substring(0, line.length() - 1) : line);
        }

        action.accept(sb.toString());
        return true;
    }

    @Override
    public Spliterator<String> trySplit() {
        // Cannot safely split continuation lines → return null
        // Optionally, you could split by chunks if the input is a RandomAccess list
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE; // unknown
    }

    @Override
    public int characteristics() {
        return ORDERED | NONNULL | IMMUTABLE;
    }


    public static void main(String[] args) {
        List<String> lines = List.of(
                "first line\\",
                " continued",
                "second line",
                "third line\\",
                " still third\\",
                " ends here"
        );

        Spliterator<String> spliterator = new ContinuationLineSpliterator(lines.iterator());
        Stream<String> mergedStream = StreamSupport.stream(spliterator, false); // sequential
        mergedStream.forEach(System.out::println);
    }

}
/*1️⃣ Why a custom Spliterator?

Java Streams rely on Spliterator for splitting and iteration.

Custom Spliterator allows you to:

Transform a stream on-the-fly (merge continuation lines).

Advertise characteristics like ORDERED, NONNULL, IMMUTABLE.

Enable parallel processing where possible.

4️⃣ Key points
a) tryAdvance

Reads the next line(s) from the source iterator.

Merges continuation lines (line endsWith("\\")) into a single record.

Passes the merged line to the consumer.

b) trySplit

Parallelism consideration:

Cannot blindly split the iterator because a continuation line may cross a split boundary.

Returning null → forces sequential processing (safe).

Optional: if the source is a random-access list, you could split at safe boundaries (lines not ending with \).

c) Characteristics

ORDERED → preserves the order of lines.

NONNULL → stream will never produce null elements.

IMMUTABLE → the source is not mutated.

d) Downstream parallelism

Since trySplit() returns null, this stream is sequential.

If you can guarantee chunks without continuation lines, you could implement a split-safe logic, enabling true parallelism.

5️⃣ Notes on correctness

This handles any number of continuation lines.

Works with infinite streams (lazy evaluation).

Safe for downstream operations like map, filter, and collect.*/