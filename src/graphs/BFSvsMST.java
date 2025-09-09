package graphs;
/*
Problem:
--------
1. BFS (Breadth-First Search):
   - Traverse all nodes of a graph level by level from a given source.
   - Useful for shortest path in unweighted graphs.

2. MST (Minimum Spanning Tree using Prim's algorithm):
   - Connect all nodes in a weighted graph with minimum total edge weight.
   - Output is a tree covering all vertices with minimal sum of weights.

Intuition:
----------
- BFS and Prim's MST both "grow" a tree from a starting node.
- BFS expands nodes level-wise (FIFO queue), ignoring edge weights.
- Prim's MST expands using the minimum weight edge (priority queue) to ensure minimum total cost.
- Structurally similar: both mark visited nodes and iterate neighbors.

Dry Run Example:
----------------
Graph: weighted edges
A--1--B
A--2--C
B--3--C
C--4--D

BFS from A (ignoring weights):
Order of visit: A -> B -> C -> D

Prim's MST from A:
Select edges with minimum weights connecting unvisited nodes:
- A->B (1), A->C (2), C->D (4)
MST cost = 7
*/

import java.util.*;

public class BFSvsMST {

    // --- BFS Implementation ---
    public static void bfs(Map<Integer, List<Integer>> graph, int start) {
        System.out.println("BFS Traversal from node " + start + ":");
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("\n");
    }

    // --- Prim's MST Implementation ---
    static class Edge {
        int node, weight;
        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void primMST(Map<Integer, List<Edge>> graph, int start) {
        System.out.println("Prim's MST from node " + start + ":");
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Set<Integer> visited = new HashSet<>();
        int mstCost = 0;

        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited.contains(cur.node)) continue;

            visited.add(cur.node);
            mstCost += cur.weight;
            System.out.println("Visit node " + cur.node + " via edge weight " + cur.weight);

            for (Edge neighbor : graph.getOrDefault(cur.node, new ArrayList<>())) {
                if (!visited.contains(neighbor.node)) {
                    pq.add(neighbor);
                }
            }
        }
        System.out.println("Total MST cost: " + mstCost + "\n");
    }

    // --- Main function to demo both ---
    public static void main(String[] args) {
        // Graph for BFS (unweighted)
        Map<Integer, List<Integer>> bfsGraph = new HashMap<>();
        bfsGraph.put(0, Arrays.asList(1, 2));
        bfsGraph.put(1, Arrays.asList(0, 3));
        bfsGraph.put(2, Arrays.asList(0, 3));
        bfsGraph.put(3, Arrays.asList(1, 2));

        bfs(bfsGraph, 0);

        // Graph for Prim's MST (weighted)
        Map<Integer, List<Edge>> mstGraph = new HashMap<>();
        mstGraph.put(0, Arrays.asList(new Edge(1, 1), new Edge(2, 2)));
        mstGraph.put(1, Arrays.asList(new Edge(0, 1), new Edge(2, 3)));
        mstGraph.put(2, Arrays.asList(new Edge(0, 2), new Edge(1, 3), new Edge(3, 4)));
        mstGraph.put(3, Arrays.asList(new Edge(2, 4)));

        primMST(mstGraph, 0);
    }
}

/*
Complexity:
-----------
BFS:
- Time: O(V + E) where V = nodes, E = edges
- Space: O(V) for queue + visited set

Prim's MST (with PQ):
- Time: O(E log V) using priority queue
- Space: O(V + E) for graph + O(V) for visited + O(V) for PQ

Subtle Difference:
------------------
- BFS: uses FIFO queue, ignores edge weights
- MST: uses min-heap priority queue, considers edge weights
- Everything else (visited nodes, iterating neighbors) is structurally identical
*/
