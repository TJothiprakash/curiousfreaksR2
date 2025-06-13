package graphs.bypartite;

import java.util.*;
/*To check if a graph is bipartite, we can use BFS coloring:

✅ Key Idea:
Assign colors 0 and 1 to each vertex such that no two adjacent vertices have the same color.

If during BFS traversal, a neighbor has the same color, the graph is not bipartite.*/
public class BipartiteCheck {

    public boolean isBipartite(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]); // Undirected graph
        }

        int[] color = new int[V];
        Arrays.fill(color, -1); // -1 means uncolored

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!bfs(i, adj, color)) return false;
            }
        }

        return true;
    }

    private boolean bfs(int src, List<List<Integer>> adj, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        color[src] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (color[v] == -1) {
                    color[v] = 1 - color[u];
                    q.offer(v);
                } else if (color[v] == color[u]) {
                    return false; // adjacent same color
                }
            }
        }

        return true;
    }

    // Test
    public static void main(String[] args) {
        BipartiteCheck checker = new BipartiteCheck();

        int V1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}};
        System.out.println(checker.isBipartite(V1, edges1)); // true

        int V2 = 4;
        int[][] edges2 = {{0, 3}, {1, 2}, {3, 2}, {0, 2}};
        System.out.println(checker.isBipartite(V2, edges2)); // false
    }
}

/*
🧠 Time and Space Complexity
Time: O(V + E) – each node and edge is visited once.

Space: O(V + E) – adjacency list and color array.

*/