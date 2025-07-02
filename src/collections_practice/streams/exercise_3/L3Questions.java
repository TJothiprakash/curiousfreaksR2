package collections_practice.streams.exercise_3;

import java.util.*;
import java.util.stream.Collectors;

public class L3Questions {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("JP", "tiruppur", 40003072, true, 29));
        people.add(new Person("ArunKumar", "tiruppur", 40003073, 27));
        people.add(new Person("Thangaraj", "Mumbai", 40003074, 50));
        people.add(new Person("Logamani", "tiruppur", 40003075, true, 46));
        people.add(new Person("Gopal", "Chennai", 40003076, -0));
        people.add(new Person("Nantha Kumar", "Dharmapuri", 40003077, 31));

//        🟠 Level 3 Java Stream Questions (1–40)
//📦 Basic Grouping & Partitioning
//      // Group all people by city
        Map<String, List<String>> groupByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println("Grouped by city: " + groupByCity);

        // Partition people into employed and unemployed
        Map<Boolean, List<Person>> partitionedByEmployment = people.stream()
                .collect(Collectors.partitioningBy(Person::isEmployed));
        List<Person> employed = partitionedByEmployment.get(true);
        List<Person> unemployed = partitionedByEmployment.get(false);
        System.out.println("Employed: " + employed);
        System.out.println("Unemployed: " + unemployed);

        // Count the number of people in each city
        Map<String, Long> peopleCountByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        System.out.println("People count by city: " + peopleCountByCity);

        // Partition people into adults and minors based on age (assuming age is an attribute in Person class)
        Map<Boolean, List<Person>> partitionedByAge = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() >= 30));
        List<Person> adults = partitionedByAge.get(true);
        List<Person> minors = partitionedByAge.get(false);
//        System.out.println("Adults: " + adults);
//        System.out.println("Minors: " + minors);
//        Group names by city.
        Map<String, List<Person>> groupByCity_PeopleNames = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.toList()));
        System.out.println("Grouped by city: " + groupByCity_PeopleNames);
//        Count employed vs unemployed people.
        Map<Boolean, List<Person>> employmentStatus = people.stream().collect(Collectors.partitioningBy(Person::isEmployed));
        int employedCount = employmentStatus.get(true).size();
        int unemployedCount = employmentStatus.get(false).size();
        System.out.println("Employed: " + employedCount + " unemployed: " + unemployedCount);
//    Count how many people are in each age group.
        Map<Integer, Long> countofPeopleByAgeWise = people.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println("People count by age: " + countofPeopleByAgeWise);
//        Group people by age, then by city.
        // Group by age, then by city
        Map<Integer, Map<String, List<Person>>> grouped =
                people.stream()
                        .collect(Collectors.groupingBy(Person::getAge,
                                Collectors.groupingBy(Person::getCity)));

        // Printing the result for demonstration
        grouped.forEach((age, cityMap) -> {
            System.out.println("Age: " + age);
            cityMap.forEach((city, persons) -> {
                System.out.println("\tCity: " + city);
                persons.forEach(person -> {
                    System.out.println("\t\t" + person.getName());
                });
            });
        });

        //        Group people by first character of their name.
        Map<Character, List<Person>> groupByFirstChar = people.stream().collect(Collectors.groupingBy(p -> p.getName().charAt(0)));
        System.out.println("Grouped by first char: " + groupByFirstChar);

//        Partition people based on whether their name starts with a vowel.
        Map<Boolean, List<Person>> groupByNameStartswithVOWEL = people.stream().collect(Collectors.groupingBy(p -> startsWithVowel(p.getName())));
        System.out.println();
        System.out.println();
        System.out.println("Grouped by first char with vowel: " + groupByNameStartswithVOWEL);
        Map<Character, List<Person>> groupedByVowelStart =
                people.stream()
                        .filter(p -> startsWithVowels(p.getName()))
                        .collect(Collectors.groupingBy(p -> Character.toUpperCase(p.getName().charAt(0))));

//🔍 Nested Grouping & Mapping
//        Group people by city and then by employment status.
        Map<String, Map<Boolean, List<Person>>> groupBycityThenEmploymentStatus = people.stream().collect(Collectors.groupingBy(p -> p.getCity(), Collectors.groupingBy(p -> p.isEmployed == true)));
        System.out.println();
        System.out.println();
        System.out.println("Grouped by city then employment: " + groupBycityThenEmploymentStatus);
//                Group people by city and collect their names.
        Map<String, List<String>> groupByCitywithNameList = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println();
        System.out.println();
        System.out.println("Grouped by city with name list : " + groupByCitywithNameList);

//Group people by city and find the average age.
        Map<String, Double> cityWiseAverageAge = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.averagingDouble(Person::getAge)));
        System.out.println();
        System.out.println();
        System.out.println("CityWise average: " + cityWiseAverageAge);
