package collections_practice.streams.exercise_2;


import java.util.*;
import java.util.stream.Collectors;

/*
📘 Questions 81–100


96 Count even digits in a number.

97 Convert a comma-separated string to list.

98 Check if a list contains only unique elements.

99 Convert a list of numbers to binary strings.

100 Build a frequency histogram (Map<Integer, Integer>) from a list of numbers.
*/
public class Q80_100 {
    static List<Employee> employeeList = new ArrayList<>() ;
    public static void main(String[] args) {
        Q80_100 q80_100 = new Q80_100();
        q80_100.solve80_85();
//        q80_100.solve86_90();
        q80_100.solve91_95();
        q80_100.solve96_100();
    }

    /*
81 Sort a list of people by age then name.

82 Get top 3 highest-paid employees.

83 Group products by category and count each.


*/

    public void solve96_100() {
        // 96 Count even digits in a number.
        int number = 1234567890;
        long evenDigitCount = String.valueOf(number)
                .chars()
                .map(c -> c - '0') // convert char to int
                .filter(d -> d % 2 == 0)
                .count();
        System.out.println("96. Even digits count: " + evenDigitCount);

        // 97 Convert a comma-separated string to list.
        String csv = "apple,banana,orange";
        List<String> fruits = Arrays.stream(csv.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        System.out.println("97. CSV to list: " + fruits);

        // 98 Check if a list contains only unique elements.
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5, 6);
        boolean isUnique = numbers.size() == new HashSet<>(numbers).size();
        System.out.println("98. Contains only unique elements? " + isUnique);

        // 99 Convert a list of numbers to binary strings.
        List<Integer> numList = List.of(5, 10, 15);
        List<String> binaryList = numList.stream()
                .map(Integer::toBinaryString)
                .collect(Collectors.toList());
        System.out.println("99. Binary strings: " + binaryList);

        // 100 Build a frequency histogram (Map<Integer, Integer>) from a list of numbers.
        List<Integer> histogramList = List.of(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        Map<Integer, Long> histogram = histogramList.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        System.out.println("100. Frequency histogram: " + histogram);
    }


    public void solve80_85() {
        List<Person> personList = List.of(new Person("Alice", 30), new Person("Bob", 25), new Person("Charlie", 35));
        employeeList = List.of(new Employee("arun", 3234, 32, "manager"), new Employee("Jp", 3333, 21, "staff"));
        List<Product> productList = List.of(new Product("Shoes", "clothing"), new Product("belt ", "accessories"), new Product("Pants", "clothing"), new Product("Shirt", "clothing"));
        List<Person> sortedbyagethenName = personList.stream().sorted(Comparator.comparingInt(a -> a.age)).sorted(Comparator.comparing(a -> a.name)).collect(Collectors.toUnmodifiableList());
        System.out.println(sortedbyagethenName);
        List<Employee> top3PaidEmployees = employeeList.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).limit(3).collect(Collectors.toUnmodifiableList());
        System.out.println(top3PaidEmployees);

        Map<String, Long> productsCOuntbyCatergory = productList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        System.out.println("product count by category " + productsCOuntbyCatergory);
//        84 Convert list of digits to a number.
        List<Integer> digits = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        int result = digits.stream().reduce(0, (a, b) -> a * 10 + b);
        System.out.println(result);
//85 Convert map to JSON-like string using streams.
        String ans = productsCOuntbyCatergory.entrySet().stream().map(e -> "\"" + e.getKey() + "\": " + e.getValue()).collect(Collectors.joining(", ", "{", "}"));
        System.out.println(ans);
    }

    /*
86 Extract domain names from email list.




*/
/*
92 Find employee(s) with max salary.

93 Sort a list of file paths by filename extension.

94 Check if a list is sorted.

95 Replace all empty strings with "N/A".
*/
    public void solve91_95() {
///*91 Get names of all managers from employee list.
        System.out.println(employeeList);
        List<Employee> managerList = employeeList.stream().filter(e -> e.designation.equals("manager")).collect(Collectors.toUnmodifiableList());
        System.out.println(managerList);


    }

    public void solve86_90() {
        List<String> emailsList = new ArrayList<>();
        emailsList.add("jothiprakash.@gmail.com");
        emailsList.add("jothiprakash.@hotmail.com");
        List<String> extractDomainfromEmail = emailsList.stream().map(email -> email.substring(email.indexOf("@") + 1)).collect(Collectors.toList());
        System.out.println(extractDomainfromEmail);
//        87 Find second most frequent word.
        List<String> wordsList = List.of("hello", "world", "hello", "world", "hello", "world", "hello", "hello", "world", "word", "hello", "world");
        Map<String, Long> wordFrequencyMap = wordsList.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println(wordFrequencyMap);
        Optional<Object> secondMostFrequentWord = wordFrequencyMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).skip(1).findFirst().map(Map.Entry::getKey);
        System.out.println("second most frequent word " + secondMostFrequentWord);
//        88 Remove duplicate characters from a string.
        Set<Character> uniqueCharacters = new HashSet<>();
        String str = "Hello World";
        uniqueCharacters = str.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        System.out.println("unique characters " + uniqueCharacters);
//89 Get characters with frequency > 1.
        Map<Character, Long> characterFrequencyMap = str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("characterFrequencyMap " + characterFrequencyMap);
        List<Character> frequentCharacters = characterFrequencyMap.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println("frequentCharacters " + frequentCharacters);
//90 Create frequency map of words ignoring case.
        Map<String, Long> wordFrequencyMapIgnoreCase = wordsList.stream().map(String::toLowerCase).collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println("wordFrequencyMapIgnoreCase " + wordFrequencyMapIgnoreCase);

    }


    static class Employee {
        private String name;
        private int salary;
        private int age;
        private String designation;

        public Employee(String name, int salary, int age) {
            this.name = name;
            this.salary = salary;
            this.age = age;
        }

        public Employee(String name, int salary, int age, String designation) {
            this.name = name;
            this.salary = salary;
            this.age = age;
            this.designation = designation;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    ", age=" + age +
                    '}';
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }
    }

    static class Product {
        private String name;
        private String category;


        public Product(String name, String category) {
            this.name = name;
            this.category = category;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

    static class Person {
        private int age;
        private String name;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
