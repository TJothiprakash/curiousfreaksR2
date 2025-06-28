package graphs.multisource;
/*
✅ Question:
Given a grid of dimension n x m containing 0s and 1s. Find the unit area of the largest region of 1s.
A region is a group of 1s connected in 8 directions (horizontal, vertical, and diagonal).

Example 1:
Input: grid = {{1,1,1,0}, {0,0,1,0}, {0,0,0,1}}
Output: 5

Example 2:
Input: grid = {{0,1}}
Output: 1

Constraints:
1 ≤ n, m ≤ 500

---

✅ Intuition:
Use DFS to explore each unvisited '1' and count the number of 1s in that region.
Track the max region size. We explore in 8 directions to cover all connected cells.

---

✅ Dry Run:
Input:
1 1 1 0
0 0 1 0
0 0 0 1

Start DFS at (0,0) → it spreads to (0,1), (0,2), (1,2)
Size = 4

Then from (1,2) → (2,3) through (1,2)'s diagonal neighbor
Size = 5

Output: 5

---

✅ Time Complexity:
O(n * m) — each cell visited once.

✅ Space Complexity:
O(n * m) — visited matrix + recursion stack.
*/

public class LargestRegion {

    // 8 directions: top-left, top, top-right, left, right, bottom-left, bottom, bottom-right
    private static final int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};

    public int findMaxArea(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    int area = dfs(i, j, grid, visited);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private int dfs(int row, int col, int[][] grid, boolean[][] visited) {
        visited[row][col] = true;
        int count = 1;

        for (int d = 0; d < 8; d++) {
            int newRow = row + dRow[d];
            int newCol = col + dCol[d];

            if (isValid(newRow, newCol, grid, visited)) {
                count += dfs(newRow, newCol, grid, visited);
            }
        }
        return count;
    }

    private boolean isValid(int row, int col, int[][] grid, boolean[][] visited) {
        return row >= 0 && row < grid.length &&
                col >= 0 && col < grid[0].length &&
                grid[row][col] == 1 &&
                !visited[row][col];
    }
}

