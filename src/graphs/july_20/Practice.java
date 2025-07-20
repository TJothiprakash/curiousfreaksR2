package graphs.july_20;

import java.util.Queue;

public class Practice {
    public static void main(String[] args) {

    }

    public int[][] floodFill(int matrix[][], int srcRow, int srcCol, int newColor) {
        // Check if the source coordinates are valid
        if(srcRow < 0 || srcRow >= matrix.length || srcCol < 0 || srcCol >= matrix[0].length ) {
            return null;//out of bounds
        }
        // If the source coordinates are out of bounds, return null
        if(matrix[srcRow][srcCol] == newColor) {
            return matrix; // No need to fill if the color is already the same
        }
        // If the source cell is already the new color, return the matrix as is
        // Store the color of the source cell
        int sourceColor = matrix[srcRow][srcCol];
        class Cell{
            int row, col, color;
        public Cell(int row, int col, int color) {
                this.row = row;
                this.col = col;
                this.color = color;
            }
        }
        Queue<Cell> queue = new LinkedList<>();
        boolean [][] visited = new boolean[ matrix.length][matrix[0].length];
        queue.add(new Cell(srcRow, srcCol, sourceColor));
        visited[srcRow][srcCol] = true;       
        int []dx = {0,0, -1, +1};
        int []dy = {-1,+1, 0,0,};

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int row = current.row;
            int col = current.col;
            int color = current.color;
            // Check all four directions (up, down, left, right)
            for( int i =0; i < 4 ; i++){
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if( newRow >=0 && newRow <matrix.length && newCol >=0 && newCol < matrix[0].length){
                if(matrix[newRow][newCol] == sourceColor && !visited[newRow][newCol]){
                    matrix[newRow][newCol] = newColor; // Update the color of the cell
                    visited[newRow][newCol] = true; // Mark the cell as visited
                    queue.add(new Cell(newRow, newCol, sourceColor)); // Add the cell to the queue for further exploration
                }
            }
            }
        }
        // If the source cell is not the new color, perform a flood fill
        // using a queue or stack to explore all connected cells of the same color
        // Update the color of the connected cells to the new color
        // Return the updated matrix

        return matrix;
    }

    public int[][] floodFillUsingDFS (int matrix [][], int srcRow, int srcCol, int newColor){
        // Check if the source coordinates are valid
        if(srcRow < 0 || srcRow >= matrix.length || srcCol < 0 || srcCol >= matrix[0].length ) {
            return null;//out of bounds
        }
        // If the source coordinates are out of bounds, return null
        if(matrix[srcRow][srcCol] == newColor) {
            return matrix; // No need to fill if the color is already the same
        }
        // If the source cell is already the new color, return the matrix as is
        // Store the color of the source cell
        int sourceColor = matrix[srcRow][srcCol];
        
        // Perform a flood fill using DFS
        dfs(srcRow, srcCol, newColor, sourceColor, matrix);
        // Update the color of the connected cells to the new color
        // Return the updated matrix
        // This method uses a recursive DFS approach to fill the connected cells
        // starting from the source cell and changing their color to the new color.
        // It checks all four directions (up, down, left, right) and fills the cells
        // that have the same color as the source cell.
        // The method modifies the input matrix in place and returns it.
        return matrix;
    }

    private void dfs(int row, int col, int newColor, int sourceColor, int [][]matrix){

        if(row >= 0 && row <matrix.length && col >= 0 && col < matrix[0].length && matrix[row][col]!= sourceColor )
        {
            return; // Out of bounds or not the source color
        }
        if(matrix[row][col] == sourceColor) {
            matrix[row][col] = newColor; // Update the color of the cell
            // Recursively call dfs for all four directions (up, down, left, right)
            dfs(row - 1, col, newColor, sourceColor, matrix); // Up
            dfs(row + 1, col, newColor, sourceColor, matrix); // Down
            dfs(row, col - 1, newColor, sourceColor, matrix); // Left
            dfs(row, col + 1, newColor, sourceColor, matrix); // Right

        }

    }


//     // ✅ Problem
// Given a 2D board filled with 'x' and 'o', capture all regions surrounded by 'x'. A region is captured by flipping all 'o's into 'x' in that region.

// 💡 Intuition
// Any 'o' that is:

// On the border, or

// Connected to a border 'o'
// must not be changed.

// So we:

// Start BFS/DFS from all border 'o's, and mark all connected 'o's as safe (say, mark them '@').

// Traverse entire matrix:

// Flip unmarked 'o' → 'x' (they're surrounded).

