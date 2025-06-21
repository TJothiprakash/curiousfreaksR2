package collections_practice.streams.exercise_2;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*### Questions 41–60
51. Convert a map to a list of entries.
52. Generate a list of the first 10 squares.
53. Find first repeated character in a string.
54. Find last element of a list safely.
55. Filter and collect only valid email addresses.
56. Compute sum of salaries by department.
57. Check if two lists are equal ignoring order.
58. Collect list into a Set.
59. Remove strings containing a specific substring.
60. Count lowercase letters in a string.
*/
public class Q40_60 {
    public static void main(String[] args) throws ParseException {
        Q40_60 sol = new Q40_60();
//        sol.solveQns();
//        sol.printQns();
            sol.printQns2();
    }

    /*
41. Count how many strings have length > 3.
42. Find the most frequent string in a list.
43. Remove all numbers less than 10.
44. Replace nulls with a default string.
45. Find the total number of characters in all strings.*/
    public void solveQns() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(2, "JOthiprakash"));
        persons.add(new Person(1, "JOhannes"));
        persons.add(new Person(1, "JO"));
        persons.add(new Person(3, "JOhn"));
        persons.add(new Person(4, "JOhnson"));
        persons.add(new Person(5, "JOhn"));
        persons.add(new Person(6, "Thamaraikani"));
        persons.add(new Person(7, "Palanisamy"));
        persons.add(new Person(8, "Arunkumar"));
        persons.add(new Person(9, "Manikandan"));
        persons.add(new Person(10, "Srikanth"));
        persons.add(new Person(11, "VInodth"));
        persons.add(new Person(12, "Rahulraja"));
        persons.add(new Person(13, "Veeramani"));
        persons.add(new Person(14, "Rajesh"));
        persons.add(new Person(15, "Vijay"));
        persons.add(new Person(16, "Jeyaseelan"));

        List<String> question_41 = persons.stream().map(Person::name).filter(s -> s.length() > 3).toList();
        System.out.println("List of strings with length > 3 is " + question_41);
        String question_42 = String.valueOf(persons.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))   // Map<String, Long>
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())   // Find entry with max count
                .map(Map.Entry::getKey)              // Get the key (string)
                .orElse(null));
        System.out.println("Most frequent string is " + question_42);
        List<Integer> question_43 = persons.stream().map(Person::id).filter(n -> n >= 10).toList();
        System.out.println("List of numbers less than 10 is " + question_43);
        List<String> question_44 = persons.stream().map(Person::name).map(s -> s == null ? "Default" : s).toList();
        System.out.println("List of names with null replaced with Default is " + question_44);
        int question_45 = persons.stream().mapToInt(Person::id).sum();
        System.out.println("Sum of all numbers is " + question_45);
    }

    /*46. Get a list of unique characters from a list of strings.
47. Find the average length of strings in a list.
48. Remove duplicates based on string length.
49. Sort a list of dates in descending order.
50. Partition employees by active/inactive status.
*/
    public static void printQns() throws ParseException {


        List<Person> persons = new ArrayList<>(List.of(
                new Person(2, "JOthiprakash"), new Person(1, "JOhannes"),
                new Person(1, "JO"), new Person(3, "JOhn"),
                new Person(4, "JOhnson"), new Person(5, "JOhn"),
                new Person(6, "Thamaraikani"), new Person(7, "Palanisamy"),
                new Person(8, "Arunkumar"), new Person(9, "Manikandan"),
                new Person(10, "Srikanth"), new Person(11, "VInodth"),
                new Person(12, "Rahulraja"), new Person(13, "Veeramani"),
                new Person(14, "Rajesh"), new Person(15, "Vijay"),
                new Person(16, "Jeyaseelan")
        ));

        // 46. Unique characters from a list of strings
        List<Character> question_46 = persons.stream()
                .flatMap(p -> p.name().chars().mapToObj(c -> (char) c))
                .distinct()
                .toList();
        System.out.println("46. Unique characters: " + question_46);

        // 47. Average string length
        double avgLength = persons.stream()
                .map(Person::name)
                .mapToInt(String::length)
                .average()
                .orElse(0);
        System.out.println("47. Average string length: " + avgLength);

        // 48. Remove duplicates based on string length
        List<String> uniqueByLength = persons.stream()
                .map(Person::name)
                .collect(Collectors.toMap(
                        String::length,
                        name -> name,
                        (existing, replacement) -> existing // keep first
                ))
                .values().stream()
                .toList();
        System.out.println("48. Strings with unique lengths: " + uniqueByLength);

        // 49. Sort dates in descending order
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Date> listOfDates = new ArrayList<>(List.of(
                new Date(), sdf.parse("12/12/2020"), sdf.parse("12/12/2021"),
                sdf.parse("12/12/2022"), sdf.parse("12/12/2023"), sdf.parse("12/12/2024"),
                sdf.parse("12/12/2025"), sdf.parse("12/12/2026"), sdf.parse("12/12/2027"),
                sdf.parse("12/12/2028"), sdf.parse("12/12/2029"), sdf.parse("12/12/2030"),
                sdf.parse("12/12/2031"), sdf.parse("12/12/2032")
        ));

        List<Date> sortedDates = listOfDates.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("49. Dates in descending order: ");
        sortedDates.forEach(System.out::println);

        // 50. Partition employees by active/inactive
        List<Employee> employees = List.of(
                new Employee(false), new Employee(true),
                new Employee(false), new Employee(true),
                new Employee(false), new Employee(true)
        );

        Map<Boolean, List<Employee>> partitioned = employees.stream()
                .collect(Collectors.partitioningBy(Employee::getStatus));
        System.out.println("50. Active employees: " + partitioned.get(true));
        System.out.println("50. Inactive employees: " + partitioned.get(false));
    }

    // Sample Person and Employee classes
    record Person(int id, String name) {
    }

    static class Employee {
        private final boolean status;

        public Employee(boolean status) {
            this.status = status;
        }

        public boolean getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Employee{" + "status=" + status + '}';
        }
    }

    /*51. Convert a map to a list of entries.
52. Generate a list of the first 10 squares.
53. Find first repeated character in a string.
54. Find last element of a list safely.
55. Filter and collect only valid email addresses.*/
    public static void printQns2() {
        // 51. Convert a map to a list of entries
        Map<String, Integer> sampleMap = Map.of("a", 1, "b", 2, "c", 3);
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(sampleMap.entrySet());
        System.out.println("51. Map entries as list: " + entries);

        // 52. Generate a list of the first 10 squares
        List<Integer> squares = IntStream.rangeClosed(1, 10)
                .map(n -> n * n)
                .boxed()
                .toList();
        System.out.println("52. First 10 squares: " + squares);

        // 53. Find first repeated character in a string
//        String input = "abcaefg";
//        Optional<Character> firstRepeated = input.chars()
//                .mapToObj(c -> (char) c)
//                .collect(LinkedHashMap::new,
//                        (map, ch) -> map.put(ch,( map.getOrDefault(ch, 0) + 1)),
//                        Map::putAll)
//                .entrySet().stream()
//                .filter(e -> e.getValue() > 1)
//                .map(Map.Entry::getKey)
//                .findFirst();
//        System.out.println("53. First repeated character: " + firstRepeated.orElse(null));

        // 54. Find last element of a list safely
        List<String> names = List.of("Alice", "Bob", "Charlie");
        String lastElement = names.isEmpty() ? null : names.get(names.size() - 1);
        System.out.println("54. Last element (safe): " + lastElement);

        // 55. Filter and collect only valid email addresses
        List<String> emails = List.of("test@example.com", "invalid@", "hello@domain.com", "nope.com");
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

        List<String> validEmails = emails.stream()
                .filter(email -> emailPattern.matcher(email).matches())
                .toList();
        System.out.println("55. Valid emails: " + validEmails);
    }

    public static void runAllTasks() {
        // Task 56: Compute sum of salaries by department
        class Employee {
            String name;
            String department;
            double salary;

            Employee(String name, String department, double salary) {
                this.name = name;
                this.department = department;
                this.salary = salary;
            }

            String getDepartment() { return department; }
            double getSalary() { return salary; }
        }

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "HR", 5000),
                new Employee("Bob", "HR", 6000),
                new Employee("Charlie", "IT", 8000),
                new Employee("David", "IT", 9000),
                new Employee("Eve", "Sales", 7000)
        );

        Map<String, Double> salaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ));
        System.out.println("56. Sum of Salaries by Department: " + salaryByDept);

        // Task 57: Check if two lists are equal ignoring order
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(4, 3, 2, 1);
        boolean areEqualIgnoringOrder = new HashSet<>(list1).equals(new HashSet<>(list2));
        System.out.println("57. Lists equal ignoring order: " + areEqualIgnoringOrder);

        // Task 58: Collect list into a Set
        List<String> fruitList = Arrays.asList("apple", "banana", "orange", "banana", "apple");
        Set<String> fruitSet = new HashSet<>(fruitList);
        System.out.println("58. List into Set: " + fruitSet);

        // Task 59: Remove strings containing a specific substring
        List<String> foodList = new ArrayList<>(Arrays.asList("apple", "banana", "pineapple", "kiwi"));
        foodList.removeIf(s -> s.contains("apple"));
        System.out.println("59. After removing 'apple': " + foodList);

        // Task 60: Count lowercase letters in a string
        String sentence = "Hello World from JAVA 2025!";
        long lowercaseCount = sentence.chars().filter(Character::isLowerCase).count();
        System.out.println("60. Lowercase letters count: " + lowercaseCount);
    }



}