import java.util.Scanner;
import java.util.Random;

public class TTTSharma {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        boolean humanVsComputer = chooseGameMode();
        initBoard();

        char winner = ' ';

        while (winner == ' ') {
            if (humanVsComputer) {
                if (currentPlayer == 'X') {
                    yourTurn();
                } else {
                    machineTurn();
                }
            } else {
                yourTurn();
            }

            winner = checkWinner();
        }

        gameBoard(); // Moved the gameBoard method call here

        
    }


    private static boolean chooseGameMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose game mode (1 for human vs human, 2 for human vs computer): ");
        int choice = scanner.nextInt();
        return choice == 2;
    }

    public static void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void gameBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < board[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("-----------");
            }
        }
    }


    private static void yourTurn() {
    boolean validMove = false;

    while (!validMove) {
        gameBoard();
        int[] move = getMove();
        int row = move[0];
        int col = move[1];

        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            validMove = true;

            char winner = checkWinner();
            if (winner != ' ') {
             
                if (winner == 'D') {
                    System.out.println("It's a draw!");
                } else {
                    System.out.println("Player " + winner + " wins!");
                }
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player after checking for a winner
        } else {
            System.out.println("Invalid move. Please try again.");
        }
    }
}


    private static int[] getMove() {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];
        System.out.print("Player " + currentPlayer + ", enter your move (row column): ");
        move[0] = scanner.nextInt() - 1;
        move[1] = scanner.nextInt() - 1;
        return move;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static void machineTurn() {
        System.out.println("Computer's turn:");

        boolean validMove = false;
        Random rand = new Random();

        while (!validMove) {
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);

            if (isValidMove(row, col)) {
                board[row][col] = 'O';
                validMove = true;
                currentPlayer = 'X'; // Switch back to the human player
            }
        }

        
    }

    private static char checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return currentPlayer;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return currentPlayer;
            }
        }

        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return currentPlayer;
        }

        boolean isBoardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    isBoardFull = false;
                    break;
                }
            }
        }

        if (isBoardFull) {
            return 'D'; // Draw
        }

        return ' ';
    }
}
