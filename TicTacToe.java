// Salah Nasser Hasan Meqdam 
// G1A023095
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            playGame(scanner);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
        }

        System.out.println("Thanks for playing Tic-Tac-Toe!");
    }

    public static void playGame(Scanner scanner) {
        // Print welcome message and instructions
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Instructions:");
        System.out.println("1. Enter the names of the players.");
        System.out.println("2. Choose 'X' or 'O' for each player.");
        System.out.println("3. Players take turns to make a move.");
        System.out.println("4. The first player to get three in a row wins.");
        System.out.println("5. Have fun!\n");

        String player1Name = "";
        String player2Name = "";
        while (player1Name.trim().isEmpty()) {
            System.out.print("Enter the name of Player 1: ");
            player1Name = scanner.nextLine();
        }
        while (player2Name.trim().isEmpty()) {
            System.out.print("Enter the name of Player 2: ");
            player2Name = scanner.nextLine();
        }

        char player1Symbol, player2Symbol;

        while (true) {
            System.out.println(player1Name + ", choose your symbol (X or O): ");
            player1Symbol = scanner.next().toUpperCase().charAt(0);

            if (player1Symbol == 'X' || player1Symbol == 'O') {
                player2Symbol = (player1Symbol == 'X') ? 'O' : 'X';
                break;
            } else {
                System.out.println("Invalid symbol choice. Please choose 'X' or 'O'.");
            }
        }

        char[][] board = new char[3][3];
        initializeBoard(board);
        boolean gameOver = false;
        char currentPlayer = player1Symbol;

        while (!gameOver) {
            printBoard(board);
            int[] move = getPlayerMove(board, currentPlayer, player1Name, player2Name);

            try {
                int row = move[0] - 1; 
                int col = move[1] - 1;

                if (isValidMove(board, row, col)) {
                    board[row][col] = currentPlayer;

                    if (checkWin(board, currentPlayer)) {
                        printBoard(board);
                        System.out.println("Congratulations, " + player1Name + "! You won!");
                        System.out.println("Sorry, " + player2Name + ". You lost.");
                        gameOver = true;
                    } else if (isBoardFull(board)) {
                        printBoard(board);
                        System.out.println("It's a draw!");
                        gameOver = true;
                    } else {
                        currentPlayer = (currentPlayer == player1Symbol) ? player2Symbol : player1Symbol;
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid row and column numbers.");
                scanner.nextLine(); // Clear input buffer
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please enter row and column numbers between 1 and 3.");
                scanner.nextLine(); // Clear input buffer
            }
        }
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static int[] getPlayerMove(char[][] board, char currentPlayer, String player1Name, String player2Name) {
        Scanner scanner = new Scanner(System.in);
        String currentPlayerName = (currentPlayer == 'X') ? player1Name : player2Name;

        while (true) {
            try {
                System.out.print(currentPlayerName + ", enter your move (row [1-3] and column [1-3]): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (row >= 1 && row <= 3 && col >= 1 && col <= 3) {
                    return new int[]{row, col};
                } else {
                    System.out.println("Invalid input. Please enter row and column numbers between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid row and column numbers.");
                scanner.nextLine(); 
            }
        }
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ');
    }

    public static boolean checkWin(char[][] board, char currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; 
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true; 
        }
        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
