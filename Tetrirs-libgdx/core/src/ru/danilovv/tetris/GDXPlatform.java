package ru.danilovv.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private ShapeRenderer shapeRenderer;

    private ScoreOutput _scoreOutput;

    private static final Color[] colors = {Color.BLACK, Color.BLUE, Color.RED,
            Color.GREEN, Color.YELLOW, Color.ORANGE};

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

        public int X() {
            return x;
        }

        public int Y() {
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
        _textureRegions = new HashMap<String, TextureRegion>();
        _properties = new ArrayList<BlockProperty>();
        _actors = new ArrayList<Actor>();
        shapeRenderer = new ShapeRenderer();
    }

    static {
        mutex = new Object();
    }

    public GDXPlatform() {
        _scoreOutput = new ScoreOutput(0);
        _actors.add(this);
        _actors.add(_scoreOutput);
    }

    public GDXPlatform(int width, int height) {
        _scoreOutput = new ScoreOutput(0);
        _actors.add(this);
        this.setWidth(width);
        this.setHeight(height);
        _actors.add(_scoreOutput);
    }

    public void init(final PlatformKeyListener keyListener, CoreInputListener inputListener) {
        _keyListener = keyListener;
        inputListener.init(_keyListener);
        addListener(inputListener);
        loadTextures();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        synchronized (mutex) {
            for (BlockProperty property : _properties) {
                drawBlock(batch, property);
            }
            /*for (BlockProperty property : _properties) {
                drawBlock(batch, _textureRegions.get("color" + property.getColorIndex()), property);
            }*/
        }
    }

    private void drawBlock(Batch batch, BlockProperty property) {
        batch.end();


        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.translate(property.X(), property.Y(), 0);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(colors[property.getColorIndex()]);
        Gdx.gl.glLineWidth(5);
        final int arcRadius = 5;
        final int offset = 1;
        final int width = property.getWidth() - offset * 2;
        final int height = property.getHeight() - offset * 2;
        final int posX = arcRadius + offset;
        final int posY = arcRadius + offset;

        shapeRenderer.rect(posX, offset, width - arcRadius * 2, height);
        shapeRenderer.rect(offset, posY, width, height - arcRadius * 2);

        shapeRenderer.arc(posX, posY, arcRadius, 180, 90, 50);
        shapeRenderer.arc(property.getWidth() - arcRadius - offset, posY, arcRadius, 270, 90, 10);
        shapeRenderer.arc(property.getWidth() - arcRadius - offset, property.getHeight() - arcRadius - offset,
                arcRadius, 0, 90, 10);
        shapeRenderer.arc(posX, property.getHeight() - arcRadius - offset, arcRadius, 90, 90, 10);

        shapeRenderer.end();


        batch.begin();
    }

    private void drawBlock(Batch batch, TextureRegion region, BlockProperty property) {
        batch.draw(region, property.X(), property.Y(),
                property.getWidth(), property.getHeight());
    }

    private void loadTextures() {
        Texture texture;
        texture = new Texture(Gdx.files.internal("images/colorsAtlas.png"));

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
            _properties = new ArrayList<BlockProperty>();
        }
        this.setColor(Colors.getColors().get("BLUE"));
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {

    }

    @Override
    public void fillRect(int colorIndex, int x, int y, int width, int height) {
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
        return (int) (this.getHeight() + _scoreOutput.getHeight() + (_scoreOutput.getY() - this.getHeight()));
    }
}


/*      shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.BLACK);
        //Gdx.gl.glLineWidth(5);
        shapeRenderer.rect(0, 0, property.getWidth(), property.getHeight());

        shapeRenderer.end();
*/


/*
batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());

        shapeRenderer.translate(property.X(), property.Y(), 0);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(colors[property.getColorIndex()]);

        shapeRenderer.rect(0, 0, property.getWidth(), property.getHeight());
        //shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        Gdx.gl.glLineWidth(5);
        shapeRenderer.rect(0, 0, property.getWidth(), property.getHeight());
        //shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.setColor(colors[property.getColorIndex()]);
        Gdx.gl.glLineWidth(5);
        shapeRenderer.arc(5, 5, 5, 180, 90,20);
        shapeRenderer.arc(property.getWidth() - 5, 5, 5, 270, 90);
        shapeRenderer.arc(property.getWidth()-5, property.getHeight()-5, 5, 0, 90);
        shapeRenderer.arc(5, property.getHeight()-5, 5, 90, 90);
        //shapeRenderer.line(x, y, x2, y2);
        shapeRenderer.end();

        batch.begin();*/
