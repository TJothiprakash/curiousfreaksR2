package collections_practice.streams;

import java.util.List;
import java.util.stream.Collectors;

class PersonClass {
    private String name;
    private int age;

    // Constructor
    public PersonClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters (needed for accessing fields)
    public String getName() { return name; }
    public int getAge() { return age; }

    // Convert a single Person object to JSON format
    public String toJson() {
        return String.format("{\"name\":\"%s\",\"age\":%d}", name, age);
    }
}

public class Main2 {

        public static void main(String[] args) {
        List<PersonClass> people = List.of(
                new PersonClass("Alice", 30),
                new PersonClass("Bob", 25),
                new PersonClass("Charlie", 35)
        );

        // Convert list of objects to JSON using Streams
        String jsonArray = people.stream()
                .map(PersonClass::toJson)
                .collect(Collectors.joining(",", "[", "]"));

        System.out.println(jsonArray);
        System.out.println("lets look on somethign else");
    }
}
