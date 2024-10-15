import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MinesweeperAviJosh {
    private int boardWidth;
    private int boardLength;
    private CellAviJosh[][] board;
    private boolean gameStarted;
    private Timer timer;
    private int secondsPassed;
    private boolean gameOver;
    private int steps;

    public MinesweeperAviJosh(int boardWidth, int boardLength) {
        this.boardWidth = boardWidth;
        this.boardLength = boardLength;
        this.board = new CellAviJosh[boardWidth][boardLength];
        this.gameStarted = false;
        this.timer = new Timer();
        this.secondsPassed = 0;
        this.gameOver = false;
        this.steps = 0;
    }

    public CellAviJosh[][] gameBoard(int x, int y) {
        Random rand = new Random();
        int minesPlaced = rand.nextInt(y - x + 1) + x;

        boolean[][] minePlaces = new boolean[boardWidth][boardLength];
        int minesRemaining = minesPlaced;

        while (minesRemaining > 0) {
            int mine1 = rand.nextInt(boardWidth);
            int mine2 = rand.nextInt(boardLength);

            if (!minePlaces[mine1][mine2]) {
                minePlaces[mine1][mine2] = true;
                minesRemaining--;
            }
        }

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (minePlaces[i][j]) {
                    board[i][j] = new CellAviJosh(true, false, 0, false, "*");
                } else {
                    board[i][j] = new CellAviJosh(false, true, 0, false, "#");
                }
            }
        }

        countAdjacentMines(board);
        setAllContents(board);

        return board;
    }

    public void countAdjacentMines(CellAviJosh[][] game) {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardLength; j++) {
                int count = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (i + k >= 0 && i + k < boardWidth && j + l >= 0 && j + l < boardLength) {
                            if (game[i + k][j + l].hasMine()) {
                                count++;
                            }
                        }
                    }
                }
                game[i][j].setSurroundingMines(count);
            }
        }
    }

    public void setAllContents(CellAviJosh[][] game) {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (game[i][j].hasMine()) {
                    game[i][j].setEmpty(false);
                    game[i][j].setPrintedSymbol("* ");
                } else {
                    if (!game[i][j].hasMine() && game[i][j].getSurroundingMines() >= 1) {
                        game[i][j].setEmpty(true);
                        game[i][j].setPrintedSymbol(game[i][j].getSurroundingMines() + " ");
                    } else {
                        game[i][j].setEmpty(true);
                        game[i][j].setPrintedSymbol("_ ");
                    }
                }

                if (game[i][j].isFlagged()) {
                    game[i][j].setEmpty(true);
                    game[i][j].setPrintedSymbol("F ");
                }
            }
        }
    }

    public void printBoard(CellAviJosh[][] game, boolean mine) {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (mine || game[i][j].isRevealed()) {
                    System.out.print(game[i][j].getPrintedSymbol());
                } else {
                    if (game[i][j].isFlagged()) {
                        System.out.print("F ");
                    } else {
                        System.out.print("# ");
                    }
                }
            }
            System.out.println();
        }
    }

    public boolean checkWinner(CellAviJosh[][] game) {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (!game[i][j].hasMine() && !game[i][j].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void play(CellAviJosh[][] game) {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {

            if (!gameStarted) {
                startTimer();
                gameStarted = true;
            }
            
            System.out.print("Enter row and column (e.g., 1 2): ");
            try {
                int row = scanner.nextInt();
                int column = scanner.nextInt();

                if (row < 0 || row >= boardWidth || column < 0 || column >= boardLength) {
                    System.out.println("Invalid input, choose a different cell.");
                    continue;
                }

                System.out.print("Do you want to reveal (R) or flag (F) the cell? ");
                String action = scanner.next().toUpperCase();

                if (action.equals("R")) {
                    if (!game[row][column].isRevealed() && !game[row][column].isFlagged()) {
                        revealCell(game, row, column);
                        steps++;
                    } else {
                        System.out.println("Cell already revealed or flagged, choose a different action.");
                    }
                } else if (action.equals("F")) {
                    flagCell(game, row, column);
                } else {
                    System.out.println("Invalid action. Choose 'R' to reveal or 'F' to flag.");
                }
                printBoard(game, false);
                if (checkWinner(game)) {
                    gameOver = true;
                    System.out.println("You win!");
                    System.out.println("Your total steps: " + steps);
                    System.out.println("Time taken: " + formatTime(secondsPassed));
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid integers.");
                scanner.next();
            }
        }

        //printBoard(game, true);
        timer.cancel();
        scanner.close();
    }

    private void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
            }
        }, 1000, 1000);
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private void revealCell(CellAviJosh[][] game, int row, int column) {
    if (game[row][column].hasMine()) {
        gameOver = true;
        printBoard(game, true);
        System.out.println("Game over! You stepped on a mine.");
        System.out.println("Your total steps: " + steps);
        System.out.println("Time taken: " + formatTime(secondsPassed));
        System.exit(0); 
    } else if (!game[row][column].isRevealed() && !game[row][column].isFlagged()) {
        game[row][column].setRevealed(true);

        if (game[row][column].getSurroundingMines() == 0) {
            revealNeighbors(game, row, column);
        }
    }
}


    private void revealNeighbors(CellAviJosh[][] game, int row, int column) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < boardWidth && j >= 0 && j < boardLength && !game[i][j].isRevealed() && !game[i][j].isFlagged()) {
                    revealCell(game, i, j);
                }
            }
        }
    }

    private void flagCell(CellAviJosh[][] game, int row, int column) {
    if (!game[row][column].isRevealed()) {
        game[row][column].toggleFlag();
        //printBoard(game, false); // Print the updated board after flagging
    } else {
        System.out.println("Cannot flag a revealed cell. Choose a different action.");
    }
}

}