package collections_practice.streams.exercise_2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
*
66 Sort a map by its values using streams.

67 Get top 5 longest strings.

68 Get list of prime numbers from a list.

69 Check if a number exists in the stream.

70 Count unique elements in a list.

71 Filter out all uppercase strings.

72 Get the product of all numbers.

73 Get all palindromic strings.

74 Find longest palindrome in a list.

75 Merge two lists using streams.

76 Get sum of squares of even numbers.

77 Sort strings ignoring case.

78 Get list of students who passed all subjects.

79 Find common elements in two lists.

80 Convert a list to a delimited string with prefix and suffix.

*/
public class Q60_80 {
    public static void main(String[] args) {
        Q60_80 sol = new Q60_80();
        sol.solve60_65();
    }

    /*
61Find the third smallest element in a list.

62Convert a list of booleans to count of true values.

*/
    public void solve60_65() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);

        int thriedSmallest = list.stream().sorted().skip(2).findFirst().get();
        int third = list.stream().sorted().skip(2).findFirst().get();
        System.out.println("Third smallest element is " + thriedSmallest);
        List<Boolean> booleans = new ArrayList<>();
        booleans.add(true);
        booleans.add(false);
        booleans.add(true);
        booleans.add(false);
        booleans.add(true);
        booleans.add(false);
        booleans.add(true);
        booleans.add(false);
        booleans.add(true);
        booleans.add(false);
        long trueCount = booleans.stream().filter(Boolean::booleanValue).count();
        System.out.println("Number of true values is " + trueCount);
        /*
63 Generate an infinite stream of odd numbers and limit to 20.

64 Filter employees with salary > 50k and sort by name.

 65 Group words by their first character.*/
        /*Stream.iterate(1, n -> n + 2).limit(20).collect(Collectors.toList())

         */
        List<Integer> result = Stream.iterate(1, n -> n + 2).limit(20).collect(Collectors.toList());
        System.out.println(result);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 50000));
        employees.add(new Employee("Mary", 30000));
        employees.add(new Employee("Peter", 10000));
        employees.add(new Employee("Paul", 20000));
        employees.add(new Employee("Anna", 40000));
        employees.add(new Employee("Kim", 60000));
        employees.add(new Employee("Robert", 80000));
        employees.add(new Employee("Jessica", 100000));
        employees.add(new Employee("Sarah", 120000));
        employees.add(new Employee("Linda", 140000));
        employees.add(new Employee("Betty", 160000));
        employees.add(new Employee("Charlie", 180000));
        employees.add(new Employee("Susan", 200000));
        employees.add(new Employee("Joseph", 220000));
        employees.add(new Employee("Amanda", 240000));
        employees.add(new Employee("Dorothy", 260000));
        employees.add(new Employee("William", 280000));
        List<Employee> empwithgt50Kandsortedbyname = employees.stream().filter(e-> e.getSalary() > 50000).collect(Collectors.toList());
        System.out.println(empwithgt50Kandsortedbyname);
        List<Employee> peoplewithSalaylessthan50K = employees.stream().filter(e -> e.getSalary() < 50000).collect(Collectors.toUnmodifiableList());
        System.out.println(peoplewithSalaylessthan50K);
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("grape");
        words.add("cherry");
        words.add("mango");
        words.add("apple");
        words.add("banana");
        words.add("grape");
        words.add("cherry");
        words.add("mango");
        words.add("apple");
        words.add("banana");
        Map<Character, List<String>> wordMap = words.stream()
                .collect(Collectors.groupingBy(word -> word.charAt(0)));
        System.out.println(wordMap);
    }
}
