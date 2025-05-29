package graphs.connectedcomponents;

public class IslandCounter {
    static int n, m;
    static boolean[][] visited;

    public static int numIslands(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        visited = new boolean[n][m];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 'L') {
                    System.out.println("Starting new island DFS from: (" + i + ", " + j + ")");
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    private static void dfs(char[][] grid, int x, int y) {
        visited[x][y] = true;

        for (int dir = 0; dir < 8; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m &&
                    !visited[nx][ny] && grid[nx][ny] == 'L') {
                dfs(grid, nx, ny);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid1 = {
                {'L', 'L', 'W', 'W', 'W'},
                {'W', 'L', 'W', 'W', 'L'},
                {'L', 'W', 'W', 'L', 'L'},
                {'W', 'W', 'W', 'W', 'W'},
                {'L', 'W', 'L', 'L', 'W'}
        };

        char[][] grid2 = {
                {'W', 'L', 'L', 'L', 'W', 'W', 'W'},
                {'W', 'W', 'L', 'L', 'W', 'L', 'W'}
        };

        System.out.println("Islands in grid1: " + numIslands(grid1)); // Output: 4
        System.out.println("Islands in grid2: " + numIslands(grid2)); // Output: 2
    }
}
