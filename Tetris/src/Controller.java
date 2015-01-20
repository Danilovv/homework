public class Controller implements ModelListener, EventProcessor {

    private Model _model;
    private View _view;

    public Controller(Model model, View view) {
        _model = model;
        _view = view;
    }

    @Override
    public void updateState(State state) {
        _view.updateState(state);
    }

    @Override
    public void moveLeft() {
        _model.moveLeft();
    }

    @Override
    public void moveRight() {
        _model.moveRight();
    }

    @Override
    public void drop() {
        _model.drop();
    }

    @Override
    public void rotate() {
        _model.rotate();
    }

    @Override
    public void moveDown() {
        _model.moveDown();
    }

    @Override
    public void newGame() {
        _model.newGame();
    }

    @Override
    public void resetGame() {
        _model.resetGame();
    }
}
