package graphs.shortestpath;
/*
🧩 QUESTION:
Given a square chessboard of size (n x n), and the initial position and target position of a knight,
find the **minimum number of steps** the knight will take to reach the target.

Note:
- Knight moves in 8 possible "L" directions.
- Positions are 1-based indexed.

Examples:
Input: n = 3, knightPos = [3, 3], targetPos = [1, 2] ➝ Output: 1
Input: n = 6, knightPos = [4, 5], targetPos = [1, 1] ➝ Output: 3
*/

/*
💡 INTUITION:
This is a shortest path problem in an **unweighted grid**, and knight moves to exactly 8 positions.

Use **BFS (Breadth-First Search)** to explore all reachable positions:
- Each move = 1 cost
- Visit each valid position once (with a visited[][] grid)
- As soon as we reach the target, return the step count
*/

/*
🧪 DRY RUN: (Commented)
Input: n = 6, knightPos = [4, 5], targetPos = [1, 1]

0-based: start = (3, 4), target = (0, 0)

Level 0: (3, 4)
Level 1: (5, 3), (5, 5), (1, 3), (1, 5), (2, 2), (4, 2)
Level 2: explore each of the above, eventually reach (0, 0)
Minimum steps = 3
*/

/*
⏱️ TIME AND SPACE COMPLEXITY:
Time  = O(N^2) in the worst case (visiting every cell)
Space = O(N^2) for visited matrix and BFS queue
*/

import java.util.LinkedList;
import java.util.Queue;

public class KnightMinSteps {

    static class Cell {
        int x, y, steps;

        Cell(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }

    public static int minKnightMoves(int n, int[] knightPos, int[] targetPos) {
        int startX = knightPos[0] - 1;
        int startY = knightPos[1] - 1;
        int endX = targetPos[0] - 1;
        int endY = targetPos[1] - 1;

        System.out.println("Start Position (0-based): (" + startX + ", " + startY + ")");
        System.out.println("Target Position (0-based): (" + endX + ", " + endY + ")");

        if (startX == endX && startY == endY) {
            System.out.println("Already at the target!");
            return 0;
        }

        // Knight's 8 directions
        int[] dx = {2, 2, -2, -2, 1, -1, 1, -1};
        int[] dy = {1, -1, 1, -1, 2, 2, -2, -2};

        boolean[][] visited = new boolean[n][n];
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(startX, startY, 0));
        visited[startX][startY] = true;
        System.out.println("BFS Started...");

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            System.out.println("Current Cell: (" + current.x + ", " + current.y + "), Steps = " + current.steps);

            for (int i = 0; i < 8; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                // Check boundaries
                if (newX >= 0 && newY >= 0 && newX < n && newY < n) {
                    System.out.println("  Checking Move: → (" + newX + ", " + newY + ")");

                    if (newX == endX && newY == endY) {
                        System.out.println("  ✅ Target reached at (" + newX + ", " + newY + ") in " + (current.steps + 1) + " steps");
                        return current.steps + 1;
                    }

                    if (!visited[newX][newY]) {
                        visited[newX][newY] = true;
                        queue.offer(new Cell(newX, newY, current.steps + 1));
                        System.out.println("  ➕ Enqueued: (" + newX + ", " + newY + "), Steps = " + (current.steps + 1));
                    } else {
                        System.out.println("  ⛔ Already visited: (" + newX + ", " + newY + ")");
                    }
                } else {
                    System.out.println("  ❌ Out of bounds: (" + newX + ", " + newY + ")");
                }
            }
        }

        System.out.println("Target not reachable.");
        return -1;
    }

    public static void main(String[] args) {
        int n1 = 3;
        int[] knightPos1 = {3, 3};
        int[] targetPos1 = {1, 2};
        System.out.println("Minimum steps (Test 1): " + minKnightMoves(n1, knightPos1, targetPos1)); // Output: 1

        int n2 = 6;
        int[] knightPos2 = {4, 5};
        int[] targetPos2 = {1, 1};
        System.out.println("Minimum steps (Test 2): " + minKnightMoves(n2, knightPos2, targetPos2)); // Output: 3
    }
}
