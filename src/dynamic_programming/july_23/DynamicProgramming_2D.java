package dynamic_programming.july_23;

import java.util.*;

public class DynamicProgramming_2D {
    public static void main(String[] args) {

    }

    //paths in 2d array
    public List<List<Integer>> uniquePaths( int arr[][]) {
        int minSum[] = {Integer.MAX_VALUE};
        int n = arr.length;

        int m = arr[0].length;

        List<List<Integer>> result = new ArrayList<>();

        traverseGrid(n, m, 0, 0, arr, result, new ArrayList<>(), minSum);
        return result;
    }

    private void traverseGrid(int n, int m, int i, int j, int[][] arr, List<List<Integer>> result, List<Integer> currpath, int minsum[]) {
        if (i >= n || j >= m) return;
        currpath.add(arr[i][j]);
        if (i == n - 1 && j == m - 1) {
            int sum = currpath.stream().mapToInt(a -> a).sum();
            if (sum < minsum[0]) {
                minsum[0] = sum;
            }
            result.add(currpath);
            return;
        }

        traverseGrid(n, m, i + 1, j, arr, result, currpath, minsum);
        traverseGrid(n, m, i, j + 1, arr, result, currpath, minsum);

    }

    //  an obstacle is known as arr[i][j] =0;
    public List<List<Integer>> uniquePathswithObstacle(int arr[][]) {

        if (arr[0][0] == 0 || arr[arr.length - 1][arr[0].length - 1] == 0)
            return new ArrayList<>();// cant start or end the path traversal
        int n = arr.length;
        int m = arr[0].length;

        List<List<Integer>> result = new ArrayList<>();

        traverseGridwithObstacle(n, m, 0, 0, arr, result, new ArrayList<>());
        return result;
    }

    private void traverseGridwithObstacle(int n, int m, int i, int j, int[][] arr, List<List<Integer>> result, List<Integer> currpath) {
        if (i >= n || j >= m || arr[i][j] == 0) return;
        currpath.add(arr[i][j]);
        if (i == n - 1 && j == m - 1) {

            result.add(new ArrayList<>(currpath));
            return;
        }
        traverseGridwithObstacle(n, m, i + 1, j, arr, result, currpath);
        traverseGridwithObstacle(n, m, i, j + 1, arr, result, currpath);

    }


    public int minPathSuminGrid(int grid[][]) {
        int n = grid.length;

        int m = grid[0].length;
        Map<Integer, Integer> memo = new HashMap<>();
        int minSum = 0;
        minPath(n, m, 0, 0, grid, minSum, memo);
        return minSum;

    }

    private void minPath(int n, int m, int i, int j, int[][] grid, int minSum, Map<Integer, Integer> memo) {

    }

    // 2D triangle traversal
    public OptionalInt trangleTravesrsalPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int maxPathSum = 0;
//        List<Integer> currpath =new ArrayList<>();
        // starting from all elements from the first row
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for (int j = 0; j < n; j++) {
            triangleMaxPathUtil(i, j, n, m, grid, maxPathSum, list);
        }
        return list.stream().mapToInt(a -> a).max();
    }

    private void triangleMaxPathUtil(int i, int j, int n, int m, int[][] grid, int maxPathSum, List<Integer> list) {
        if (i >= n || j >= m) return;

        if (i == n - 1) {
            list.add(maxPathSum);
        } else {

            triangleMaxPathUtil(i + 1, j, n, m, grid, grid[i][j] + maxPathSum, list);
            triangleMaxPathUtil(i + 1, j + 1, n, m, grid, grid[i][j] + maxPathSum, list);
            triangleMaxPathUtil(i + 1, j - 1, n, m, grid, grid[i][j] + maxPathSum, list);
        }

//        list.remove(list.size() - 1);

    }

    public int maxPathSum(int[][] triangle) {
        int n = triangle.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        int max = Integer.MIN_VALUE;
        for (int j = 0; j < triangle[0].length; j++) {
            max = Math.max(max, dfs(triangle, 0, j, memo));
        }
        return max;
    }

    private int dfs(int[][] tri, int i, int j, int[][] memo) {
        if (i >= tri.length || j < 0 || j >= tri[i].length) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int down = dfs(tri, i + 1, j, memo);
        int downLeft = dfs(tri, i + 1, j - 1, memo);
        int downRight = dfs(tri, i + 1, j + 1, memo);

        return memo[i][j] = tri[i][j] + Math.max(down, Math.max(downLeft, downRight));
    }

    public int minPathSum(int[][] triangle) {
        int n = triangle.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < triangle[0].length; j++) {
            min = Math.min(min, dfs1(triangle, 0, j, memo));
        }
        return min;
    }

    private int dfs1(int[][] tri, int i, int j, int[][] memo) {
        if (i >= tri.length || j < 0 || j >= tri[i].length) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int down = dfs1(tri, i + 1, j, memo);
        int downLeft = dfs1(tri, i + 1, j - 1, memo);
        int downRight = dfs1(tri, i + 1, j + 1, memo);

        return memo[i][j] = tri[i][j] + Math.min(down, Math.min(downLeft, downRight));
    }

    public static int minFallingPathSumTrianlewithConstraint(int[][] grid) {
        int n = grid.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, dfs2(0, j, grid, memo));
        }
        return min;
    }

    private static int dfs2(int i, int j, int[][] grid, int[][] memo) {
        int n = grid.length;
        if (i == n) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        int min = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++) {
            if (k == j) continue; // skip same column
            min = Math.min(min, dfs2(i + 1, k, grid, memo));
        }

        memo[i][j] = grid[i][j] + (min == Integer.MAX_VALUE ? 0 : min);
        return memo[i][j];
    }

}
