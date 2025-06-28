package graphs.toposort;
import java.util.*;

/*
✅ QUESTION:
You are given `n` tasks and a list of prerequisites.
Return any valid task order such that all prerequisites are satisfied.
Return empty array if impossible (cycle exists).

Each prerequisite [a, b] means: to pick `a`, finish `b` first.
*/

public class TaskOrderDFS {

    public static int[] findTaskOrder(int n, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] pre : prerequisites) {
            adj[pre[1]].add(pre[0]);  // b -> a
        }

        int[] color = new int[n]; // 0 = unvisited, 1 = visiting, 2 = visited
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (color[i] == 0 && hasCycle(i, adj, color, result)) {
                return new int[0]; // cycle detected
            }
        }

        Collections.reverse(result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private static boolean hasCycle(int node, List<Integer>[] adj, int[] color, List<Integer> result) {
        color[node] = 1; // visiting

        for (int neighbor : adj[node]) {
            if (color[neighbor] == 1) return true; // back edge
            if (color[neighbor] == 0 && hasCycle(neighbor, adj, color, result)) return true;
        }

        color[node] = 2; // visited
        result.add(node); // add post DFS
        return false;
    }

    // ✅ Validate ordering
    public static boolean isValidOrder(int n, int[][] prerequisites, int[] order) {
        if (order.length != n) return false;
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < order.length; i++) pos.put(order[i], i);
        for (int[] pre : prerequisites) {
            if (pos.get(pre[0]) < pos.get(pre[1])) return false; // a must come after b
        }
        return true;
    }

    // 🔍 TEST CASES
    public static void main(String[] args) {
        int n1 = 2;
        int[][] prereq1 = {{1, 0}};
        int[] order1 = findTaskOrder(n1, prereq1);
        System.out.println("Order 1: " + Arrays.toString(order1));
        System.out.println("Valid: " + isValidOrder(n1, prereq1, order1));

        int n2 = 4;
        int[][] prereq2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] order2 = findTaskOrder(n2, prereq2);
        System.out.println("\nOrder 2: " + Arrays.toString(order2));
        System.out.println("Valid: " + isValidOrder(n2, prereq2, order2));

        int n3 = 3;
        int[][] prereq3 = {{0, 1}, {1, 2}, {2, 0}};
        int[] order3 = findTaskOrder(n3, prereq3);
        System.out.println("\nOrder 3: " + Arrays.toString(order3));
        System.out.println("Valid: " + isValidOrder(n3, prereq3, order3));
    }
}

/*
🕰️ TIME & SPACE COMPLEXITY:

Time:  O(n + m) → n = #tasks, m = #prerequisites
Space: O(n + m) → adjacency list + recursion + result

✨ This DFS approach is ideal when:
- You want recursion
- You want to build the actual task order
- You want cycle detection during traversal
*/
