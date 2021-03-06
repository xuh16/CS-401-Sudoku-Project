package sudokugame;

import java.io.*;
import java.util.Scanner;
import sudokugame.SudokuInterface;

public class SudokuGame implements SudokuInterface {

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
        int group;
        printBoard();

        while (!hasPuzzleBeenSolved()) {
            hasAGameUpdateOccured = false;
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (isCellEmpty(row, col)) {
                        //Print Allowed Values in Row
                        System.out.println("---------------------------------");
                        System.out.print("(");
                        for (int i = 0; i < BOARD_SIZE; i++) {
                            System.out.print(getAllowedValuesInCurrentRow(row)[i] + ",");
                        }
                        System.out.println(")     Allowed values in row " + (row + 1));

                        //Print Allowed Values in Column
                        System.out.print("(");
                        for (int i = 0; i < BOARD_SIZE; i++) {
                            System.out.print(getAllowedValuesInCurrentCol(col)[i] + ",");
                        }
                        System.out.println(")     Allowed values in col " + (col + 1));

                        //Print Allowed Values in Group
                        group = 3 * (row / 3) + (col / 3);
                        System.out.print("(");
                        for (int i = 0; i < BOARD_SIZE; i++) {
                            System.out.print(getAllowedValuesInCurrentGroup(row, col)[i] + ",");
                        }
                        System.out.println(")     Allowed values in group " + (group + 1));

                        //Print Allowed Values in Cell
                        System.out.print("(");
                        for (int i = 0; i < getAllowedValuesInTheCurrentCell(row, col).length; i++) {
                            System.out.print(getAllowedValuesInTheCurrentCell(row, col)[i] + ",");
                        }
                        int spaces = 23 - (getAllowedValuesInTheCurrentCell(row, col).length * 2);
                        System.out.printf(")");
                        for (int i = 0; i < spaces; i++) {
                            System.out.print(" ");
                        }
                        System.out.println("Allowed values in cell (" + (col + 1) + "," + (row + 1) + ")");

                        if (isOnlyOneValueAllowedInTheCurrentCell(row, col)) {
                            updateGame(getTheUniqueAllowedValue(row, col), row, col);
                            hasAGameUpdateOccured = true;
                        }
                    }
                }
            }
            if (!hasAGameUpdateOccured) {
                //End game - puzzle is unsolvable
                System.out.println("The game is unsolvable");
                System.exit(0);
            }
        }
    }

    public int[][] retrieveSudokuGameFromFile(String sudokuFile) throws IOException {
        Scanner fileInput = new Scanner(new File(sudokuFile));
        String oneRow;
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            oneRow = fileInput.nextLine();
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = Integer.parseInt(oneRow.charAt(2 * col) + "");
            }
        }
        return board;
    }

    public void printBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(game[row][col]);
                if (col == 2 || col == 5) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
            if (row == 2 || row == 5) {
                System.out.println("-----------------");
            }
        }
        System.out.println("");
    }

    public boolean isCellEmpty(int row, int col) {
        return game[row][col] == EMPTY_CELL;
    }

    public boolean isOnlyOneValueAllowedInTheCurrentCell(int row, int col) {
        return getAllowedValuesInTheCurrentCell(row, col).length == 1;
    }

    public int[] getAllowedValuesInTheCurrentCell(int row, int col) {
        int[] allowedValuesInCurrentRow = getAllowedValuesInCurrentRow(row);
        int[] allowedValuesInCurrentCol = getAllowedValuesInCurrentCol(col);
        int[] allowedValuesInCurrentGroup = getAllowedValuesInCurrentGroup(row, col);

        int[] temp = new int[BOARD_SIZE];
        int index = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (allowedValuesInCurrentRow[i] != 0
                    && allowedValuesInCurrentCol[i] != 0
                    && allowedValuesInCurrentGroup[i] != 0) {
                temp[index] = i + 1;
                index++;
            }
        }

        int[] allowedValues = new int[index];
        for (int i = 0; i < index; i++) {
            allowedValues[i] = temp[i];
        }
        return allowedValues;
    }

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
            if (!hasValueInRow) {
                allowedValueInRow[i - 1] = i;
            }
        }

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
            if (!hasValueInCol) {
                allowedValueInCol[i - 1] = i;
            }
        }

        return allowedValueInCol;

    }

    public int[] getAllowedValuesInCurrentGroup(int row, int col) {
        int corner_row = (row / 3) * 3;
        int corner_col = (col / 3) * 3;
        int cellValue;
        int[] allowedValuesInCurrentGroup = new int[BOARD_SIZE];
        for (int i = 0; i < GROUP_SIZE; i++) {
            for (int j = 0; j < GROUP_SIZE; j++) {
                cellValue = game[corner_row + i][corner_col + j];
                if (cellValue != EMPTY_CELL) {
                    allowedValuesInCurrentGroup[cellValue - 1] = cellValue;
                }
            }
        }
        //Reverse the array
        for (int i = 0; i < BOARD_SIZE; i++) {
            cellValue = allowedValuesInCurrentGroup[i];
            if (cellValue == EMPTY_CELL) {
                allowedValuesInCurrentGroup[i] = i + 1;
            } else {
                allowedValuesInCurrentGroup[i] = 0;
            }
        }

        return allowedValuesInCurrentGroup;
    }
    
    public int getTheUniqueAllowedValue(int row, int col) {
        int[] allowedValues = getAllowedValuesInTheCurrentCell(row, col);
        return allowedValues[0];
    }

    public void updateGame(int allowedValue, int row, int col) {
        game[row][col] = allowedValue;
        currentStep++;
        System.out.println("\nStep " + currentStep + ": " + allowedValue + " is placed at row: " + (row + 1) + " column: " + (col + 1) + "\n");
        printBoard();
    }

    public boolean hasPuzzleBeenSolved() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (game[row][col] == 0) {
                    return false;
                }
            }
        }
        showGameSolvedMessage();
        return true;
    }

    public void showGameSolvedMessage() {
        System.out.println("\nGame solved.");
    }

}
