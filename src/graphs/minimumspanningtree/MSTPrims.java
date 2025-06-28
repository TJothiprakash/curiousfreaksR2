package graphs.minimumspanningtree;/*
✅ PROBLEM: Minimum Spanning Tree (MST) Weight using Prim's Algorithm

Given a weighted, undirected, and connected graph with V vertices and E edges,
find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph.

The graph is represented using an adjacency list:
  - Each element adj[i] is a list of pairs (neighbor, weight)

-----------------------------------------------------------

💡 INTUITION:
- To build a Minimum Spanning Tree (MST), we need to connect all vertices with the minimum total edge weight.
- We use **Prim's Algorithm** to grow the MST:
  - Start with any node.
  - Repeatedly add the smallest edge that connects to a new node.
  - Use a min-heap (priority queue) to always pick the lowest-weight edge.

-----------------------------------------------------------

🧠 DRY RUN:
Input:
3 3
0 1 5
1 2 3
0 2 1

Edges:
  - (0-1, 5), (1-2, 3), (0-2, 1)

Prim's Algorithm Steps:
- Start at node 0
- Add edge (0-2, 1) → MST weight = 1
- From 2, add edge (2-1, 3) → MST weight = 4
- All nodes connected → DONE

Final Output: 4

-----------------------------------------------------------
*/

import java.util.*;

public class MSTPrims {

    static class Pair {
        int node, weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static int findMSTWeight(int V, List<List<List<Integer>>> adj) {
        boolean[] visited = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        pq.offer(new Pair(0, 0));
        int sum = 0;

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.node;
            int wt = current.weight;

            if (visited[u]) continue;

            visited[u] = true;
            sum += wt;
            System.out.println("Include node " + u + " with edge weight " + wt);

            for (List<Integer> neighbor : adj.get(u)) {
                int v = neighbor.get(0);
                int weight = neighbor.get(1);
                if (!visited[v]) {
                    pq.offer(new Pair(v, weight));
                    System.out.println(" → Considering edge " + u + " -> " + v + " with weight " + weight);
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices and edges:");
        int V = sc.nextInt();
        int E = sc.nextInt();

        List<List<List<Integer>>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        System.out.println("Enter edges in format: u v w");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            adj.get(u).add(Arrays.asList(v, w));
            adj.get(v).add(Arrays.asList(u, w)); // because undirected
        }

        int mstWeight = findMSTWeight(V, adj);
        System.out.println("Total weight of MST: " + mstWeight);
    }
}

/*
-----------------------------------------------------------

⏱️ TIME COMPLEXITY:
- O((V + E) log V)
  - Each edge may be added to the priority queue once
  - Heap operations take log V

🧠 SPACE COMPLEXITY:
- O(V + E) for adjacency list
- O(V) for visited and priority queue

-----------------------------------------------------------
*/
