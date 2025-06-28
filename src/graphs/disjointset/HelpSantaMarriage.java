package graphs.disjointset;
/*
PROBLEM:
Santa wants to marry the girl with the most connections. Girls are connected in a graph.
- A group = connected component
- k = size of the largest connected group
- Return the k-th prime number (1 is considered composite, 2 is 1st prime)
- If no connections (m == 0), return -1

INTUITION:
- Build an undirected graph using adjacency list.
- Run BFS/DFS to find all connected components and track their sizes.
- Pick the maximum component size (k)
- Return the k-th prime number

DRY RUN:
Input:
n = 10
g = {{1,2},{2,3},{3,4},{4,5},{6,7},{9,10}}

Connected Components:
1: 1–2–3–4–5 (size 5)
2: 6–7 (size 2)
3: 8 (isolated)
4: 9–10 (size 2)
Max size = 5 ⇒ Return 5th prime ⇒ 11

TIME & SPACE:
- Time: O(N + M)
- Space: O(N + M)
*/

import java.util.*;

public class HelpSantaMarriage {

    static final int MAX = 100005;
    static List<Integer> primes = new ArrayList<>();

    // Precompute primes using Sieve of Eratosthenes
    static void precomputePrimes() {
        boolean[] isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 not prime

        for (int i = 2; i * i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j += i)
                    isPrime[j] = false;
            }
        }

        for (int i = 2; i < MAX; i++) {
            if (isPrime[i]) primes.add(i);
        }
    }

    public static int helpSanta(int n, int m, int[][] g) {
        if (m == 0) return -1;

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int[] edge : g) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n + 1];
        int maxGroupSize = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                int size = dfs(i, adj, visited);
                maxGroupSize = Math.max(maxGroupSize, size);
            }
        }

        return primes.get(maxGroupSize - 1); // k-th prime (0-indexed)
    }

    private static int dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        int count = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    count++;
                    stack.push(neighbor);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        precomputePrimes();

        int[][] g1 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {6, 7}, {9, 10}};
        int[][] g2 = {{1, 2}};
        int[][] g3 = {}; // No connections

        System.out.println("Example 1: " + helpSanta(10, 6, g1)); // Output: 11
        System.out.println("Example 2: " + helpSanta(2, 1, g2));  // Output: 3
        System.out.println("Example 3: " + helpSanta(5, 0, g3));  // Output: -1
    }
}
