package collections_practice.streams.exercise_3;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//L3 quesions 31- 40

public class CustomCollectorsDemo {

    static class Person {
        private String name;
        private String city;
        private int age;
        private boolean employed;

        public Person(String name, String city, int age, boolean employed) {
            this.name = name;
            this.city = city;
            this.age = age;
            this.employed = employed;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        public int getAge() {
            return age;
        }

        public boolean isEmployed() {
            return employed;
        }

        @Override
        public String toString() {
            return name + "(" + age + ", " + (employed ? "Employed" : "Unemployed") + ")";
        }
    }

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("JP", "Tiruppur", 31, true),
                new Person("Arun", "Tiruppur", 26, false),
                new Person("Gopal", "Chennai", 35, true),
                new Person("Raj", "Chennai", 35, false),
                new Person("Kumar", "Mumbai", 30, false),
                new Person("Ravi", "Mumbai", 40, true),
                new Person("Esha", "Mumbai", 28, true)
        );

        // 1️⃣ Group people by city and collect people’s names in comma-separated string.
        Map<String, String> cityToNamesCsv = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.mapping(Person::getName, Collectors.joining(", "))
                ));
        System.out.println("1️⃣ City -> Names (CSV): " + cityToNamesCsv);

        // 2️⃣ Partition people into even and odd ages.
        Map<Boolean, List<Person>> evenOddPartition = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() % 2 == 0));
        System.out.println("2️⃣ Even age: " + evenOddPartition.get(true));
        System.out.println("   Odd age: " + evenOddPartition.get(false));

        // 3️⃣ Group by city and for each, find youngest person.
        Map<String, Person> youngestPerCity = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(Person::getAge)),
                                Optional::get
                        )
                ));
        System.out.println("3️⃣ Youngest per city: " + youngestPerCity);

        // 4️⃣ Group by city and collect sorted names list.
        Map<String, List<String>> sortedNamesByCity = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.mapping(
                                Person::getName,
                                Collectors.collectingAndThen(Collectors.toList(), list -> {
                                    list.sort(Comparator.naturalOrder());
                                    return list;
                                })
                        )
                ));
        System.out.println("4️⃣ City -> Sorted Names: " + sortedNamesByCity);

        // 5️⃣ Group by employment status and collect age histogram (map of age → count).
        Map<Boolean, Map<Integer, Long>> employmentAgeHistogram = people.stream()
                .collect(Collectors.groupingBy(
                        Person::isEmployed,
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.counting()
                        )
                ));
        System.out.println("5️⃣ Employment → Age histogram: " + employmentAgeHistogram);

        // 6️⃣ Group by age and check if all from a city.
        Map<Integer, Boolean> ageGroupSameCity = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Person::getCity, Collectors.toSet()),
                                cities -> cities.size() == 1
                        )
                ));
        System.out.println("6️⃣ Age → All Same City: " + ageGroupSameCity);

        // 7️⃣ Count people per city and filter only where count > 1.
        Map<String, Long> cityWithMoreThanOne = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.counting()
                )).entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("7️⃣ Cities with more than 1 person: " + cityWithMoreThanOne);

        // 8️⃣ Group people by city, then partition by age > 30.
        Map<String, Map<Boolean, List<Person>>> cityAgePartition = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.partitioningBy(p -> p.getAge() > 30)
                ));
        System.out.println("8️⃣ City → (Age > 30 partition): " + cityAgePartition);

        // 9️⃣ Partition people by employment, then group by city.
        Map<Boolean, Map<String, List<Person>>> empThenCity = people.stream()
                .collect(Collectors.partitioningBy(
                        Person::isEmployed,
                        Collectors.groupingBy(Person::getCity)
                ));
        System.out.println("9️⃣ Employment → City → People: " + empThenCity);

        // 🔟 Partition people by name length > 4 and count each group.
        Map<Boolean, Long> nameLengthPartition = people.stream()
                .collect(Collectors.partitioningBy(
                        p -> p.getName().length() > 4,
                        Collectors.counting()
                ));
        System.out.println("🔟 Name length > 4 partition counts: " + nameLengthPartition);
    }
}
