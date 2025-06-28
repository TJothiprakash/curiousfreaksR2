package graphs.shortestpath;
/*
🧩 QUESTION:
Given a 2D binary matrix A of size N x M, find the minimum number of steps from (0,0) to (X,Y)
You can only move up, down, left, or right and only through cells with value 1.

Return -1 if:
- A[0][0] == 0 (start blocked)
- Target is unreachable

📥 Example 1:
Input:
A = [
  [1,0,0,0],
  [1,1,0,1],
  [0,1,1,1]
], X=2, Y=3
Output: 5

Path: (0,0) → (1,0) → (1,1) → (2,1) → (2,2) → (2,3)

📥 Example 2:
A = [
  [1,1,1,1],
  [0,0,0,1],
  [0,0,0,1]
], X=0, Y=3
Output: 3
Path: (0,0) → (0,1) → (0,2) → (0,3)
*/

/*
💡 INTUITION:
This is a classic **shortest path in unweighted grid** problem using **BFS**:
- All moves cost 1
- Explore 4 directions (up/down/left/right)
- Track visited cells to avoid cycles
- Stop as soon as (X,Y) is reached
*/

/*
🧪 DRY RUN (Example 1):
A = [
  [1,0,0,0],
  [1,1,0,1],
  [0,1,1,1]
]
X = 2, Y = 3

Start at (0,0)
Level 1: (1,0)
Level 2: (1,1)
Level 3: (2,1)
Level 4: (2,2)
Level 5: (2,3) ✅ reached → answer = 5
*/

/*
⏱️ TIME & SPACE COMPLEXITY:
Time: O(N * M) - we may visit each cell once
Space: O(N * M) - visited matrix + BFS queue
*/

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBinaryMatrix {

    static class Cell {
        int x, y, steps;

        Cell(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }

    public static int shortestDistance(int N, int M, int X, int Y, int[][] A) {
        // Base case: starting cell is blocked
        if (A[0][0] == 0) return -1;

        // If target is same as source
        if (X == 0 && Y == 0) return 0;

        // Directions: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        boolean[][] visited = new boolean[N][M];
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Cell curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];

                if (newX == X && newY == Y && A[newX][newY] == 1) {
                    return curr.steps + 1;
                }

                if (newX >= 0 && newY >= 0 && newX < N && newY < M &&
                        A[newX][newY] == 1 && !visited[newX][newY]) {

                    visited[newX][newY] = true;
                    queue.offer(new Cell(newX, newY, curr.steps + 1));
                }
            }
        }

        return -1; // Target not reachable
    }

    // 🔹 Driver Code
    public static void main(String[] args) {
        int[][] A1 = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 1, 1}
        };
        int result1 = shortestDistance(3, 4, 2, 3, A1);
        System.out.println("Output (Test 1): " + result1); // 5

        int[][] A2 = {
                {1, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1}
        };
        int result2 = shortestDistance(3, 4, 0, 3, A2);
        System.out.println("Output (Test 2): " + result2); // 3
    }
}
