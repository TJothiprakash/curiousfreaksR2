package collections_practice.streams.exercise_2;

import others.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
### Questions 21–40

31. Convert a list of objects to a list of one of their fields.
32. Check if a string list contains a word (case-insensitive).
33. Find the sum of all even numbers.
34. Check if no element is null.
35. Find the frequency of each character in a string.
*/
public class T20to40Qns {
    private List<String> QNS;

    public T20to40Qns(List<String> QNS) {
        this.QNS = QNS;
    }

    public static void main(String[] args) {
        List<String> QNS = new ArrayList<>();
        QNS.add("Hello");
        QNS.add("World");
        QNS.add("Java");
        QNS.add("8");
        QNS.add("10");
        QNS.add("12");
        QNS.add("Wov verified");
        QNS.add("Wov verified");
        QNS.add("Hahahah");
        QNS.add("HAHAHAH");
        QNS.add("This day is the best day ");
        QNS.add("This day is the gloomy day ");
        T20to40Qns sol = new T20to40Qns(QNS);
//        sol.printQns(QNS);
//        sol.printQns2(QNS);
//        sol.printQns3();
        sol.printQns4();
    }


    /*21. Find the longest string in a list.
22. Reverse sort a list of strings.
23. Remove duplicates and sort a list.
24. Flatten a list of lists.
25. Convert a list of strings to a list of integers.*/
    public static void printQns(List<String> QNS) {
        String question_21 = QNS.stream().max((s1, s2) -> s1.length() - s2.length()).get();
        System.out.println("Longest string is " + question_21);

        List<String> question_22 = QNS.stream().sorted((s1, s2) -> s2.compareTo(s1)).toList();
        System.out.println("Reverse sorted list is " + question_22);

        List<String> question_23 = QNS.stream().distinct().sorted().toList();
        System.out.println("Distinct sorted list is " + question_23);

        List<String> question_24 = QNS.stream().flatMap(s -> List.of(s.split("")).stream()).toList();
        System.out.println("Flattened list is " + question_24);
        Map<String, Integer> question_25 = question_23.stream().collect(java.util.stream.Collectors.toMap(s -> s, s -> s.length()));
        System.out.println("Map of strings and their lengths is " + question_25);
    }

    /*26. Remove all empty strings from a list.
27. Generate a list of 10 random numbers.
28. Group strings by their length.
29. Find the string with the most vowels.
30. Create a comma-separated string of numbers greater than 5.
*/
    public void printQns2(List<String> QNS) {
        List<Integer> question_27 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            question_27.add((int) (Math.random() * 10));
        }
        System.out.println("List of 10 random numbers is " + question_27);

        Map<Integer, List<String>> question_28 = QNS.stream().collect(java.util.stream.Collectors.groupingBy(s -> s.length()));
        System.out.println("List of strings grouped by their length is " + question_28);
//string with most vowels
        List<String> question_29 = QNS.stream().filter(s -> s.matches(".*[aeiou].*")).collect(java.util.stream.Collectors.toList());

        System.out.println("String with most vowels is " + question_29.get(0));

        String question_30 = question_27.stream().filter(n -> n > 5).map(Object::toString).collect(java.util.stream.Collectors.joining(","));
        System.out.println("Comma-separated string of numbers greater than 5 is " + question_30);
    }

    /*
31. Convert a list of objects to a list of one of their fields.
32. Check if a string list contains a word (case-insensitive).
33. Find the sum of all even numbers.
34. Check if no element is null.
35. Find the frequency of each character in a string.*/
    public void printQns3() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(2, "JOthiprakash"));
        persons.add(new Person(1, "JOhannes"));
        persons.add(new Person(3, "JOhn"));
        persons.add(new Person(4, "JOhnson"));
        persons.add(new Person(5, "JOhn"));
        persons.add(new Person(6, "Thamaraikani"));
        persons.add(new Person(7, "Palanisamy"));
        persons.add(new Person(8, "Arunkumar"));
        persons.add(new Person(9, "Manikandan"));
        persons.add(new Person(10, "Srikanth"));
        persons.add(new Person(11, "VInodth"));
        persons.add(new Person(12, "Rahul raja"));
        persons.add(new Person(13, "Veeramani"));
        persons.add(new Person(14, "Rajesh"));
        persons.add(new Person(15, "Vijay"));
        persons.add(new Person(16, "Jeyaseelan"));
        List<String> names = persons.stream().map(Person::name).toList();
        System.out.println("Names are " + names);
        List<String> ids = persons.stream().map(Person::id).toList().stream().map(Object::toString).collect(Collectors.toList());
        System.out.println(ids);
        System.out.println(ids.get(0).getClass());

        // sum of all even numbers
        List<Integer> evenNumbers = persons.stream().map(Person::id).filter(n -> n % 2 == 0).toList();
        System.out.println("Sum of even numbers is " + evenNumbers.stream().reduce(0, Integer::sum));

        Boolean isNOnnull = persons.stream().anyMatch(Objects::nonNull);
        System.out.println(isNOnnull);
        String name = "Arunkumar";
        Map<Character, Long> charFrequency = name.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println(charFrequency);
    }

    /*36. Sort a list of strings by their lengths.
37. Get the first non-null element from a list.
38. Calculate factorial using streams.
39. Collect stream into a LinkedList instead of ArrayList.
40. Create a map of numbers to their squares.
*/
    public void printQns4() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(2, "JOthiprakash"));
        persons.add(new Person(1, "JOhannes"));
        persons.add(new Person(3, "JOhn"));
        persons.add(new Person(4, "JOhnson"));
        persons.add(new Person(5, "JOhn"));
        persons.add(new Person(6, "Thamaraikani"));
        persons.add(new Person(7, "Palanisamy"));
        persons.add(new Person(8, "Arunkumar"));
        persons.add(new Person(9, "Manikandan"));
        persons.add(new Person(10, "Srikanth"));
        persons.add(new Person(11, "VInodth"));
        persons.add(new Person(12, "Rahul raja"));
        persons.add(new Person(13, "Veeramani"));
        persons.add(new Person(14, "Rajesh"));
        persons.add(new Person(15, "Vijay"));
        persons.add(new Person(16, "Jeyaseelan"));
        List<String> names = persons.stream().map(Person::name).sorted((s1, s2) -> s1.length() - s2.length()).toList();
        System.out.println(names);

        String firstNonNull = persons.stream().filter(Objects::nonNull).findFirst().get().name();
        System.out.println("First non-null element is " + firstNonNull);

        int number = 8;

        System.out.println("Factorial of " + number + " is " + factorial(number));

        List<Person> persons2 = persons.stream().filter(Objects::nonNull).collect(Collectors.toCollection(java.util.LinkedList::new));
//        System.out.println("List of persons with non-null elements is " + persons2);

        Map<Integer, Integer> squares = persons.stream().map(Person::id).collect(Collectors.toMap(n -> n, n -> n * n));
        System.out.println("Map of numbers to their squares is " + squares);
    }

    public static long factorial(int n) {
        return IntStream.rangeClosed(1, n)
                .reduce(1, (a, b) -> a * b);
    }
}
