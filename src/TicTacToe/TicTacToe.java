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
                char cell = board[row][column];

                if (cell == '_') {
                    System.out.print("  ");
                } else {
                    System.out.print(cell + " ");
                }
            }

            System.out.println("|");
        }

        System.out.println("---------");
    }

    private int countSymbol(char symbol) {
        int count = 0;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == symbol) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean hasWinner(char symbol) {

        for (int row = 0; row < 3; row++) {
            if (board[row][0] == symbol &&
                    board[row][1] == symbol &&
                    board[row][2] == symbol) {
                return true;
            }
        }

        for (int column = 0; column < 3; column++) {
            if (board[0][column] == symbol &&
                    board[1][column] == symbol &&
                    board[2][column] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol &&
                board[1][1] == symbol &&
                board[2][2] == symbol) {
            return true;
        }

        if (board[0][2] == symbol &&
                board[1][1] == symbol &&
                board[2][0] == symbol) {
            return true;
        }

        return false;
    }

    private boolean hasEmptyCells() {

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == '_') {
                    return true;
                }
            }
        }

        return false;
    }

    public String getGameState() {

        int xCount = countSymbol('X');
        int oCount = countSymbol('O');

        boolean xWins = hasWinner('X');
        boolean oWins = hasWinner('O');

        if (Math.abs(xCount - oCount) >= 2 || (xWins && oWins)) {
            return "Impossible";
        }

        if (xWins) {
            return "X wins";
        }

        if (oWins) {
            return "O wins";
        }

        if (hasEmptyCells()) {
            return "Game not finished";
        }

        return "Draw";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");
        String cells = scanner.nextLine();

        TicTacToe game = new TicTacToe(cells);

        game.printBoard();

        System.out.println(game.getGameState());
    }

}