package ru.danilovv.tetris;

import com.badlogic.gdx.scenes.scene2d.InputListener;

abstract public class CoreInputListener extends InputListener {

    PlatformKeyListener _keyListener;

    public void init(PlatformKeyListener keyListener) {
        _keyListener = keyListener;
    }

}
