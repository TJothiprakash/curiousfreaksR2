package collections_practice.streams.exercise_3;


import java.util.*;
import java.util.stream.Collectors;

/**/
public class Quesions {
    public static void main(String[] args) {
//        🟢 Basic Mapping & Filtering
//        Filter even numbers from a list of integers.
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream().filter(a -> a % 2 == 0).toList();
        List<Integer> OddNumbers = numbers.stream().filter(a -> a % 2 == 1).toList();
        System.out.println(evenNumbers);
        //        Filter odd numbers from a list of integers.
        System.out.println("odd numbers :" + OddNumbers);

//        Square every number in the list.
        List<Integer> squares = numbers.stream().map(z -> z * z).collect(Collectors.toList());
        System.out.println("squares :" + squares);
//        Cube every number in the list.
        List<Integer> cubes = numbers.stream().map(z -> z * z * z).collect(Collectors.toList());
        System.out.println("cubes :" + cubes);
//        Convert list of strings to uppercase.
//        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve","");
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("David");
        names.add("Eve");
//        names.add("");
//        names.add(null);
        System.out.println("names :" + names);
        List<String> upperCase = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("upperCase :" + upperCase);
//        Convert list of strings to lowercase.
        List<String> lowerCase = names.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println("lowerCase :" + lowerCase);
//        Remove all null values from a list of strings.
        Iterable<String> nullRemoved = names.stream().filter(s -> s != null).collect(Collectors.toList());
        System.out.println("nullRemoved :" + nullRemoved);
//        names.removeIf(String::isEmpty);
//        System.out.println("names after removing null values :"+names);
//        Find the length of each string in the list.
        List<Integer> lengths = names.stream().map(String::length).collect(Collectors.toList());
        System.out.println("lengths :" + lengths);
//                Extract the first character from each string.
        List<Character> firstChars = names.stream().filter(s -> s.length() > 0).map(s -> s.charAt(0)).collect(Collectors.toList());
        System.out.println("firstChars :" + firstChars);
//        Filter strings with length > 5.
        List<String> filtered = names.stream().filter(s -> s.length() > 5).collect(Collectors.toList());
        System.out.println("Filtered :" + filtered);

//🔵 String & Character Filtering
//        Filter strings that start with 'a'.
        List<String> stringsStartingWithA = names.stream().filter(s -> s.startsWith("A")).collect(Collectors.toUnmodifiableList());
        System.out.println("stringsStartingWithA :" + stringsStartingWithA);
//                Filter strings that end with 'e'.
        List<String> stringsEndwithE = names.stream().filter(s -> s.endsWith("E")).collect(Collectors.toUnmodifiableList());
//                Filter strings that contain 'an'.
        List<String> StringsThatConatinAN = names.stream().filter(a -> a.contains("an")).collect(Collectors.toList());
        System.out.println("StringsThatConatinAN :" + StringsThatConatinAN);
//                Count strings with length > 3.
        List<String> StringsWithLengthGreaterThan3 = names.stream().filter(s -> s.length() > 3).collect(Collectors.toUnmodifiableList());
        System.out.println("StringsWithLengthGreaterThan3 :" + StringsWithLengthGreaterThan3);
//
//        Count strings that start with a vowel.
//        List<String>  wordsStartingwithVowel = names.stream().
//                Find the longest string in the list.
        List<String> vowelWords = names
                .stream()
                .filter(s -> !s.isEmpty())
                .filter(s -> {
                    char ch = Character.toLowerCase(s.charAt(0));
                    return "aeiou".indexOf(ch) != -1;
                })
                .collect(Collectors.toList());
//
//        Find the shortest string in the list.
        String shortest = names.stream().min(Comparator.comparingInt(String::length)).orElse("nothing found");
        System.out.println("shortest : " + shortest);
        System.out.println(names);
        System.out.println("shotest :" + shortest);
//                Sort the strings alphabetically.
        List<String> sortiedList = names.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        System.out.println("sortied :" + sortiedList);
//                Sort the strings by length.
        List<String> sortedByLength = names.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
//        Filter strings that are palindromes.
        names.add("madam");

        List<String> palindromes = names.stream().filter(a -> a.equals(new StringBuilder(a).reverse().toString())).collect(Collectors.toList());
        System.out.println("palindromes :" + palindromes);
//🟣 Number Transformations
//        Get the sum of all elements using reduce.
        List<Integer> numberList = List.of(1, 2, 3, 4, 5, 6);
//  int sum = numberList.stream().reduce(Integer::sum).orElse(0);
        int sum = numberList.stream().reduce(Integer::sum).orElse(0);
        System.out.println(sum);

//        Get the product of all elements using reduce.
        int product = numberList.stream().reduce(1, Math::multiplyExact);
        int product1 = numberList.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product1 :" + product1);
        System.out.println("product :" + product);
//        Find the maximum number using streams.
        int maxNumber = numberList.stream().reduce(Integer::max).orElse(0);
        System.out.println("maxNumber :" + maxNumber);
//        Find the minimum number using streams.
        int minNuber = numberList.stream().min(Comparator.comparingInt(Integer::intValue)).orElse(0);
        System.out.println("minNumber :" + minNuber);
//        Remove duplicates from a list of integers.
        List<Integer> removeDuplicates = numberList.stream().distinct().collect(Collectors.toList());
        System.out.println("removeDuplicates :" + removeDuplicates);
//                Sort numbers in descending order.
        List<Integer> descendingOrder = numberList.stream().sorted(Comparator.comparingInt(Integer::intValue).reversed()).collect(Collectors.toList());
        System.out.println("descendingOrder :" + descendingOrder);
//        Get the first 3 elements of a list.
        List<Integer> first3Elements = numberList.stream().sorted().limit(3).collect(Collectors.toList());
        System.out.println("first3Elements :" + first3Elements);
//        Skip the first 2 elements of a list.
        List<Integer> skipfirst2Elements = numberList.stream().sorted().skip(2).collect(Collectors.toList());
        skipfirst2Elements.forEach(System.out::println);
//        Count how many numbers are divisible by 3.
        List<Integer> divisibleby3 = numberList.stream().filter(a -> a % 3 == 0).collect(Collectors.toList());
        System.out.println("divisibleby3 :" + divisibleby3);
//
//        Filter prime numbers from a list.
        List<Integer> primeNumbers = numberList.stream().filter(Quesions::isPrime).collect(Collectors.toUnmodifiableList());
        System.out.println("primeNumbers :" + primeNumbers);
        //🟡 Mixed Type Challenges
//        Find all strings that are exactly 4 characters long.
        List<String> exactly4charlen = names.stream().filter(s -> s.length() == 4).collect(Collectors.toList());
        System.out.println("exactly4charlen :" + exactly4charlen);
        //                Convert a list of strings to a list of their lengths.
        List<Integer> stringlenlist = names.stream().map(String::length).collect(Collectors.toList());
        System.out.println("stringlenlist :" + stringlenlist);
//        Check if all numbers are even.
        Boolean isAllEven = numberList.stream().allMatch(e -> e % 2 == 0);
        System.out.println("isAllEven :" + isAllEven);
//        Check if any number is greater than 50.
        Boolean anyMatch = numberList.stream().anyMatch(d -> d > 50);
        System.out.println("anyMatch :" + anyMatch);
//        Check if none of the numbers are negative.
        Boolean noNegative = numberList.stream().noneMatch(e -> e < 0);
        System.out.println("noNegative :" + noNegative);
//        Concatenate all strings with comma separation.
        String concatenation = names.stream().reduce(String::concat).orElse("");
        String concat = names.stream().collect(Collectors.joining(","));
        System.out.println("concat :" + concat);
        System.out.println("concatenation :" + concatenation);
//        Join all strings with - separator.
        String joinwithSeparator = names.stream().collect(Collectors.joining("-"));
        System.out.println("joinwithSeparator :" + joinwithSeparator);
        List<String> capitalized = names.stream().filter(s -> !s.isEmpty()).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)).collect(Collectors.toUnmodifiableList());
        System.out.println("capitalized :" + capitalized);
//        List<Integer> numberList = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 1);

        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicates = numberList.stream()
                .filter(n -> !seen.add(n))
                .distinct() // optional: to avoid repeated duplicates
                .collect(Collectors.toList());
//        Reverse all strings in the list.
        List<String> reversedStrings = names.stream()
                .map(s -> new StringBuilder(s).reverse().toString())
                .collect(Collectors.toList());

//

    }

    private static boolean isPrime(Integer integer) {
        if (integer <= 1) return false;
        if (integer == 2) return true;

        if (integer % 2 == 0) return false;

        for (int i = 3; i < Math.sqrt(integer); i++) {
            if (integer % i == 0) return false;

        }
        return true;
    }
}
