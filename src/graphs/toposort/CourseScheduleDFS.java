package graphs.toposort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
✅ QUESTION:
You are given numCourses and prerequisites as pairs [a, b].
To take course a, you must take course b first (edge: b → a).
Return any valid order to complete all courses, or empty array if impossible.
*/

public class CourseScheduleDFS {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) adj[i] = new ArrayList<>();

        for (int[] pre : prerequisites) {
            adj[pre[1]].add(pre[0]); // b → a
        }

        int[] color = new int[numCourses]; // 0 = unvisited, 1 = visiting, 2 = visited
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (color[i] == 0 && hasCycle(i, adj, color, result)) {
                return new int[0]; // cycle exists
            }
        }

        Collections.reverse(result); // reverse post-order
        return result.stream().mapToInt(i -> i).toArray();
    }

    private static boolean hasCycle(int node, List<Integer>[] adj, int[] color, List<Integer> result) {
        color[node] = 1; // visiting

        for (int neighbor : adj[node]) {
            if (color[neighbor] == 1) return true; // back edge → cycle
            if (color[neighbor] == 0 && hasCycle(neighbor, adj, color, result)) return true;
        }

        color[node] = 2; // visited
        result.add(node); // post-order append
        return false;
    }

    // ✅ TEST CASES
    public static void main(String[] args) {
        int numCourses1 = 2;
        int[][] pre1 = {{1, 0}};
        System.out.println("Order 1: " + Arrays.toString(findOrder(numCourses1, pre1)));

        int numCourses2 = 4;
        int[][] pre2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Order 2: " + Arrays.toString(findOrder(numCourses2, pre2)));

        int numCourses3 = 1;
        int[][] pre3 = {};
        System.out.println("Order 3: " + Arrays.toString(findOrder(numCourses3, pre3)));

        int numCourses4 = 2;
        int[][] pre4 = {{0, 1}, {1, 0}};
        System.out.println("Order 4: " + Arrays.toString(findOrder(numCourses4, pre4))); // cycle
    }
}

/*
🕰️ TIME & SPACE COMPLEXITY:

Time: O(N + E) = O(numCourses + prerequisites.length)
Space: O(N + E) — adjacency list, recursion stack, result

✨ Suitable for up to 2000 nodes as per constraints
*/

