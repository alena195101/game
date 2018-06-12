package states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;

import entities.MenuButton;
import entities.MenuPicture;
import handler.Variables;

public class MenuScreen implements Screen{
	TextureAtlas atlas;
	Sprite spite;
	Stage stage;
	TextureAtlas tatlas;
	private MenuButton menuButton;
	Skin skin;
	private OrthographicCamera camera;
	
	public MenuScreen () {
	}
	@Override
	public void show() {
		
		
		stage = new Stage(new FillViewport(Variables.V_WIDTH, Variables.V_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		stage.addActor(new MenuPicture());
		
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Variables.V_WIDTH, Variables.V_HEIGHT);
		menuButton = new MenuButton(stage);
	} 
	
	
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
		dispose();
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
