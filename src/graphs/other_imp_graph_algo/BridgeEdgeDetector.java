package graphs.other_imp_graph_algo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BridgeEdgeDetector {

    /*
     🔍 Problem:
     Given an undirected graph with V vertices and E edges, determine whether the edge (c, d)
     is a bridge. An edge is a bridge if removing it increases the number of connected components.

     🔑 Intuition:
     - Temporarily remove the edge (c, d)
     - Run DFS from node `c`
     - If node `d` is no longer reachable, the edge (c, d) is a bridge
     - Otherwise, it's not

     ✅ Dry Run:
     Example: V = 4, edges = [[0,1], [1,2], [2,3]], c = 1, d = 2
     Remove (1,2), do DFS from 1 → can’t reach 2 → It is a bridge

     Example: V = 5, edges = [[0,1], [0,3], [1,2], [2,0], [3,4]], c = 0, d = 2
     Remove (0,2), DFS from 0 → can still reach 2 → Not a bridge
    */

    public static boolean isBridge(int V, @NotNull List<List<Integer>> adj, int c, int d) {
        // Remove the edge c-d temporarily
        adj.get(c).remove(Integer.valueOf(d));
        adj.get(d).remove(Integer.valueOf(c));

        boolean[] visited = new boolean[V];
        dfs(c, adj, visited);

        // Restore the edge (optional if graph is reused later)
        adj.get(c).add(d);
        adj.get(d).add(c);

        // If d is unreachable after removing the edge, it is a bridge
        return !visited[d];
    }

    private static void dfs(int node, @NotNull List<List<Integer>> adj, boolean @NotNull [] visited) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }

    public static void main(String[] args) {
        // Test case 1: Should return true
        int V1 = 4;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}};
        int c1 = 1, d1 = 2;
        System.out.println("Is edge (" + c1 + "," + d1 + ") a bridge? " +
                solveBridge(V1, edges1, c1, d1)); // true

        // Test case 2: Should return false
        int V2 = 5;
        int[][] edges2 = {{0, 1}, {0, 3}, {1, 2}, {2, 0}, {3, 4}};
        int c2 = 0, d2 = 2;
        System.out.println("Is edge (" + c2 + "," + d2 + ") a bridge? " +
                solveBridge(V2, edges2, c2, d2)); // false
    }

    public static boolean solveBridge(int V, int[][] edges, int c, int d) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return isBridge(V, adj, c, d);
    }

    /*
     📊 Time Complexity: O(V + E)
     - DFS visits each node and edge once

     🧠 Space Complexity: O(V + E)
     - Adjacency list + visited array
    */
}
