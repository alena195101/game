package entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import handler.Variables;
import states.LevelsScreen;
import states.Play;
import states.RunCat;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


public class MenuButton extends Stage{
	public TextButton.TextButtonStyle textButtonStyle;
	TextureAtlas atlas;
	Stage stage;

	
	public MenuButton( Stage stage) {
		this.stage = stage;
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
		TextButton playButton = new TextButton("Play", textButtonStyle);
		playButton.setBounds(110, 220, 115, 50);
		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent e, float x, float y) {
				RunCat.cat.changeScreen(new Play("level1",Variables.life));

			}
		} );
		TextButton levelsButton = new TextButton("Levels", textButtonStyle);
		levelsButton.setBounds(110, 180, 115, 50);
		levelsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent e, float x, float y) {
				RunCat.cat.changeScreen(new LevelsScreen());
			}
		} );
		TextButton exit = new TextButton("Exit", textButtonStyle);
		exit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent e, float x, float y) {
				Gdx.app.exit();
			}
		} );
		exit.setBounds(110, 140, 115, 50);
		/*
		TextButton logs = new TextButton("Logs", textButtonStyle);
		logs.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent e, float x, float y) {
				//Gdx.app.exit();
				//RunCat.cat.changeScreen(new LevelsScreen());
			}
		} );
		logs.setBounds(110, 140, 115, 50);
		
		stage.addActor(logs);*/
		stage.addActor(exit);
		stage.addActor(levelsButton);
		stage.addActor(playButton);
	}
	
}
