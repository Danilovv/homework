package ru.danilovv.tetris;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

class GameFrame extends Actor {

    public GameFrame(int fieldX, int fieldY) {
        setX(fieldX);
        setY(fieldY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

    }
}
