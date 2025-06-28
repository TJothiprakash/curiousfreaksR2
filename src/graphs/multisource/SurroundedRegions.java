package graphs.multisource;
/*
✅ Question:
Given a 2D matrix of characters 'X' and 'O', replace all 'O's that are completely
surrounded by 'X's with 'X'.

A group of 'O's is surrounded if every cell adjacent (up, down, left, right)
is either 'X' or another 'O'
that is also eventually surrounded.

Only 'O's that are **not connected to border 'O's** can be converted to 'X'.

---

✅ Examples:

Input:
[['X', 'X', 'X', 'X'],
 ['X', 'O', 'X', 'X'],
 ['X', 'O', 'O', 'X'],
 ['X', 'O', 'X', 'X'],
 ['X', 'X', 'O', 'O']]

Output:
[['X', 'X', 'X', 'X'],
 ['X', 'X', 'X', 'X'],
 ['X', 'X', 'X', 'X'],
 ['X', 'X', 'X', 'X'],
 ['X', 'X', 'O', 'O']]

---

✅ Intuition:

- We only keep 'O's that are **connected to the border**.
- So we:
  1. Traverse border cells, and for every 'O' on the border, run **DFS/BFS** to mark it and its connected 'O's.
  2. After this:
     - Convert unmarked 'O' → 'X' (surrounded)
     - Convert marked temporary char → 'O' (restore safe regions)

---

✅ Dry Run:

Input:
[['X','X','X'],
 ['X','O','X'],
 ['X','X','X']]

Middle 'O' is fully surrounded → converted to 'X'.

Result:
[['X','X','X'],
 ['X','X','X'],
 ['X','X','X']]

---

✅ Time Complexity:
O(n * m) — Each cell is visited at most once.

✅ Space Complexity:
O(n * m) — For visited array or recursion stack (DFS).

---

✅ Constraints:
1 ≤ mat.length ≤ 100
1 ≤ mat[0].length ≤ 100
*/

public class SurroundedRegions {

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int n = board.length;
        int m = board[0].length;

        // Step 1: Traverse all border 'O's and mark connected 'O's
        for (int i = 0; i < n; i++) {
            // Left and Right borders
            if (board[i][0] == 'O') dfs(i, 0, board);
            if (board[i][m - 1] == 'O') dfs(i, m - 1, board);
        }
        for (int j = 0; j < m; j++) {
            // Top and Bottom borders
            if (board[0][j] == 'O') dfs(0, j, board);
            if (board[n - 1][j] == 'O') dfs(n - 1, j, board);
        }

        // Step 2: Replace all unmarked 'O' with 'X', and marked ones back to 'O'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // surrounded region
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O'; // restore safe region
                }
            }
        }
    }

    private void dfs(int row, int col, char[][] board) {
        int n = board.length;
        int m = board[0].length;

        if (row < 0 || col < 0 || row >= n || col >= m || board[row][col] != 'O') return;

        board[row][col] = '#'; // temporary marker for safe region

        // Explore in 4 directions
        dfs(row - 1, col, board); // up
        dfs(row + 1, col, board); // down
        dfs(row, col - 1, board); // left
        dfs(row, col + 1, board); // right
    }
}
