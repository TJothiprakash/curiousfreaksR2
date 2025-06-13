package graphs.toposort;

import java.util.*;

public class Toposort {
    public static void main(String[] args) {

    }

    public List<Integer> topoSort(List<List<Integer>> adj, int V) {
        int indegree[] = new int[V];
        Queue<Integer> q = new LinkedList<>();
        List<Integer> toposort = new ArrayList<>();


        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                indegree[v]++;
            }
        }

        //enque all nodes with indegree 0
        for (int u = 0; u < V; u++) {
            if (indegree[u] == 0) {
                q.add(u);
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            toposort.add(u);
            for (int v : adj.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.add(v);
                }
            }
        }
        if (toposort.size() < V) {
            throw new RuntimeException("Cycle detected ! Topological sort is not possible");
        }

        return toposort;
    }

        public List<Integer> topoSortUsingDFS(List<List<Integer>> adj, int V) {
            Boolean vis[] = new Boolean[V];
            Arrays.fill(vis, false);
            Stack<Integer> toposort = new Stack<>();
            int node = 0;
            for (int u = 0; u < V; u++) {

                dfshelper(node, vis, toposort, V, adj);
            }
            return toposort;
        }

        static void dfshelper(int node, Boolean vis[], Stack<Integer> toposort, int V, List<List<Integer>> adj) {
            vis[node] = true;
            for (int n : adj.get(node)) {
                if (!vis[n]) {

                    dfshelper(n, vis, toposort, V, adj);
                    toposort.push(n);
                }
            }
        }
}
