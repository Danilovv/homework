package ru.danilovv.tetris.desktop;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import ru.danilovv.tetris.CoreInputListener;
import ru.danilovv.tetris.PlatformKeyListener;
import ru.danilovv.tetris.Tetris;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		DesktopInputListener desktopInputListener = new DesktopInputListener();

		new LwjglApplication(new Tetris(desktopInputListener), config);
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
