package graphs.other_imp_graph_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
🎯 Problem: Articulation Points in Graph
Given a connected undirected graph, return all vertices (articulation points)
removing which increases the number of connected components.

📥 Input: Number of vertices V, and adjacency list adj.
📤 Output: List of articulation points in sorted order. If none, return [-1].

🧠 Intuition:
Use Tarjan's Algorithm with:
- disc[]: discovery time of each vertex
- low[]: lowest reachable ancestor from this vertex
- parent[]: DFS parent of each node
- isAP[]: boolean array to track articulation points

🧪 Dry Run:
Graph:
     0
    / \
   1   2
  / \
 3   4

Removing 1 disconnects 3 and 4 ⇒ articulation point
Removing 4 cuts its only connection ⇒ articulation point
Output: [1, 4]
*/

public class ArticulationPointsTarjan {

    private int time;

    public List<Integer> articulationPoints(int V, List<List<Integer>> adj) {
        int[] disc = new int[V];     // discovery time
        int[] low = new int[V];      // low-link value
        int[] parent = new int[V];   // DFS parent
        boolean[] visited = new boolean[V];
        boolean[] isAP = new boolean[V]; // is articulation point

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        time = 0;

        for (int u = 0; u < V; u++) {
            if (!visited[u]) {
                dfs(u, adj, visited, disc, low, parent, isAP);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isAP[i]) result.add(i);
        }

        return result.isEmpty() ? List.of(-1) : result;
    }

    private void dfs(int u, List<List<Integer>> adj, boolean[] visited,
                     int[] disc, int[] low, int[] parent, boolean[] isAP) {

        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                dfs(v, adj, visited, disc, low, parent, isAP);

                // Update low value
                low[u] = Math.min(low[u], low[v]);

                // ✅ Rule 2: Non-root with low[v] >= disc[u]
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    isAP[u] = true;
                }

                // ✅ Rule 1: Root with 2+ children
                if (parent[u] == -1 && children > 1) {
                    isAP[u] = true;
                }

            } else if (v != parent[u]) {
                // Update low[u] for back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // 🔍 Sample Test Driver
    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Sample graph
        adj.get(0).addAll(List.of(1, 2));
        adj.get(1).addAll(List.of(0, 3, 4));
        adj.get(2).add(0);
        adj.get(3).add(1);
        adj.get(4).add(1);

        ArticulationPointsTarjan apFinder = new ArticulationPointsTarjan();
        List<Integer> result = apFinder.articulationPoints(V, adj);

        System.out.println("Articulation Points: " + result);  // Output: [1, 4]
    }
}

/*
🧠 Summary:
- Use DFS and track discovery and low values.
- Special handling for root node with 2+ children.
- Use parent[] to identify tree structure and avoid revisiting parent via back edge.

⏱️ Time Complexity: O(V + E)
💾 Space Complexity: O(V)
*/
