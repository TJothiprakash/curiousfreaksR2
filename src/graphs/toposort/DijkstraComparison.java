package graphs.toposort;

import java.util.*;

public class DijkstraComparison {

    static class Pair {
        int node, weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    // ✅ STANDARD DIJKSTRA: SRC TO ALL NODES
    public static int[] dijkstraAll(int V, List<List<Pair>> adj, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.node;

            for (Pair neighbor : adj.get(u)) {
                int v = neighbor.node;
                int wt = neighbor.weight;

                if (dist[v] > dist[u] + wt) {
                    dist[v] = dist[u] + wt;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        return dist;
    }

    /*
    // ✅ SINGLE-TARGET DIJKSTRA (Early Exit)
    public static int dijkstraToTarget(int V, List<List<Pair>> adj, int src, int target) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.node;
            int d = current.weight;

            // Early exit if target reached
            if (u == target) {
                return d;
            }

            for (Pair neighbor : adj.get(u)) {
                int v = neighbor.node;
                int wt = neighbor.weight;

                if (dist[v] > dist[u] + wt) {
                    dist[v] = dist[u] + wt;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        return -1; // if unreachable
    }
    */

    public static void main(String[] args) {
        int V = 6;
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Edges: u → v (weight)
        adj.get(0).add(new Pair(1, 2));
        adj.get(0).add(new Pair(2, 4));
        adj.get(1).add(new Pair(3, 7));
        adj.get(2).add(new Pair(3, 1));
        adj.get(3).add(new Pair(4, 3));
        adj.get(4).add(new Pair(5, 1));

        int src = 0;

        // ✅ Standard: shortest to all
        int[] distances = dijkstraAll(V, adj, src);
        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Node " + i + " : " + (distances[i] == Integer.MAX_VALUE ? "-1 (unreachable)" : distances[i]));
        }

        /*
        // ✅ Single-target version (uncomment if needed)
        int target = 5;
        int shortest = dijkstraToTarget(V, adj, src, target);
        System.out.println("Shortest distance from " + src + " to " + target + " = " + shortest);
        */
    }
}
