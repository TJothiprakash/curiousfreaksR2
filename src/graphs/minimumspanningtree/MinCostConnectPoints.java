package graphs.minimumspanningtree;
/*
✅ PROBLEM: Min Cost to Connect All Points (Leetcode 1584)

You are given an array of points in 2D plane. Each point is represented as points[i] = [xi, yi].
The cost to connect two points is the Manhattan Distance:
    |xi - xj| + |yi - yj|

Your task is to return the **minimum cost** to connect all the points such that there is exactly **one simple path** between any two points.

-------------------------------------------------------
💡 INTUITION:
- We need to build a **Minimum Spanning Tree (MST)**.
- The graph is **complete**: each point can connect to every other.
- Instead of storing all edges (which is O(N^2)), we:
    - Use **Prim’s Algorithm**
    - Dynamically compute cost from current node to all unvisited nodes.

-------------------------------------------------------
🧠 DRY RUN:

Input: [[0,0],[2,2],[3,10],[5,2],[7,0]]

Use Prim’s:
- Start at point 0
- Greedily pick the nearest unvisited point by Manhattan distance
- Connect all with minimum total cost

Expected Output: 20

-------------------------------------------------------
*/

import java.util.Arrays;

public class MinCostConnectPoints {

    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        // Start from point 0
        minDist[0] = 0;

        int totalCost = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            // Step 1: Find the unvisited node with minimum edge weight
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || minDist[j] < minDist[u])) {
                    u = j;
                }
            }

            visited[u] = true;
            totalCost += minDist[u];
            System.out.println("Added point " + u + " with cost " + minDist[u]);

            // Step 2: Update minDist for unvisited neighbors
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int cost = manhattan(points[u], points[v]);
                    if (cost < minDist[v]) {
                        minDist[v] = cost;
                        System.out.println(" → Updated dist for point " + v + " to " + cost);
                    }
                }
            }
        }

        return totalCost;
    }

    private static int manhattan(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public static void main(String[] args) {
        int[][] points1 = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println("Test 1 Output: " + minCostConnectPoints(points1)); // Expected: 20

        int[][] points2 = {{3, 12}, {-2, 5}, {-4, 1}};
        System.out.println("Test 2 Output: " + minCostConnectPoints(points2)); // Expected: 18

        int[][] points3 = {{1, 1}, {3, 3}, {4, 4}, {0, 0}};
        System.out.println("Test 3 Output: " + minCostConnectPoints(points3)); // Expected: 10
    }
}

/*
-------------------------------------------------------
⏱️ TIME COMPLEXITY:
- O(N^2) for scanning all unvisited points for each point
- O(N^2) updates over n nodes

🧠 SPACE COMPLEXITY:
- O(N) for visited and minDist arrays

Note: This is optimal for N ≤ 1000

-------------------------------------------------------
*/
