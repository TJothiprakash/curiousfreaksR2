package graphs.other_imp_graph_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/*✅ What is a Strongly Connected Component (SCC)?
In a directed graph, a Strongly Connected Component (SCC) is a maximal set of nodes such that:

🔁 Every node is reachable from every other node in the set — following the direction of edges.

✅ SCC vs Normal Connectivity:
In an undirected graph, being connected just means "can reach".

In a directed graph, SCC means "can reach AND be reached back".

🧠 Why SCCs Matter
SCCs are useful in:

Analyzing software dependencies (mutual dependencies).

Optimizing compilers (e.g., dead code).

Condensing graphs into DAGs.

Solving 2-SAT.

*/
/*
🎯 Problem: Count Strongly Connected Components in a Directed Graph

Given a directed graph represented using an adjacency list,
return the number of strongly connected components (SCCs).

🧠 Intuition:
Use Kosaraju’s algorithm:
1️⃣ First DFS to build finishing order (stack)
2️⃣ Transpose the graph (reverse all edges)
3️⃣ Second DFS on transposed graph in stack order, count SCCs

🔬 Dry Run:
Input: [[2, 3], [0], [1], [4], []]

Step 1: Finish stack: [4, 3, 0, 1, 2]
Step 2: Transpose: [[1], [2], [0], [0], [3]]
Step 3: DFS on transpose gives SCCs: [0,1,2], [3], [4] → Total = 3

📊 Time: O(V + E)
💾 Space: O(V + E)
*/

public class SCCKosaraju {

    // Step 1: DFS to populate finish time stack
    private void dfs1(int node, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs1(neighbor, visited, stack, adj);
            }
        }
        stack.push(node);
    }

    // Step 3: DFS on transposed graph
    private void dfs2(int node, boolean[] visited, List<List<Integer>> transpose) {
        visited[node] = true;
        for (int neighbor : transpose.get(node)) {
            if (!visited[neighbor]) {
                dfs2(neighbor, visited, transpose);
            }
        }
    }

    public int countSCCs(int V, List<List<Integer>> adj) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        // Step 1: Populate stack with finishing times
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs1(i, visited, stack, adj);
            }
        }

        // Step 2: Transpose the graph
        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < V; i++) transpose.add(new ArrayList<>());

        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                transpose.get(v).add(u); // reverse edge
            }
        }

        // Step 3: DFS on transpose in order of stack
        Arrays.fill(visited, false);
        int sccCount = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                dfs2(node, visited, transpose);
                sccCount++;
            }
        }

        return sccCount;
    }

    // 🧪 Sample Main
    public static void main(String[] args) {
        SCCKosaraju scc = new SCCKosaraju();

        List<List<Integer>> adj1 = Arrays.asList(
                Arrays.asList(2, 3), // 0
                Arrays.asList(0),    // 1
                Arrays.asList(1),    // 2
                Arrays.asList(4),    // 3
                Arrays.asList()      // 4
        );
        System.out.println("SCC Count (adj1): " + scc.countSCCs(adj1.size(), adj1)); // 3

        List<List<Integer>> adj2 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(0)
        );
        System.out.println("SCC Count (adj2): " + scc.countSCCs(adj2.size(), adj2)); // 1

        List<List<Integer>> adj3 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList()
        );
        System.out.println("SCC Count (adj3): " + scc.countSCCs(adj3.size(), adj3)); // 2
    }
}

