package collections_practice.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class mar_5 {
    public static void main(String[] args) {

        // Convert to modifiable ArrayList
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<String> words = new ArrayList<>(Arrays.asList("Apple", "Orange", "Lemon", "Guava", "Roseberry", "Grape", "Banana", "Kivic"));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));



 /*
Implement a custom collector to calculate the product of all integers in a list.
Group a list of objects by a property and count occurrences using streams.
Combine elements from multiple lists using streams (e.g., zip operation).
Implement a stream pipeline to read lines from a file and process them.
Find the frequency of each word in a list of strings using streams.
Given a list of integers, find the k-th smallest element using streams.
 */

//xReverse a list of strings using streams.
        String reversedListOFStrings = String.valueOf(words.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()));
        System.out.println(reversedListOFStrings);


//Implement a stream to generate Fibonacci sequence up to a specified nullmber of terms.
        int numTerms = 5; // Specify the number of terms

        // Generate Fibonacci sequence using Streams
        List<Integer> fibonacciNumbers = Stream.iterate(new int[]{0, 1}, fib -> new int[]{fib[1], fib[0] + fib[1]})
                .limit(numTerms)
                .map(fib -> fib[0])  // Extract the first number of each pair
                .collect(Collectors.toList());

        System.out.println("Fibonacci Sequence: " + fibonacciNumbers);


//Check if two lists of integers have any common elements using streams.
        List<Integer> commonElements = list1.stream().filter(list2::contains).collect(Collectors.toList());

        System.out.println(commonElements);
//Convert a list of objects to JSON using streams.


//Implement a stream to read data from a database and process it.


//Find the difference between the largest and smallest integers in a list using streams.
        int min = list1.stream().min(Comparator.naturalOrder()).get();
        int max = list1.stream().max(Comparator.naturalOrder()).get();
        int difference = max - min;
        System.out.println(difference);


//Calculate the median of a list of integers using streams.
        OptionalDouble median = list1.stream()
                .sorted() // Sort the list
                .collect(Collectors.collectingAndThen(Collectors.toList(), sortedList -> {
                    int size = sortedList.size();
                    if (size % 2 == 1) {
                        return OptionalDouble.of(sortedList.get(size / 2)); // Odd size -> Middle element
                    } else {
                        return OptionalDouble.of((sortedList.get(size / 2 - 1) + sortedList.get(size / 2)) / 2.0); // Even size -> Average of middle elements
                    }
                }));

        System.out.println("Median: " + (median.isPresent() ? median.getAsDouble() : "No data"));


//Given a list of strings, find the most common character using streams.
        Character frequentCharacter = words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c)) // Convert words to character stream
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Count occurrences
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Find max entry (most common character)
                .map(Map.Entry::getKey)
                .orElse(null); // Handle empty list case

        System.out.println("Most common character: " + frequentCharacter);


//Implement a stream to parse and process XML data.


//Filter a list of strings to get only those starting with a vowel using streams.
        List<String> vowelsStartingStrings = words.stream()
                .filter(w -> w.matches("(?i)^[aeiou].*"))  // ✅ Regex to check if word starts with a vowel
                .collect(Collectors.toList());
        System.out.println(vowelsStartingStrings);


//Implement a stream to perform batch processing of data.
        int batchSize = 10;
        List<List<Integer>> batches = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.groupingBy(n -> (n - 1) / batchSize))
                .values()
                .stream()
                .collect(Collectors.toList());

        batches.forEach(batch -> {
            System.out.println("Processing batch: " + batch);
            processBatch(batch);
        });


//Given a list of integers, find the frequency of each number using streams.
        Map<Integer, Integer> frequency = list1.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
        System.out.println("Frequency of each number: " + frequency);


//Sort a list of objects by multiple criteria using streams.
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 30, 50000),
                new Employee("Zackalice", 30, 50000),
                new Employee("Bob", 25, 60000),
                new Employee("Charlie", 35, 50000),
                new Employee("David", 28, 70000),
                new Employee("Eve", 30, 50000)
        );

