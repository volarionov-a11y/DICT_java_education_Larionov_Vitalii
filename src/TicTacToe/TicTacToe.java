package TicTacToe;

import java.util.Scanner;

public class TicTacToe {

    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                board[row][column] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("---------");

        for (int row = 0; row < 3; row++) {
            System.out.print("| ");

            for (int column = 0; column < 3; column++) {
                System.out.print(board[row][column] + " ");
            }

            System.out.println("|");
        }

        System.out.println("---------");
    }

    private void makeMove(Scanner scanner) {

        while (true) {
            System.out.print("Enter the coordinates: ");

            String input = scanner.nextLine();
            String[] parts = input.trim().split("\\s+");

            if (parts.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int row;
            int column;

            try {
                row = Integer.parseInt(parts[0]);
                column = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (row < 1 || row > 3 || column < 1 || column > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            row--;
            column--;

            if (board[row][column] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board[row][column] = currentPlayer;
            break;
        }
    }

    private boolean hasWinner(char player) {

        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player &&
                    board[row][1] == player &&
                    board[row][2] == player) {
                return true;
            }
        }

        for (int column = 0; column < 3; column++) {
            if (board[0][column] == player &&
                    board[1][column] == player &&
                    board[2][column] == player) {
                return true;
            }
        }

        if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) {
            return true;
        }

        return false;
    }

    private boolean isDraw() {

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    private void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        printBoard();

        while (true) {

            makeMove(scanner);

            printBoard();

            if (hasWinner(currentPlayer)) {
                System.out.println(currentPlayer + " wins");
                break;
            }

            if (isDraw()) {
                System.out.println("Draw");
                break;
            }

            switchPlayer();
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}