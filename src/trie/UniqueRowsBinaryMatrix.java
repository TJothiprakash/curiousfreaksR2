package trie;
/***************************************************************
 *  Problem:  Unique Rows in a Binary Matrix
 *
 *  Given a binary matrix `M` (only 0 / 1 values) with `row`
 *  rows and `col` columns, return *all* unique rows **in the
 *  order they first appear**.
 *
 *  ────────────────────────────────────────────────────────────
 *  Intuition
 *  ────────────────────────────────────────────────────────────
 *  A row is a sequence of bits.  Detecting duplicates can be
 *  done in two classic ways:
 *
 *    1. **Hash‑Set Approach** – Serialize each row as a string
 *       (or integer) and keep a `HashSet` to skip repeats.
 *       Fast to write, relies on Java’s hashing.
 *
 *    2. **Trie Approach** – Insert each row bit‑by‑bit into a
 *       binary trie.  Each leaf corresponds to one distinct
 *       row.  Duplicate detection becomes a trie traversal:
 *       if the leaf already existed, the row is not unique.
 *
 *  Both guarantee `O(row × col)` time, but they trade off
 *  constant factors and memory layout.  The trie is handy when
 *  you cannot (or do not want to) concatenate bits into a
 *  single hash key (e.g., very long rows, custom alphabet).
 *
 *  ────────────────────────────────────────────────────────────
 *  Dry‑Run (Example 1)
 *  ────────────────────────────────────────────────────────────
 *  Matrix
 *      1 1 0 1   ← insert “1101”   • unseen → keep
 *      1 0 0 1   ← insert “1001”   • unseen → keep
 *      1 1 0 1   ← insert “1101”   • seen   → skip
 *
 *  Result ➜  [ [1,1,0,1], [1,0,0,1] ]
 *
 *  ────────────────────────────────────────────────────────────
 *  Code
 *  ────────────────────────────────────────────────────────────
 *  The file below offers **both** implementations:
 *
 *  • `uniqueRowWithSet`  – hash‑set solution (very concise)
 *  • `uniqueRowWithTrie` – explicit trie solution
 *
 *  The public `uniqueRow` method simply delegates to the trie
 *  version so it passes any online‑judge template that expects
 *  exactly that signature.  Change the delegation line if you
 *  wish to test the other method.
 ****************************************************************/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueRowsBinaryMatrix {

    /* ==== 1) Public API expected by the driver ==== */
    public static List<List<Integer>> uniqueRow(int[][] M, int row, int col) {
        // You may switch to the set version for comparison:
        // return uniqueRowWithSet(M, row, col);
        return uniqueRowWithTrie(M, row, col);
    }

    /* ==== 2) Hash‑Set implementation ==== */
    public static List<List<Integer>> uniqueRowWithSet(int[][] M, int row, int col) {
        List<List<Integer>> result = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder(col);
            for (int j = 0; j < col; j++) sb.append(M[i][j]);
            String key = sb.toString();

            if (seen.add(key)) {                 // adds + returns true if not present
                List<Integer> list = new ArrayList<>(col);
                for (char c : key.toCharArray()) list.add(c - '0');
                result.add(list);
            }
        }
        return result;
    }

    /* ==== 3) Trie implementation ==== */
    private static class TrieNode {
        TrieNode zero, one;
        boolean isLeaf;   // marks end of a complete row
    }

    public static List<List<Integer>> uniqueRowWithTrie(int[][] M, int row, int col) {
        List<List<Integer>> result = new ArrayList<>();
        TrieNode root = new TrieNode();

        for (int i = 0; i < row; i++) {
            TrieNode curr = root;
            for (int j = 0; j < col; j++) {
                if (M[i][j] == 0) {
                    if (curr.zero == null) curr.zero = new TrieNode();
                    curr = curr.zero;
                } else {
                    if (curr.one == null) curr.one = new TrieNode();
                    curr = curr.one;
                }
            }
            if (!curr.isLeaf) {        // first time this exact path appears
                curr.isLeaf = true;
                List<Integer> list = new ArrayList<>(col);
                for (int j = 0; j < col; j++) list.add(M[i][j]);
                result.add(list);
            }
        }
        return result;
    }

    /* ==== 4) Quick self‑test (optional main) ==== */
    public static void main(String[] args) {
        int[][] m1 = {{1, 1, 0, 1}, {1, 0, 0, 1}, {1, 1, 0, 1}};
        System.out.println(uniqueRow(m1, 3, 4));   // [[1, 1, 0, 1], [1, 0, 0, 1]]

        int[][] m2 = {{0, 0, 0, 1}, {0, 0, 0, 1}};
        System.out.println(uniqueRow(m2, 2, 4));   // [[0, 0, 0, 1]]
    }
}

/*──────────────────────────────────────────────────────────────
  Complexity Analysis
  ─────────────────────────────────────────────────────────────
  Let R = row and C = col (with R, C ≤ 40 here).

  • **Set Approach**
        Time   : O(R × C)  – build key + hash.
        Space  : O(R × C)  – store up to R distinct keys.

  • **Trie Approach**
        Time   : O(R × C)  – one node visited per bit.
        Space  : O(U × C)  – U = #unique rows ≤ R; each unique
                             row adds at most C new nodes.

  When rows are short (≤ 64), you could hash them as a single
  `long` and the approaches converge.  The trie shines when
  rows are very long or when you need prefix‑based queries.
──────────────────────────────────────────────────────────────*/