//        System.out.println("before sorting employee" + employees);
        // Sort by salary, then by name
        List<Employee> byName = employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());
        System.out.println();
        System.out.println();
//        System.out.println("By name: " + byName);

//Calculate the Hamming distance between two lists of integers using streams.


//Write a stream to find the sum of integers from 1 to 100.
        int sum = Stream.iterate(1, n -> n + 1)
                .limit(100)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("sum of integers from 1 to 100 is " + sum);







//Convert a list of strings to uppercase using streams.
        List<String> upperCaseWords = words.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> upperCaseWords1 = words.parallelStream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(upperCaseWords1);

//Count the number of distinct elements in a list using streams.
        int distinctCount = (int) list1.stream().distinct().count();
        System.out.println("distinct count is " + distinctCount);


//Find the maximum element in a list of integers using streams.
        int maxElement = list2.stream().max(Comparator.comparingInt(Integer::intValue)).orElse(-1);
        System.out.println("max element is " + maxElement);


//Given a list of strings, find the shortest string.
        Optional shortestString = words.stream()
                .map(String::trim)
                .min(Comparator
                        .comparingInt
                                (String::length));

        System.out.println("shortest string is " + shortestString.orElse("object is empty"));


//Find the average of a list of doubles using streams.

        OptionalDouble average = list2.stream()
                .mapToDouble(i -> i) // Convert to DoubleStream
                .average(); // Compute average
        System.out.println("Average: " + average);
        // Print the average (handling OptionalDouble)
        System.out.println(average.isPresent() ? average.getAsDouble() : "No values present");


//Convert a list of integers to a list of their squares.
        List<Integer> squares = list1.stream().mapToInt(i -> i * i).boxed().collect(Collectors.toList());
        System.out.println("squared list " + squares);


//Sort a list of strings in alphabetical order using streams.
        List<String> sortedWords = words.stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
        System.out.println(sortedWords);


//Find the sum of all integers in a list using streams.


//Check if all elements in a list satisfy a condition (e.g., all numbers greater than 10).
        list1.add(22);
        list1.add(24);

        List<Integer> conditinalsvalues = list1.stream().filter(i -> i > 22).collect(Collectors.toUnmodifiableList());
        System.out.println(conditinalsvalues);


//Group a list of strings by their lengths using streams.
        Map<Integer, List<String>> groupingByLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(groupingByLength);


//Remove duplicates from a list using streams.
        list2.add(2);
        list2.add(3);
        System.out.println(list2);

        List<Object> distinctElements = list2.stream().distinct().collect(Collectors.toUnmodifiableList());
        System.out.println("distinct elements are " + distinctElements);


//Concatenate all strings from a list into a single string using streams.
        String concatStr = words.stream().collect(Collectors.joining(","));
        System.out.println(concatStr);


        list1.add(14);
//Given a list of integers, find the first even number greater than 10.
        int firstEvenGreaterThan = list1.stream().filter(i ->
                i > 10 && i % 2 == 0).findAny().orElse(-1);
        System.out.println(firstEvenGreaterThan);


//Compute the factorial of a number using streams.
        List<Integer>factorials = list2.stream().map(mar_5::factorial) // Map each number to its factorial
                .collect(Collectors.toList());

        System.out.println("Factorials: " + factorials);



//Find the second largest number in a list of integers using streams.
        Optional<Integer> secondLargestNumber = list1.stream().sorted((a, b) -> Integer.compare(b,a)).skip(1).findFirst();
        System.out.println(secondLargestNumber);

