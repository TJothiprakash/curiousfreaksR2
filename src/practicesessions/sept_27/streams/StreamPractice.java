package practicesessions.sept_27.streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamPractice {

    public static void main(String[] args) {
        practice();
    }

    private static void practice() {
        // ------------------ Easy 1 ------------------
        List<String> names = List.of("alpha", "Beta", "ALPHA", "beta", "gamma");
        List<String> uniqueLowerSorted = names.stream()
                .map(String::toLowerCase)      // normalize case
                .distinct()                    // unique
                .sorted()                      // natural order
                .toList();
        System.out.println("Easy 1 result: " + uniqueLowerSorted);

        // ------------------ Medium 1 ------------------
        // Input: name,age
        List<String> people = List.of("JP,26", "Ana,31", "Raj,31", "Mei,29");
        Map<Integer, List<String>> ageToNames = people.stream()
                .map(s -> s.split(","))               // split into [name, age]
                .collect(Collectors.groupingBy(
                        arr -> Integer.parseInt(arr[1]), // key = age
                        TreeMap::new,                     // keep keys sorted
                        Collectors.mapping(arr -> arr[0], // value = name
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> {
                                            Collections.sort(list); // sort names
                                            return list;
                                        }
                                )
                        )
                ));
        System.out.println("Medium 1 result: " + ageToNames);

        // ------------------ Medium 2 ------------------
        List<String> logs = List.of("INFO:init", "WARN:disk", "ERROR:db", "INFO:done", "ERROR:io");
        Map<String, Long> levelCounts = logs.stream()
                .map(s -> s.split(":")[0]) // get level
                .collect(Collectors.groupingBy(
                        level -> level,
                        LinkedHashMap::new,      // maintain insertion order
                        Collectors.counting()
                ));
        System.out.println("Medium 2 result: " + levelCounts);

        // ------------------ Medium 3 ------------------
        List<List<Integer>> nestedLists = List.of(
                List.of(1, 2, 3),
                List.of(2, 3, 4),
                List.of(3, 4, 5)
        );
        List<Integer> uniqueSquaresDesc = nestedLists.stream()
                .flatMap(List::stream)
                .map(x -> x * x)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("Medium 3 result: " + uniqueSquaresDesc);

        // ------------------ Hard 1 ------------------
        List<String[]> transactions = List.of(
                new String[]{"t1","u1","200"},
                new String[]{"t2","u2","150"},
                new String[]{"t3","u1","300"},
                new String[]{"t4","u3","50"},
                new String[]{"t5","u2","500"}
        );

        List<Map.Entry<String, Integer>> topUsers = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t[1], // userId
                        Collectors.summingInt(t -> Integer.parseInt(t[2])) // sum amount
                ))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    int cmp = e2.getValue().compareTo(e1.getValue()); // descending total
                    if (cmp == 0) return e1.getKey().compareTo(e2.getKey()); // tie-break by userId
                    return cmp;
                })
                .limit(2)
                .toList();

        System.out.println("Hard 1 result: " + topUsers);
    }
}
