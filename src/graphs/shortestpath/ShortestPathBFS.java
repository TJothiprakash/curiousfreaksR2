package graphs.shortestpath;

import java.util.*;


//shortest path unweighted graph

public class ShortestPathBFS {
    // Modified bfs to store the parent of nodes along with
    // the distance from the source node
    static void bfs(List<List<Integer>> graph, int S,
                    List<Integer> par, List<Integer> dist) {
        // Queue to store the nodes in the order they are
        // visited
        Queue<Integer> q = new LinkedList<>();
        // Mark the distance of the source node as 0
        dist.set(S, 0);
        // Push the source node to the queue
        q.add(S);

        // Iterate until the queue is not empty
        while (!q.isEmpty()) {
            // Pop the node at the front of the queue
            int node = q.poll();

            // Explore all the neighbors of the current node
            for (int neighbor : graph.get(node)) {
                // Check if the neighboring node is not
                // visited
                if (dist.get(neighbor)
                        == Integer.MAX_VALUE) {
                    // Mark the current node as the parent
                    // of the neighboring node
                    par.set(neighbor, node);
                    // Mark the distance of the neighboring
                    // node as the distance of the current
                    // node + 1
                    dist.set(neighbor, dist.get(node) + 1);
                    // Insert the neighboring node to the
                    // queue
                    q.add(neighbor);
                }
            }
        }
    }

    // Function to print the shortest distance between the
    // source vertex and destination vertex
    static void
    printShortestDistance(List<List<Integer>> graph, int S,
                          int D, int V) {
        // par[] array stores the parent of nodes
        List<Integer> par
                = new ArrayList<>(Collections.nCopies(V, -1));

        // dist[] array stores the distance of nodes from S
        List<Integer> dist = new ArrayList<>(
                Collections.nCopies(V, Integer.MAX_VALUE));

        // Function call to find the distance of all nodes
        // and their parent nodes
        bfs(graph, S, par, dist);

        if (dist.get(D) == Integer.MAX_VALUE) {
            System.out.println(
                    "Source and Destination are not connected");
            return;
        }

        // List path stores the shortest path
        List<Integer> path = new ArrayList<>();
        int currentNode = D;
        path.add(D);
        while (par.get(currentNode) != -1) {
            path.add(par.get(currentNode));
            currentNode = par.get(currentNode);
        }

        // Printing path from source to destination
        for (int i = path.size() - 1; i >= 0; i--)
            System.out.print(path.get(i) + " ");
    }

    // Driver program to test above functions
    public static void main(String[] args) {
        // no. of vertices
        int V = 8;
        // Source and Destination vertex
        int S = 2, D = 6;
        // Edge list
        List<List<Integer>> edges = Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(1, 2),
                Arrays.asList(0, 3), Arrays.asList(3, 4),
                Arrays.asList(4, 7), Arrays.asList(3, 7),
                Arrays.asList(6, 7), Arrays.asList(4, 5),
                Arrays.asList(4, 6), Arrays.asList(5, 6));

        // List to store the graph as an adjacency list
        List<List<Integer>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> edge : edges) {
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }

        printShortestDistance(graph, S, D, V);
    }
}