package graphs.disjointset;
/*
PROBLEM:
You're given a binary matrix grid[n][n]. You can change **at most one 0 to 1**.
Return the size of the largest island after the change.

DEFINITION:
- Island = group of 1s connected 4-directionally.
- You are allowed to flip at most one 0 → 1 to maximize island size.

INTUITION:
1. Label each island with a unique ID (starting from 2 to avoid conflict with 0/1).
2. Store area of each island ID in a map.
3. For every 0 in the grid, simulate turning it into 1 and check adjacent island IDs.
4. Merge areas of those unique neighbors to get potential max area.

DRY RUN:
grid = [[1, 0], [0, 1]]
- Label 1st island (1) → id=2, area=1
- Label 2nd island (2) → id=3, area=1
- Check cell (0,1): neighbors → id=2 and id=3 → total area = 1+1+1 = 3
=> Output: 3

TIME & SPACE:
- Time: O(n^2)
- Space: O(n^2)
*/

import java.util.*;

public class LargestIslandAfterFlip {

    static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        Map<Integer, Integer> islandArea = new HashMap<>();
        int islandId = 2;

        // Step 1: Label all islands with unique IDs and calculate their area
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, islandId);
                    islandArea.put(islandId, area);
                    islandId++;
                }
            }
        }

        int max = 0;
        boolean hasZero = false;

        // Step 2: Try flipping each 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    hasZero = true;
                    Set<Integer> neighborIslands = new HashSet<>();
                    for (int[] d : DIRS) {
                        int ni = i + d[0], nj = j + d[1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                            if (grid[ni][nj] > 1) {
                                neighborIslands.add(grid[ni][nj]);
                            }
                        }
                    }
                    int sum = 1; // for the flipped cell
                    for (int id : neighborIslands) {
                        sum += islandArea.getOrDefault(id, 0);
                    }
                    max = Math.max(max, sum);
                }
            }
        }

        // Edge case: no 0s at all
        if (!hasZero) {
            return n * n;
        }

        return max;
    }

    private static int dfs(int[][] grid, int i, int j, int id) {
        int n = grid.length;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});
        grid[i][j] = id;
        int area = 1;

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            for (int[] d : DIRS) {
                int ni = cell[0] + d[0], nj = cell[1] + d[1];
                if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] == 1) {
                    grid[ni][nj] = id;
                    stack.push(new int[]{ni, nj});
                    area++;
                }
            }
        }

        return area;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1, 0}, {0, 1}};
        int[][] grid2 = {{1, 1}, {1, 0}};
        int[][] grid3 = {{1, 1}, {1, 1}};

        System.out.println("Example 1: " + largestIsland(grid1)); // 3
        System.out.println("Example 2: " + largestIsland(grid2)); // 4
        System.out.println("Example 3: " + largestIsland(grid3)); // 4
    }
}

