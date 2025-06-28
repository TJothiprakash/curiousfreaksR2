package graphs.toposort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
✅ QUESTION:
Find all safe nodes in a directed graph —
 where all paths from that node eventually lead to a terminal node.

 A directed graph of V vertices and E edges is given in the form of an adjacency list adj.
  Each node of the graph is labelled with a distinct integer in the range 0 to V - 1.

A node is a terminal node if there are no outgoing edges.
 A node is a safe node if every possible path starting from that node leads to a terminal node.

You have to return an array containing all the safe nodes of the graph.
The answer should be sorted in ascending order.
*/

public class EventualSafeNodes {

    public static @NotNull List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        int[] state = new int[V]; // 0 = unvisited, 1 = visiting, 2 = safe
        List<Integer> result = new ArrayList<>();

        for (int node = 0; node < V; node++) {
            if (isSafe(node, adj, state)) {
                result.add(node);
            }
        }

        return result;
    }

    private static boolean isSafe(int node, List<List<Integer>> adj, int @NotNull [] state) {
        if (state[node] != 0) return state[node] == 2;

        state[node] = 1; // mark as visiting

        for (int neighbor : adj.get(node)) {
            if (!isSafe(neighbor, adj, state)) {
                return false; // cycle or unsafe path detected
            }
        }

        state[node] = 2; // safe node
        return true;
    }

    // 🔍 TEST CASES
    public static void main(String[] args) {
        List<List<Integer>> graph1 = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(5),
                Arrays.asList(0),
                Arrays.asList(5),
                Arrays.asList(),
                Arrays.asList()
        );
        System.out.println("Safe Nodes: " + eventualSafeNodes(7, graph1)); // [2, 4, 5, 6]

        List<List<Integer>> graph2 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList()
        );
        System.out.println("Safe Nodes: " + eventualSafeNodes(4, graph2)); // [0, 1, 2, 3]

        List<List<Integer>> graph3 = Arrays.asList(
                Arrays.asList(1,4,5,6),
                Arrays.asList(2),
                Arrays.asList(0)
        );
        System.out.println("Safe Nodes: " + eventualSafeNodes(3, graph3)); // []
    }
}

/*
🧠 INTUITION:
- Use DFS to mark nodes part of cycles.
- Memoize results with `state[]` to avoid re-computation.
- Once a node is proven safe (no cycles from it), mark it and reuse.

🕰️ TIME: O(V + E)
🧠 SPACE: O(V) — for recursion and memo
*/
