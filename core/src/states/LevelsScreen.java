package states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;

import entities.LevelButton;
import handler.Variables;

public class LevelsScreen  implements Screen{
	TextureAtlas atlas;
	Sprite spite;
	Stage stage;
	TextureAtlas tatlas;
	private LevelButton levelButton;
	Skin skin;
	Game game;
	private OrthographicCamera camera;

	public LevelsScreen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		stage = new Stage(new FillViewport(Variables.V_WIDTH, Variables.V_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Variables.V_WIDTH, Variables.V_HEIGHT);
		levelButton = new LevelButton(stage, game);
	}
	BitmapFont f;
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor( 1, 1, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
