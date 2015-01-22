package ru.danilovv.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tetris extends ApplicationAdapter {

	private final CoreInputListener _inputListener;

	private Stage _stage;

	OrthographicCamera camera;

	public Tetris(CoreInputListener inputListener) {
		_inputListener = inputListener;
	}

	@Override
	public void create () {
		Gdx.graphics.setDisplayMode(400,700, false);

		_stage = new Stage(new FitViewport(400, 700));
		Gdx.input.setInputProcessor(_stage);

		camera = (OrthographicCamera) _stage.getCamera();
		camera.setToOrtho(true, _stage.getWidth(), _stage.getHeight());

		GDXPlatform _platform = new GDXPlatform();
		for (Actor actor : _platform.getActors()) {
			_stage.addActor(actor);
		}

		_stage.setKeyboardFocus(_platform);
		_platform.setBounds(0, 0, _platform.getWidth(), _platform.getHeight());

		View _view = new View(_platform);
		_platform.init(_view, _inputListener);


		Model model = new Model();
		final Controller controller = new Controller(model, _view);
		model.addListener(controller);
		_view.setListener(controller);

		model.newGame();

		ScheduledExecutorService service
				= Executors.newSingleThreadScheduledExecutor();

		service.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
					controller.moveDown();
			}
		}, 1, 1, TimeUnit.SECONDS);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.5f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		_stage.getViewport().update(width, height, false);
	}

}
