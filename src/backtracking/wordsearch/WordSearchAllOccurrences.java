package backtracking.wordsearch;
import java.util.*;


/*🔹 Problem: Word Search – All Non-Overlapping Occurrences

Given:

Board of characters

Word to search

Goal:

Find all positions where the word occurs.

Words cannot overlap → same cell cannot be used in multiple occurrences.

Output:

List of paths, each path = list of coordinates for that occurrence.

🔹 Intuition

Use backtracking DFS like before.

Maintain a global visited array.

Each DFS returns the path of coordinates for one word occurrence.

Mark all those cells in visited → ensure non-overlapping.

Continue searching rest of board for other occurrences.

Pattern takeaway: path collection + global constraint + backtracking.

🔹 Dry Run

Board:

A B C
A B C


Word = "ABC"

Start at (0,0): finds path → mark (0,0),(0,1),(0,2) as used

Continue search: next occurrence starts at (1,0): finds path → mark (1,0),(1,1),(1,2) as used

Paths collected: 2 occurrences.*/
public class WordSearchAllOccurrences {

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C'},
                {'A','B','C'},
                {'A','C', 'D'},
                {'B','C', 'D'},
                {'B','C', 'D'}

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
                List<int[]> path = new ArrayList<>();
                if (dfs(board, word, 0, i, j, visited, path)) {
                    results.add(new ArrayList<>(path)); // collect path
                    // mark visited cells permanently to avoid overlap
                    for (int[] cell : path) visited[cell[0]][cell[1]] = true;
                }
            }
        }
        return results;
    }

    private static boolean dfs(char[][] board, String word, int index, int i, int j,
                               boolean[][] visited, List<int[]> path) {
        if (index == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (visited[i][j] || board[i][j] != word.charAt(index)) return false;

        path.add(new int[]{i, j}); // include this cell in path

        boolean found = dfs(board, word, index + 1, i + 1, j, visited, path)
                || dfs(board, word, index + 1, i - 1, j, visited, path)
                || dfs(board, word, index + 1, i, j + 1, visited, path)
                || dfs(board, word, index + 1, i, j - 1, visited, path);

        if (!found) path.remove(path.size() - 1); // backtrack
        return found;
    }
}

/*Complexity

Time: O(M*N*4^L) × occurrences (L = word length)

Space: O(L) recursion stack + O(M*N) visited array + O(occurrences*L) for paths

Slightly more complex than single occurrence because we maintain all paths + permanent visited.*/