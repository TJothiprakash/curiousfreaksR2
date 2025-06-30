package backtracking.recursion_basics;



/*🎯 Problem
Generate all combinations of length r = 2 from the string "abc" using binary recursion (take/not-take style).

🧠 Intuition
At each character (by index), we have two choices:

Take it → include it in the current combo

Not take it → skip and go to the next index

Continue until:

Combo size = r → ✅ valid answer

Index = end of string → ⛔ stop recursion

🔁 Recursion Tree (for "abc", r = 2)
Each node = index, currentCombo

arduino
Copy
Edit
                  (0, "")
              /            \
          (1, "a")        (1, "")
         /       \        /     \
   (2, "ab")   (2, "a") (2, "b")  (2, "")
     |           |        |         |
  DONE         (3,a)   (3,"bc")    ...
               ❌      DONE       ❌
✅ Valid combos:
ab

ac

bc

*/

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CombinationRecursion {
    public static void main(String[] args) {
        String input = "abc";
        int r = 2; // choose any r
        System.out.println("r: " + r);
        System.out.println("Input: " + input);
        List<String> result = new java.util.ArrayList<>();
        generateCombinations(input, 0, "", r, result);
        System.out.println(result);
        System.out.println(result.size() + " combinations"); // n! / r! (n-r)! n= 3, r = 2
    }

    static void generateCombinations(String input, int index, @NotNull String current, int r, List<String> result) {
        System.out.println("Call -> index: " + index + ", current: " + current);

        if (current.length() == r) {
            System.out.println("Combination: " + current);
            result.add(current);
            return;
        }

        if (index == input.length()) return;

        // Include character at index
        generateCombinations(input, index + 1, current + input.charAt(index), r, result);

        // Exclude character at index
        generateCombinations(input, index + 1, current, r, result);
    }
}
