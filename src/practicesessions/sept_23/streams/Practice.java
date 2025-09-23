package practicesessions.sept_23.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        practice();
    }

    private static void practice() {
        List<String> names = new ArrayList<>(List.of("arun", "Bala", "Anil", "bala", "Ajay", ""));
        List<String> answer = names.stream().
                filter(name -> !name.isEmpty() && Character.isUpperCase(name.charAt(0)))
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(answer);

        /*Medium — anagram groups top-2: Group words by their sorted-letter key,
         then return the top 2 groups by size as
          Map<String,List<String>> with each group’s values sorted;
           input: ["eat","tea","tan","ate","nat","bat","tab"]
           and expected top-2 groups by size: {"aet":["ate","eat","tea"], "abt":["bat","tab"]}.*/


    }
}
