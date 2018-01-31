
package sudokugame;


//Max’s code

import java.io.*;
import java.util.Scanner;

public class SudokuGame implements SudokuInterface {

    private static final int BOARD_SIZE = 9;

    public static void main(String[] args) throws IOException {

        int[][] board = retrieveSudokuGameFromFile("sudokuGame.sud");
        int step = 0;
        boolean hasAGameUpdateOccured;
        printBoard(board);

        /*
        while (!hasPuzzleBeenSolved()) {
            hasAGameUpdateOccured = false;
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (isCellEmpty(row,col) && isOnlyOneValueAllowedInTheCurrentCell(row,col)) {
                        System.out.print("Step: " + step++ + " ");
                        updateGame(getTheUniqueAllowedValue(row,col), row, col);
                        hasAGameUpdateOccured = true;
                    }
                }
            }
            if (!hasAGameUpdateOccured) {
                //End game - puzzle is unsolvable
            }
        }

        showGameSolvedMessage();
        */
    }

    static int[][] retrieveSudokuGameFromFile(String sudokuFile) throws IOException {
        Scanner fileInput = new Scanner(new File(sudokuFile));
        String oneRow;
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            oneRow = fileInput.nextLine();
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = Integer.parseInt(oneRow.charAt(2*col)+"");
            }
        }
        return board;
    }
    static void printBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col]);
                if (col == 2 || col == 5)
                    System.out.print("|");
                else
                    System.out.print(" ");
            }
            System.out.println("");
            if (row == 2 || row == 5)
                System.out.println("-----------------");
        }
    }
    /*
    static boolean isCellEmpty(int row, int col) {

    }
    static boolean isOnlyOneValueAllowedInTheCurrentCell(int row, int col) {

    }
    static int[] getAllowedValuesInTheCurrentCell(int row, int col) {

    }
    static int[] getAllowedValuesInCurrentRow(int row) {

    }
    static int[] getAllowedValuesInCurrentCol(int col) {

    }
    static int[] getAllowedValuesInCurrentGroup(int row, int col) {

    }
    static int[] getAllowedValuesBasedOnTheThreeRules(int[] allowedValuesInCurrentRow,
            int[] allowedValuesInCurrentCol, int[] allowedValuesInCurrentGroup) {

    }
    static int getTheUniqueAllowedValue(int row, int col) {

    }
    static void updateGame(int allowedValue, int row, int col) {

    }
    static boolean hasPuzzleBeenSolved() {

    }
    static void showGameSolvedMessage() {

    }
*/

}
