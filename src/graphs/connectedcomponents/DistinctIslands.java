package graphs.connectedcomponents;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DistinctIslands {
    public int countDistinctIslands(int[] @NotNull [] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        Set<String> uniqueIslands = new HashSet<>();

        // Directions: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        String[] dir = {"U", "D", "L", "R"};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    StringBuilder path = new StringBuilder();
                    dfs(i, j, grid, visited, path, dx, dy, dir, "S"); // 'S' = start
                    uniqueIslands.add(path.toString());
                    System.out.println("Island shape: " + path);
                }
            }
        }

        return uniqueIslands.size();
    }

    private void dfs(int i, int j, int[][] grid, boolean[]  @NotNull [] visited,
                     @NotNull StringBuilder path, int[] dx, int[] dy, String[] dir, String move) {
        visited[i][j] = true;
        path.append(move); // Append direction we came from

        for (int k = 0; k < 4; k++) {
            int ni = i + dx[k];
            int nj = j + dy[k];

            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length
                    && grid[ni][nj] == 1 && !visited[ni][nj]) {
                dfs(ni, nj, grid, visited, path, dx, dy, dir, dir[k]);
            }
        }

        path.append("B"); // Append 'B' for backtrack
    }

    public static void main(String[] args) {
        DistinctIslands obj = new DistinctIslands();

        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };
        System.out.println("Output: " + obj.countDistinctIslands(grid1)); // 1

        int[][] grid2 = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };
        System.out.println("Output: " + obj.countDistinctIslands(grid2)); // 3
    }
}
