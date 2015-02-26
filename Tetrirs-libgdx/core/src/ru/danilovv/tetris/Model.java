package ru.danilovv.tetris;

import java.util.LinkedList;
import java.util.List;

public class Model implements EventProcessor {
    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;
    //EventProcessor _eventProcessor;
    Logic _logic;
    State _state;
    private List<ModelListener> _listeners;

    public Model() {
        _listeners = new LinkedList<ModelListener>();
        _state = new State(HEIGHT, WIDTH);
        _logic = new Logic(_state);
    }

    public void init() {
        _state.init();
    }

    @Override
    public void moveLeft() {
        _logic.moveLeft();
        updateState();
    }

    @Override
    public void moveRight() {
        _logic.moveRight();
        updateState();
    }

    @Override
    public void drop() {
        _logic.drop();
        updateState();
    }

    @Override
    public void rotate() {
        _logic.rotate();
        updateState();
    }

    @Override
    public void moveDown() {
        _logic.moveDown();
        updateState();
    }

    @Override
    public void newGame() {
        _logic.newGame();
        updateState();
    }

    @Override
    public void resetGame() {
        _logic.resetGame();
    }

    private void updateState() {
        for (ModelListener listener : _listeners) {
            listener.updateState(_state);
        }
    }

    public void addListener(ModelListener listener) {
        _listeners.add(listener);
    }
}
