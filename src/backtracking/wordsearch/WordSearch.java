package backtracking.wordsearch;


/*Problem: Word Search

Given:

A 2D board of characters.

A word (string).

Task:
Check if the word exists in the board by moving horizontally or vertically (up/down/left/right).

You cannot use the same cell twice in a single word.

Example:

Board:
[['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']]

Word = "ABCCED" → True
Word = "SEE"    → True
Word = "ABCB"   → False

🔹 Intuition

Start DFS from each cell that matches the first letter.

Recursively try all 4 directions (up/down/left/right).

Keep a visited state to avoid revisiting same cell.

If DFS reaches end of word → success.

Backtrack by unmarking visited when returning.

Pattern takeaway: grid traversal + choice + constraint check + backtrack.

🔹 Dry Run (Example)

Word = "ABCCED"

Start at (0,0) 'A' → matches word[0]

Go right → 'B' matches word[1]

Go right → 'C' matches word[2]

Go down → 'C' matches word[3]

Go left → 'E' matches word[4]

Go down → 'D' matches word[5] ✅ Success

Visited cells are marked during DFS and unmarked while backtracking.*/

public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";

        System.out.println(exist(board, word1)); // true
        System.out.println(exist(board, word2)); // true
        System.out.println(exist(board, word3)); // false
    }

    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j, visited)) return true;
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
        if (index == word.length()) return true; // all chars matched
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false; // out of bounds
        if (visited[i][j] || board[i][j] != word.charAt(index)) return false; // already visited or char mismatch

        visited[i][j] = true;

        // explore 4 directions
        boolean found = dfs(board, word, index + 1, i + 1, j, visited)
                || dfs(board, word, index + 1, i - 1, j, visited)
                || dfs(board, word, index + 1, i, j + 1, visited)
                || dfs(board, word, index + 1, i, j - 1, visited);

        visited[i][j] = false; // backtrack
        return found;
    }
}

/*🔹 Complexity

Time: O(M * N * 4^L)

M×N = number of cells.

L = length of the word.

4^L = maximum DFS path exploration per starting cell.

Space: O(L) recursion stack + O(M*N) visited array.

Note: Space can be reduced by modifying board in-place instead of using a visited array.

This is a classic backtracking + grid traversal pattern — different from Rat in a Maze because it checks matching sequence instead of just reachability*/