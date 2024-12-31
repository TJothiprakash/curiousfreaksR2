package collections_practice.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        // streams practice
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
//David
        /*Practice creating streams using methods like:
stream() from collections.
Stream.of() or Arrays.stream() for arrays.*
*/
        Stream<Integer> numberStream = numbers.stream();
        Stream<String> nameStream = Stream.of(String.valueOf(names));
        System.out.println(String.valueOf(names));

        // numberStream.filter(num -> num % 2 == 0).forEach(System.out::println);
        // names.stream().forEach((name )-> System.out.println(name.charAt(2)));
        //numbers.stream().map(n ->n * n).forEach(System.out::println);
        // names.stream().map(name -> name.toUpperCase()).forEach(System.out::println);
        // names.stream().map(name -> name.length()).forEach(System.out::println);
        /*names.stream().filter(name -> name.startsWith("B")).forEach(System.out::println);
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
       */
        // numbers.stream().filter(num -> num % 2 == 0).map(num -> num * num).forEach(System.out::println);

        // List<Integer> oddNumbers = numbers.stream().
        //   filter(num -> num % 2 != 0).toList();
        List<Integer> oddNumbers = numbers.stream().
                filter(num -> num % 2 != 0).collect(Collectors.toList());
        System.out.println(oddNumbers);

        Optional<Integer> first = numbers.stream().findFirst();
        first.ifPresent(System.out::println);

        long count = numbers.stream().filter(n -> n >= 5).count();
        System.out.println("Count: " + count);
        int sum = numbers.stream().reduce(8, Integer::min);
        System.out.println("Sum: " + sum);

        boolean hasEven = numbers.stream().anyMatch(n -> n % 11 == 0);
        System.out.println("Has even? " + hasEven);

        List<Person> people = List.of(new Person("Alice", 30), new Person("Bob", 25));
        people.stream().filter(person -> person.getAge() > 20).forEach(System.out::println);

    }

}

class Person {
    private int age;
    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String
    toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}