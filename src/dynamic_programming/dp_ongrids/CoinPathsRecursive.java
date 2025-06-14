package dynamic_programming.dp_ongrids;

public class CoinPathsRecursive {

    public static int countPaths(int[][] grid, int i, int j, int k) {
        int n = grid.length;

        // Out of bounds or sum becomes negative
        if (i >= n || j >= n || k < 0) return 0;

        // Base case: Reached bottom-right
        if (i == n - 1 && j == n - 1) {
            return (grid[i][j] == k) ? 1 : 0;
        }

        int remaining = k - grid[i][j];
        // Move right and down
        int right = countPaths(grid, i, j + 1, remaining);
        int down = countPaths(grid, i + 1, j, remaining);

        return right + down;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 6, 5},
                {3, 2, 1}
        };
        int k = 12;
        System.out.println(countPaths(arr, 0, 0, k)); // Output: 2
    }
}

class CoinPathsMemo {

    public static int countPaths(int[][] grid, int i, int j, int k, int[][][] dp) {
        int n = grid.length;

        if (i >= n || j >= n || k < 0) return 0;

        if (i == n - 1 && j == n - 1) {
            return (grid[i][j] == k) ? 1 : 0;
        }

        if (dp[i][j][k] != -1) return dp[i][j][k];

        int remaining = k - grid[i][j];

        int right = countPaths(grid, i, j + 1, remaining, dp);
        int down = countPaths(grid, i + 1, j, remaining, dp);

        dp[i][j][k] = right + down;
        return dp[i][j][k];
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 6, 5},
                {3, 2, 1}
        };
        int k = 12;
        int n = arr.length;

        int[][][] dp = new int[n][n][k + 1];
        for (int[][] rows : dp) {
            for (int[] cols : rows) {
                java.util.Arrays.fill(cols, -1);
            }
        }

        System.out.println(countPaths(arr, 0, 0, k, dp)); // Output: 2
    }
}

