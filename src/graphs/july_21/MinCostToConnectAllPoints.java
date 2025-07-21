package graphs.july_21;

import graphs.commons.disjointset.DisjointSet;
import graphs.commons.disjointset.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinCostToConnectAllPoints {

    public static void main(String[] args) {
        int[][] points1 = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        int[][] points2 = {{3, 12}, {-2, 5}, {-4, 1}};

        System.out.println("Minimum Cost (Example 1): " + minCostConnectPoints(points1)); // Output: 20
        System.out.println("Minimum Cost (Example 2): " + minCostConnectPoints(points2)); // Output: 18
    }

    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<Edge> allEdges = new ArrayList<>();

        // Step 1: Generate all possible edges with Manhattan distances
        for (int i = 0; i < n; i++) {
            int[] pointA = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] pointB = points[j];
                int weight = Math.abs(pointA[0] - pointB[0]) + Math.abs(pointA[1] - pointB[1]);
                allEdges.add(new Edge(i, j, weight));
            }
        }

        // Step 2: Sort edges by weight
        Collections.sort(allEdges, (a, b) -> Integer.compare(a.weight, b.weight));

        // Step 3: Kruskal's algorithm with Disjoint Set
        DisjointSet ds = new DisjointSet(n);
        int cost = 0, edgesUsed = 0;

        for (Edge edge : allEdges) {
            if (ds.union(edge.src, edge.dest)) {
                cost += edge.weight;
                edgesUsed++;
                if (edgesUsed == n - 1) break;
            }
        }

        return cost;
    }
}
