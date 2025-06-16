package lowleveldesign.june16;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Game1 game = new TicTacToeGame();
        game.startGame();
    }
}

interface Game1 {
    void startGame();
}

interface GameEngineTicTacToe {
    void buildAndInitializeGame();
}

enum Symbol {
    X, O, EMPTY;
}

class Cell {
    int row, col;
    Symbol symbol;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.symbol = Symbol.EMPTY;
    }
}

class TicTacToeBoard {
    Cell[][] board;
    int size;

    public TicTacToeBoard(int size) {
        this.size = size;
        board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col].symbol == Symbol.EMPTY;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Symbol s = board[i][j].symbol;
                System.out.print((s == Symbol.EMPTY ? "-" : s) + " ");
            }
            System.out.println();
        }
    }
}

class TPlayer {
    String name;
    Symbol symbol;

    public TPlayer(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public void makeMove(TicTacToeBoard board, Scanner scanner) {
        int row, col;
        while (true) {
            System.out.println(name + " (" + symbol + ") enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row >= 0 && col >= 0 && row < board.size && col < board.size && board.isCellEmpty(row, col)) {
                board.board[row][col].symbol = symbol;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}

class TicTacToeGame implements Game1, GameEngineTicTacToe {
    Queue<TPlayer> players = new LinkedList<>();
    TicTacToeBoard board;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void buildAndInitializeGame() {
        System.out.println("Enter board size (3 for normal Tic Tac Toe): ");
        int size = scanner.nextInt();
        board = new TicTacToeBoard(size);

        System.out.println("Enter name for Player 1:");
        TPlayer p1 = new TPlayer(scanner.next(), Symbol.X);

        System.out.println("Enter name for Player 2:");
        TPlayer p2 = new TPlayer(scanner.next(), Symbol.O);

        players.add(p1);
        players.add(p2);
    }

    @Override
    public void startGame() {
        buildAndInitializeGame();
        playGame();
    }

    public void playGame() {
        int totalMoves = 0;
        int maxMoves = board.size * board.size;
        board.printBoard();

        while (true) {
            TPlayer currentPlayer = players.poll();
            currentPlayer.makeMove(board, scanner);
            totalMoves++;
            board.printBoard();

            if (checkWin(currentPlayer.symbol)) {
                System.out.println(currentPlayer.name + " wins!");
                break;
            }

            if (totalMoves == maxMoves) {
                System.out.println("It's a draw!");
                break;
            }

            players.offer(currentPlayer);
        }
    }

    private boolean checkWin(Symbol symbol) {
        int n = board.size;
        // Check rows and columns
        for (int i = 0; i < n; i++) {
            boolean rowWin = true;
            boolean colWin = true;
            for (int j = 0; j < n; j++) {
                if (board.board[i][j].symbol != symbol) rowWin = false;
                if (board.board[j][i].symbol != symbol) colWin = false;
            }
            if (rowWin || colWin) return true;
        }

        // Check diagonals
        boolean mainDiagonal = true, antiDiagonal = true;
        for (int i = 0; i < n; i++) {
            if (board.board[i][i].symbol != symbol) mainDiagonal = false;
            if (board.board[i][n - i - 1].symbol != symbol) antiDiagonal = false;
        }
        return mainDiagonal || antiDiagonal;
    }
}
