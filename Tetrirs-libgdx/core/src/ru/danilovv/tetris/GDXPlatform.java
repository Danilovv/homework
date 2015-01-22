package ru.danilovv.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GDXPlatform extends Actor implements Platform {

    private static final Object mutex;

    private PlatformKeyListener _keyListener;

    public Map<String, TextureRegion> _textureRegions;
    private List<BlockProperty> _properties;
    private List<Actor> _actors;

    private ScoreOutput _scoreOutput;

    private class BlockProperty {
        private int x;
        private int y;
        private int width;
        private int height;
        private int colorIndex;

        public BlockProperty(int x, int y, int width, int height, int colorIndex) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.colorIndex = colorIndex;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getColorIndex() {
            return colorIndex;
        }
    }

    // initializer
    {
        _textureRegions = new HashMap<>();
        _properties = new ArrayList<>();
        _actors = new ArrayList<>();
    }

    static {
        mutex = new Object();
    }

    public GDXPlatform() {
        _scoreOutput = new ScoreOutput(0);
        _actors.add(this);
        _actors.add(_scoreOutput);
    }

    public void init(final PlatformKeyListener keyListener, CoreInputListener inputListener) {
        this.setWidth(400f);
        this.setHeight(700f);
        _keyListener = keyListener;
        inputListener.init(_keyListener);
        addListener(inputListener);
        loadTextures();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        synchronized (mutex) {
            for (BlockProperty property : _properties) {
                drawBlock(batch, _textureRegions.get("color" + property.getColorIndex()), property);
            }
        }
    }

    private void drawBlock(Batch batch, TextureRegion region, BlockProperty property) {
        batch.draw(region, property.getX(), property.getY(),
                property.getWidth(), property.getHeight());
    }

    private void loadTextures() {
        Texture texture;
        texture  = new Texture(Gdx.files.internal("images/colorsAtlas.png"));

        TextureRegion tmp[][] = TextureRegion.split(texture, texture.getWidth() / 8, 32);

        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                System.out.println(i + " " + j);
            }
        }

        _textureRegions.put("color0", tmp[0][0]);
        _textureRegions.put("color1", tmp[0][1]);
        _textureRegions.put("color2", tmp[0][2]);
        _textureRegions.put("color3", tmp[0][3]);
        _textureRegions.put("color4", tmp[0][4]);
        _textureRegions.put("color5", tmp[0][5]);
    }

    public List<Actor> getActors() {
        return _actors;
    }

    @Override
    public void setKeyListener(PlatformKeyListener listener) {

    }

    @Override
    public void clearArea() {
        synchronized (mutex) {
            _properties = new ArrayList<>();
        }
        this.setColor(Colors.getColors().get("BLUE"));
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {

    }

    @Override
    public void fillRect(int colorIndex, int x, int y, int width, int height) {
        //if(colorIndex == 0) return;
        synchronized (mutex) {
            _properties.add(new BlockProperty(x, y, width, height, colorIndex));
        }
    }

    @Override
    public void drawScore(int score, int x, int y) {
        _scoreOutput.set(score, x, y);
    }

    @Override
    public int getBackgroundColorIndex() {
        return 0;
    }

    @Override
    public int getPlatformWidth() {
        return (int) this.getWidth();
    }

    @Override
    public int getPlatformHeight() {
        return (int) this.getHeight();
    }
}
