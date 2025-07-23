package graphs.july_22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BridgesinGraph {
    public static void main(String[] args) {

    }
    /*A critical connection refers to an edge that, upon removal, will make it impossible for certain nodes to reach each other through any path. You are given an undirected connected graph with v vertices and e edges where each vertex is distinct and ranges from 0 to v-1, and you have to find all critical connections in the graph. It is ensured that there is at least one such edge present.

Note: Return the connections in sorted order.

Examples:

Input:

Output:
0 1
0 2
Explanation:
On removing edge (0, 1), you will not be able to reach node 0 and 2 from node 1. Also, on removing edge (0, 2), you will not be able to reach node 0
and 1 from node 2.
Input:

Output:
2 3
Explanation:
The edge between nodes 2 and 3 is the only Critical connection in the given graph.
Constraints:
1 ≤ v, e ≤ 104

*/

    public List<List<Integer>> findAllBridges(int V, int[][] edges) {
        List<List<Integer>> result = new ArrayList<>();
        // build adjlist
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        int[] visited = new int[V];
        int[] tin = new int[V];
        int[] discovertime = new int[V];
        dfs(0, -1, visited, tin, discovertime, result, adjList);

        return result;
    }

    private int counter = 1;

    private void dfs(int node, int parent, int[] visited, int[] tin, int[] discovertime, List<List<Integer>> result, List<List<Integer>> adjList) {
        visited[node] = 1;

        tin[node] = discovertime[node] = counter++;

        for (int v : adjList.get(node)) {
            if (v == parent) continue;

            if (visited[v] == 0) {

                dfs(v, node, visited, tin, discovertime, result, adjList);
                discovertime[node] = Math.min(discovertime[node], discovertime[v]);
                if (discovertime[v] > tin[v]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(v);
                    temp.add(node);
                    result.add(temp);
                } else {
                    discovertime[v] = Math.min(discovertime[v], tin[v]);
                }
            }
        }

    }

    /*Given an adjacency list, adj of Directed Graph, Find the number of strongly connected components in the graph.


    Examples :

    Input: adj[][] = [[2, 3], [0], [1], [4], []]

    Output: 3
    Explanation: We can clearly see that there are 3 Strongly Connected Components in the Graph.

    Input: adj[][] = [[1], [2], [0]]

    Output: 1
    Explanation: All of the nodes are connected to each other. So, there's only one SCC.
    Input: adj[][] = [[1], []]
    Output: 2
    Constraints:
    2<=adj.size()<=106
    0<=edges<=adj.size()-1

    Expected Complexities*/
//    ---------------KOSARAJU's ALGORITHM------------------------------//
    public int kosarajuAlgorithm(List<List<Integer>> adjList, int N) {
        int[] visited = new int[N];
        Stack<Integer> stack = new Stack<>();

        // Step 1: DFS and fill stack
        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                dfs1(adjList, visited, stack, i);
            }
        }

        // Step 2: Transpose the graph
        List<List<Integer>> transposed = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            transposed.add(new ArrayList<>());
        }

        for (int u = 0; u < N; u++) {
            for (int v : adjList.get(u)) {
                transposed.get(v).add(u);
            }
        }

        // Step 3: DFS on transposed graph
        Arrays.fill(visited, 0);
        int scc = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited[node] == 0) {
                dfs2(transposed, visited, node);
                scc++;
            }
        }

        return scc;
    }

    private void dfs1(List<List<Integer>> adjList, int[] visited, Stack<Integer> stack, int node) {
        visited[node] = 1;
        for (int i : adjList.get(node)) {
            if (visited[i] == 0) {
                dfs1(adjList, visited, stack, i);
            }
        }
        stack.push(node);
    }

    private void dfs2(List<List<Integer>> adjList, int[] visited, int node) {
        visited[node] = 1;
        for (int i : adjList.get(node)) {
            if (visited[i] == 0) {
                dfs2(adjList, visited, i);
            }
        }
    }



}
