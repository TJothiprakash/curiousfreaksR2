package graphs.disjointset;

/*
PROBLEM:
You are given `n` computers (0 to n-1) and a list of connections [a, b] (bidirectional cables).
You can remove a connection and reconnect it elsewhere.

Return the minimum number of operations to connect all computers.
Return -1 if not enough connections.

INTUITION:
- To fully connect `n` computers, you need at least `n - 1` connections.
- If you have fewer, return -1.
- Use Disjoint Set Union (DSU) to track components.
- For each connection, union the two computers.
- At the end, if there are `k` components, we need `k - 1` extra cables.

DRY RUN:
n = 4, connections = [[0,1],[0,2],[1,2]]

parent[] initialized: [0, 1, 2, 3]

union(0,1) → merge → parent[1] = 0
union(0,2) → merge → parent[2] = 0
union(1,2) → same root → skip

Components left = 2 → Need 1 cable → ✅ return 1

TIME & SPACE:
Time: O(N + E * α(N)) ≈ O(N + E)
Space: O(N)
*/

public class DisjointSet {

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
        System.out.println("\nTotal components: " + components);
        System.out.println("Extra cables needed: " + (components - 1));

        return components - 1;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        int components;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            System.out.println("Initialized DSU with " + n + " nodes");
        }

        public int find(int x) {
            if (parent[x] != x) {
                System.out.println("Path compression for node " + x);
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            System.out.println("Union of " + x + " and " + y);
            System.out.println("Root of " + x + ": " + rootX + ", Root of " + y + ": " + rootY);

            if (rootX == rootY) {
                System.out.println("Already connected.");
                return;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                System.out.println("Attaching root " + rootY + " under root " + rootX);
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                System.out.println("Attaching root " + rootX + " under root " + rootY);
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
                System.out.println("Equal rank: root " + rootY + " under root " + rootX + ", rank of " + rootX + " increased");
            }

            components--;
            System.out.println("Components reduced to: " + components);
        }

        public int getComponents() {
            return components;
        }
    }

    public static void main(String[] args) {
        int[][] conn1 = {{0,1},{0,2},{1,2}};
        System.out.println("Result: " + makeConnected(4, conn1)); // Expected: 1
    }
}


/*✅ Code We're Dry Running:
java
Copy
Edit
int[][] conn1 = {{0,1},{0,2},{1,2}};
System.out.println(makeConnected(4, conn1)); // Output: 1
📦 Initial State:
n = 4 → computers = {0, 1, 2, 3}

connections = [[0,1],[0,2],[1,2]]

We’ll use Union-Find to track components.

🚀 Step-by-step Execution:
1️⃣ Check if we have enough connections:
java
Copy
Edit
if (connections.length < n - 1) return -1;
connections.length = 3, n - 1 = 3 → ✅ enough

2️⃣ Initialize DSU:
java
Copy
Edit
UnionFind uf = new UnionFind(4);
Inside constructor:

java
Copy
Edit
parent: [0, 1, 2, 3]
rank:   [0, 0, 0, 0]
components = 4
3️⃣ Loop over connections:
🔄 Connection: [0, 1]
java
Copy
Edit
uf.union(0, 1);
find(0) = 0

find(1) = 1
→ Different roots → connect them

rank[0] == rank[1] → attach 1 under 0, increase rank of 0

Now:

java
Copy
Edit
parent: [0, 0, 2, 3]
rank:   [1, 0, 0, 0]
components: 3
🔄 Connection: [0, 2]
java
Copy
Edit
uf.union(0, 2);
find(0) = 0

find(2) = 2
→ Different roots → connect

rank[0] > rank[2] → attach 2 under 0

Now:

java
Copy
Edit
parent: [0, 0, 0, 3]
rank:   [1, 0, 0, 0]
components: 2
🔄 Connection: [1, 2]
java
Copy
Edit
uf.union(1, 2);
find(1) = find(0) = 0 (path compression)

find(2) = find(0) = 0 (path compression)
→ Same root → already connected → do nothing

No change.

✅ Final Check:
java
Copy
Edit
return uf.getComponents() - 1;
components = 2 → need 2 - 1 = 1 extra connection

🧾 Final Answer: 1
Explanation:
We have 2 components: {0,1,2} and {3}
We need 1 more cable to connect computer 3 → return 1 ✅

📌 State Recap:
java
Copy
Edit
parent: [0, 0, 0, 3]
components: 2
✅ Output:
java
Copy
Edit
Result: 1
*/
