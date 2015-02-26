package ru.danilovv.tetris;

public class Logic implements EventProcessor {

    public State state;

    public Logic(State state) {
        this.state = state;
    }

    @Override
    public void moveLeft() {
        if (!state.canMoveLeft()) {
            return;
        }
        state.moveFigureLeft();
    }

    @Override
    public void moveRight() {
        if (!state.canMoveRight()) {
            return;
        }
        state.moveFigureRight();
    }

    @Override
    public void drop() {
        do {
            moveDown();
        } while (state.canMoveDown());
    }

    @Override
    public void rotate() {
        state.rotate();
        if (!state.isFigureInBox()) {
            state.setFigureToBeAtTheBox();
        }
    }

    @Override
    public void moveDown() {
        if (!state.canMoveDown()) {
            state.pasteFigure();
            int countOfRemovedRows = state.removeFullRows();
            state.putScore(countOfRemovedRows);
            state.nextFigure();

            if (!state.canMoveDown()
                    && !state.canMoveLeft()
                    && !state.canMoveRight()) {
                resetGame();
            }
            return;
        }
        state.moveFigureDown();
    }

    @Override
    public void newGame() {
        state.init();
    }

    @Override
    public void resetGame() {
        state.reset();
    }
}
