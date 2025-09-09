package practicesessions.sept_8;

public class LongestIncreasingPath {

    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    private int rows, cols;
    private int[][] dp;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        rows = matrix.length;
        cols = matrix[0].length;
        dp = new int[rows][cols];

        int maxPath = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j));
            }
        }
        return maxPath;
    }

    private int dfs(int[][] matrix, int r, int c) {
        // If already computed, return memoized value
        if (dp[r][c] != 0) return dp[r][c];

        int maxLen = 1; // at least the cell itself
        for (int[] dir : dirs) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                    && matrix[nr][nc] > matrix[r][c]) {
                maxLen = Math.max(maxLen, 1 + dfs(matrix, nr, nc));
            }
        }

        dp[r][c] = maxLen; // memoize
        return maxLen;
    }

    public static void main(String[] args) {
        LongestIncreasingPath solver = new LongestIncreasingPath();
        int[][] matrix = {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };
        System.out.println(solver.longestIncreasingPath(matrix)); // Output: 4
    }
}
