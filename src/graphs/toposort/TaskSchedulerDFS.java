package graphs.toposort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/*
✅ QUESTION:
Given N tasks and a list of prerequisites [a, b] meaning b → a,
return "Yes" if it's possible to complete all tasks, otherwise "No".

Solve using DFS + cycle detection.
*/

public class TaskSchedulerDFS {

    public static @NotNull String canFinish(int N, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[N];
        for (int i = 0; i < N; i++) adj[i] = new ArrayList<>();

        for (int[] pre : prerequisites) {
            int u = pre[1], v = pre[0]; // u → v
            adj[u].add(v);
        }

        int[] color = new int[N]; // 0 = unvisited, 1 = visiting, 2 = visited

        for (int i = 0; i < N; i++) {
            if (color[i] == 0) {
                if (hasCycleDFS(i, adj, color)) return "No";
            }
        }
        return "Yes";
    }

    private static boolean hasCycleDFS(int node, List<Integer> @NotNull [] adj, int @NotNull [] color) {
        color[node] = 1; // mark as visiting

        for (int neighbor : adj[node]) {
            if (color[neighbor] == 1) return true;      // back edge found
            if (color[neighbor] == 0 && hasCycleDFS(neighbor, adj, color)) return true;
        }

        color[node] = 2; // mark as fully visited
        return false;
    }

    // 🔍 TEST CASES
    public static void main(String[] args) {
        int N1 = 4;
        int[][] prereq1 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Test 1: " + canFinish(N1, prereq1)); // Yes

        int N2 = 2;
        int[][] prereq2 = {{1, 0}, {0, 1}};
        System.out.println("Test 2: " + canFinish(N2, prereq2)); // No

        int N3 = 6;
        int[][] prereq3 = {{1, 0}, {2, 1}, {3, 2}, {4, 5}};
        System.out.println("Test 3: " + canFinish(N3, prereq3)); // Yes

        int N4 = 3;
        int[][] prereq4 = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Test 4: " + canFinish(N4, prereq4)); // No
    }
}

/*
🕰️ TIME & SPACE COMPLEXITY:

Time:  O(N + P)
Space: O(N + P) — graph + recursion + color[]

✨ DFS is preferred when:
- You’re not comfortable with indegrees
- You want more control on traversal
- You're used to recursion

🔥 Key Idea:
- If we revisit a node that's being explored (color 1), it forms a cycle.
*/

