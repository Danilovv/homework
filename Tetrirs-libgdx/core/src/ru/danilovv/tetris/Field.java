package ru.danilovv.tetris;

public class Field {
    public static final int HEIGHT = 20;
    public int[][] box;

    public Field(int rows, int columns) {
        box = new int[rows][columns];
    }

    public boolean hasConflict(int column, int row, int[][] data) {
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[r].length; c++) {
                if(data[r][c] > 0) {

                    if(!isInside(r + row, c + column)) {
                        return true;
                    }

                    if(box[r+row][c+column] > 0) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    private boolean isInside(int i, int j) {
        return i>=0 && j>=0 && i<box.length && j<box[0].length;
    }

    public int getRows() {
        return box.length;
    }

    public int getColumns() {
        return box[0].length;
    }

    public int removeFullRows() {
        int [][] b = new int[box.length][box[0].length];
        int pointer = box.length - 1;
        int countOfFullRows = 0;
        for(int row = pointer; row >= 0; row--) {
            if(isFull(box[row])) {
                countOfFullRows++;
                continue;
            }
            b[pointer--] = box[row];
        }

        box = b;

        return countOfFullRows;
    }

    private boolean isFull(int[] row) {
        for (Integer cell : row) {
            if(cell == 0) {
                return false;
            }
        }
        return true;
    }
}
