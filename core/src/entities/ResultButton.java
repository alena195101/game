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
import states.MenuScreen;
import states.Play;
import states.RunCat;

public class ResultButton extends Stage{
	public TextButton.TextButtonStyle textButtonStyle;
	TextureAtlas atlas;
	Stage stage;
	String s="",res;
	TextButton exit = null;
	TextButton tryButton = null;
	TextButton menuButton = null;
	public ResultButton(Stage stage, String level,String result) {
		this.stage = stage;

		this.res = result;
		this.s = level;
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
		if ((res == "LOSE" )||((res.equals("WON"))&&(s.equals("level2"))) ) {
			tryButton = new TextButton("Try again", textButtonStyle);
			tryButton.setBounds(110, 180, 115, 50);
			tryButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent e, float x, float y) {
					
					Variables.score=0;
					Variables.life =3;
					if(s == "level1") {
						Variables.level = 1;
					}else {
						Variables.level = 2;
					}
					RunCat.cat.changeScreen(new Play(s,Variables.life));
	
				}
			});
			
			menuButton = new TextButton("Menu", textButtonStyle);
			menuButton.setBounds(110, 140, 115, 50);
			menuButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent e, float x, float y) {
					RunCat.cat.changeScreen(new MenuScreen());
				}
			} );
			
			
			exit = new TextButton("Exit", textButtonStyle);
			exit.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent e, float x, float y) {
					Gdx.app.exit();
				}
			} );
			exit.setBounds(110, 100, 115, 50);
			
		}else if((res == "WON")&&(s == "level1")){
				tryButton = new TextButton("Next level", textButtonStyle);
				tryButton.setBounds(110, 180, 115, 50);
				tryButton.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent e, float x, float y) {
						Variables.score=0;
						Variables.level = 2;
						Variables.life = 3;
						RunCat.cat.changeScreen(new Play("level2",Variables.life));
					}
					} );
				
				menuButton = new TextButton("Menu", textButtonStyle);
				menuButton.setBounds(110, 140, 115, 50);
				menuButton.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent e, float x, float y) {
						RunCat.cat.changeScreen(new MenuScreen());
					}
				} );
				
				exit = new TextButton("Exit", textButtonStyle);
				exit.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent e, float x, float y) {
						Gdx.app.exit();
					}
				} );
				exit.setBounds(110, 100, 115, 50);
				
			}
		if (tryButton != null)
			stage.addActor(tryButton);
		if (menuButton != null)
			stage.addActor(menuButton);
		if (exit != null)
			stage.addActor(exit);
	}

}
