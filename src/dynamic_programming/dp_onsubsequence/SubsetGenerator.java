package dynamic_programming.dp_onsubsequence;

import java.util.HashSet;
import java.util.Set;

public class SubsetGenerator {
    public static void main(String[] args) {
        String in = "aaab";
        Set<String> subsets = new HashSet<>();  // Use Set to avoid duplicates
        generateSubsets(0, in, "", subsets);

        System.out.println("All unique subsets of '" + in + "':");
        System.out.println(subsets);
        System.out.println("Unique subset count: " + subsets.size());
    }

    static void generateSubsets(int index, String s, String current, Set<String> result) {
        if (index == s.length()) {
            result.add(current);  // Only unique strings will be stored
            return;
        }

        // Include current character
        generateSubsets(index + 1, s, current + s.charAt(index), result);

        // Exclude current character
        generateSubsets(index + 1, s, current, result);
    }
}
