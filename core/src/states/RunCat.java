package states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class RunCat extends Game{
	Game game;
	public static RunCat cat;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		cat = this;
		setScreen(new MenuScreen());
	}
	
	public void changeScreen(Screen sc) {
 		cat.setScreen(sc);
	}
}

/*
 * runcat
 * Play
 * variables
 * 
 * */


