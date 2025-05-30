package backtracking;
import java.util.*;

public class NQueensSolver {

    public static List<List<Integer>> solveNQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, new ArrayList<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), result, n);
        return result;
    }

    private static void backtrack(int col, List<Integer> current,
                                  Set<Integer> rows, Set<Integer> diag1, Set<Integer> diag2,
                                  List<List<Integer>> result, int n) {
        if (col == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int row = 1; row <= n; row++) {
            int d1 = row - col;
            int d2 = row + col;
            if (rows.contains(row) || diag1.contains(d1) || diag2.contains(d2)) {
                continue;
            }

            current.add(row);
            rows.add(row);
            diag1.add(d1);
            diag2.add(d2);

            backtrack(col + 1, current, rows, diag1, diag2, result, n);

            // Backtrack
            current.remove(current.size() - 1);
            rows.remove(row);
            diag1.remove(d1);
            diag2.remove(d2);
        }
    }

    // Driver code for quick testing
    public static void main(String[] args) {
        int n = 3;
        List<List<Integer>> solutions = solveNQueens(n);
        for (List<Integer> solution : solutions) {
            System.out.println(solution);
        }
    }
}
