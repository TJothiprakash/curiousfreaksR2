package graphs.disjointset;
/*
PROBLEM:
You are given `n` computers (0 to n-1) connected by ethernet cables: connections[i] = [a, b].
Any computer can reach another directly or indirectly.
You can remove a cable between any two directly connected computers and place it between any disconnected pair.

Return the minimum number of operations needed to connect all computers.
Return -1 if not possible.

INTUITION:
- To fully connect `n` nodes, you need at least `n - 1` connections.
- If connections.length < n - 1 → Not possible.
- Else, use Union-Find to find number of components `k`.
- Need (k - 1) cables to connect these components.

DRY RUN:
n = 4, connections = [[0,1],[0,2],[1,2]]
connections.length = 3 (ok)
Components:
- 0–1, 0–2 connected
- 3 is alone → 2 components
→ Need 1 cable → return 1

TIME & SPACE:
- Time: O(N + E)
- Space: O(N)
*/

public class NetworkConnector {

    public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            System.out.println("Not enough connections. Need at least " + (n - 1));
            return -1;
        }

        UnionFind uf = new UnionFind(n);

        for (int[] edge : connections) {
            System.out.println("\nProcessing connection: " + edge[0] + " - " + edge[1]);
            uf.union(edge[0], edge[1]);
        }

        int components = uf.getComponents();
        System.out.println("\nTotal connected components: " + components);
        int needed = components - 1;
        System.out.println("Minimum cables needed to connect all components: " + needed);

        return needed;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        int components;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            components = size;
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            System.out.println("Initialized Union-Find with " + size + " nodes.");
        }

        public int find(int x) {
            if (parent[x] != x) {
                System.out.println("Path compression for node " + x + ", current parent: " + parent[x]);
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            System.out.println("Union nodes " + x + " and " + y);
            System.out.println("Root of " + x + ": " + rootX + ", Root of " + y + ": " + rootY);

            if (rootX == rootY) {
                System.out.println("They are already in the same component.");
                return;
            }

            // Union by rank
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                System.out.println("Attaching root " + rootY + " under root " + rootX);
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                System.out.println("Attaching root " + rootX + " under root " + rootY);
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
                System.out.println("Equal rank: Attaching root " + rootY + " under root " + rootX + " and increasing rank of " + rootX);
            }

            components--;
            System.out.println("Components count reduced to: " + components);
        }

        public int getComponents() {
            return components;
        }
    }

    public static void main(String[] args) {
        int[][] conn1 = {{0,1},{0,2},{1,2}};
        int[][] conn2 = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        int[][] conn3 = {{0,1},{0,2},{0,3},{1,2}};

        System.out.println("----- Example 1 -----");
        System.out.println("Result: " + makeConnected(4, conn1)); // Output: 1

        System.out.println("\n----- Example 2 -----");
        System.out.println("Result: " + makeConnected(6, conn2)); // Output: 2

        System.out.println("\n----- Example 3 -----");
        System.out.println("Result: " + makeConnected(6, conn3)); // Output: -1
    }
}

