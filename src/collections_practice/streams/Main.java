package collections_practice.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(numbers);
        //write your code here
        List<Integer> squares = numbers.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(squares);
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eva");
        int longestLength = findMaxLength(names);
        System.out.println(longestLength);
        List<String> sentences = Arrays.asList("Java Stream API provides a fluent interface for processing sequences of elements.", "It supports functional-style operations on streams of elements, such as map-reduce transformations.", "In this exercise, you need to count the total number of words in all sentences.");
        long totalWords = countDistinctWords(sentences);
        System.out.println(totalWords);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = getSUmofList(numbers1);
        System.out.println("Sum of numbers: " + numbers1 + "Is " + sum);
        List<String> words = Arrays.asList("apple","apple","banana", "cherry", "date", "elderberry");
        //write your code here
        Map<Integer, List<String>> map = getMappedWords(words);
        System.out.println(map);
    }

    private static Map<Integer, List<String>> getMappedWords(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(String::length));
    }

    private static int getSUmofList(List<Integer> numbers1) {

        return numbers1.stream().mapToInt(n -> n).sum();

    }

    private static long countDistinctWords(List<String> sentences) {

        /*Use Stream API to count the total number of distinct
        words (case-insensitive) in all the seztences. Expected Output:*/
        return sentences.stream().flatMap(s -> Arrays.stream(s.split(" "))).map(String::toLowerCase).distinct().count();
    }

    private static int findMaxLength(List<String> names) {
        return names.stream().mapToInt(n -> n.length()).max().getAsInt();

    }
}