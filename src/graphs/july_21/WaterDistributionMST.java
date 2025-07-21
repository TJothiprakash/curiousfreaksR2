package graphs.july_21;

import graphs.commons.disjointset.DisjointSet;

import java.util.*;

public class WaterDistributionMST {

    static class WaterEdge extends Edge {
        WaterEdge(int u, int v, int weight) {
            super(u, v, weight);
        }
    }

    static class WaterUnionFind extends DisjointSet {
        WaterUnionFind(int n) {
            super(n);
        }
    }

    // Kruskal's Algorithm
    public static int minCostToSupplyWaterKruskal(int n, int[] wells, int[][] pipes) {
        List<Edge> edges = new ArrayList<>();

        // Add virtual edges from node 0 to each house
        for (int i = 0; i < n; i++) {
            edges.add(new WaterEdge(0, i + 1, wells[i]));
        }

        // Add actual pipe edges
        for (int[] pipe : pipes) {
            edges.add(new WaterEdge(pipe[0], pipe[1], pipe[2]));
        }

        Collections.sort(edges);
        WaterUnionFind uf = new WaterUnionFind(n + 1);
        int totalCost = 0;

        for (Edge e : edges) {
            if (uf.union(e.u, e.v)) {
                totalCost += e.weight;
            }
        }

        return totalCost;
    }

    // Prim's Algorithm
    public static int minCostToSupplyWaterPrim(int n, int[] wells, int[][] pipes) {
        Map<Integer, List<int[]>> adj = new HashMap<>();

        // Initialize adjacency list
        for (int i = 1; i <= n; i++) adj.put(i, new ArrayList<>());

        for (int[] pipe : pipes) {
            adj.get(pipe[0]).add(new int[]{pipe[1], pipe[2]});
            adj.get(pipe[1]).add(new int[]{pipe[0], pipe[2]});
        }

        // Add well options as edges from node 0
        for (int i = 0; i < n; i++) {
            adj.get(i + 1).add(new int[]{0, wells[i]});
        }

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Start from any node. We can simulate starting from house 1
        pq.offer(new int[]{1, 0});

        int totalCost = 0;
        while (!pq.isEmpty() && visited.size() < n + 1) {
            int[] cur = pq.poll();
            int u = cur[0], cost = cur[1];
            if (visited.contains(u)) continue;

            visited.add(u);
            totalCost += cost;

            for (int[] nei : adj.getOrDefault(u, new ArrayList<>())) {
                int v = nei[0], w = nei[1];
                if (!visited.contains(v)) {
                    pq.offer(new int[]{v, w});
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int n1 = 3;
        int[] wells1 = {1, 2, 2};
        int[][] pipes1 = {{1, 2, 1}, {2, 3, 1}};

        System.out.println("Kruskal's: " + minCostToSupplyWaterKruskal(n1, wells1, pipes1)); // Output: 3
        System.out.println("Prim's: " + minCostToSupplyWaterPrim(n1, wells1, pipes1));       // Output: 3

        int n2 = 2;
        int[] wells2 = {1, 1};
        int[][] pipes2 = {{1, 2, 1}, {1, 2, 2}};

        System.out.println("Kruskal's: " + minCostToSupplyWaterKruskal(n2, wells2, pipes2)); // Output: 2
        System.out.println("Prim's: " + minCostToSupplyWaterPrim(n2, wells2, pipes2));       // Output: 2
    }
}
