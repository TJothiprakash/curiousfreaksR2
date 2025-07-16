package backtracking.basics;
/*

➕ 2. Combinations (Backtracking)
        🔹 Problem:
        Given n = 4, k = 2, find all combinations of k numbers from 1 to n
        Output:

        csharp
        Copy
        Edit
        [1, 2]
        [1, 3]
        [1, 4]
        [2, 3]
        [2, 4]
        [3, 4]
        🧠 Intuition:
        Loop from start to n

        At each step:

        Add current number

        Recurse with next number

        Backtrack (remove)

        ✅ Java Code – Combinations via Backtracking
        java
        Copy
        Edit
*/

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CombinationBacktracking {
    public static void main(String[] args) {
        int n = 4, k = 2;
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        for (List<Integer> combo : result) {
            System.out.println(combo);
        }
    }

    static void backtrack(int start, int n, int k, @NotNull List<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= n; i++) {
            path.add(i);
            backtrack(i + 1, n, k, path, result);
            path.remove(path.size() - 1);  // 🔁 Backtrack
        }
    }
}/*
⏱️ Time & Space Complexity:
Time: O(C(n, k)) = n! / (k!(n−k)!)

Space: O(k) depth

✅ Summary Comparison
Feature	Permutations	Combinations
Output Count	n!	C(n, k)
Order matters?	✅ Yes	❌ No
Visited[]?	✅ Yes (to track used elements)	❌ No (use start to avoid reuse)
Use Case	Arrangements, N-Queens	Picking teams, subsets
*/


