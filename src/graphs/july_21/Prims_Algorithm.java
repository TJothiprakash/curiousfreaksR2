package graphs.july_21;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prims_Algorithm {
//
// Prims algorithm
//    steps
//    start from any one node and build the min spannig tree
//    explore all paths and take the min weight
//     repeat until it reaches the n-1 edges

    public static void main(String[] args) {

    }

    public int MSTUsingPrims(int edges[][], int n) {
        // build adj list
        List<List<Edge>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            adjList.get(u).add(new Edge(u, v, weight));
            adjList.get(v).add(new Edge(v, u, weight));
        }

//         set up pq, visited array, mst variable
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int mst = 0;
//  we can start with any node literally i'm gonna start with first element from the edges array
        Edge start = new Edge(edges[0][0], edges[0][1], edges[0][2]);
        pq.add(start);

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currU = current.u;
            int currV = current.v;
            if (visited[currV]) continue;
            visited[currV] = true;
            mst += current.weight;
            for (Edge e : adjList.get(currU)) {
                if (!visited[e.v]) {
                    pq.add(e);
                }
            }
        }
        return mst;
    }
}

//
//class Edge implements Comparable<Edge> {
//    int u, v, weight;
//
//    public Edge(int u, int v, int weight) {
//        this.u = u;
//        this.v = v;
//        this.weight = weight;
//    }
//
//    @Override
//    public int compareTo(@NotNull Edge o) {
//        return this.weight - o.weight;
//    }
//}
