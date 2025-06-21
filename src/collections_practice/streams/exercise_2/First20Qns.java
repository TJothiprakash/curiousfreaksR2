package collections_practice.streams.exercise_2;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*### Questions 1–20
6. Sort a list of integers.
7. Find the maximum element in a list of integers.
8. Find the minimum element in a list of integers.
9. Join all strings in a list with a comma.
10. Get a list of distinct elements from a list.
16. Group a list of employees by department.
17. Partition a list of numbers into even and odd.
18. Count the occurrences of each word in a list.
19. Get the average of a list of doubles.
20. Find the second highest number in a list.
*/
public class First20Qns {
    public static void main(String[] args) {
/*1. Convert a list of integers to a list of their squares.
2. Find all strings that start with "a" and are of length 3.
3. Count the number of empty strings in a list.
4. Remove all null elements from a list.
5. Convert a list of strings to uppercase.
*/
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().mapToInt(x -> x * x).boxed().collect(Collectors.toList());
        System.out.println(squares);

        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve", "null", "null", "", "", "");

        List<String> Question_2 = names.stream().filter(n -> n.startsWith("a")).filter(n -> n.length() > 3).toList();
        System.out.println(Question_2);
        int Question_3 = (int) names.stream().filter(n -> n.isEmpty()).count();
        int Question_3_1 = (int) names.stream().filter(n -> n.isBlank()).count();
//        int Question_3_2 = (int) names.stream().filter(n -> ).count();
        System.out.println("isBlank " + Question_3_1);
        System.out.println("isEmpty " + Question_3);
        // names.removeAll(List.of(null, ""));
        System.out.println(names);

        List<String> question_5 = names.stream().map(String::toUpperCase).toList();
        System.out.println(question_5);

        /*
6. Sort a list of integers.
7. Find the maximum element in a list of integers.
8. Find the minimum element in a list of integers.
9. Join all strings in a list with a comma.
10. Get a list of distinct elements from a list.*/

        List<Integer> numbers1 = new ArrayList<>();// List.of(1, 2, 3, 4, 53, 65, 7, 8, 9, 50);
        numbers1.add(1);
        numbers1.add(34);
        numbers1.add(32);
        numbers1.add(54);
        numbers1.add(64);
        numbers1.add(23);
        numbers1.add(12);
        numbers1.add(365);
        numbers1.add(329);
//        numbers1.sort((n1, n2) -> n1 - n2);
//        System.out.println(numbers1);
       /* List<Integer> ans = numbers1.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("reverse order " + ans);
        Optional<Integer> Questrion_7 = numbers1.stream().max(Integer::compareTo);
        System.out.println("Max is " + Questrion_7);
        Optional<Integer> Questrion_8 = numbers1.stream().min(Integer::compareTo);
        System.out.println("Min is " + Questrion_8);
        String Question_9 = numbers1.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println("Joining all numbers " + Question_9);
        List<Integer> Question_10 = numbers1.stream().distinct().collect(Collectors.toList());
        System.out.println("Distinct numbers " + Question_10);
*/
        First20Qns first20Qns = new First20Qns();
        first20Qns.last10Questions();
    }

    /*11. Skip the first 5 elements of a stream.
    12. Limit the stream to 10 elements.
    13. Check if any element in a list is negative.
    14. Check if all elements in a list are even.
    */
    public void last10Questions() {
        List<Integer> numbers2 = new ArrayList<>();
        numbers2.add(1);
        numbers2.add(34);
        numbers2.add(32);
        numbers2.add(54);
        numbers2.add(64);
        numbers2.add(23);
        numbers2.add(12);
        numbers2.add(365);
        numbers2.add(329);
        numbers2.add(100);
        numbers2.add(1000);
        numbers2.add(10000);
        numbers2.add(100000);
        numbers2.add(1000000);
        numbers2.stream().sorted();
        List<Integer> question_11 = numbers2.stream().skip(5).collect(Collectors.toList());
        System.out.println(question_11);
        List<Integer> question_12 = numbers2.stream().limit(10).collect(Collectors.toUnmodifiableList());
        System.out.println(question_12);
        Boolean Question_13 = numbers2.stream().anyMatch(n -> n < 0);
        System.out.println(Question_13);
        Boolean question_14 = numbers2.stream().allMatch(n -> n % 2 == 0);
        System.out.println(question_14);
//    15. Convert a list of employees to a map of name to salary.
        Employee emp1 = new Employee("Alice", 30);
        Employee emp2 = new Employee("Bob", 25);
        Employee emp3 = new Employee("Charlie", 35);
        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        Map<String, Integer> emplyeeSalarymap = employees.stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        System.out.println(emplyeeSalarymap);
    }


}

class Employee {
    private String name;
    private int salary;
    private boolean status;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, int salary, boolean status) {
        this.name = name;
        this.salary = salary;
        this.status = status;

    }

    public Employee(boolean status) {
        this.name = "Default";
        this.salary = 0;
        this.status = status;

    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}