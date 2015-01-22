package ru.danilovv.tetris.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import ru.danilovv.tetris.CoreInputListener;
import ru.danilovv.tetris.PlatformKeyListener;
import ru.danilovv.tetris.Tetris;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Tetris(new DesktopInputListener());
        }

        static class DesktopInputListener extends CoreInputListener {

                private PlatformKeyListener _keyListener;

                public void init(PlatformKeyListener keyListener) {
                        _keyListener = keyListener;
                }

                @Override
                public boolean keyDown (InputEvent event, int keycode) {
                        switch (keycode) {
                                case Input.Keys.LEFT: {
                                        _keyListener.moveLeft();
                                        break;
                                }
                                case Input.Keys.RIGHT: {
                                        _keyListener.moveRight();
                                        break;
                                }
                                case Input.Keys.DOWN: {
                                        _keyListener.drop();
                                        break;
                                }
                                case Input.Keys.UP: {
                                        _keyListener.rotate();
                                        break;
                                }
                        }

                        return true;
                }

        }
}