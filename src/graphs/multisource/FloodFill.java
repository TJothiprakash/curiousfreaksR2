package graphs.multisource;
/*
✅ Question:
You are given a 2D grid image[][] of size n * m, where each cell contains a color value.
Given a starting pixel (sr, sc) and a new color, perform a flood fill that recolors all
4-directionally connected pixels with the same original color to the new color.

Flood fill changes the color of the starting pixel and all connected pixels (up, down, left, right)
that have the same original color.

Examples:

Input:
image = [[1, 1, 1, 0], [0, 1, 1, 1], [1, 0, 1, 1]], sr = 1, sc = 2, newColor = 2
Output:
[[2, 2, 2, 0], [0, 2, 2, 2], [1, 0, 2, 2]]

Input:
image = [[1, 1, 1], [1, 1, 0], [1, 0, 1]], sr = 1, sc = 1, newColor = 2
Output:
[[2, 2, 2], [2, 2, 0], [2, 0, 1]]

Input:
image = [[0, 1, 0], [0, 1, 0]], sr = 0, sc = 1, newColor = 0
Output:
[[0, 0, 0], [0, 0, 0]]

Constraints:
1 ≤ n ≤ m ≤ 500
0 ≤ image[i][j], newColor ≤ 10
0 ≤ sr < n
0 ≤ sc < m

---

✅ Intuition:
This is a classical DFS problem:
- Starting from the source pixel (sr, sc), recursively change all adjacent pixels (4-directionally)
  that have the same original color.
- Stop when you hit a pixel of a different color or boundary.
- Base case: If newColor is the same as the current color, return immediately (avoid infinite loop).

---

✅ Dry Run:
Input:
image = [[1, 1, 1],
         [1, 1, 0],
         [1, 0, 1]], sr = 1, sc = 1, newColor = 2

Start at (1,1), original color = 1
→ Change (1,1) to 2
→ Recursively change (0,1), (1,0), (1,2), (2,1) if color == 1
→ Changes propagate to all reachable 1's → Output = [[2,2,2],[2,2,0],[2,0,1]]

---

✅ Time Complexity:
O(n * m) — Worst case: all pixels are the same color and visited once.

✅ Space Complexity:
O(n * m) — Recursion stack (DFS) or queue (if BFS is used).
*/

public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];

        // Base case: If color is already newColor, avoid infinite recursion
        if (originalColor == newColor) return image;

        dfs(sr, sc, image, originalColor, newColor);
        return image;
    }

    private void dfs(int row, int col, int[][] image, int originalColor, int newColor) {
        // Out of bounds or color mismatch
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length ||
                image[row][col] != originalColor) {
            return;
        }

        // Change color
        image[row][col] = newColor;

        // Move in 4 directions
        dfs(row - 1, col, image, originalColor, newColor); // up
        dfs(row + 1, col, image, originalColor, newColor); // down
        dfs(row, col - 1, image, originalColor, newColor); // left
        dfs(row, col + 1, image, originalColor, newColor); // right
    }
}
