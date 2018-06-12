package entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import handler.Variables;
import states.Play;
import states.RunCat;

public class LevelButton extends Stage{
	public TextButton.TextButtonStyle textButtonStyle;
	TextureAtlas atlas;
	Stage stage;
	Game game;
	

	public LevelButton(Stage stage, Game game) {
		this.stage = stage;
		this.game = game;
		atlas = new TextureAtlas(Gdx.files.internal("assets/image/menu.txt"));
		Skin skin = new Skin();
		skin.addRegions(atlas);
		
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("assets/font/font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
		p.color = Color.WHITE;
		p.size = 100;
		p.borderColor = Color.BROWN;
		p.borderWidth = 5;
		p.shadowOffsetX = 8;
		p.shadowOffsetY = 8;
		
		BitmapFont f = gen.generateFont(p);
		f.getData().setScale(0.1f);
		Gdx.input.setInputProcessor(stage);
		
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button1");
		textButtonStyle.down = skin.getDrawable("button2");
		textButtonStyle.font = f;
		TextButton levels1Button = new TextButton("Level 1", textButtonStyle);
		levels1Button.setBounds(110, 180, 115, 50);
		levels1Button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent e, float x, float y) {
				Variables.score=0;
				Variables.level = 1;
				Variables.life = 3;
				RunCat.cat.changeScreen(new Play("level1",Variables.life));

			}
		} );
		TextButton levels2Button = new TextButton("Level 2", textButtonStyle);
		levels2Button.setBounds(110, 140, 115, 50);
		levels2Button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent e, float x, float y) {
				Variables.score=0;
				Variables.level = 2;
				Variables.life = 3;
				RunCat.cat.changeScreen(new Play("level2",Variables.life));
			}
		} );

		stage.addActor(levels1Button);
		stage.addActor(levels2Button);

		
	}
	
	

	
	

}
