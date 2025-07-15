package backtracking.july_14;//
//    void backtrack(state) {
//        if (base condition) {
//            // process or store solution
//            return;
//        }
//
//        for (choice in possible choices) {
//            make the choice
//            backtrack(updated state)
//            undo the choice
//        }
//    }/******************************************************************************
// *  Problem — Generate All Permutations of a String (Backtracking)
// *
// *  Given a string s (assume its characters may repeat), print and/or
// *  return every distinct permutation of its characters.
// *
// *  ---------------------------------------------------
// *  Intuition
// *  ---------------------------------------------------
// *  • Backtracking is a depth‑first search that *builds* the answer one
// *    decision at a time, and *undoes* the decision while returning.
// *  • At any level we decide *which unused character* goes next.
// *  • We need:
// *        1.  a data structure that tells us which indices are already used
// *            (boolean[] visited),
// *        2.  a “path” that stores the partial permutation
// *            (StringBuilder path),
// *        3.  a result list that captures each full permutation.
// *  • Whenever the path length equals s.length(), we have a full
// *    permutation — add a *copy* to the result and back‑track.
// *
// *  ---------------------------------------------------
// *  Dry‑Run on "ABC"
// *  ---------------------------------------------------
// *  State shown as (path, visited)
// *  1. ("", F F F)
// *        └── choose A
// *  2. ("A", T F F)
// *        ├── choose B → ("AB", T T F)
// *        │       └── choose C → ("ABC")  ✓ add & backtrack
// *        └── choose C → ("AC", T F T)
// *                └── choose B → ("ACB") ✓ add & backtrack
// *        …and so on until every branch explored.
// *
// *  ---------------------------------------------------
// *  Complete Code
// *  ---------------------------------------------------
// */

import java.util.ArrayList;
import java.util.List;

public class Practice {

    /* Driver -------------------------------------------------------------- */
    public static void main(String[] args) {
        List<String> perms = permute("ABC");
        System.out.println(perms);   // [ABC, ACB, BAC, BCA, CAB, CBA]
    }

    /* Public API ---------------------------------------------------------- */
    public static List<String> permute(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        char[] chars = s.toCharArray();
        boolean[] used = new boolean[chars.length];
        backtrack(chars, used, new StringBuilder(), result);
        return result;
    }

    /* Core Backtracking --------------------------------------------------- */
    private static void backtrack(char[] chars,
                                  boolean[] used,
                                  StringBuilder path,
                                  List<String> result) {

        /* Base case: all positions filled → store permutation */
        if (path.length() == chars.length) {
            result.add(path.toString());           // copy‑by‑value
            return;
        }

        /* Explore every choice for the next position */
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;                 // skip used indices

            // ---- make the choice ----
            used[i] = true;
            path.append(chars[i]);

            // ---- explore deeper ----
            backtrack(chars, used, path, result);

            // ---- undo the choice (back‑track) ----
            path.deleteCharAt(path.length() - 1);
            used[i] = false;
        }
    }
}
/*
/ * ------------------------------------------------------------------------
 *  Complexity
 *  ------------------------------------------------------------------------
 *  • Time   : O(n! · n)
 *             There are n! permutations and copying StringBuilder into
 *             the result costs up to O(n) each.
 *  • Space  : O(n)  recursion depth + O(n) path + O(n) visited
 *             (excluding the O(n!) output).
 * ----------------------------------------------------------------------   */