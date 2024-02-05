import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];

    public static void main(String[] args) {
        initializeBoard();
        char currentPlayer = PLAYER_X;
        boolean isGameOver = false;

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (!isGameOver) {
            printBoard();

            if (currentPlayer == PLAYER_X) {
                playerMove(PLAYER_X);
            } else {
                // AI move
                int[] bestMove = getBestMove();
                board[bestMove[0]][bestMove[1]] = PLAYER_O;
                System.out.println("AI player (O) chose row " + bestMove[0] + ", column " + bestMove[1]);
            }

            if (isWin(PLAYER_X) || isWin(PLAYER_O) || isBoardFull()) {
                printBoard();
                if (isWin(PLAYER_X)) {
                    System.out.println("Congratulations! You win!");
                } else if (isWin(PLAYER_O)) {
                    System.out.println("AI player wins!");
                } else {
                    System.out.println("It's a draw!");
                }
                isGameOver = true;
            }

            // Switch player
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("---------");
            }
        }
    }

    private static void playerMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.print("Enter row and column (0-2) for " + player + ": ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY) {
                board[row][col] = player;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static boolean isWin(char player) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] getBestMove() {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = PLAYER_O;
                    int score = minimax(board, 0, false);
                    board[i][j] = EMPTY;
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (isWin(PLAYER_X)) {
            return -10;
        } else if (isWin(PLAYER_O)) {
            return 10;
        } else if (isBoardFull()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER_O;
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = EMPTY;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER_X;
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = EMPTY;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
