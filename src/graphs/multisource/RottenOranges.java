package graphs.multisource;
/*
✅ Question:
Given a matrix mat[][] of dimension n x m where:
- 0: Empty cell
- 1: Cell with fresh orange
- 2: Cell with rotten orange

A rotten orange at (i,j) can rot adjacent (up, down, left, right) fresh oranges in 1 unit time.
Return the minimum time required to rot all oranges, or -1 if impossible.

Examples:

Input:  [[0,1,2], [0,1,2], [2,1,1]]
Output: 1

Input:  [[2,2,0,1]]
Output: -1

Input:  [[2,2,2], [0,2,0]]
Output: 0

Constraints:
1 ≤ n, m ≤ 500
mat[i][j] ∈ {0, 1, 2}

---

✅ Intuition:
We simulate the rotting process using **Breadth-First Search (BFS)**:
- Push all rotten oranges (value 2) into a queue with timestamp = 0.
- For each rotten orange in queue, spread rot to 4-directionally adjacent fresh oranges.
- Track time and reduce fresh count.
- If any fresh oranges remain at the end → return -1.

---

✅ Dry Run:
Input:
[[0,1,2],
 [0,1,2],
 [2,1,1]]

Initial Rotten positions: (0,2), (1,2), (2,0)
Time 0: rot (0,1), (1,1), (2,1)
Time 1: rot (2,2)

Fresh = 0 → Return 1

---

✅ Time Complexity:
O(n * m) — each cell visited at most once in BFS.

✅ Space Complexity:
O(n * m) — queue + visited (implicitly).

*/

import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int row, col, time;

    Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

public class RottenOranges {
    public int orangesRotting(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        Queue<Pair> queue = new LinkedList<>();
        int freshCount = 0;
        int maxTime = 0;

        // Step 1: Initialize the queue with all rotten oranges and count fresh ones
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 2) {
                    queue.add(new Pair(i, j, 0));
                } else if (mat[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // 4 directions: up, down, left, right
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        // Step 2: BFS
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            maxTime = Math.max(maxTime, p.time);

            for (int d = 0; d < 4; d++) {
                int newRow = p.row + dRow[d];
                int newCol = p.col + dCol[d];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m &&
                        mat[newRow][newCol] == 1) {
                    mat[newRow][newCol] = 2; // rot it
                    freshCount--;
                    queue.add(new Pair(newRow, newCol, p.time + 1));
                }
            }
        }

        return (freshCount == 0) ? maxTime : -1;
    }
}

