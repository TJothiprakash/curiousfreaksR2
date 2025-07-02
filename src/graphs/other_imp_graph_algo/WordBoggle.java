package graphs.other_imp_graph_algo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
🎯 Problem:
Given a board and a dictionary, find all words from the dictionary that can be formed from
8-directionally adjacent characters on the board. A cell can be used only once per word.

🧠 Intuition:
- For each word in the dictionary:
   - Try to search it from every cell on the board.
   - Use backtracking (DFS) to explore paths.
   - Mark cells visited and unmark on backtrack.
- This is like Word Search II but with 8 directions.

🔍 Dry Run:
dictionary: ["CAT"]
board:
C A P
A N D
T I E

Try "CAT":
Start at (0,0) → 'C' → go to (0,1) → 'A' → go to (1,0) → 'T' ✅ → Found.

📦 Output: CAT

📊 Time Complexity:
O(N * R * C * 8^W), where N = number of words, W = word length

💾 Space Complexity:
O(W) for recursion stack + visited array
*/

public class WordBoggle {

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static boolean dfs(char[][] board, String word, int idx, int x, int y, boolean[][] visited) {
        if (idx == word.length()) return true;

        int R = board.length;
        int C = board[0].length;

        if (x < 0 || y < 0 || x >= R || y >= C) return false;
        if (visited[x][y] || board[x][y] != word.charAt(idx)) return false;

        visited[x][y] = true;

        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (dfs(board, word, idx + 1, nx, ny, visited)) {
                visited[x][y] = false;
                return true;
            }
        }

        visited[x][y] = false;
        return false;
    }

    public static String[] wordBoggle(String[] dictionary, char[][] board) {
        Set<String> result = new HashSet<>();
        int R = board.length, C = board[0].length;

        for (String word : dictionary) {
            boolean found = false;
            for (int i = 0; i < R && !found; i++) {
                for (int j = 0; j < C && !found; j++) {
                    boolean[][] visited = new boolean[R][C];
                    if (dfs(board, word, 0, i, j, visited)) {
                        result.add(word);
                        found = true;
                    }
                }
            }
        }

        String[] ans = result.toArray(new String[0]);
        Arrays.sort(ans); // lexicographical order
        return ans;
    }

    // 🧪 Sample Test
    public static void main(String[] args) {
        String[] dict1 = {"CAT"};
        char[][] board1 = {
                {'C', 'A', 'P'},
                {'A', 'N', 'D'},
                {'T', 'I', 'E'}
        };

        System.out.println("Output 1:");
        for (String w : wordBoggle(dict1, board1))
            System.out.print(w + " "); // Output: CAT

        System.out.println("\n");

        String[] dict2 = {"GEEKS", "FOR", "QUIZ", "GO"};
        char[][] board2 = {
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}
        };

        System.out.println("Output 2:");
        for (String w : wordBoggle(dict2, board2))
            System.out.print(w + " "); // Output: GEEKS QUIZ
    }
}
