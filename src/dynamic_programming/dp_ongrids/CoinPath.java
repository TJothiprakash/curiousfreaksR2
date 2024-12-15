package dynamic_programming.dp_ongrids;

/*Given a n x n matrix such that each of its cells contains some coins. Count the number of ways to collect exactly k coins while moving from top left corner of the matrix to the bottom right. From a cell (i, j), you can only move to (i+1, j) or (i, j+1).

Example 1:

Input:
k = 12, n = 3
arr[] = [[1, 2, 3],
       [4, 6, 5],
       [3, 2, 1]]
Output:
2
Explanation:
There are 2 possible paths with exactly 12 coins, (1 + 2 + 6 + 2 + 1) and (1 + 2 + 3 + 5 + 1).
Example 2:

Input:
k = 16, n = 3
arr[] = [[1, 2, 3],
       [4, 6, 5],
       [9, 8, 7]]
Output:
0
Explanation:
There are no possible paths that lead to sum=16
Your Task:
You don't need to read input or print anything. Your task is to complete the function numberOfPath() which takes integers n, k and a 2D matrix arr[][] as input parameters and returns an integer denoting the number of possible paths.

Expected Time Complexity: O(n*n*k)
Expected Auxiliary Space: O(n*n*k)

Constraints:
1 <= k < 100
1 <= n < 100
0 <= arrij <= 200

*/
public class CoinPath {

    // Memoization cache to store results of subproblems
    private int[][][] memo;

    public static void main(String[] args) {
        CoinPath cp = new CoinPath();

        // Test case 1
        int[][] arr1 = {
                {1, 2, 3},
                {4, 6, 5},
                {3, 2, 1}
        };
        System.out.println(cp.countPaths(3, 12, arr1));  // Output: 2

        // Test case 2
        int[][] arr2 = {
                {1, 2, 3},
                {4, 6, 5},
                {9, 8, 7}
        };
        System.out.println(cp.countPaths(3, 16, arr2));  // Output: 0
    }

    public int countPaths(int n, int k, int[][] arr) {
        // Initialize memoization cache with -1 (indicating uncomputed states)
        memo = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int coins = 0; coins <= k; coins++) {
                    memo[i][j][coins] = -1;
                }
            }
        }

        // Call the recursive helper function starting from the top-left corner
        return dfs(0, 0, k, n, arr);
    }

    // Recursive function to count paths from (i, j) with remaining coins to collect
    private int dfs(int i, int j, int remainingCoins, int n, int[][] arr) {
        // If out of bounds or the remaining coins are negative, return 0
        if (i >= n || j >= n || remainingCoins < 0) {
            return 0;
        }

        // If we reached the bottom-right corner, check if we have exactly 0 remaining coins
        if (i == n - 1 && j == n - 1) {
            return remainingCoins == 0 ? 1 : 0;
        }

        // Check if the result for this state is already computed
        if (memo[i][j][remainingCoins] != -1) {
            return memo[i][j][remainingCoins];
        }

        // Take the current coin from arr[i][j]
        int currentCoin = arr[i][j];

        // Calculate number of paths by moving down and right
        int right = dfs(i, j + 1, remainingCoins - currentCoin, n, arr);
        int down = dfs(i + 1, j, remainingCoins - currentCoin, n, arr);

        // Store the result in the memoization cache
        memo[i][j][remainingCoins] = right + down;

        return memo[i][j][remainingCoins];
    }
}

