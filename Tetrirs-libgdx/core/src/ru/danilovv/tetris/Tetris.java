package ru.danilovv.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.imageio.spi.IIORegistry;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tetris extends ApplicationAdapter {

    private final CoreInputListener _inputListener;
    private Stage _stage;
    private OrthographicCamera camera;

    private GDXPlatform _platform;
    private View _view;
    private Model _model;

    private int _width;
    private int _height;

    public Tetris(CoreInputListener inputListener) {
        _inputListener = inputListener;
    }

    public static class IO {
        public static final IIORegistry theRegistry = IIORegistry.getDefaultInstance();

        public static class URLHandle extends FileHandle {
            final URL url;

            public URLHandle(URL url) {
                this(url.toString());
            }

            public URLHandle(String url) {
                try {
                    this.url = new URL(url);
                } catch(Exception e) {
                    throw new GdxRuntimeException("Couldn't create URLHandle for '" + url + "'", e);
                }
            }


            @Override
            public String name() {
                return "lalka";
            }

            @Override
            public FileHandle child(String name) {
                return null;
            }

            @Override
            public FileHandle parent() {
                return null;
            }

            @Override
            public InputStream read () {
                try {
                    return url.openStream();
                } catch (IOException e) {
                    throw new GdxRuntimeException("Couldn't read URL '" + url.toString() + "'");
                }
            }
        }

    }

    @Override
    public void create() {

        ArrayList<Integer> integers = new ArrayList<Integer>();
        for(int i = 0 ; i < 20; i++) {
            integers.add(i);
        }

        System.out.println("original - " + integers);
        System.out.println("sub 0-10 - " + integers.subList(0, 10));
        System.out.println("sub 4-9 - " + integers.subList(4, 9));

        /*Texture instance = new Texture(Gdx.files.internal("images/badlogic-new.png"));

        instance.getTextureData().prepare();

        Pixmap bPM = instance.getTextureData().consumePixmap();
        int toReturn = 0;
        for (int x = 0; x < bPM.getWidth(); x++ )
            for(int y = 0; y < bPM.getHeight(); y++) {
                toReturn = bPM.getPixel(x, y);
                com.badlogic.gdx.graphics.Color color = new Color(toReturn);
                System.out.println("Color is " + color);
            }
        bPM.dispose();*/




        /*URL url = null;
        try {
            url = new URL("http://www.badlogicgames.com/wordpress/wp-content/uploads/2012/01/badlogic-new.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(url);
        IO.URLHandle urlHandle = new IO.URLHandle("http://www.badlogicgames.com/wordpress/wp-content/uploads/2012/01/badlogic-new.png");
        System.out.println(urlHandle);*/

        return;

        /*setFrameSizeByDisplaySize();

        initMVC();

        final Controller controller = getController();

        startGame(controller);

        initDisplaySettings();*/
    }

    private void setFrameSizeByDisplaySize() {
        /*_height = Gdx.graphics.getHeight();
        _width = _height / 2;*/
        _height = 800;
        _width = _height / 2;
        System.out.println(_width + "w DEF h" + _height);
    }

    private void initMVC() {
        _stage = new Stage();

        _platform = new GDXPlatform(_width, _height);
        for (Actor actor : _platform.getActors()) {
            _stage.addActor(actor);
        }

        Gdx.input.setInputProcessor(_stage);
        _stage.setKeyboardFocus(_platform);

        _platform.setBounds(0, 0, _platform.getWidth(), _platform.getHeight());

        _view = new View(_platform);
        _platform.init(_view, _inputListener);

        _model = new Model();
    }

    private Controller getController() {
        final Controller controller = new Controller(_model, _view);
        _model.addListener(controller);
        _view.setListener(controller);
        return controller;
    }

    private void startGame(final Controller controller) {
        _model.newGame();

        ScheduledExecutorService service
                = Executors.newSingleThreadScheduledExecutor();

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                controller.moveDown();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    private void initDisplaySettings() {
        _width = _platform.getPlatformWidth();
        _height = _platform.getPlatformHeight();
        System.out.println(_width + "w h" + _height);

        Gdx.graphics.setDisplayMode(_width, _height, false);
        _stage.setViewport(new ScreenViewport());
        camera = (OrthographicCamera) _stage.getCamera();
        camera.setToOrtho(true);
    }

    @Override
    public void render() {
        /*Gdx.gl.glClearColor(0, 0, 0.5f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        _stage.draw();*/
    }

    @Override
    public void resize(int width, int height) {
//        _stage.getViewport().update(width, height, true);
    }

}
