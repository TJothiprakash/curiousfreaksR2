    package collections_practice.streams.sept_11;


    /*Design a pipeline to parse large log lines into domain events, deduplicate by eventId,
     window by minute, and compute per-minute counts; discuss short-circuiting, ordering,
    and pitfalls of side effects and shared mutability in parallel streams.*/

    import java.time.Instant;
    import java.time.temporal.ChronoUnit;
    import java.util.ArrayList;
    import java.util.LinkedHashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.stream.Collector;
    import java.util.stream.Collectors;

    public class Pipeline {

        public static void main(String[] args) {
            // NOTE: DomainEvent now accepts (String eventId, long timestamp)
            List<DomainEvent> events = List.of(
                    new DomainEvent("e1", Instant.parse("2025-09-11T10:05:23Z").toEpochMilli()),
                    new DomainEvent("e2", Instant.parse("2025-09-11T10:05:35Z").toEpochMilli()),
                    new DomainEvent("e1", Instant.parse("2025-09-11T10:05:50Z").toEpochMilli()), // duplicate e1
                    new DomainEvent("e3", Instant.parse("2025-09-11T10:06:05Z").toEpochMilli()),
                    new DomainEvent("e2", Instant.parse("2025-09-11T10:06:20Z").toEpochMilli())  // duplicate e2
            );

            // 1) Deduplicate by eventId (keep first seen)
            List<DomainEvent> deduped = events.stream()
                    .collect(deduplicateByEventId());

            System.out.println("Deduplicated events:");
            deduped.forEach(System.out::println);

            // 2) Window by minute and compute per-minute counts
            Map<Instant, Long> perMinuteCounts = deduped.stream()
                    .collect(Collectors.groupingBy(
                            e -> Instant.ofEpochMilli(e.timestamp).truncatedTo(ChronoUnit.MINUTES),
                            Collectors.counting()
                    ));

            System.out.println("\nPer-minute counts (window -> count):");
            perMinuteCounts.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
        }

        /**
         * Collector that deduplicates DomainEvent by eventId, keeping the first encountered event for each id.
         *
         * Implementation details:
         * - Supplier: LinkedHashMap::new so insertion order (first seen) is preserved.
         * - Accumulator: putIfAbsent(eventId, event) => keeps first seen.
         * - Combiner: merges map2 into map1 using putIfAbsent so earlier entries in map1 are retained.
         * - Finisher: convert map.values() (in insertion order) to an ArrayList.
         *
         * Note: This collector preserves first-seen for sequential streams. For parallel streams,
         * each sub-accumulator preserves local insertion order; combiner merges try to keep first-seen
         * from each sub-accumulator but global "first" depends on how the stream splits.
         */
        public static Collector<DomainEvent, ?, List<DomainEvent>> deduplicateByEventId() {
            return Collector.of(
                    // Supplier -> use LinkedHashMap to preserve insertion order (first-seen)
                    LinkedHashMap<String, DomainEvent>::new,

                    // Accumulator -> keep the first event for each id
                    (map, event) -> map.putIfAbsent(event.eventId, event),

                    // Combiner -> merge two maps; keep existing entries in map1
                    (map1, map2) -> {
                        map2.forEach(map1::putIfAbsent);
                        return map1;
                    },

                    // Finisher -> produce a List preserving insertion order
                    map -> new ArrayList<>(map.values())
            );
        }

        // DomainEvent with eventId and timestamp (no message)
        static class DomainEvent {
            final String eventId;
            final long timestamp; // epoch millis

            DomainEvent(String eventId, long timestamp) {
                this.eventId = eventId;
                this.timestamp = timestamp;
            }

            @Override
            public String toString() {
                // nice readable representation
                return eventId + "@" + Instant.ofEpochMilli(timestamp).toString();
            }
        }
    }


    /* generic collector
    * public static <T, K> Collector<T, ?, List<T>> deduplicateByKey(Function<T, K> keyExtractor) {
    return Collector.of(
        LinkedHashMap<K, T>::new,                     // supplier
        (map, element) -> map.putIfAbsent(keyExtractor.apply(element), element), // accumulator
        (map1, map2) -> { map2.forEach(map1::putIfAbsent); return map1; },      // combiner
        map -> new ArrayList<>(map.values())          // finisher
    );
}
*/