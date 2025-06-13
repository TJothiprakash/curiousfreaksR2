package graphs.bypartite;


import java.util.ArrayList;
import java.util.List;

/*You are given an undirected graph consisting of V vertices and E edges represented
 by a list edges[][], along with an integer m. Your task is to determine whether it is possible
 to color the graph using at most m different colors such that no two adjacent vertices share the same
 color. Return true if the graph can be colored with at most m colors, otherwise return false.

Note: The graph is indexed with 0-based indexing.

Examples✅ Approach
Build an adjacency list from the edge list.

Try coloring each node from 0 to V-1 recursively:

For each vertex, try all colors from 1 to m.

Use a isSafe() function to ensure no adjacent node has the same color.

Backtrack if no color is valid.

:

Input: V = 4, edges[][] = [[0, 1], [1, 3], [2, 3], [3, 0], [0, 2]], m = 3
Output: true
Explanation: It is possible to color the given graph using 3 colors, for example, one of
the possible ways vertices can be colored as follows:

Vertex 0: Color 1
Vertex 1: Color 2
Vertex 2: Color 2
Vertex 3: Color 3
Input: V = 3, edges[][] = [[0, 1], [1, 2], [0, 2]], m = 2
Output: false
Explanation: It is not possible to color the given graph using only 2 colors because
vertices 0, 1, and 2 form a triangle.
Constraints:
1 ≤ V ≤ 10
1 ≤ E = edges.size() ≤ (V*(V-1))/2
0 ≤ edges[i][j] ≤ V-1
1 ≤ m ≤ V

*/
public class MColoring {

    public boolean graphColoring(int V, int[][] edges, int m) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]); // because undirected
        }

        int[] colors = new int[V]; // colors[i] = assigned color to vertex i (0 means unassigned)

        return solve(0, adj, colors, m, V);
    }

    private boolean solve(int node, List<List<Integer>> adj, int[] colors, int m, int V) {
        if (node == V) return true;

        for (int color = 1; color <= m; color++) {
            if (isSafe(node, adj, colors, color)) {
                colors[node] = color;

                if (solve(node + 1, adj, colors, m, V)) return true;

                colors[node] = 0; // backtrack
            }
        }
        return false; // no valid color found
    }

    private boolean isSafe(int node, List<List<Integer>> adj, int[] colors, int color) {
        for (int neighbor : adj.get(node)) {
            if (colors[neighbor] == color) return false;
        }
        return true;
    }

    // Test
    public static void main(String[] args) {
        MColoring solver = new MColoring();

        int V1 = 4, m1 = 3;
        int[][] edges1 = {{0, 1}, {1, 3}, {2, 3}, {3, 0}, {0, 2}};
        System.out.println(solver.graphColoring(V1, edges1, m1)); // true

        int V2 = 3, m2 = 2;
        int[][] edges2 = {{0, 1}, {1, 2}, {0, 2}};
        System.out.println(solver.graphColoring(V2, edges2, m2)); // false
    }
}
