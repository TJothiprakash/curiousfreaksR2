package practicesessions.sept_23.streams;

import java.util.*;
import java.util.stream.*;

public class Solution {

    /*
     * Problem:
     * Flatten nested lists of integers, remove duplicates, sort descending, take top 5.
     */

    public static List<Integer> flattenUniqueTop5(List<List<Integer>> nestedLists) {
        return nestedLists.stream()
                .flatMap(List::stream)                 // flatten nested lists
                .distinct()                            // keep unique elements
                .sorted(Comparator.reverseOrder())     // descending sort
                .limit(5)                              // top 5 elements
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(5, 1, 3),
                Arrays.asList(3, 6),
                Arrays.asList(7, 2, 5)
        );

        List<Integer> result = flattenUniqueTop5(input);
        System.out.println(result); // Answer: [7, 6, 5, 3, 2]
    }
}
