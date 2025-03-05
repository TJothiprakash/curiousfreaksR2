package collections_practice.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Stream_Practice {
    public static void main(String[] args) {
        //write your code here


        //convert a list of integers to a list of their squares.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10);
        //     System.out.println(numbers);
        List<Integer> squares = numbers.stream().map(n -> n * n).collect(Collectors.toList());
//    System.out.println(squares);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)  // Keep only even numbers
                .collect(Collectors.toList());

//        System.out.println("Even numbers: " + evenNumbers);

        // Find the sum of all numbers in a list.
        long sum = numbers.stream().mapToInt(n -> n).sum();
//        System.out.println("Sum of numbers: " + numbers + " is " + sum);

        //   Convert a list of strings to uppercase.
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        List<String> upperCaseWords = words.stream().map(String::toUpperCase).collect(Collectors.toList());
//        System.out.println(upperCaseWords);

        //  Find the longest string in a list.
        String longestString = upperCaseWords.stream().max(String::compareTo).get();
//        System.out.println("Longest string: " + longestString);

        String longestString1 = upperCaseWords.stream()
                .max(Comparator.comparingInt(String::length)) // Compare based on length
                .orElse(""); // Use orElse() to handle empty lists safely

        System.out.println("Longest string: " + longestString1);

        /*
*/
        // Find the second-highest number in a list.
        int secondHighest = numbers.stream().distinct().sorted().skip(1).findFirst().orElse(0);
        System.out.println("Second highest number: " + secondHighest);

//Sort a list of strings by their length.
        List<String> words1 = Arrays.asList("Apple","Apple", "Amazon", "asohasundari", "alagalan", "van", "car", "bus", "train", "bike", "motorcycle", "helicopter", "airplane");
        System.out.println("Original words: " + words1);
        List<String> sortedWords = words1.stream().sorted(Comparator.comparingInt((String::length))).collect(Collectors.toList());
        System.out.println("Sorted words by length: " + sortedWords);
//Find the most frequently occurring character in a given string.
        Character mostFrequentCharacter = words1.stream()
                .flatMap(str -> str.chars().mapToObj(c -> (char) c)) // Convert all words into a stream of characters
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Count occurrences
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Find the character with the highest count
                .map(Map.Entry::getKey)
                .orElse(null); // Handle empty list case

        System.out.println("Most frequent character: " + mostFrequentCharacter);
//Find the total length of all strings combined in a list.
        int totalLength = words1.stream().mapToInt(str -> str.length()).sum();
        System.out.println("Total length: " + totalLength);
//Remove duplicate elements from a list.

        List<Integer> uniqeList = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println("Uniqe list: " + uniqeList);


//Find the average salary from a list of employees.
        List<Employee> allEmployees = new ArrayList<>();
        Employee employee1 = new Employee("1", "jp", 23, 5000);
        Employee employee2 = new Employee("2", "srikan", 22, 5000);
        Employee employee3 = new Employee("3", "arun", 20, 5000);
        Employee employee4 = new Employee("4", "varun", 34, 5000);
        Employee employee5 = new Employee("6", "hf", 22, 5000);
        allEmployees.add(employee1);
        allEmployees.add(employee2);
        allEmployees.add(employee3);
        allEmployees.add(employee4);
        allEmployees.add(employee5);
        double averageSalary = allEmployees.stream()
                .mapToDouble(Employee::getSalary)  // Use mapToDouble if salary is double
                .average()
                .orElse(0);

        System.out.println("Average salary: " + Math.round(averageSalary));  // Proper rounding


        //Group a list of people by their age (using Collectors.groupingBy).
        Map<Integer, List<Employee>> groupedByAge = allEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getAge));

        System.out.println("Employees grouped by age:");
        groupedByAge.forEach((age, employees) -> System.out.println("Age " + age + ": " + employees));

//Find the first string that starts with 'A'.
        Optional<String> firstStringStartingWIthA = words1.stream().filter(w -> w.startsWith(("A"))).findFirst();
        System.out.println(firstStringStartingWIthA);
// list of words that    start with "A"
        List<String> wordsStartingWithA = words1.stream().filter(w -> w.startsWith(("A"))).collect(Collectors.toList());
        System.out.println("Words starting with 'A': " + wordsStartingWithA);

//Check if all elements in a list are even numbers.
        long evensum = numbers.stream().filter(n -> n % 2 == 0)
                .mapToInt(Integer::valueOf).sum();
        System.out.println(numbers);
        System.out.println(evensum +" " );

//Count the occurrences of each word in a list.
        Map<String,  Long> occurrences = words1.stream().collect(Collectors.groupingBy(word-> word,Collectors.counting()));
        System.out.println(occurrences);
    }
}
