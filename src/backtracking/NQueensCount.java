package backtracking;
public class NQueensCount {
    public static void main(String[] args) {
        NQueensCount sol = new NQueensCount();
        System.out.println(sol.totalNQueens(4));
    }
    public int totalNQueens(int n) {
        // These arrays track column and diagonal occupation
        boolean[] cols = new boolean[n];          // columns
        boolean[] d1 = new boolean[2 * n];        // main diagonal (row + col)
        boolean[] d2 = new boolean[2 * n];        // anti diagonal (row - col + n)

        return backtrack(0, cols, d1, d2, n);
    }

    private int backtrack(int row, boolean[] cols, boolean[] d1, boolean[] d2, int n) {
        // If all rows have queens, it's a valid solution
        if (row == n) return 1;

        int count = 0;
        for (int col = 0; col < n; col++) {
            // If this column or diagonals are already occupied, skip
            if (cols[col] || d1[row + col] || d2[row - col + n]) continue;

            // Place queen
            cols[col] = true;
            d1[row + col] = true;
            d2[row - col + n] = true;

            // Recurse for next row
            count += backtrack(row + 1, cols, d1, d2, n);

            // Backtrack (remove queen)
            cols[col] = false;
            d1[row + col] = false;
            d2[row - col + n] = false;
        }

        return count;
    }
}
