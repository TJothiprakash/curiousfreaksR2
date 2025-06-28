package graphs.shortestpath;
/*
✅ Question:
You are given a 2D grid (matrix) representing a hospital of size R x C:
- 0 = Empty ward
- 1 = Uninfected patient
- 2 = Infected patient

Each minute, an infected patient at (i,j) can infect uninfected neighbors:
(i+1,j), (i-1,j), (i,j+1), (i,j-1)

You must return the **minimum number of minutes** required to infect **all patients**.
Return -1 if it's **impossible** to infect all.

---

✅ Examples:

Input:
3 5
2 1 0 2 1
1 0 1 2 1
1 0 0 2 1

Output: 2

Explanation:
- Minute 0: 4 infected at [0,0], [0,3], [1,3], [2,3]
- Minute 1: infects 6 others
- Minute 2: infects remaining
- All infected in 2 units

Input:
3 5
2 1 0 2 1
0 0 1 2 1
1 0 0 2 1

Output: -1

Explanation:
Some uninfected cells can never be reached.

---

✅ Intuition:

- It's a **multi-source BFS**: we start from all infected cells (`2`) and spread outward.
- For each minute, we infect neighboring `1`s.
- Track how many `1`s are infected. If all are infected → return time.
- If after BFS any `1`s remain → return -1.

---

✅ Dry Run:

Grid:
2 1
1 1

Start BFS from (0,0)
Minute 1: (0,1), (1,0)
Minute 2: (1,1)
Done in 2 units.

---

✅ Time Complexity:
O(R * C) — every cell visited once at most

✅ Space Complexity:
O(R * C) — for queue and visited tracking

---

✅ Constraints:
1 ≤ R,C ≤ 1000
0 ≤ mat[i][j] ≤ 2
*/

import java.util.LinkedList;
import java.util.Queue;

public class InfectAllPatients {

    static class Cell {
        int row, col, time;

        Cell(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    public int helpaterp(int[][] hospital) {
        int r = hospital.length;
        int c = hospital[0].length;

        Queue<Cell> q = new LinkedList<>();
        boolean[][] visited = new boolean[r][c];
        int totalUninfected = 0;

        // Directions: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Step 1: Push all initially infected patients into queue
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (hospital[i][j] == 2) {
                    q.offer(new Cell(i, j, 0));
                    visited[i][j] = true;
                } else if (hospital[i][j] == 1) {
                    totalUninfected++;
                }
            }
        }

        int infectedCount = 0;
        int time = 0;

        // Step 2: Multi-source BFS
        while (!q.isEmpty()) {
            Cell current = q.poll();
            int x = current.row;
            int y = current.col;
            time = current.time;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c &&
                        hospital[nx][ny] == 1 && !visited[nx][ny]) {

                    visited[nx][ny] = true;
                    q.offer(new Cell(nx, ny, time + 1));
                    infectedCount++;
                }
            }
        }

        // Step 3: Check if all uninfected were infected
        if (infectedCount == totalUninfected) {
            return time;
        } else {
            return -1;
        }
    }
}
