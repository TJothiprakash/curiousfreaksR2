package graphs.july_21;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumSpanningTreePracticeQuestions {
    public static void main(String[] args) {
            MinimumSpanningTreePracticeQuestions obj = new MinimumSpanningTreePracticeQuestions();

            int[][] edges = {
                    {0, 1, 5},
                    {1, 2, 3},
                    {0, 2, 1}
            };

            obj.findMST(edges, 3); // Expected output: 4


    }
// what is min spanning tree : all vertices(N) connected with N-1 edges with lowest cost
//    ✅ A subgraph that connects all N nodes using exactly N-1 edges
//✅ Has no cycles
//✅ The sum of edge weights is minimized
//    commomnly used mst - > kruskals, Prims
//    kruskals -> sort all the edge weights , add till all of them connected
//    prims -> start from a single node , add one by one only if the edge weight is minimum till adding all vertices
//     where MST used? network design -> water pipelines, electric grids, fiber optic cables laying all these with min cost
//    image segmentation -> in computer vision used in grouping pixels/regions
//    civil engineering -> designing roads and pipeline systems efficiently
//     clustering -> used in hierarchical clustering algorithms( single link clustering)
//    Approximization Algorithms -> Used in approximations for NP hard problems like Travelling Salesman problems


//     Kruskal’s Algorithm — Key Concepts
//Sort all edges by weight (ascending).
//
//Use Union-Find (Disjoint Set Union) to avoid cycles.
//
//Include edges one by one:
//
//Only add if the nodes are in different components (no cycle).
//
//Stop when (V - 1) edges are added.


    public int findMST(int[][] edges, int n) {
        List<Edge> edgeList = new ArrayList<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            edgeList.add(new Edge(u, v, weight));
        }
        Collections.sort(edgeList);
        DSU dsu = new DSU(n);
        int mstWeight = 0, edgesUsed = 0;

        for (Edge edge : edgeList) {
            if (dsu.findUnion(edge.u, edge.v)) {
                mstWeight += edge.weight;
                edgesUsed++;
                if (edgesUsed == n - 1) break;
            }
        }

        System.out.println(mstWeight);
        return mstWeight;
    }
}





class Edge implements Comparable<Edge> {
    int u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;

    }

    @Override
    public int compareTo(@NotNull Edge o) {
        return this.weight - o.weight;
    }
}


class DSU {

    int[] parent, rank;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int findParent(int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node]);
        }
        return parent[node];
    }

    public boolean findUnion(int x, int y) {
        int u = findParent(x);
        int v = findParent(y);

        if (u == v) return false;

        if (rank[u] < rank[v]) {
            parent[u] = v;
        } else if (rank[u] > rank[v]) {
            parent[v] = u;
        } else {
            parent[v] = u;
            rank[u]++;
        }

        return true;
    }


}