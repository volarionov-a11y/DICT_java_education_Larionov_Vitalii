package TicTacToe;

import java.util.Scanner;

public class TicTacToe {

    private char[][] board;

    public TicTacToe(String cells) {
        board = new char[3][3];
        fillBoard(cells);
    }

    private void fillBoard(String cells) {
        int index = 0;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                char symbol = cells.charAt(index);

                if (symbol == '_') {
                    board[row][column] = ' ';
                } else {
                    board[row][column] = symbol;
                }

                index++;
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

    public void makeMove(Scanner scanner) {

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

            board[row][column] = 'X';
            break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");
        String cells = scanner.nextLine();

        TicTacToe game = new TicTacToe(cells);

        game.printBoard();

        game.makeMove(scanner);

        game.printBoard();
    }
}