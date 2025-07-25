package graphs;

import java.util.*;

class GraphOtherImpProblems {

    // This class can be used to implement various graph-related problems
    // that are not covered by the standard algorithms in the Graph class.

    // Example methods could include:
    // - Finding strongly connected components
    // - Detecting cycles in directed graphs
    // - Topological sorting
    // - Shortest path algorithms (Dijkstra's, Bellman-Ford)
    public static void main(String[] args) {

    }

    int timer = 1;

    public List<List<Integer>> tarjanAlgorithm(int n, List<List<Integer>> edges) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        for (List<Integer> edge : edges) {
            int u = edge.get(0), v = edge.get(1);
            adjList.get(u).add(v);
            adjList.get(v).add(u); // Undirected graph
        }

        int[] visited = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                dfs(i, -1, visited, tin, low, adjList, result);
            }
        }

        return result;
    }

    public void dfs(int u, int parent, int[] visited, int[] tin, int[] low,
            List<List<Integer>> adjList, List<List<Integer>> result) {

        visited[u] = 1;
        tin[u] = low[u] = timer++;

        for (int v : adjList.get(u)) {
            if (v == parent)
                continue;

            if (visited[v] == 0) {
                dfs(v, u, visited, tin, low, adjList, result);
                low[u] = Math.min(low[u], low[v]);

                // ✔️ Check for bridge
                if (low[v] > tin[u]) {
                    result.add(Arrays.asList(u, v));
                }
            } else {
                // ✔️ Back-edge
                low[u] = Math.min(low[u], tin[v]);
            }
        }
    }

    //
    /*
     * Articulation Point I in a Graph
     * You are given an undirected connected graph with V vertices and adjacency
     * Given an undirected connected graph with V vertices and adjacency list adj.
     * You are required to find all the vertices removing which (and edges through
     * it) disconnects the graph into 2 or more components and return it in sorted
     * manner.
     * Note: Indexing is zero-based i.e nodes numbering from (0 to V-1). There might
     * be loops present in the graph.
     * 
     * Example 1:
     * 
     * Input:
     * 
     * Output:{1,4}
     * Explanation: Removing the vertex 1 will
     * discconect the graph as-
     * 
     * Removing the vertex 4 will disconnect the
     * graph as-
     * 
     * 
     * 
     * Your Task:
     * You don't need to read or print anything. Your task is to complete the
     * function articulationPoints() which takes V and adj as input parameters and
     * returns a list containing all the vertices removing which turn the graph into
     * two or more disconnected components in sorted order. If there are no such
     * vertices then returns a list containing -1.
     * 
     * 
     * Expected Time Complexity: O(V + E)
     * Expected Auxiliary Space: O(V)
     * 
     * 
     * Constraints:
     * 1 ≤ V ≤ 105
     */
    private int counter = 1;

    public List<Integer> articulationPoints(int V, List<List<Integer>> adj) {
        int[] visited = new int[V];
        int[] tin = new int[V];
        int[] low = new int[V];

        Set<Integer> articulationPoints = new HashSet<>();

        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfs(i, -1, visited, tin, low, adj, articulationPoints);
            }
        }

        if (articulationPoints.isEmpty())
            return List.of(-1);

        List<Integer> result = new ArrayList<>(articulationPoints);
        Collections.sort(result);
        return result;
    }

    private void dfs(int u, int parent, int[] visited, int[] tin, int[] low,
            List<List<Integer>> adj, Set<Integer> articulationPoints) {

        visited[u] = 1;
        tin[u] = low[u] = counter++;
        int childCount = 0;

        for (int v : adj.get(u)) {
            if (v == parent)
                continue;

            if (visited[v] == 0) {
                dfs(v, u, visited, tin, low, adj, articulationPoints);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] >= tin[u] && parent != -1) {
                    articulationPoints.add(u);
                }
                childCount++;
            } else {
                low[u] = Math.min(low[u], tin[v]);
            }
        }

        if (parent == -1 && childCount > 1) {
            articulationPoints.add(u);
        }
    }
}
