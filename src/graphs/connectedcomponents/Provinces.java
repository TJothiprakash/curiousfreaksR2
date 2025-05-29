package graphs.connectedcomponents;
public class Provinces {
    public static int numProvinces(int V, int[][] adj) {
        boolean[] visited = new boolean[V];
        int provinces = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited);
                provinces++;
            }
        }

        return provinces;
    }

    private static void dfs(int node, int[][] adj, boolean[] visited) {
        visited[node] = true;

        for (int j = 0; j < adj.length; j++) {
            if (adj[node][j] == 1 && !visited[j]) {
                dfs(j, adj, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] adj1 = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        };
        System.out.println(numProvinces(3, adj1)); // Output: 2

        int[][] adj2 = {
                {1, 1},
                {1, 1}
        };
        System.out.println(numProvinces(2, adj2)); // Output: 1
    }
}

