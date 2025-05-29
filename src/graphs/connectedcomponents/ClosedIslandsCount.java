package graphs.connectedcomponents;

public class ClosedIslandsCount {
    public int closedIslands(int[][] mat, int N, int M) {
        // Step 1: Remove all islands connected to borders
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ((i == 0 || j == 0 || i == N - 1 || j == M - 1) && mat[i][j] == 1) {
                    dfs(mat, i, j, N, M);
                }
            }
        }

        // Step 2: Count closed islands
        int count = 0;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (mat[i][j] == 1) {
                    dfs(mat, i, j, N, M);
                    count++;
                }
            }
        }

        return count;
    }

    // DFS to mark visited land
    private void dfs(int[][] mat, int i, int j, int N, int M) {
        if (i < 0 || j < 0 || i >= N || j >= M || mat[i][j] == 0) {
            return;
        }

        mat[i][j] = 0; // mark visited

        dfs(mat, i + 1, j, N, M);
        dfs(mat, i - 1, j, N, M);
        dfs(mat, i, j + 1, N, M);
        dfs(mat, i, j - 1, N, M);
    }

    public static void main(String[] args) {
        int[][] mat = {
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1}
        };

        ClosedIslandsCount sol = new ClosedIslandsCount();
        int result = sol.closedIslands(mat, mat.length, mat[0].length);
        System.out.println("Number of Closed Islands: " + result);
    }

}
