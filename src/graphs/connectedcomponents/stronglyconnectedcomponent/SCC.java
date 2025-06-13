package graphs.connectedcomponents.stronglyconnectedcomponent;

import java.util.*;

public class SCC {
    private int time = 0;
    private List<List<Integer>> result = new ArrayList<>();
    private int[] disc, low;
    private boolean[] onStack;
    private Deque<Integer> stack;

    public List<List<Integer>> tarjans(int V, List<List<Integer>> adj) {
        disc = new int[V];
        low = new int[V];
        onStack = new boolean[V];
        stack = new ArrayDeque<>();

        Arrays.fill(disc, -1); // unvisited

        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                dfs(i, adj);
            }
        }

        // Sort each SCC
        for (List<Integer> comp : result) Collections.sort(comp);

        // Sort list of SCCs lexicographically
        result.sort((a, b) -> {
            int n = Math.min(a.size(), b.size());
            for (int i = 0; i < n; i++) {
                if (!a.get(i).equals(b.get(i))) return a.get(i) - b.get(i);
            }
            return a.size() - b.size();
        });

        return result;
    }

    private void dfs(int u, List<List<Integer>> adj) {
        disc[u] = low[u] = time++;
        stack.push(u);
        onStack[u] = true;

        for (int v : adj.get(u)) {
            if (disc[v] == -1) {
                dfs(v, adj);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (low[u] == disc[u]) {
            List<Integer> scc = new ArrayList<>();
            int v;
            do {
                v = stack.pop();
                onStack[v] = false;
                scc.add(v);
            } while (u != v);
            result.add(scc);
        }
    }

    // Sample test
    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Graph edges (example: 0 -> 2, 2 -> 1, 1 -> 0, 0 -> 3, 3 -> 4)
        adj.get(0).add(2);
        adj.get(2).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(4);

        SCC sccSolver = new SCC();
        List<List<Integer>> sccs = sccSolver.tarjans(V, adj);

        System.out.println("Strongly Connected Components:");
        for (List<Integer> comp : sccs) {
            System.out.println(comp);
        }
    }
}

/*
* ✅ Kosaraju's Algorithm Overview (Step-by-Step)
Kosaraju’s algorithm works in 3 main steps:

🧭 Step 1: DFS & Stack
Do a DFS traversal and store the finishing order of each node in a stack.

🔄 Step 2: Reverse the Graph
Reverse the direction of all edges to get the transpose of the graph.

🚀 Step 3: DFS in Reverse Order
Pop nodes from the stack and do DFS on the reversed graph.

Each DFS gives you one SCC.

💡 Why it works?
The finishing times in the original graph help prioritize deeper nodes.
*  Reversing the edges lets us collect the entire SCC cleanly in one DFS.*/
class KosarajuSCC {
    public List<List<Integer>> tarjans(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        // Step 1: Fill stack with finishing times
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs1(i, adj, visited, stack);
            }
        }

        // Step 2: Transpose the graph
        List<List<Integer>> reversed = new ArrayList<>();
        for (int i = 0; i < V; i++) reversed.add(new ArrayList<>());

        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                reversed.get(v).add(u);
            }
        }

        // Step 3: Do DFS based on stack order
        Arrays.fill(visited, false);
        List<List<Integer>> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> scc = new ArrayList<>();
                dfs2(node, reversed, visited, scc);
                Collections.sort(scc);
                result.add(scc);
            }
        }

        // Sort all SCCs lexicographically
        result.sort((a, b) -> {
            int len = Math.min(a.size(), b.size());
            for (int i = 0; i < len; i++) {
                if (!a.get(i).equals(b.get(i))) return a.get(i) - b.get(i);
            }
            return a.size() - b.size();
        });

        return result;
    }

    private void dfs1(int u, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs1(v, adj, visited, stack);
            }
        }
        stack.push(u); // Finished processing
    }

    private void dfs2(int u, List<List<Integer>> adj, boolean[] visited, List<Integer> scc) {
        visited[u] = true;
        scc.add(u);
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs2(v, adj, visited, scc);
            }
        }
    }

    // Test method
    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Sample input: 0→2, 2→1, 1→0, 0→3, 3→4
        adj.get(0).add(2);
        adj.get(2).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(4);
        KosarajuSCC kosarajuSCC = new KosarajuSCC();
        List<List<Integer>> result = kosarajuSCC.tarjans(V, adj);
        System.out.println(result);
    }
}