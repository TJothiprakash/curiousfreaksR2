package lld.nokiasnakegame;

import java.util.*;

enum Direction {
    UP, DOWN, LEFT, RIGHT;
}

class Position {
    int x, y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Position move(Direction dir, int boardSize) {
        Position newPos = null;
        switch (dir) {
            case UP -> {
                int newX = x - 1;
                if (newX < 0) newX = boardSize - 1;
                newPos = new Position(newX, y);
            }
            case DOWN -> {
                int newX = x + 1;
                if (newX == boardSize) newX = 0;
                newPos = new Position(newX, y);
            }
            case LEFT -> {
                int newY = y - 1;
                if (newY < 0) newY = boardSize - 1;
                newPos = new Position(x, newY);
            }
            case RIGHT -> {
                int newY = y + 1;
                if (newY == boardSize) newY = 0;
                newPos = new Position(x, newY);
            }
        }
        ;
        return newPos;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position newPos)) return false;
        return (this.x == newPos.x && this.y == newPos.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

interface SnakeGame {
    void moveSnake(Direction dir);

    boolean isGameOver();
}

class SnakeGameImpl implements SnakeGame {

    public final int snakeInitialSize;
    public final int size;
    public final Deque<Position> body;
    public final Set<Position> bodySet;
    public boolean gameOver = false;
    public int move;

    public SnakeGameImpl(int boardSize) {
        this.size = boardSize;
        this.body = new LinkedList<>(); // Note: Last position denotes head
        this.bodySet = new HashSet<>();
        this.move = 0;
        this.snakeInitialSize = 3;
        // Initialize snake of size `snakeInitialSize`
        initializeSnake(this.snakeInitialSize);
    }

    private void initializeSnake(int snakeSize) {
        for (int i = 0; i < snakeSize; ++i) {
            // TODO: Make it random
            Position pos = new Position(0, i);
            this.body.addLast(pos);
            this.bodySet.add(pos);
        }
    }

    /*
    Time Comp: O(1)
    Space Comp: O(N*N), where N is size of Board.
     */
    @Override
    public void moveSnake(Direction dir) {
        if (gameOver) return;

        Position currHead = body.peekLast();
        Position newHead = currHead.move(dir, this.size);

        // check for collision
        if (bodySet.contains(newHead)) {
            gameOver = true;
            return;
        }

        Position tail = body.peekFirst();
        boolean isGrow = (++move % 5 == 0);
        if (!isGrow) {
            body.pollFirst();
            bodySet.remove(tail);
        }

        body.addLast(newHead);
        bodySet.add(newHead);
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    public void printSnake() {
        for (Position pos : body) {
            System.out.printf(pos.x + " " + pos.y + ", ");
        }
        System.out.println();
//        System.out.println("Snake: " + body);
    }
}

public class Main {
    public static void main(String[] args) {
        int boardSize = 6;
        SnakeGameImpl game = new SnakeGameImpl(boardSize);
        game.printSnake();
        Direction[] moves = {
                Direction.RIGHT,
                Direction.RIGHT,
                Direction.LEFT,
                Direction.LEFT,
                Direction.UP,
                Direction.UP,
                Direction.DOWN,
                Direction.DOWN,
                Direction.RIGHT,
                Direction.RIGHT,
                Direction.DOWN,
                Direction.LEFT,
                Direction.UP,
        };

        for (Direction dir : moves) {
            game.moveSnake(dir);
            game.printSnake();
            if (game.isGameOver()) {
                System.out.println("Game Over");
                break;
            }
        }
    }
}