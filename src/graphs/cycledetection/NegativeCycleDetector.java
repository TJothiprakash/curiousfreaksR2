package graphs.cycledetection;

public class NegativeCycleDetector {
    public static int isNegativeWeightCycle(int n, int[][] edges) {
        int[] dist = new int[n];

        for (int start = 0; start < n; start++) {
            // Initialize distances
            for (int i = 0; i < n; i++) dist[i] = Integer.MAX_VALUE;
            dist[start] = 0;

            System.out.println("Starting from node: " + start);

            // Relax edges n - 1 times
            for (int i = 1; i < n; i++) {
                System.out.println("Iteration " + i);
                for (int[] edge : edges) {
                    int u = edge[0];
                    int v = edge[1];
                    int w = edge[2];

                    if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        System.out.println("Updated dist[" + v + "] to " + dist[v] + " via " + u);
                    }
                }
            }

            // Check for negative weight cycle
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    System.out.println("Negative cycle detected via edge: " + u + " -> " + v + " with weight " + w);
                    return 1;
                }
            }
        }

        System.out.println("No negative cycle found");
        return 0;
    }

    public static void main(String[] args) {
        int n1 = 3;
        int[][] edges1 = {{0, 1, -1}, {1, 2, -2}, {2, 0, -3}};
        System.out.println("Output: " + isNegativeWeightCycle(n1, edges1)); // Output: 1

        int n2 = 3;
        int[][] edges2 = {{0, 1, -1}, {1, 2, -2}, {2, 0, 3}};
        System.out.println("Output: " + isNegativeWeightCycle(n2, edges2)); // Output: 0
    }
}
