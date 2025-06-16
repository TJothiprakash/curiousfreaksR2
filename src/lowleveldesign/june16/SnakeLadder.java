package lowleveldesign.june16;

import java.util.*;

// === ENUMS ===
enum Difficulty {
    EASY, MEDIUM, HARD
}

enum JumpType {
    SNAKE, LADDER
}

// === INTERFACES ===
interface Game {
    void start();
}

interface Jump {
    int getStart();

    int getEnd();

    JumpType getType();
}

// === ENTITIES ===
class Snake implements Jump {
    private final int head, tail;

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getStart() {
        return head;
    }

    public int getEnd() {
        return tail;
    }

    public JumpType getType() {
        return JumpType.SNAKE;
    }
}

class Ladder implements Jump {
    private final int start, end;

    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public JumpType getType() {
        return JumpType.LADDER;
    }
}

class Player {
    String name;
    int position = 1;

    public Player(String name) {
        this.name = name;
    }

    public void maketurn() {
        // logic for placing his symbol
    }
}

class PlayerPosition {
    int x, y;

    public PlayerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Dice {
    private final int numSides;

    public Dice(int numSides) {
        this.numSides = numSides;
    }

    public int roll() {
        return (int) (Math.random() * numSides) + 1;
    }
}

class BoardConfig {
    int size;
    int snakeCount;
    int ladderCount;

    public BoardConfig(int size, int snakeCount, int ladderCount) {
        this.size = size;
        this.snakeCount = snakeCount;
        this.ladderCount = ladderCount;
    }
}

class Board {
    int size;
    List<Jump> jumps = new ArrayList<>();

    public Board(BoardConfig config) {
        this.size = config.size;
        generateJumps(config);
    }

    private void generateJumps(BoardConfig config) {
        Random rand = new Random();
        Set<Integer> used = new HashSet<>();

        while (jumps.stream().filter(j -> j.getType() == JumpType.LADDER).count() < config.ladderCount) {
            int start = rand.nextInt(maxCell() - 20) + 1;
            int end = rand.nextInt(maxCell() - start - 1) + start + 1;
            if (!used.contains(start) && !used.contains(end)) {
                jumps.add(new Ladder(start, end));
                used.add(start);
                used.add(end);
            }
        }

        while (jumps.stream().filter(j -> j.getType() == JumpType.SNAKE).count() < config.snakeCount) {
            int head = rand.nextInt(maxCell() - 20) + 21;
            int tail = rand.nextInt(head - 1) + 1;
            if (!used.contains(head) && !used.contains(tail)) {
                jumps.add(new Snake(head, tail));
                used.add(head);
                used.add(tail);
            }
        }
    }

    public int applyJumps(int position) {
        for (Jump j : jumps) {
            if (j.getStart() == position) return j.getEnd();
        }
        return position;
    }

    public int maxCell() {
        return size * size;
    }

    public PlayerPosition getPositionFromIndex(int index) {
        int row = (index - 1) / size;
        int col = (index - 1) % size;
        if (row % 2 == 1) col = size - 1 - col;
        return new PlayerPosition(size - 1 - row, col);
    }
}

class GameEngine {
    private final Queue<Player> players;
    private final Board board;
    private final Dice dice;

    public GameEngine(List<String> playerNames, Board board, Dice dice) {
        this.players = new LinkedList<>();
        playerNames.forEach(name -> players.offer(new Player(name)));
        this.board = board;
        this.dice = dice;
    }

    public void runGameLoop() {
        while (true) {
            Player current = players.poll();
            int roll = dice.roll();
            int nextPos = current.position + roll;

            if (nextPos <= board.maxCell()) {
                int finalPos = board.applyJumps(nextPos);
                current.position = finalPos;
                PlayerPosition pp = board.getPositionFromIndex(finalPos);
                System.out.println(current.name + " moves to " + finalPos + " (" + pp.x + "," + pp.y + ")");

                if (finalPos == board.maxCell()) {
                    System.out.println(current.name + " wins!");
                    break;
                }
            } else {
                System.out.println(current.name + " rolled too high. Stays at " + current.position);
            }
            players.offer(current);
        }
    }
}

class SnakeLadderGame implements Game {
    private final Difficulty difficulty;

    public SnakeLadderGame(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void start() {
        BoardConfig config = switch (difficulty) {
            case EASY -> new BoardConfig(10, 5, 8);
            case MEDIUM -> new BoardConfig(15, 10, 5);
            case HARD -> new BoardConfig(25, 20, 3);
        };

        Board board = new Board(config);
        Dice dice = new Dice(6);
        List<String> playerNames = List.of("Alice", "Bob");

        GameEngine engine = new GameEngine(playerNames, board, dice);
        engine.runGameLoop();
    }
}

class Main {
    public static void main(String[] args) {
        Game game = new SnakeLadderGame(Difficulty.MEDIUM);
        game.start();
    }
}