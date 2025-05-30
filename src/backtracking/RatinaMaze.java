package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RatinaMaze {
    public static void main(String[] args) {
        int[][] mat = {{1, 0, 0, 0}, {1, 1, 0, 1}, {0, 1, 0, 0}, {1, 1, 1, 1}};
        int n = mat.length;
        System.out.println(mat[0][0] + " " + mat[0][1] + " " + mat[0][2] + " " + mat[0][3]);
        System.out.println(mat[1][0] + " " + mat[1][1] + " " + mat[1][2] + " " + mat[1][3]);
        System.out.println(mat[2][0] + " " + mat[2][1] + " " + mat[2][2] + " " + mat[2][3]);
        System.out.println(mat[3][0] + " " + mat[3][1] + " " + mat[3][2] + " " + mat[3][3]);
        RatinaMaze sol = new RatinaMaze();
        List<String> result = sol.findPath(mat, n);
        System.out.println(result);

    }

    public List<String> findPath(int[][] mat, int n) {
        List<String> result = new ArrayList<>();
        if (mat[0][0] == 0 || mat[n - 1][n - 1] == 0) return result; // No path if start or end is blocked

        boolean[][] visited = new boolean[n][n];
        backtrack(mat, n, 0, 0, "", visited, result);

        Collections.sort(result); // Lexicographical order
        return result;
    }

    private void backtrack(int[][] mat, int n, int i, int j, String path, boolean[][] visited, List<String> result) {
        if (i == n - 1 && j == n - 1) {
            result.add(path);
            return;
        }

        // Mark current cell as visited
        visited[i][j] = true;

        // Define direction vectors and corresponding path letters in lexicographical order
        int[] dx = {+1, 0, 0, -1};         // D, L, R, U
        int[] dy = {0, -1, +1, 0};
        char[] dir = {'D', 'L', 'R', 'U'};

        for (int d = 0; d < 4; d++) {
            int nextX = i + dx[d];
            int nextY = j + dy[d];

            if (isSafe(nextX, nextY, n, mat, visited)) {
                backtrack(mat, n, nextX, nextY, path + dir[d], visited, result);
            }
        }

        // Backtrack
        visited[i][j] = false;
    }

    private boolean isSafe(int x, int y, int n, int[][] mat, boolean[][] visited) {
        return (x >= 0 && y >= 0 && x < n && y < n && mat[x][y] == 1 && !visited[x][y]);
    }
}

