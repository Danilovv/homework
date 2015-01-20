public class State {

    public Field field;
    public Figure figure;
    public int figureRow;
    public int figureColumn;
    private int _score;

    public State(int rows, int columns) {
        field = new Field(rows, columns);
    }

    public void init() {
        nextFigure();
    }

    public void nextFigure() {
        figure = Figure.generateRandom();
        figureColumn = field.box[0].length/2
                - figure.data[0].length/2;
        figureRow = 0;
    }

    public void moveFigureLeft() {
        figureColumn--;
    }

    public void moveFigureRight() {
        figureColumn++;
    }

    public void moveFigureDown() {
        figureRow++;
    }

    public boolean canMoveDown() {
        return !field.hasConflict(figureColumn, figureRow + 1, figure.data);
    }

    public boolean canMoveLeft() {
        return !field.hasConflict(figureColumn - 1, figureRow, figure.data);
    }

    public boolean canMoveRight() {
        return !field.hasConflict(figureColumn + 1, figureRow, figure.data);
    }

    public void pasteFigure() {
        for (int r = 0; r < figure.data.length; r++) {
            for (int c = 0; c < figure.data[r].length; c++) {
                if(figure.data[r][c] != 0) {
                    field.box[figureRow + r][figureColumn + c] = figure.data[r][c];
                }
            }
        }
    }

    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public int removeFullRows() {
        return field.removeFullRows();
    }

    public void rotate() {
        figure.data = Figure.rotateLeft(figure.getData());
    }

    public boolean isFigureInBox() {
        return !field.hasConflict(figureColumn, figureRow, figure.data);
    }

    public void setFigureToBeAtTheBox() {

        while (figureColumn < 0) {
            figureColumn++;
        }

        while (figureColumn + figure.data[0].length > field.getColumns() - 1) {
            figureColumn--;
        }
    }

    public void putScore(int countOfRemovedRows) {
        for(int i = 1; i <= countOfRemovedRows; i++) {
            _score += i * field.getColumns();
        }
    }

    public int getScore() {
        return _score;
    }

    public void reset() {
        field = new Field(field.box.length, field.box[0].length);
        _score = 0;
    }
}
