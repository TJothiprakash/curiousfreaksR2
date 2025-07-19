package graphs.shortestpath;

import java.io.IOException;
import java.util.*;

class Main12 {

    public static void main(String[] args) throws IOException {
        int n = 8, m = 10;
        int[][] edge = {{5, 32}, {5, 2}, {32, 335}, {335, 2}, {6, 754}, {754, 74}, {6, 7545}, {7545, 74}, {2, 6}, {7545, 335}};
        int[] nodeValues = {5, 32, 335, 2, 6, 74, 754, 7545}; // Custom node values

        ShortestPathUsingRandomNodeValuesUnit obj = new ShortestPathUsingRandomNodeValuesUnit();
        int[] res = obj.shortestPath(edge, n, m, 5, nodeValues);

        for (int i = 0; i < n; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
}

public class ShortestPathUsingRandomNodeValuesUnit {

    public int[] shortestPath(int[][] edges, int n, int m, int src, int[] nodeValues) {
        // Map for storing original node values and their corresponding indices
        HashMap<Integer, Integer> nodeToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodeToIndex.put(nodeValues[i], i); // Mapping node value to its index
        }

        // Create an adjacency list using the mapped indices
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = nodeToIndex.get(edges[i][0]);
            int v = nodeToIndex.get(edges[i][1]);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Distance array initialized with a large value
        int[] dist = new int[n];
        Arrays.fill(dist, (int) 1e9);
        dist[nodeToIndex.get(src)] = 0;

        // BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(nodeToIndex.get(src));

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : adj.get(node)) {
                if (dist[node] + 1 < dist[neighbor]) {
                    dist[neighbor] = dist[node] + 1;
                    q.add(neighbor);
                }
            }
        }

        // Set unreachable nodes to -1
        for (int i = 0; i < n; i++) {
            if (dist[i] == (int) 1e9) {
                dist[i] = -1;
            }
        }

        return dist;
    }
}
