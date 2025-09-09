package backtracking.wordsearch;
import java.util.*;


/*Problem: Word Search – All Overlapping Occurrences

Goal:

Collect all occurrences of the word in the board.

Cells can be reused across different occurrences.*/
public class WordSearchOverlapping {

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C'},
                {'A','B','C'}
        };
        String word = "ABC";

        List<List<int[]>> occurrences = findAllOccurrences(board, word);
        System.out.println("Total occurrences: " + occurrences.size());
        for (List<int[]> path : occurrences) {
            System.out.println("Occurrence:");
            for (int[] cell : path) {
                System.out.print(Arrays.toString(cell) + " ");
            }
            System.out.println();
        }
    }

    public static List<List<int[]>> findAllOccurrences(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        List<List<int[]>> results = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, word, 0, i, j, visited, new ArrayList<>(), results);
            }
        }
        return results;
    }

    private static void dfs(char[][] board, String word, int index, int i, int j,
                            boolean[][] visited, List<int[]> path, List<List<int[]>> results) {
        if (index == word.length()) {
            results.add(new ArrayList<>(path));
            return;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        if (visited[i][j] || board[i][j] != word.charAt(index)) return;

        visited[i][j] = true;
        path.add(new int[]{i, j});

        // explore all 4 directions
        dfs(board, word, index + 1, i + 1, j, visited, path, results);
        dfs(board, word, index + 1, i - 1, j, visited, path, results);
        dfs(board, word, index + 1, i, j + 1, visited, path, results);
        dfs(board, word, index + 1, i, j - 1, visited, path, results);

        // backtrack
        visited[i][j] = false;
        path.remove(path.size() - 1);
    }
}

/*🔹 Key Changes from Non-Overlapping Version

We don’t mark cells permanently after finding a path.

Backtracking resets visited[i][j] = false each time → allows overlapping.

DFS continues exploring all paths starting from every cell.

🔹 Complexity

Time: O(M * N * 4^L) × number of occurrences (L = word length).

Space: O(L) recursion + O(M*N) visited + O(occurrences * L) for storing paths.*/