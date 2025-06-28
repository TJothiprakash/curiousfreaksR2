package graphs.shortestpath;

/*
✅ Question:
Given an `n x n` grid filled with values:
- 0 → Wall (not traversable)
- 1 → Source
- 2 → Destination
- 3 → Blank (can be traversed)

You can move in 4 directions: up, down, left, right.
Return 1 if a path exists from the source to the destination, else return 0.

---

✅ Examples:

Input:
grid = {{3,0,3,0,0},
        {3,0,0,0,3},
        {3,3,3,3,3},
        {0,2,3,0,0},
        {3,0,0,1,3}}

Output: 0

Explanation: No path exists from (4,3) to (3,1)

---

Input:
grid = {{1,3},
        {3,2}}

Output: 1

Explanation: Path exists from (0,0) to (1,1)

---

✅ Intuition:
- Traverse the grid to find the source cell.
- From the source, do **DFS/BFS traversal**, only visiting:
  - Valid grid cells
  - Not walls (0)
- If during traversal we reach the destination (2), return true.
- Otherwise, return false.

---

✅ Dry Run:

Input:
1 3
3 2

Start from (0,0) → move to (1,0) → move to (1,1)
Found 2 → Return 1

---

✅ Time Complexity:
O(n²) — We may visit each cell once in worst case.

✅ Space Complexity:
O(n²) — Visited matrix + stack/queue.

---

✅ Constraints:
1 ≤ n ≤ 500
*/

public class GridPathFinder {

    public boolean isPath(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        int srcRow = -1, srcCol = -1;

        // Step 1: Find the source (value == 1)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    srcRow = i;
                    srcCol = j;
                    break;
                }
            }
        }

        // Step 2: Do DFS from source
        return dfs(srcRow, srcCol, grid, visited);
    }

    private boolean dfs(int row, int col, int[][] grid, boolean[][] visited) {
        int n = grid.length;

        // Out of bounds or wall or visited
        if (row < 0 || col < 0 || row >= n || col >= n ||
                grid[row][col] == 0 || visited[row][col]) {
            return false;
        }

        // If destination is found
        if (grid[row][col] == 2) return true;

        visited[row][col] = true;
/*
      boolean f1=  dfs(row - 1, col, grid, visited) ; // up
               boolean f2 = dfs(row + 1, col, grid, visited); // down
                boolean f3 =dfs(row, col - 1, grid, visited) ; // left
                boolean f4 =dfs(row, col + 1, grid, visited);

        // Explore 4 directions
        return f1 || f2 || f3 || f4;*/
        // Explore 4 directions
        return dfs(row - 1, col, grid, visited) || // up
                dfs(row + 1, col, grid, visited) || // down
                dfs(row, col - 1, grid, visited) || // left
                dfs(row, col + 1, grid, visited);   // right
    }

    // Optional wrapper for return type as int if needed
    public int is_Possible(int[][] grid) {
        return isPath(grid) ? 1 : 0;
    }
}
