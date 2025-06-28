package graphs.toposort;
import java.util.*;

/*
✅ QUESTION:
Given a Directed Acyclic Graph (DAG) with V vertices and E edges,
 return a topological sort using **DFS**.

Each edge edges[i] = [u, v] represents u → v.
Topological sort ensures u appears before v in the ordering.
Return any valid topological ordering.

---

Example:
Input: V = 4, E = 3, edges = [[3, 0], [1, 0], [2, 0]]
Output: [3, 2, 1, 0] (or any valid topo order)

*/

public class TopologicalSortDFS {

    public static List<Integer> topoSortDFS(int V, int[][] edges) {
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
        }

        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!stack.isEmpty()) topoOrder.add(stack.pop());
        return topoOrder;
    }

    private static void dfs(int node, List<Integer>[] adj, boolean[] visited, Deque<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, stack);
            }
        }
        stack.push(node); // Post-order push
    }

    // ✅ Utility: Validate result (used in test only)
    public static boolean isValidTopologicalOrder(int V, int[][] edges, List<Integer> order) {
        if (order.size() != V) return false;
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < order.size(); i++) position.put(order.get(i), i);

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (position.get(u) >= position.get(v)) return false;
        }
        return true;
    }

    // 🔍 TEST CASES
    public static void main(String[] args) {
        int V1 = 4;
        int[][] edges1 = {{3, 0}, {1, 0}, {2, 0}};
        List<Integer> result1 = topoSortDFS(V1, edges1);
        System.out.println("DFS Topo Order 1: " + result1);
        System.out.println("Valid: " + isValidTopologicalOrder(V1, edges1, result1));

        int V2 = 6;
        int[][] edges2 = {{1, 3}, {2, 3}, {4, 1}, {4, 0}, {5, 0}, {5, 2}};
        List<Integer> result2 = topoSortDFS(V2, edges2);
        System.out.println("\nDFS Topo Order 2: " + result2);
        System.out.println("Valid: " + isValidTopologicalOrder(V2, edges2, result2));
    }
}

/*
🕰️ TIME & SPACE COMPLEXITY

Time:  O(V + E)
Space: O(V + E) — Adjacency list + visited[] + recursion stack

✨ DFS version is great when:
- You want a recursive approach
- You prefer post-order traversal
- Stack is acceptable for holding result
*/
