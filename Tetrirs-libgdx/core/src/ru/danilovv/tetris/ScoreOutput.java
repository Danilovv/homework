package ru.danilovv.tetris;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ScoreOutput extends Actor {
    private final String template = "Score: ";
    protected int _state;
    protected BitmapFont _font = new BitmapFont();

    public ScoreOutput(int state) {
        _state = state;
        _font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        _font.setScale(1.2f,-1.2f);
    }

    public void set(int score, int x, int y) {
        _state = score;
        setX(x);
        setY(y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        _font.draw(batch, template + _state, getX(), getY());
    }
}
