package graphs.minimumspanningtree;
/*
✅ PROBLEM: Minimum Cost to Supply Water to All Houses (Prim’s Algorithm)

You are given:
- `n` houses numbered from 1 to n
- `wells[i-1]` → cost to build a well in house `i`
- `pipes[j] = [house1j, house2j, costj]` → cost to connect two houses with a pipe

Your task:
- Either build wells or lay pipes
- Return the **minimum total cost** to supply water to all houses

-----------------------------------------------------
💡 INTUITION:
- Treat each house as a node
- Model the problem as a graph:
  - Add a **virtual node 0** and connect it to each house i with cost = wells[i - 1]
  - This models building a well directly in house i
- Now find the **MST** over this graph using **Prim’s Algorithm**

-----------------------------------------------------
🧠 DRY RUN:

n = 3
wells = [1,2,2]
pipes = [[1,2,1],[2,3,1]]

→ Graph:
    0-1 (1)
    0-2 (2)
    0-3 (2)
    1-2 (1)
    2-3 (1)

→ MST:
    0-1 (1) [well at house 1]
    1-2 (1)
    2-3 (1)

Total cost: 3

-----------------------------------------------------
*/

import java.util.*;

public class MinCostWaterSupply {

    static class Pair {
        int node;
        int cost;

        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // Build adjacency list with virtual node 0
        Map<Integer, List<Pair>> graph = new HashMap<>();

        for (int i = 0; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Add virtual edges from node 0 to each house (build well)
        for (int i = 1; i <= n; i++) {
            graph.get(0).add(new Pair(i, wells[i - 1]));
            graph.get(i).add(new Pair(0, wells[i - 1]));
        }

        // Add given pipes as bidirectional edges
        for (int[] pipe : pipes) {
            int u = pipe[0], v = pipe[1], cost = pipe[2];
            graph.get(u).add(new Pair(v, cost));
            graph.get(v).add(new Pair(u, cost));
        }

        // Prim’s Algorithm
        boolean[] visited = new boolean[n + 1]; // includes virtual node 0
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        pq.offer(new Pair(0, 0));

        int totalCost = 0;

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.node;
            int cost = current.cost;

            if (visited[u]) continue;

            visited[u] = true;
            totalCost += cost;
            System.out.println("Added node: " + u + ", Cost: " + cost);

            for (Pair neighbor : graph.get(u)) {
                if (!visited[neighbor.node]) {
                    pq.offer(neighbor);
                    System.out.println(" → Offer edge: " + u + " → " + neighbor.node + " at cost " + neighbor.cost);
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int n1 = 3;
        int[] wells1 = {1, 2, 2};
        int[][] pipes1 = {{1, 2, 1}, {2, 3, 1}};
        System.out.println("Test 1 Output: " + minCostToSupplyWater(n1, wells1, pipes1)); // Expected: 3

        int n2 = 2;
        int[] wells2 = {1, 1};
        int[][] pipes2 = {{1, 2, 1}, {1, 2, 2}};
        System.out.println("Test 2 Output: " + minCostToSupplyWater(n2, wells2, pipes2)); // Expected: 2

        int n3 = 4;
        int[] wells3 = {5, 3, 4, 2};
        int[][] pipes3 = {{1, 2, 1}, {2, 3, 2}, {3, 4, 1}};
        System.out.println("Test 3 Output: " + minCostToSupplyWater(n3, wells3, pipes3)); // Try variations
    }
}

/*
-----------------------------------------------------
⏱️ TIME COMPLEXITY:
- O((N + E) log N)
  - N = number of houses (including virtual node)
  - E = number of pipes + N (virtual edges)

🧠 SPACE COMPLEXITY:
- O(N + E) for graph and priority queue

-----------------------------------------------------
*/