//        Group people by city and get max age person in each city.

        Map<String, String> cityWiseMaxAgedPerson = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Person::getAge)), optionalPerson -> optionalPerson.map(Person::getName).orElse("No Person found!!"))));
        System.out.println();
        System.out.println();
        System.out.println("citi wise  max aged people: " + cityWiseMaxAgedPerson);
//
//        Group people by city and count how many are employed.
        Map<String, Long> cityWiseEmployedCOunt = people.stream().filter(e -> e.isEmployed).collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        System.out.println();
        System.out.println();
        System.out.println("CityWise employed: " + cityWiseEmployedCOunt);
        // Group people by city and list ages of people.
        Map<String, Map<Integer, List<Person>>> citywiseAgeSegregatedList = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.groupingBy(Person::getAge)));
        System.out.println();
        System.out.println();
        System.out.println("citywise age segregated list: " + citywiseAgeSegregatedList);
//                Group people by city and sum their ages.
        Map<String, Integer> citiwiseAgeSUm = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.summingInt(Person::getAge)));
        System.out.println();
        System.out.println("citywise age sum: " + citiwiseAgeSUm);
//
//                Group people by city and collect a Set of names.
        Map<String, Set<String>> citiwiseNameset = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.mapping(Person::getName, Collectors.toSet())));
        System.out.println();
        System.out.println("citywise name set: " + citiwiseNameset);

//                Partition people by age ≥ 30 and collect their names.
        List<String> ageGreaterThan30Names = people.stream()
                .filter(p -> p.getAge() >= 30)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("age greater than 30 names: " + ageGreaterThan30Names);
//        Group people by city and find total number of employed.

//📈 Counting, Reducing, Summarizing
//        Count number of cities with more than 2 people.

        int no_of_cities_with_more_than_two_peoples = (int) people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.counting())).entrySet().stream().filter(e -> e.getValue() > 2).
                count();
        System.out.println();
        System.out.println("no of cities with more than 2 persons : " + no_of_cities_with_more_than_two_peoples);

//                Count how many unique cities are there.
        int uniqueCitiesCOunt = people.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.counting())).size();
        System.out.println();
        System.out.println(" total unique cities count :" + uniqueCitiesCOunt);
//
//        Count how many people are older than 25.
        long countOfPersonsOlderThan25 = people.stream()
                .filter(p -> p.getAge() > 25)
                .count();

        System.out.println();
        System.out.println("count of people are older than 25 : " + countOfPersonsOlderThan25);

//
        Map<String, Long> result = people.stream()
                .filter(p -> p.getAge() > 30)
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        System.out.println();
        System.out.println("count of people are older than 30 : " + result.get("count"));

//
//                Count names starting with same letter using grouping.
        Map<Character, Integer> namesStartingWithSameLetter = people.stream()
                .collect(Collectors.groupingBy(
                        p -> Character.toUpperCase(p.getName().charAt(0)),
                        Collectors.reducing(0, e -> 1, Integer::sum)
                ));

        Map<Character, Long> namesStartingWithSameLetter1 = people.stream()
                .collect(Collectors.groupingBy(
                        p -> Character.toUpperCase(p.getName().charAt(0)),
                        Collectors.counting()
                ));

//                Partition people by employment and get sum of their ages.
        Map<Boolean, Integer> ageSumByEmployment = people.stream()
                .collect(Collectors.partitioningBy(
                        Person::isEmployed,
                        Collectors.summingInt(Person::getAge)
                ));

//
//                Find city with most people.
        String cityWithMostPeople = people.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No City");

//        Find city with least employed people.
        String cityWithLeastEmployed = people.stream()
                .filter(Person::isEmployed)
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No City");

//        Find city with maximum average age.
        String cityWithMaxAvgAge = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.averagingInt(Person::getAge)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No City");

//        Group people by city and get average name length.
        Map<String, Double> avgNameLengthPerCity = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.averagingInt(p -> p.getName().length())
                ));

//
//🧠 Custom Collectors Logic
//        Group people by city and collect people’s names in comma-separated string.
//
//                Partition people into even and odd ages.
//
//        Group by city and for each, find youngest person.
//
//        Group by city and collect sorted names list.
//
//        Group by employment status and collect age histogram (map of age → count).
//
//        Group by age and check if all from a city.
//
//        Count people per city and filter only where count > 1.
//
//        Group people by city, then partition by age > 30.
//
//        Partition people by employment, then group by city.
//
//                Partition people by name length > 4 and count each group.
//

    }


    public static boolean startsWithVowel(String word) {
        char firstChar = Character.toLowerCase(word.charAt(0));
        return firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u';

    }

    private static boolean startsWithVowels(String name) {
        char first = Character.toLowerCase(name.charAt(0));
        return "aeiou".indexOf(first) != -1;
    }

}
