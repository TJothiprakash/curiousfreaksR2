1️⃣ reduce vs collect – the main idea
Feature	reduce	collect
Result type	Usually same as stream element type, or you supply identity	Can produce any type, even mutable containers (like List, Map)
Mutability	Should be immutable, i.e., combine two results without modifying them	Designed for mutable accumulation, e.g., adding to a List
Associativity	Mandatory: (a ⊕ b) ⊕ c == a ⊕ (b ⊕ c)	Handled internally by collector (combiner must be associative)
Parallel safe	Only works if identity + associative function	Yes, if collector defines proper combiner
2️⃣ Why reduce fails for mutable containers in parallel

Suppose you try to collect into a List with reduce:

List<String> words = List.of("Java", "Streams", "Reduce", "Collect");

List<String> result = words.parallelStream()
.reduce(
new ArrayList<>(),                 // identity
(list, word) -> { list.add(word); return list; },  // accumulator
(list1, list2) -> { list1.addAll(list2); return list1; } // combiner
);

System.out.println(result);


❌ Problem: This can fail in parallel:

The accumulator mutates the identity list.

Two threads may mutate the same list concurrently, causing ConcurrentModificationException or lost data.

reduce assumes immutable results for parallel safety.

3️⃣ Correct approach: collect for mutable results

Use a collector designed for mutable accumulation:

List<String> result = words.parallelStream()
.collect(Collectors.toList());

System.out.println(result);


✅ Safe in parallel because:

Collectors.toList() defines:

Supplier → creates a new mutable container per thread

Accumulator → safely adds elements

Combiner → merges containers after parallel execution

4️⃣ General rule

Use reduce for:

Immutable types (numbers, strings, immutable objects)

Associative operations (sum, max, min, concatenation of strings via +)

Use collect for:

Mutable accumulation (lists, sets, maps, complex structures)

Parallel-safe collection into containers

5️⃣ Concrete example

Suppose we want a map of word lengths → list of words:

List<String> words = List.of("Java","Streams","Reduce","Collect");

// Unsafe reduce (fails in parallel)
Map<Integer, List<String>> map = words.parallelStream()
.reduce(
new HashMap<>(),
(m, word) -> {
m.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
return m;
},
(m1, m2) -> {
m2.forEach((k,v) -> m1.merge(k, v, (l1,l2) -> { l1.addAll(l2); return l1; }));
return m1;
}
); // ❌ risky in parallel


Safe alternative using collect:

Map<Integer, List<String>> map = words.parallelStream()
.collect(Collectors.groupingBy(
String::length
));

System.out.println(map);


collect handles mutable containers correctly.

Works safely in parallel.

Much cleaner and readable.

✅ Key takeaway

reduce → immutable results, must satisfy identity + associativity, not for mutables.

collect → mutable, safe for parallel streams, supports complex aggregation.

Parallel reduce on a mutable container is almost always wrong; use collect instead.