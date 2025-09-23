package practicesessions.sept_22.stream;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamPractice {
    public static void main(String[] args) {
        questions();
    }

    private static void questions() {
        List<String> names = new ArrayList<>(List.of("ram", "abi", "krish", "om", "ananya", "raj"));
        List<String> result = names.stream()
                .filter( a -> a.length() >= 3)
                .map(String::toUpperCase)
                .sorted()
                .toList();
        System.out.println(result);
        List<Integer> num = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));
        List<Integer> evenSquares = num.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .toList();
        System.out.println(evenSquares);

        List<String> words = new ArrayList<>(List.of("Ajith", "vijay", "Karthi", "ajith", "Suriya", "VIJAY"));
        List<String> dedupedWord = words.stream()
                .map(w -> w.toLowerCase())
                .distinct().sorted(Comparator.comparingInt(w -> w.length())).toList();
        System.out.println("dedupedWord = " + dedupedWord);

        List<Character> characterList = new ArrayList<>(List.of('a', 'b', 'a', 'c', 'b', 'a'));
        Map<Character, Long> charMap = characterList.stream()
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        System.out.println("charMap = " + charMap);

        List<String> question = new ArrayList<>(List.of("ram=10", "ajay=7", "ram=3", "vijay=5", "AJAY=5"));
        Map<String, Integer> answer = question.stream()
                .collect(Collectors.groupingBy(x -> {
                    x = x.split("=")[0].toLowerCase();

                    return x;
                }, Collectors.summingInt(x -> {
                    x = x.split("=")[1];
                    int res = Integer.parseInt(x);
                    return res;
                })));
        System.out.println(answer);
    }

}

