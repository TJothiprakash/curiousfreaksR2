package practicesessions.sept_27.dsa;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBinaryPathMatrix {

    private static final int[][] DIRECTIONS = {
            {-1,-1}, {-1,0}, {-1,1},
            {0,-1},          {0,1},
            {1,-1},  {1,0},  {1,1}
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (grid[0][0] == 1 || grid[n-1][m-1] == 1) return -1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // {row, col, pathLength}
        grid[0][0] = 1; // mark as visited

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], len = curr[2];

            if (r == n - 1 && c == m - 1) return len; // reached end

            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 0) {
                    queue.offer(new int[]{nr, nc, len + 1});
                    grid[nr][nc] = 1; // mark visited
                }
            }
        }

        return -1; // no path found
    }
}
