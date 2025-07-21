package graphs.july_21;


import graphs.commons.disjointset.DisjointSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NetworkConnectionRepair {

    public static void main(String[] args) {
        int n1 = 4;
        int[][] connections1 = {{0, 1}, {0, 2}, {1, 2}};
        System.out.println("Kruskal’s: " + makeConnectedKruskal(n1, connections1));
        System.out.println("Prim’s:    " + makeConnectedPrim(n1, connections1));

        int n2 = 6;
        int[][] connections2 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        System.out.println("Kruskal’s: " + makeConnectedKruskal(n2, connections2));
        System.out.println("Prim’s:    " + makeConnectedPrim(n2, connections2));

        int n3 = 6;
        int[][] connections3 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}};
        System.out.println("Kruskal’s: " + makeConnectedKruskal(n3, connections3));
        System.out.println("Prim’s:    " + makeConnectedPrim(n3, connections3));
    }

    // ✅ KRUSKAL’S METHOD
    public static int makeConnectedKruskal(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;

        DisjointSet ds = new DisjointSet(n);
        int components = n;

        for (int[] conn : connections) {
            int u = conn[0], v = conn[1];
            if (ds.union(u, v)) {
                components--; // merged components
            }
        }

        return components - 1;
    }

    // ✅ PRIM’S METHOD
    public static int makeConnectedPrim(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int[] conn : connections) {
            adj.get(conn[0]).add(conn[1]);
            adj.get(conn[1]).add(conn[0]);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, adj, visited);
            }
        }

        return components - 1;
    }

    private static void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
    }
}
