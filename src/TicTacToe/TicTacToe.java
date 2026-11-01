package TicTacToe;

public class TicTacToe {

    private char[][] board;

    public TicTacToe() {
        board = new char[][]{
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {'X', 'X', 'O'}
        };
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.printBoard();
    }
}