// Flip '@' → 'o' back.
 public int[][] replaceSymbolWithX(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null; // Invalid matrix
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        class Cell {
            int row, col;
            public Cell(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        Queue<Cell> queue = new LinkedList<>();

        // Step 1: Add all border 'o' cells to the queue
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 'o') queue.add(new Cell(i, 0));
            if (matrix[i][cols - 1] == 'o') queue.add(new Cell(i, cols - 1));
        }
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 'o') queue.add(new Cell(0, j));
            if (matrix[rows - 1][j] == 'o') queue.add(new Cell(rows - 1, j));
        }

        // Step 2: BFS from border 'o' cells and mark all connected 'o' as '@'
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            int r = cell.row;
            int c = cell.col;

            if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r][c] != 'o') continue;

            matrix[r][c] = '@';

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                queue.add(new Cell(nr, nc));
            }
        }

        // Step 3: Replace all remaining 'o' with 'x'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 'o') matrix[i][j] = 'x';
            }
        }

        // Step 4: Replace '@' back to 'o'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '@') matrix[i][j] = 'o';
            }
        }

        // Return the matrix (optional to convert to int[][] as per method signature)
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = matrix[i][j];

        return result;
    }
 public char[][] solve(char[][] board) {
        if (board == null || board.length == 0) return board;

        int rows = board.length;
        int cols = board[0].length;

        // Step 1: Run DFS from border 'o' cells
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'o') dfs(i, 0, board);
            if (board[i][cols - 1] == 'o') dfs(i, cols - 1, board);
        }

        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'o') dfs(0, j, board);
            if (board[rows - 1][j] == 'o') dfs(rows - 1, j, board);
        }

        // Step 2: Replace all remaining 'o' with 'x'
        // Step 3: Replace '@' (visited) back to 'o'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'o') board[i][j] = 'x';
                else if (board[i][j] == '@') board[i][j] = 'o';
            }
        }

        return board;
    }

    private void dfs(int row, int col, char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // Boundary check or already visited
        if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] != 'o') return;

        board[row][col] = '@'; // Mark visited

        // 4 directions
        dfs(row - 1, col, board); // up
        dfs(row + 1, col, board); // down
        dfs(row, col - 1, board); // left
        dfs(row, col + 1, board); // right
    }

//     // 🔍 Problem Recap:
// Given a grid of 0s and 1s, find the maximum area of a connected region of 1s.
// A region is defined as a group of 1s connected horizontally, vertically, or diagonally (8 directions total).

// 🧠 Intuition:
// For every cell containing 1:

// Start a DFS from that cell (if not visited).

// During DFS, count how many 1s are in that connected region.

// Track the maximum area seen so far.

// ✅ Key Things to Track:
// visited[][] array to avoid revisiting.

// For each unvisited 1, call dfs(i, j) and return area of region.

// Compare returned area with maxArea.

// 🔄 Directions Array (8 directions):
// java
// Copy
// Edit
// int[] dx = {-1, -1, -1,  0, 0, 1, 1, 1};
// int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};

        public int maxAreaOfisland( int [][]grid){
            if( grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0; // Invalid grid
            }
            int rows = grid.length;
            int cols = grid[0].length;
            boolean [][] visited = new boolean[rows][cols];
            int maxArea = 0;
            int []dx = {-1, -1, -1, 0, 0, 1, 1, 1};
            int []dy = {-1, 0, 1, -1, 1, -1, 0, 1};
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    if(grid[i][j] == 1 && !visited[i][j]) {
                        int area = dfs(i, j, grid, visited, dx, dy);
                        maxArea = Math.max(maxArea, area);
                    }
                }
            }
            return maxArea;
        }


        private int dfs( int row, int col, int [][]grid, boolean [][]visited, int []dz, int []dy){
            if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0 || visited[row][col]) {
                return 0; // Out of bounds or not part of the island
            }
            visited[row][col] = true; // Mark the cell as visited
            int area = 1; // Count this cell
            // Explore all 8 directions
            for(int i = 0; i < 8; i++) {
                area += dfs(row + dz[i], col + dy[i], grid, visited, dx, dy);
            }
            return area;


        }   
        
        
        
        public int maxAreaOfIslandBFS(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int maxArea = 0;

        // 8 directions: up, down, left, right, and diagonals
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = 0;
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int row = current[0];
                        int col = current[1];
                        area++;

                        for (int k = 0; k < 8; k++) {
                            int newRow = row + dx[k];
                            int newCol = col + dy[k];
                            if (newRow >= 0 && newRow < rows &&
                                newCol >= 0 && newCol < cols &&
                                grid[newRow][newCol] == 1 &&
                                !visited[newRow][newCol]) {
                                queue.add(new int[]{newRow, newCol});
                                visited[newRow][newCol] = true;
                            }
                        }
                    }

                    maxArea = Math.max(maxArea, area);
                }
            }
        }
    
        return maxArea;
    }
    

    public int[][] rottenOranges(int [][]oranges){
        if (oranges == null || oranges.length == 0 || oranges[0].length == 0) {
            return oranges; // Invalid input
        }

        int rows = oranges.length;
        int cols = oranges[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // Step 1: Initialize the queue with rotten oranges and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (oranges[i][j] == 2) {
                    queue.add(new int[]{i, j});
                } else if (oranges[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // If there are no fresh oranges, return the original matrix
        if (freshCount == 0) {
            return oranges;
        }

        // Directions for adjacent cells (up, down, left, right)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int minutes = 0;

        // Step 2: BFS to spread rot to adjacent fresh oranges
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean hasRottenThisMinute = false;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];

                for (int d = 0; d < 4; d++) {
                    int newRow = row + dx[d];
                    int newCol = col + dy[d];

                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                        oranges[newRow][newCol] == 1) {
                        oranges[newRow][newCol] = 2; // Mark as rotten
                        freshCount--; // Decrease fresh count
                        queue.add(new int[]{newRow, newCol});
                        hasRottenThisMinute = true;
                    }
                }
            }

            if (hasRottenThisMinute) {
                minutes++; // Increment time only if something rotted this minute
            }
        }

      return oranges;
    }

}
