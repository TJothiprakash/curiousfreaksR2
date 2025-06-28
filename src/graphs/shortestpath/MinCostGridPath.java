package graphs.shortestpath;
/*
✅ Question:
You're given an N x N grid where each cell has a positive integer cost.
You need to find the **minimum total cost** path from the **top-left (0,0)** to **bottom-right (n-1,n-1)**.
You can move in 4 directions: up, down, left, right.

---

✅ Examples:

Input:
grid = {
  {9,4,9,9},
  {6,7,6,4},
  {8,3,3,7},
  {7,4,9,10}
}
Output: 43

Explanation:
9 → 4 → 7 → 3 → 3 → 7 → 10 = 43

Input:
grid = {
  {4,4},
  {3,7}
}
Output: 14

Path: 4 → 3 → 7

---

✅ Intuition:
This is a **weighted shortest path** problem on a grid.
- So we use **Dijkstra's Algorithm** with a **priority queue**.
- Each cell is a node, and moving to adjacent cells has the cost of the target cell.
- Start from (0,0) and keep relaxing the neighbors using min-heap (priority queue).
- Stop when we reach (n-1, n-1).

---

✅ Dry Run:

grid = {
  {4, 4},
  {3, 7}
}

Start from (0,0) = 4
Move to (1,0) = 3 → total 7
Then to (1,1) = 7 → total 14 ✅

---

✅ Time Complexity:
O(n² * log(n²)) — Dijkstra with min-heap

✅ Space Complexity:
O(n²) — distance matrix + visited

---

✅ Constraints:
1 ≤ n ≤ 500
1 ≤ grid[i][j] ≤ 500
*/

import java.util.*;

public class MinCostGridPath {

    static class Cell implements Comparable<Cell> {
        int row, col, cost;
        Cell(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        public int compareTo(Cell other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public int minimumCostPath(int[][] grid) {
        int n = grid.length;
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        boolean[][] visited = new boolean[n][n];

        // Min-heap priority queue
        PriorityQueue<Cell> pq = new PriorityQueue<>();

        // Start from top-left
        dist[0][0] = grid[0][0];
        pq.offer(new Cell(0, 0, grid[0][0]));

        // Direction vectors: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            int x = curr.row;
            int y = curr.col;

            // If already visited
            if (visited[x][y]) continue;
            visited[x][y] = true;

            // If destination reached
            if (x == n - 1 && y == n - 1) {
                return dist[x][y];
            }

            // Explore neighbors
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    int newCost = dist[x][y] + grid[nx][ny];
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.offer(new Cell(nx, ny, newCost));
                    }
                }
            }
        }

        // This will never be reached due to constraints
        return -1;
    }
}
