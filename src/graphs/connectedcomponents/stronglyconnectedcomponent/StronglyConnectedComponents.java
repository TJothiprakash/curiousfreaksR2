package graphs.connectedcomponents.stronglyconnectedcomponent;


import java.util.*;

public class StronglyConnectedComponents {
    public List<List<Integer>> tarjansSCC(int V, List<List<Integer>> adj) {
        // Step 1: Fill stack with finish times
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                dfs1(i, adj, visited, stack);
        }

        // Step 2: Transpose the graph
        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < V; i++) transpose.add(new ArrayList<>());
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                transpose.get(v).add(u);
            }
        }

        // Step 3: Do DFS on transposed graph in order of stack
        Arrays.fill(visited, false);
        List<List<Integer>> sccList = new ArrayList<>();
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> component = new ArrayList<>();
                dfs2(node, transpose, visited, component);
                Collections.sort(component);
                sccList.add(component);
            }
        }

        // Sort outer list
        sccList.sort((a, b) -> a.get(0) - b.get(0));
        return sccList;
    }

    private void dfs1(int node, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int nei : adj.get(node)) {
            if (!visited[nei]) dfs1(nei, adj, visited, stack);
        }
        stack.push(node);
    }

    private void dfs2(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int nei : adj.get(node)) {
            if (!visited[nei]) dfs2(nei, adj, visited, component);
        }
    }

    // Utility method to build graph and test
    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {
                {0, 2}, {2, 1}, {1, 0}, {0, 3}, {3, 4}
        };

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        StronglyConnectedComponents sccFinder = new StronglyConnectedComponents();
        List<List<Integer>> sccs = sccFinder.tarjansSCC(V, adj);

        for (List<Integer> comp : sccs) {
            for (int node : comp) System.out.print(node + " ");
            System.out.println();
        }
    }
}
