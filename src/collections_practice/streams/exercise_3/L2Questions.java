package collections_practice.streams.exercise_3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class L2Questions {
    public static void main(String[] args) {

        // 🔵 Sorting & Distinct Operations
        List<Integer> numbers = Arrays.asList(1, 6, 2, 3, 4, 5, 6, 7, 8, 9, 10, 104);
        List<String> names = new ArrayList<>(List.of(
                "John", "Mike", "Jane", "John", "MikeTyson", "JaneTyson",
                "Jhonson", "Taylor Swift", "Rose", "Arun kumar", "madam", "123", "42"
        ));

        // 41-42
        System.out.println("Ascending: " + numbers.stream().sorted().toList());
        System.out.println("Descending: " + numbers.stream().sorted(Comparator.reverseOrder()).toList());

        // 43-44
        System.out.println("Strings by length (asc): " + names.stream().sorted(Comparator.comparingInt(String::length)).toList());
        System.out.println("Strings by length (desc): " + names.stream().sorted(Comparator.comparingInt(String::length).reversed()).toList());

        // 45-46
        System.out.println("Unique strings: " + names.stream().distinct().toList());
        System.out.println("Unique, sorted numbers: " + numbers.stream().distinct().sorted().toList());

        // 47-48
        System.out.println("Top 3 largest: " + numbers.stream().sorted(Comparator.reverseOrder()).limit(3).toList());
        System.out.println("Bottom 3 smallest: " + numbers.stream().sorted().limit(3).toList());

        // 49-50
        Integer secondHighest = numbers.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1);
        Integer secondSmallest = numbers.stream().sorted().skip(1).findFirst().orElse(-1);
        System.out.println("2nd Highest: " + secondHighest);
        System.out.println("2nd Smallest: " + secondSmallest);

        // 🟣 Reduce & Aggregate
        OptionalDouble avg = numbers.stream().mapToInt(i -> i).average();
        System.out.println("Average: " + avg.orElse(0));

        int sumOfSquares = numbers.stream().map(n -> n * n).reduce(0, Integer::sum);
        System.out.println("Sum of Squares: " + sumOfSquares);

        String concatUpper = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println("Concat Uppercase: " + concatUpper);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);

        long countGt100 = numbers.stream().filter(n -> n > 100).count();
        System.out.println("Count > 100: " + countGt100);

        String longest = names.stream().max(Comparator.comparingInt(String::length)).orElse("");
        String shortest = names.stream().min(Comparator.comparingInt(String::length)).orElse("");
        System.out.println("Longest: " + longest);
        System.out.println("Shortest: " + shortest);

        int last = numbers.isEmpty() ? -1 : numbers.get(numbers.size() - 1);
        System.out.println("Last element: " + last);

        Optional<Integer> first = numbers.stream().findFirst();
        System.out.println("First element (optional): " + first);

        Map<Integer, Long> freqMap = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Frequency map: " + freqMap);

        // 🟡 Matching & Searching
        boolean containsZ = names.stream().anyMatch(s -> s.toLowerCase().contains("z"));
        System.out.println("Any contains 'z': " + containsZ);

        boolean allLowercase = names.stream().allMatch(s -> s.equals(s.toLowerCase()));
        System.out.println("All lowercase: " + allLowercase);

        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);
        System.out.println("None negative: " + noneNegative);

        boolean anyPalindrome = names.stream()
                .map(String::toLowerCase)
                .anyMatch(s -> s.equals(new StringBuilder(s).reverse().toString()));
        System.out.println("Any palindrome: " + anyPalindrome);

        boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
        System.out.println("All even: " + allEven);

        int firstEven = numbers.stream().filter(n -> n % 2 == 0).findFirst().orElse(-1);
        int anyOdd = numbers.stream().filter(n -> n % 2 != 0).findAny().orElse(-1);
        System.out.println("First even: " + firstEven);
        System.out.println("Any odd: " + anyOdd);

        long wordsLen3to6 = names.stream().filter(s -> s.length() >= 3 && s.length() <= 6).count();
        System.out.println("Words length 3–6: " + wordsLen3to6);

        String containsAandE = names.stream().filter(s -> s.contains("a") && s.contains("e")).findFirst().orElse("Not found");
        System.out.println("Contains 'a' and 'e': " + containsAandE);

        int firstGt100 = numbers.stream().filter(n -> n > 100).findFirst().orElse(-1);
        System.out.println("First > 100: " + firstGt100);

        // 🔴 Sublist & Slice
        System.out.println("Skip first 5: " + names.stream().skip(5).toList());
        System.out.println("First 4: " + names.stream().limit(4).toList());

        List<Integer> mid3 = numbers.size() >= 7 ? numbers.stream().skip(2).limit(3).toList() : List.of();
        System.out.println("Middle 3: " + mid3);

        List<Integer> reversed = new ArrayList<>(numbers);
        Collections.reverse(reversed);
        System.out.println("Reversed: " + reversed);

        List<Integer> squaredPositives = numbers.stream().filter(n -> n > 0).map(n -> n * n).toList();
        System.out.println("Squared positives: " + squaredPositives);

        int top5Sum = numbers.stream().sorted(Comparator.reverseOrder()).limit(5).mapToInt(Integer::intValue).sum();
        System.out.println("Top 5 sum: " + top5Sum);

        OptionalDouble bottom3Avg = numbers.stream().sorted().limit(3).mapToInt(Integer::intValue).average();
        System.out.println("Bottom 3 avg: " + bottom3Avg.orElse(0));

        Map<Boolean, List<Integer>> partitioned = numbers.stream().collect(Collectors.partitioningBy(n -> n > 50));
        System.out.println("Partitioned > 50: " + partitioned);

        List<String> last2Chars = names.stream()
                .filter(s -> s.length() >= 2)
                .map(s -> s.substring(s.length() - 2))
                .toList();
        System.out.println("Last 2 chars: " + last2Chars);

        List<String> digitOnlyStrings = names.stream().filter(s -> s.matches("\\d+")).toList();
        System.out.println("Strings with only digits: " + digitOnlyStrings);
    }
}
