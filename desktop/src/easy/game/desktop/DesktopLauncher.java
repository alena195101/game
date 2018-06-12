package easy.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import handler.Variables;
import states.RunCat;
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width =  Variables.V_WIDTH * Variables.SCALE;
		config.height = Variables.V_HEIGHT * Variables.SCALE;
		new LwjglApplication(new RunCat(), config);
	}
}
