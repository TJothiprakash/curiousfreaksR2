package graphs.other_imp_graph_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
🎯 Problem:
You are given a connected undirected graph with v vertices and e edges.
A **critical connection** (bridge) is an edge, whose removal increases the number
of connected components.

You must return all such **edges (u,v)** in sorted order.

🧠 Intuition:
This is a classic **Tarjan's Bridge-Finding Algorithm**:
- Maintain discovery time and low-link values for each node.
- If during DFS, we find: `low[v] > disc[u]`, then edge (u,v) is a bridge.

This means there’s no way back from `v` to `u` or its ancestors.

🧪 Dry Run:
Input:
V = 4
Edges: (0-1), (1-2), (2-0), (1-3)

Graph:
   0
  / \
 1---2
 |
 3

Remove (1,3) → 3 gets disconnected. So it's a bridge.
*/

public class CriticalConnectionsTarjan {

    private int time;
    private List<List<Integer>> bridges;

    public List<List<Integer>> criticalConnections(int V, List<List<Integer>> adj) {
        int[] disc = new int[V];     // discovery time
        int[] low = new int[V];      // lowest reachable ancestor
        int[] parent = new int[V];   // parent in DFS tree
        boolean[] visited = new boolean[V];
        bridges = new ArrayList<>();

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);
        time = 0;

        for (int u = 0; u < V; u++) {
            if (!visited[u]) {
                dfs(u, adj, visited, disc, low, parent);
            }
        }

        // Sort each edge, then sort entire list
        for (List<Integer> edge : bridges)
            edge.sort(Comparator.naturalOrder());
        bridges.sort((a, b) -> {
            if (!a.get(0).equals(b.get(0))) return a.get(0) - b.get(0);
            return a.get(1) - b.get(1);
        });

        return bridges;
    }

    private void dfs(int u, List<List<Integer>> adj, boolean[] visited,
                     int[] disc, int[] low, int[] parent) {

        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                dfs(v, adj, visited, disc, low, parent);

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    bridges.add(List.of(u, v));
                }

            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]); // Back edge
            }
        }
    }

    // 🔍 Sample Test Driver
    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 0},
                {1, 3}
        };

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        CriticalConnectionsTarjan solver = new CriticalConnectionsTarjan();
        List<List<Integer>> result = solver.criticalConnections(V, adj);

        System.out.println("🔗 Critical Connections (Bridges):");
        for (List<Integer> edge : result) {
            System.out.println(edge.get(0) + " " + edge.get(1));
        }
    }
}

/*
📊 Time Complexity: O(V + E)
💾 Space Complexity: O(V + E)


Key Notes:
- Single DFS run is enough.
- Condition for bridge: low[v] > disc[u]
- Use parent[] to avoid backtracking to parent.

JP — this file includes everything: problem, intuition, dry run, code, and test.
No external dependencies.

Ready for next problem or merge with articulation points? 🔥
*/
