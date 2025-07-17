package dynamic_programming.dp_ongrids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAGPathCounter {
    static List<List<Integer>> graph;
    static int[] memo;

    public static int countPaths(int src, int dest) {
        if (src == dest) return 1;
        if (memo[src] != -1) return memo[src];

        int totalPaths = 0;
        for (int neighbor : graph.get(src)) {
            totalPaths += countPaths(neighbor, dest);
        }

        memo[src] = totalPaths;
        return totalPaths;
    }

    public static int countDistinctPaths(int V, int[][] edges, int src, int dest) {
        // Build the adjacency list
        graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        // Initialize memoization array
        memo = new int[V];
        Arrays.fill(memo, -1);

        return countPaths(src, dest);
    }

    public static void main(String[] args) {
        int[][] edges1 = {{0, 1}, {0, 3}, {2, 0}, {2, 1}, {1, 3}};
        int V1 = 4, src1 = 2, dest1 = 3;
        System.out.println("Output: " + countDistinctPaths(V1, edges1, src1, dest1)); // Output: 3

        int[][] edges2 = {{0, 1}, {1, 2}, {1, 3}, {2, 3}};
        int V2 = 4, src2 = 0, dest2 = 3;
        System.out.println("Output: " + countDistinctPaths(V2, edges2, src2, dest2)); // Output: 2
    }
}
