package sudokugame;

import java.io.IOException;

public interface SudokuInterface {
    int[][] retrieveSudokuGameFromFile(String sudokuFile) throws IOException;
    void printBoard();
    boolean isCellEmpty(int row, int col);
    boolean isOnlyOneValueAllowedInTheCurrentCell(int row, int col);
    int[] getAllowedValuesInTheCurrentCell(int row, int col);
    int[] getAllowedValuesInCurrentRow(int row);
    int[] getAllowedValuesInCurrentCol(int col);
    int[] getAllowedValuesInCurrentGroup(int row, int col);
    int getTheUniqueAllowedValue(int row, int col);
    void updateGame(int allowedValue, int row, int col);
    boolean hasPuzzleBeenSolved();
    void showGameSolvedMessage();
}