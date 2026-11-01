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
                board[row][column] = cells.charAt(index);
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");
        String cells = scanner.nextLine();

        TicTacToe game = new TicTacToe(cells);

        game.printBoard();
    }
}