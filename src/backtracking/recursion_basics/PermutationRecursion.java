package backtracking.recursion_basics;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PermutationRecursion {
    public static void main(String[] args) {
        String input = "abc";
        boolean[] used = new boolean[input.length()];
        List<String> result = new java.util.ArrayList<>();
        generatePermutations(input, "", used, result);
        System.out.println(result);
        System.out.println(result.size() + " permutations");
    }

    static void generatePermutations(@NotNull String input, @NotNull String current, boolean[] used, @NotNull List<String> result) {
        System.out.println("Call -> current: " + current);

        if (current.length() == input.length()) {
            System.out.println("Permutation: " + current);
            result.add(current);
            return;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!used[i]) {
                used[i] = true;
                generatePermutations(input, current + input.charAt(i), used, result);
                used[i] = false;
            }
        }
    }
}
