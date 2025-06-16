package lowleveldesign.june16.sudoku;

public class Main {

    public static void main(String[] args) {
        int[][] puzzle = {
                {5, 3, 4, 6, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}

        };
        Sudoku sudoku = new Sudoku(puzzle, 9);

        sudoku.setBoard(puzzle, 9);
        sudoku.printBoard();
        System.out.println("hey threee");
//        sudoku.printBoard();
        System.out.println("");

        SudokuSolver solver = new SudokuSolver();
        solver.solve(sudoku);
        if (solver.solve(sudoku)) {
            sudoku.printBoard();
        } else {
            System.out.println("No solution found");
        }

    }
}

class Sudoku {
    //backtracking algorithm
    // straightforard approach not any magic heere
    // rules : every row has 1-9 and evey column has 1-9 exactly has once
    // every 3 by 3 has 1-9 exactly once

    int board[][];
    int row = 0;
    int col = 0;
    int size = 0;
    int val = 0;

    public Sudoku(int[][] board, int size) {
        this.board = board;
        this.size = size;
    }

    public void setBoard(int[][] board, int size) {
        this.board = board;
    }

    public int get(int row, int col) {
        return board[row][col];
    }

    public void set(int row, int col, int val) {
        board[row][col] = val;
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col] == 0;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("------+-------+------");
            for (int j = 0; j < size; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


}


