package graphs.shortestpath;
import java.util.*;

/*
✅ QUESTION:
You're given a directed weighted graph as a 2D matrix `dist[][]` of size n x n.

- `dist[i][j]` is the weight from node i to j.
- If there's no edge between i and j, then `dist[i][j] = 108` (considered as ∞).
- Negative weights are allowed.
- No negative weight cycles exist.

🔔 Update the `dist[][]` matrix in-place to store the shortest distance from i to j.

---

Example 1:
Input:
dist = [
  [0, 4, 108, 5, 108],
  [108, 0, 1, 108, 6],
  [2, 108, 0, 3, 108],
  [108, 108, 1, 0, 2],
  [1, 108, 108, 4, 0]
]

Output:
[
  [0, 4, 5, 5, 7],
  [3, 0, 1, 4, 6],
  [2, 6, 0, 3, 5],
  [3, 7, 1, 0, 2],
  [1, 5, 5, 4, 0]
]
*/

/*
💡 INTUITION (Floyd-Warshall):
- Try each node `k` as an intermediate node between every pair (i, j)
- If using `k` improves the path from `i` to `j`, update it

Formally:
  for all i, j:
    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

⚠️ Make sure to avoid integer overflow by checking dist[i][k] and dist[k][j] are not INF
*/

public class FloydWarshall {

    static final int INF = 108;

    public static void shortest_distance(int[][] dist) {
        int n = dist.length;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }

    // Utility function to print a matrix
    public static void printMatrix(int[][] dist) {
        for (int[] row : dist) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // 🔍 TEST CASES
    public static void main(String[] args) {
        System.out.println("Test Case 1:");
        int[][] dist1 = {
                {0, 4, 108, 5, 108},
                {108, 0, 1, 108, 6},
                {2, 108, 0, 3, 108},
                {108, 108, 1, 0, 2},
                {1, 108, 108, 4, 0}
        };
        shortest_distance(dist1);
        printMatrix(dist1);

        System.out.println("\nTest Case 2:");
        int[][] dist2 = {
                {0, -1, 2},
                {1, 0, 108},
                {3, 1, 0}
        };
        shortest_distance(dist2);
        printMatrix(dist2);
    }
}

/*
🕰️ TIME & SPACE COMPLEXITY

Time:  O(N^3) — Three nested loops
Space: O(1) — In-place update (no extra space)

✨ Handles:
- Negative weights
- Multiple paths
- ∞ represented by 108 (but any large constant works)

⚠️ Does not work if negative weight **cycles** exist
*/
