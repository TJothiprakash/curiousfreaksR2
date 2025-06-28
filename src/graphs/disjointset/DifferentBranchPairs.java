package graphs.disjointset;
// File: DifferentBranchPairs.java

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DifferentBranchPairs {

    // ==========================
    // 📌 Problem
    // ==========================
    /*
     * The director of your college is planning to send 2 people to the ICPC regionals.
     * He wants them to be from different branches. You are given N students and P pairs.
     * Each pair denotes two students from the same branch.
     * Return the number of valid pairs such that both students are from different branches.
     */

    // ==========================
    // 💡 Intuition
    // ==========================
    /*
     * Consider each student as a node in a graph.
     * Each pair forms an undirected edge indicating same-branch students.
     * So, we can group students into connected components (branches).
     *
     * Total number of ways to form pairs from N students = N * (N - 1) / 2
     * Invalid pairs (same branch) = sum of (size * (size - 1) / 2) for each component
     * Valid pairs = total - invalid
     */

    // ==========================
    // 🔍 Dry Run
    // ==========================
    /*
     * Example:
     * N = 5
     * pairs = {{0, 1}, {2, 3}, {0, 4}}
     * Components: [0, 1, 4], [2, 3]
     * Sizes: 3, 2
     * Total pairs = 5 * 4 / 2 = 10
     * Invalid = (3 * 2 / 2) + (2 * 1 / 2) = 3 + 1 = 4
     * Valid = 10 - 4 = 6 ✅
     */

    // ==========================
    // ✅ Code
    // ==========================

    static class Logger {
        static void log(String msg) {
            System.out.println("[LOG] " + msg);
        }
    }

    public static int numberOfPairs(int N, int[][] pairs) {
        Logger.log("Total Students: " + N + ", Pairs Provided: " + pairs.length);

        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());

        for (int[] pair : pairs) {
            adj.get(pair[0]).add(pair[1]);
            adj.get(pair[1]).add(pair[0]);
        }

        // DFS to find connected components
        boolean[] visited = new boolean[N];
        List<Integer> componentSizes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                int size = dfs(i, adj, visited);
                componentSizes.add(size);
                Logger.log("Component Found: Size = " + size);
            }
        }

        // Total pairs from all N students
        long totalPairs = (long) N * (N - 1) / 2;

        // Invalid pairs within same component (branch)
        long invalidPairs = 0;
        for (int size : componentSizes) {
            invalidPairs += (long) size * (size - 1) / 2;
        }

        Logger.log("Total Pairs: " + totalPairs);
        Logger.log("Invalid Pairs (Same Branch): " + invalidPairs);

        return (int) (totalPairs - invalidPairs);
    }

    private static int dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;
        int count = 1;

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                    count++;
                }
            }
        }

        return count;
    }

    // ==========================
    // 🧪 Test Cases
    // ==========================
    public static void main(String[] args) {
        int N1 = 5;
        int[][] pairs1 = {{0, 1}, {2, 3}, {0, 4}};
        System.out.println("Output 1: " + numberOfPairs(N1, pairs1)); // Expected: 6

        int N2 = 4;
        int[][] pairs2 = {{0, 2}};
        System.out.println("Output 2: " + numberOfPairs(N2, pairs2)); // Expected: 5

        int N3 = 6;
        int[][] pairs3 = {{0, 1}, {2, 3}, {4, 5}};
        System.out.println("Output 3: " + numberOfPairs(N3, pairs3)); // Expected: 12

        int N4 = 3;
        int[][] pairs4 = {};
        System.out.println("Output 4: " + numberOfPairs(N4, pairs4)); // Expected: 3
    }

    // ==========================
    // ⏱️ Complexity
    // ==========================
    /*
     * Time Complexity:
     * - Building graph: O(P)
     * - DFS: O(N + P)
     * - Final computation: O(C) where C = number of components
     * ✅ Total: O(N + P)
     *
     * Space Complexity:
     * - Adjacency List: O(N + P)
     * - Visited array: O(N)
     * ✅ Total: O(N + P)
     */
}

