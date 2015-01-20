public class View implements PlatformKeyListener {

    private static final int CELL_SIZE = 30;

    private int _fieldHeight;
    private int _fieldWidth;
    private int _fieldOffsetX;
    private int _fieldOffsetY;

    private EventProcessor _processor;
    private Platform _platform;

    public View(Platform platform) {
        _platform = platform;
        _platform.setKeyListener(this);
    }

    public void updateState(State state) {
        if(_fieldHeight == 0) {
            calculateFieldDimensions(state.getField());
        }
        _platform.clearArea();
        drawField(state.getField());
        drawFigure(state);
        drawScore(state.getScore());
    }

    private void drawField(Field field) {
        _platform.fillRect(_platform.getBackgroundColorIndex(), _fieldOffsetX, _fieldOffsetY, _fieldWidth, _fieldHeight);

        int [][] matrix = field.box;
        drawMatrix(matrix, 0, 0);
    }

    private void calculateFieldDimensions(Field field) {
        _fieldHeight = field.getRows() * CELL_SIZE;
        _fieldWidth = field.getColumns() * CELL_SIZE;
        _fieldOffsetX = (_platform.getWidth() - _fieldWidth) / 2;
        _fieldOffsetY = (_platform.getHeight() - _fieldHeight) / 2;
    }

    private void drawFigure(State state) {
        int [][] matrix = state.getFigure().getData();
        int rowShift = state.figureRow;
        int columnShift = state.figureColumn;

        drawMatrix(matrix, rowShift, columnShift);
        //g.setColor(Color.GREEN);
        //g.fillRect(state.figureColumn * CELL_SIZE, state.figureRow * CELL_SIZE, CELL_SIZE, CELL_SIZE);

    }

    private void drawMatrix(int[][] matrix, int rowShift, int columnShift) {
        for (int r = 0; r < matrix.length; r++) {
            for(int c = 0 ; c < matrix[r].length; c++) {
                if(matrix[r][c] == 0) {
                    continue;
                }
                drawCell(matrix[r][c], rowShift + r, columnShift + c);
            }
        }
    }

    private void drawCell(int colorIndex, int row, int col) {
        _platform.fillRect(colorIndex, col * CELL_SIZE + _fieldOffsetX,
                row * CELL_SIZE + _fieldOffsetY, CELL_SIZE, CELL_SIZE);
        _platform.drawRect(col * CELL_SIZE + _fieldOffsetX,
                row * CELL_SIZE + _fieldOffsetY, CELL_SIZE, CELL_SIZE);
    }

    private void drawScore(int score) {
        _platform.drawScore(score, _fieldOffsetX, _fieldOffsetY + _fieldHeight + CELL_SIZE);
    }

    public void setListener(EventProcessor processor) {
        _processor = processor;
    }

    @Override
    public void moveLeft() {
        _processor.moveLeft();
    }

    @Override
    public void moveRight() {
        _processor.moveRight();
    }

    @Override
    public void drop() {
        _processor.drop();
    }

    @Override
    public void rotate() {
        _processor.rotate();
    }
}
