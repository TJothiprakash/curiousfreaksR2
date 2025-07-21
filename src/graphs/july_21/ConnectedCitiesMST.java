package graphs.july_21;

import graphs.commons.disjointset.DisjointSet;
import graphs.commons.disjointset.Edge;

import java.util.*;

public class ConnectedCitiesMST {

    public static void main(String[] args) {
        int n = 5;
       List< graphs.commons.disjointset.Edge> edges = createConnectedGraph();
        System.out.println(edges);
        System.out.println("Kruskal's MST Cost: " + kruskalMST(n, edges));
        System.out.println("Prim's MST Cost: " + primsMST(n, edges));
    }

    public static List<Edge> createConnectedGraph() {
        return List.of(
                new Edge(0, 1, 10),
                new Edge(0, 2, 6),
                new Edge(0, 3, 5),
                new Edge(1, 3, 15),
                new Edge(2, 3, 4),
                new Edge(3, 4, 7)
        );
    }

    // ✅ Kruskal’s Algorithm
    public static int kruskalMST(int n, List<Edge> edges) {
        edges = new ArrayList<>(edges); // Make mutable
        edges.sort(Comparator.comparingInt(e -> e.weight)); // Sort by weight

        DisjointSet ds = new DisjointSet(n);
        int totalCost = 0, edgesUsed = 0;

        for (Edge edge : edges) {
            if (ds.union(edge.src, edge.dest)) {
                totalCost += edge.weight;
                edgesUsed++;
                if (edgesUsed == n - 1) break;
            }
        }
        return edgesUsed == n - 1 ? totalCost : -1; // return -1 if disconnected
    }

    // ✅ Prim’s Algorithm
    public static int primsMST(int n, List<Edge> edges) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (Edge edge : edges) {
            graph.get(edge.src).add(edge);
            graph.get(edge.dest).add(new Edge(edge.dest, edge.src, edge.weight)); // undirected
        }

        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        minHeap.offer(new Edge(-1, 0, 0)); // start from city 0

        int totalCost = 0, citiesVisited = 0;

        while (!minHeap.isEmpty() && citiesVisited < n) {
            Edge current = minHeap.poll();
            int city = current.dest;
            if (visited[city]) continue;

            visited[city] = true;
            totalCost += current.weight;
            citiesVisited++;

            for (Edge neighbor : graph.get(city)) {
                if (!visited[neighbor.dest]) {
                    minHeap.offer(neighbor);
                }
            }
        }

        return citiesVisited == n ? totalCost : -1; // -1 if not all cities are connected
    }
}