//
//
//
//
////Partition a list of integers into odd and even numbers using streams.
//
//        List<Integer>   evenNum = list1.stream().filter(i -> i %2 == 0).collect(Collectors.toList());
//        List<Integer> oddNum = list1.stream().filter(i -> i %2!= 0).collect(Collectors.toList());
//        System.out.print("oddNum: " + oddNum);
//        System.out.println( evenNum);
//        // Partitioning list into even and odd numbers
//        Map<Boolean, List<Integer>> partitioned = list1.stream()
//                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
//
//        List<Integer> evenNum2 = partitioned.get(true);  // Even numbers (key: true)
//        List<Integer> oddNum2= partitioned.get(false);  // Odd numbers (key: false)
//
//        System.out.println("Even Numbers: " + evenNum2);
//        System.out.println("Odd Numbers: " + oddNum2);
//
//
//
//
//
////Check if a list of strings contains a specific substring using streams.
//        boolean containsString = words.stream().anyMatch(w -> w.contains("na"));
//        System.out.println("List contains substring 'na': " + containsString);
//        System.out.println();
//        String returnContainedString = words.stream().filter(w -> w.contains("na")).findFirst().orElse(null);
//        System.out.println(returnContainedString);
//
//
////Convert a list of objects to a map using streams, where keys are extracted from object properties.
//        List<Employee> allEmployees = new ArrayList<>();
//        Employee employee1 = new Employee(1, "jp", 23, 5000);
//        Employee employee2 = new Employee(2, "srikan", 22, 5000);
//        Employee employee3 = new Employee(3, "arun", 20, 5000);
//        Employee employee4 = new Employee(4, "varun", 34, 5000);
//        Employee employee5 = new Employee(6, "hf", 22, 5000);
//        allEmployees.add(employee1);
//        allEmployees.add(employee2);
//        allEmployees.add(employee3);
//        allEmployees.add(employee4);
//        allEmployees.add(employee5);
//        Map<Integer, Employee> employeeMap = allEmployees.stream().collect(Collectors.toMap(Employee::getId, e -> e));
//        System.out.println(employeeMap);
//
////Find the index of the first occurrence of a specific element in a list using streaks
//// to get teh index of word that contains G as the first character
//        int index = words.stream().filter(w -> w.startsWith("G")).map(words::indexOf).findAny().orElse(-1);
//        System.out.println(words);
//        System.out.println("index pf the word Guva is " + index);
////Remove null values from a list using streams.
//        list1.add(0);
//        list1.add(null);
//        List<Object> nonNullValues = list1.stream().filter(Objects::nonNull).collect(Collectors.toList());
//        System.out.println(nonNullValues);
//
//
////Merge two lists of strings into a single list using streams.
//        List<Object> mergedList;
//        mergedList = Stream.concat(
//                words.stream(), list1.stream()).collect(Collectors.toList());
//        System.out.println(mergedList);
//
////Given a list of strings, find the longest word using streams.*/
//        String longestWord = String.valueOf(words.stream().max(Comparator.comparingInt(String::length)));
//        System.out.println(longestWord);
//
////Implement a stream to handle large volumes of data efficiently.
//
//
////Find the intersection of two lists of integers using streams.
//        List<Integer> intersection = list1.stream().filter(list2::contains).collect(Collectors.toList());
//        System.out.println(intersection);
//
////Implement a stream to handle real-time data processing.
//
//
////Given a list of strings, find the longest substring without repeating characters using streams.
////String longestSubString = words.stream().
//
//
//        List<String> words1 = Arrays.asList("racecar", "banana", "level", "hello", "madam", "world");
//
//        String longestPalindrome = words.stream()
//                .filter(mar_5::isPalindrome) // Keep only palindromes
//                .max(Comparator.comparingInt(String::length)) // Get the longest one
//                .orElse("No palindrome found"); // Default if no palindrome exists
//
//        System.out.println("Longest palindrome: " + longestPalindrome);
//

    }


    // Helper method to check if a word is a palindrome
    private static boolean isPalindrome(String word) {
        return word.equals(new StringBuilder(word).reverse().toString());
    }
    // Method to compute factorial using streams
    private static int factorial(int n) {
        return IntStream.rangeClosed(1, n) // Generates numbers from 1 to n
                .reduce(1, (a, b) -> a * b); // Multiplies all numbers
    }

    private static void processBatch(List<Integer> batch) {
        System.out.println("Batch processed: " + batch);
    }
}

