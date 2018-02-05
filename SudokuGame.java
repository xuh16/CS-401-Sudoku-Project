package sudokugame;

//Max's code

import java.io.*;
import java.util.Scanner;
import sudokugame.SudokuInterface;

public class SudokuGame /*implements SudokuInterface*/ {

    private static final int BOARD_SIZE = 9;
    private static final int GROUP_SIZE = 3;
    private static final int EMPTY_CELL = 0;
    private static int currentStep = 0;
    private static int[][] game;

    public static void main(String[] args) throws IOException {        
        new SudokuGame();
    }
    
    SudokuGame() throws IOException {
        game = retrieveSudokuGameFromFile("sudokuGame.sud");
        boolean hasAGameUpdateOccured;
        printBoard();
        
        /*
        while (!hasPuzzleBeenSolved()) {
            hasAGameUpdateOccured = false;
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (isCellEmpty(row,col) && isOnlyOneValueAllowedInTheCurrentCell(row,col)) {
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
    
    public int[][] retrieveSudokuGameFromFile(String sudokuFile) throws IOException {
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
    public void printBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(game[row][col]);
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
    
    public boolean isCellEmpty(int row, int col) {
        return game[row][col] == EMPTY_CELL;
    }
    /*
    public boolean isOnlyOneValueAllowedInTheCurrentCell(int row, int col) {
        return getAllowedValuesInTheCurrentCell(row,col).length == 1;
    }
    /*
    public int[] getAllowedValuesInTheCurrentCell(int row, int col) {
        int[] allowedValuesInCurrentRow = getAllowedValuesInCurrentRow(row);
        int[] allowedValuesInCurrentCol = getAllowedValuesInCurrentCol(col);
        int[] allowedValuesInCurrentGroup = getAllowedValuesInCurrentGroup(row,col);
        
        int[] temp = new int[BOARD_SIZE];
        int index = 0;
        
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (allowedValuesInCurrentRow[i] != 0 &&
                allowedValuesInCurrentCol[i] != 0 &&
                allowedValuesInCurrentGroup[i] != 0) {
                    temp[index] = i+1;
                    index++;
            }
        }
        
        int[] allowedValues = new int[index];
        for (int i = 0; i < index; i++) {
            allowedValues[i] = temp[i];
        }
        
        return allowedValues;
    }
    /*
    public int[] getAllowedValuesInCurrentRow(int row) {
        int[] allowedValueInRow = new int[BOARD_SIZE];
        boolean hasValueInRow;
        for (int i = 1; i < 10; i++) {
            hasValueInRow = false;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (game[row][j] == i) {
                    hasValueInRow = true;
                }
            }
            if (hasValueInRow == false) {
                allowedValueInRow[i - 1] = i;
            }
        }
        System.out.println("---------------------------------");
        System.out.print("(");
        for (int k = 0; k < BOARD_SIZE; k++) {
            System.out.print(allowedValueInRow[k] + ",");
        }
        System.out.println(")     Allowed values in row " + (row + 1));
        return allowedValueInRow;
    
    }
    
    public int[] getAllowedValuesInCurrentCol(int col) {
        int[] allowedValueInCol = new int[BOARD_SIZE];
        boolean hasValueInCol;
        for (int i = 1; i < 10; i++) {
            hasValueInCol = false;
            for (int j = 0; j < BOARD_SIZE; j++) {

                if (game[j][col] == i) {
                    hasValueInCol = true;
                }
            }

            if (hasValueInCol == false) {
                allowedValueInCol[i - 1] = i;
            }
        }
        System.out.print("(");
        for (int l = 0; l < BOARD_SIZE; l++) {
            System.out.print(allowedValueInCol[l] + ",");
        }
        System.out.println(")     Allowed values in col " + (col + 1));

        return allowedValueInCol;
        
    }
    public int[] getAllowedValuesInCurrentGroup(int row, int col) {
        
    }
    public int[] getAllowedValuesBasedOnTheThreeRules(int[] allowedValuesInCurrentRow, 
            int[] allowedValuesInCurrentCol, int[] allowedValuesInCurrentGroup) {
        
    }
    public int getTheUniqueAllowedValue(int row, int col) {
        
    }
    public void updateGame(int allowedValue, int row, int col) {
        
    }
    public boolean hasPuzzleBeenSolved() {
        
    }
*/
    public void showGameSolvedMessage() {
        System.out.println("\nGame solved.");
    }
    
}
