package states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;


import entities.ResultButton;
import handler.Variables;

public class ResultScreen implements Screen{
	private String res,level;
	private Stage stage;
	private OrthographicCamera camera;
	private ResultButton resButton;
	BitmapFont f;
	Game game;
	
	public ResultScreen(String result, String level) {
		this.res = result;
		this.level = level;
	}

	
	@Override
	public void show() {
		stage = new Stage(new FillViewport(Variables.V_WIDTH, Variables.V_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Variables.V_WIDTH, Variables.V_HEIGHT);
		
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("assets/font/font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
		p.color = Color.WHITE;
		p.size = 100;
		p.borderColor = Color.BROWN;
		p.borderWidth = 5;
		p.shadowOffsetX = 8;
		p.shadowOffsetY = 8;
		
		resButton = new ResultButton(stage, level, res);
		
		f = gen.generateFont(p);
		f.getData().setScale(0.3f);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor( 1, 1, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		
		stage.getBatch().begin();
		f.draw(stage.getBatch(), res, 130 , Variables.V_HEIGHT-20);
		stage.getBatch().end();
		
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
