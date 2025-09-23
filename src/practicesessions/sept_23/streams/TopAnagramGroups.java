package practicesessions.sept_23.streams;

import java.util.*;
import java.util.stream.Collectors;

public class TopAnagramGroups {
    public static void main(String[] args) {
        List<String> words = List.of("eat", "tea", "tan", "ate", "nat", "bat", "tab");

        // Step 1: Group words by sorted letters
        Map<String, List<String>> anagramGroups = new HashMap<>();
        for (String word : words) {
            char[] w = word.toCharArray();
            Arrays.sort(w);
            String key = new String(w); // sorted letters as key
            anagramGroups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        // Step 2: Sort each group's words alphabetically
        anagramGroups.values().forEach(Collections::sort);

        // Step 3: Find top 2 groups by size
        Map<String, List<String>> top2Groups = anagramGroups.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .limit(2)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,             // merge function (not needed here)
                        LinkedHashMap::new       // preserve order
                ));

        // Print result
        System.out.println(top2Groups);
    }
}
