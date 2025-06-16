package lowleveldesign.june16.sudoku;

public class SudokuSolver {
    boolean solve(Sudoku sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku.get(i, j) == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(sudoku, i, j, num)) {
                            sudoku.set(i, j, num);
                            if (solve(sudoku)) return true;
                            sudoku.set(i, j, 0); // backtrack
                        }
                    }
                    return false; // if no number fits here, backtrack
                }
            }
        }
        return true; // no empty cell found: solved!
    }


    boolean isSafe(Sudoku sudoku, int row, int col, int num) {
        // check row, chekc column, check 3/3 grid
        for (int i = 0; i < 9; i++) {
            if (sudoku.get(row, i) == num || sudoku.get(i, col) == num
                    || sudoku.get(3 * (row / 3) + i / 3, 3 * (col / 3) + i % 3) == num) {
                return false;
            }
        }
        return true;
    }

}
