package backtracking;

import java.util.*;

public class KeyPad {
    private static final Map<Integer, String> digitToLetters = Map.of(
            2, "abc",
            3, "def",
            4, "ghi",
            5, "jkl",
            6, "mno",
            7, "pqrs",
            8, "tuv",
            9, "wxyz"
    );

    public List<String> possibleWords(int[] arr, int N) {
        List<String> result = new ArrayList<>();
        if (arr == null || arr.length == 0) return result;
        backtrack(arr, N, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(int[] arr, int N, int index, StringBuilder current, List<String> result) {
        if (index == N) {
            result.add(current.toString());
            return;
        }

        String letters = digitToLetters.get(arr[index]);
        if (letters != null) {
            for (char c : letters.toCharArray()) {
                current.append(c);
                backtrack(arr, N, index + 1, current, result);
                current.deleteCharAt(current.length() - 1); // Backtrack
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        KeyPad sol = new KeyPad();
        System.out.println(sol.possibleWords(new int[]{2, 3}, 2));         // [ad, ae, af, bd, be, bf, cd, ce, cf]
        System.out.println(sol.possibleWords(new int[]{3, 4, 5}, 3));      // 27 combinations
        System.out.println(sol.possibleWords(new int[]{2}, 1));            // [a, b, c]
    }
}
