import java.util.Scanner;

import static java.lang.Math.max;

public class Game {
    private final int WIDTH = 8;
    private final int HEIGHT = 8;
    private final Character BLACK = 'b';
    private final Character WHITE = 'w';
    private final Character EMPTY = '•';
    private final Character POSSIBLE = '○';
    private final Character side;
    private Character turn;
    private Character[][] field;
    private int black_counter;
    private int white_counter;
    private final int mode;
    private final int difficulty;
    Scanner scanner;

    protected Game(Character side, int difficulty, int mode) {
        this.side = side;
        this.turn = BLACK;
        this.black_counter = 2;
        this.white_counter = 2;
        this.mode = mode;
        this.difficulty = difficulty;
        this.scanner = new Scanner(System.in);
        createField();
    }

    private void createField() {
        field = new Character[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                field[i][j] = EMPTY;
            }
        }
        field[3][3] = WHITE;
        field[3][4] = BLACK;
        field[4][3] = BLACK;
        field[4][4] = WHITE;
    }

    public int processGame() {
        if (mode == 1) {
            processPlayerVsBot();
        } else {
            processPlayerVsPlayer();
        }
        markPossibleMoves();
        displayField();
        System.out.println("Game ended with the score: b" + black_counter + " w" + white_counter);
        if (mode == 1) {
            return side == BLACK ? black_counter : white_counter;
        }
        return max(black_counter, white_counter);
    }

    private void processPlayerVsPlayer() {
        while (this.white_counter != 0 && this.black_counter != 0 && this.white_counter + this.black_counter != 64) {
            markPossibleMoves();
            displayField();
            if (turn == side) {
                processPlayerMove();
            } else {
                processPlayerMove();
            }
        }
    }

    private void processPlayerVsBot() {
        while (this.white_counter != 0 && this.black_counter != 0 && this.white_counter + this.black_counter != 64) {
            markPossibleMoves();
            displayField();
            if (turn == side) {
                processPlayerMove();
            } else {
                processComputerMove();
            }
        }
    }

    private void markPossibleMoves() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (field[i][j] == POSSIBLE) {
                    field[i][j] = EMPTY;
                }
                if (field[i][j] == EMPTY) {
                    if (overlapsEnemy(i, j)) {
                        field[i][j] = POSSIBLE;
                    }
                }
            }
        }
    }

    private boolean overlapsEnemy(int line, int col) {
        boolean isNeighbor;
        int x;
        int y;
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                }
                x = line;
                y = col;
                isNeighbor = true;
                while (true) {
                    x += i;
                    y += j;

                    if (x < 0 || x >= HEIGHT || y < 0 || y >= WIDTH) {
                        break;
                    }
                    if (field[x][y] == EMPTY || field[x][y] == POSSIBLE) {
                        break;
                    }
                    if (isNeighbor && field[x][y] != turn) {
                        isNeighbor = false;
                    } else if (isNeighbor) {
                        break;
                    }
                    if (field[x][y] == turn) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void processPlayerMove() {
        displayPossibleMoves();
        System.out.print("Select cell: ");
        int[] cell = getInputCell();
        makeMove(cell[0], cell[1]);
        turn = turn == BLACK ? WHITE : BLACK;
    }

    private int[] getInputCell() {
        int[] cell = new int[2];
        cell[0] = -1;
        cell[1] = -1;
        if (scanner.hasNextInt()) {
            cell[0] = scanner.nextInt();
            if (scanner.hasNextInt()) {
                cell[1] = scanner.nextInt();
            } else {
                scanner.nextLine();
            }
        } else {
            scanner.nextLine();
        }
        while (isOutOfBoard(cell) || field[cell[0]][cell[1]] != POSSIBLE) {
            System.out.print("\nIncorrect input! Try again: ");
            if (scanner.hasNextInt()) {
                cell[0] = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    cell[1] = scanner.nextInt();
                } else {
                    scanner.nextLine();
                }
            } else {
                scanner.nextLine();
            }
        }
        return cell;
    }

    private boolean isOutOfBoard(int[] cell) {
        return cell[0] < 0 || cell[0] > 7 || cell[1] < 0 || cell[1] > 7;
    }

    private void displayPossibleMoves() {
        System.out.println("\nPossible moves for you:");
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (field[i][j] == POSSIBLE) {
                    System.out.println(i + " " + j);
                }
            }
        }
    }

    private void processComputerMove() {
        int optimalX = 0;
        int optimalY = 0;
        float optimalRate = 0;
        float curRate;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (field[i][j] == POSSIBLE) {
                    curRate = rateMove(i, j);
                    if (curRate > optimalRate) {
                        optimalRate = curRate;
                        optimalX = i;
                        optimalY = j;
                    }
                }
            }
        }
        System.out.println("Computer moved on cell: " + optimalX + " " + optimalY);
        System.out.println();
        makeMove(optimalX, optimalY);
        turn = turn == BLACK ? WHITE : BLACK;
    }

    private void displayField() {
        System.out.println();
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print(i);
            for (int j = 0; j < WIDTH; j++) {
                System.out.print("\t");
                System.out.print(field[i][j]);
            }
            System.out.print("\t|");
            if (i == 4) {
                System.out.println("\tScore: b" + black_counter + " w" + white_counter);
            } else {
                System.out.println();
            }
        }
        System.out.print("\t");
        for (int i = 0; i < WIDTH; i++) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println("|");
        System.out.println();
    }

    private void makeMove(int line, int col) {
//        field[line][col] = turn;
        boolean isNeighbor;
        int x;
        int y;
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                }
                x = line;
                y = col;
                isNeighbor = true;
                while (true) {
                    x += i;
                    y += j;

                    if (x < 0 || x >= HEIGHT || y < 0 || y >= WIDTH) {
                        break;
                    }
                    if (field[x][y] == EMPTY || field[x][y] == POSSIBLE) {
                        break;
                    }
                    if (isNeighbor && field[x][y] != turn) {
                        isNeighbor = false;
                    } else if (isNeighbor) {
                        break;
                    }
                    if (field[x][y] == turn) {
                        flip(line, col, x, y, i, j);
                        break;
                    }
                }
            }
        }
        field[line][col] = turn;
        if (turn == BLACK) {
            ++black_counter;
        } else {
            ++white_counter;
        }
    }

    private void flip(int x, int y, int toX, int toY, int dX, int dY) {
        while (!(x == toX && y == toY)) {
            x += dX;
            y += dY;
            field[x][y] = turn;
            if (turn == BLACK && !(x == toX && y == toY)) {
                ++black_counter;
                --white_counter;
            } else if (turn == WHITE && !(x == toX && y == toY)) {
                --black_counter;
                ++white_counter;
            }
        }
    }

    private float rateMove(int line, int col) {
        boolean isNeighbor;
        int x;
        int y;
        float rate = 0;
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0) {
                    continue;
                }
                x = line;
                y = col;
                isNeighbor = true;
                while (true) {
                    x += i;
                    y += j;

                    if (x < 0 || x >= HEIGHT || y < 0 || y >= WIDTH) {
                        break;
                    }
                    if (field[x][y] == EMPTY || field[x][y] == POSSIBLE) {
                        break;
                    }
                    if (isNeighbor && field[x][y] != turn) {
                        isNeighbor = false;
                    } else if (isNeighbor) {
                        break;
                    }
                    if (field[x][y] == turn) {
                        rate += rateLine(line, col, x, y, i, j);
                        break;
                    }
                }
            }
        }
        return rate;
    }

    private float rateLine(int x, int y, int toX, int toY, int dX, int dY) {
        float rate = 0;
        while (!(x == toX && y == toY)) {
            x += dX;
            y += dY;
            if (x == HEIGHT - 1 || y == WIDTH - 1 || x == 0 || y == 0) {
                rate += 2;
            } else {
                ++rate;
            }
//            field[x][y] = turn;
        }
        if ((toX == 0 && toY == 0) || (toX == HEIGHT - 1 && toY == WIDTH - 1) || (toX == 0 && toY == WIDTH - 1) || (toX == HEIGHT - 1 && toY == 0)) {
            rate += 0.8;
        } else if (toX == HEIGHT - 1 || toY == WIDTH - 1 || toX == 0 || toY == 0) {
            rate += 0.4;
        }
        return rate;
    }
}
