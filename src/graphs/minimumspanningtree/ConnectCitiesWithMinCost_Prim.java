package graphs.minimumspanningtree;
/*
✅ PROBLEM: Connecting Cities with Minimum Cost (Prim’s Algorithm)

You are given `n` cities labeled from 1 to n and a list `connections`, where connections[i] = [xi, yi, costi].
This represents a bidirectional connection between city xi and city yi with cost `costi`.

Your task is to return the **minimum cost** to connect all cities such that there is at least one path between every pair of cities.
If it is **impossible** to connect all the cities, return **-1**.

------------------------------------------------------------
🔗 CONSTRAINTS:
- 1 ≤ n ≤ 10^4
- 1 ≤ connections.length ≤ 10^4
- 1 ≤ xi, yi ≤ n
- 0 ≤ costi ≤ 10^5

------------------------------------------------------------
💡 INTUITION:
- We treat cities as nodes in a graph.
- We need to connect all cities with the minimum cost → **Minimum Spanning Tree (MST)**.
- We'll use **Prim's Algorithm**:
  - Start from any city.
  - Always choose the smallest-cost edge that connects a visited city to an unvisited city.
  - Use a **min-heap (priority queue)** to keep track of cheapest connections.
- If not all cities are visited after building the MST → return -1.

------------------------------------------------------------
🧠 DRY RUN:

Input:
n = 3
connections = [[1,2,5],[1,3,6],[2,3,1]]

Edges:
(1-2,5), (1-3,6), (2-3,1)

Prim’s Steps:
- Start at city 1.
- Add edge (1-2,5), then edge (2-3,1)
→ Total = 6

------------------------------------------------------------
*/

import java.util.*;

public class ConnectCitiesWithMinCost_Prim {

    static class Pair {
        int node;
        int weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static int minimumCost(int n, int[][] connections) {
        // Step 1: Build adjacency list (1-based indexing)
        Map<Integer, List<Pair>> graph = new HashMap<>();
        for (int[] conn : connections) {
            int u = conn[0], v = conn[1], w = conn[2];
            graph.computeIfAbsent(u, x -> new ArrayList<>()).add(new Pair(v, w));
            graph.computeIfAbsent(v, x -> new ArrayList<>()).add(new Pair(u, w));
        }

        // Step 2: Prim’s algorithm
        boolean[] visited = new boolean[n + 1]; // 1-based indexing
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.offer(new Pair(1, 0)); // start from city 1
        int totalCost = 0;
        int visitedCount = 0;

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int city = current.node;
            int cost = current.weight;

            if (visited[city]) continue;

            visited[city] = true;
            totalCost += cost;
            visitedCount++;

            System.out.println("Included city: " + city + " with cost: " + cost);

            for (Pair neighbor : graph.getOrDefault(city, new ArrayList<>())) {
                if (!visited[neighbor.node]) {
                    pq.offer(neighbor);
                    System.out.println(" → Edge offered: " + city + " -> " + neighbor.node + " with cost: " + neighbor.weight);
                }
            }
        }

        return visitedCount == n ? totalCost : -1;
    }

    public static void main(String[] args) {
        // Test 1
        int n1 = 3;
        int[][] connections1 = {{1, 2, 5}, {1, 3, 6}, {2, 3, 1}};
        System.out.println("Test 1 Output: " + minimumCost(n1, connections1)); // Expected: 6

        // Test 2
        int n2 = 4;
        int[][] connections2 = {{1, 2, 3}, {3, 4, 4}};
        System.out.println("Test 2 Output: " + minimumCost(n2, connections2)); // Expected: -1

        // Test 3
        int n3 = 2;
        int[][] connections3 = {{1, 2, 100}};
        System.out.println("Test 3 Output: " + minimumCost(n3, connections3)); // Expected: 100
    }
}

/*
------------------------------------------------------------
⏱️ TIME COMPLEXITY:
- Building graph: O(E)
- Prim's Algorithm: O(E log V) where E = number of connections, V = number of cities

🧠 SPACE COMPLEXITY:
- Graph: O(E)
- Visited array: O(V)
- Priority queue: O(V)

------------------------------------------------------------
*/
