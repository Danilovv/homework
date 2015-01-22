package ru.danilovv.tetris.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import ru.danilovv.tetris.CoreInputListener;
import ru.danilovv.tetris.PlatformKeyListener;
import ru.danilovv.tetris.Tetris;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidInputListener androidInputListener = new AndroidInputListener();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Tetris(androidInputListener), config);
	}

	static class AndroidInputListener extends CoreInputListener {

		private PlatformKeyListener _keyListener;

		public void init(PlatformKeyListener keyListener) {
			_keyListener = keyListener;
		}

		private class Position {
			private float x;
			private float y;

			public Position(float x, float y) {
				this.x = x;
				this.y = y;
			}

			public float X() {
				return x;
			}

			public float Y() {
				return y;
			}
		}

		private Position _lastTouchDown;
		private Position _lastTouchUp;

		@Override
		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
			_lastTouchDown = new Position(x, y);
			return true;
		}

		@Override
		public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			_lastTouchUp = new Position(x, y);
			float differenceX = Math.abs( _lastTouchUp.X()-_lastTouchDown.X() );
			float differenceY = Math.abs( _lastTouchUp.Y()-_lastTouchDown.Y() );
			if(differenceX > differenceY) {
				if(_lastTouchDown.X() > _lastTouchUp.X()) _keyListener.moveLeft();
				else _keyListener.moveRight();
			} else {
				if(_lastTouchDown.Y() > _lastTouchUp.Y()) _keyListener.rotate();
				else _keyListener.drop();
			}
		}

	}

}
