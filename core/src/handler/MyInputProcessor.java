package handler;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import entities.Player;

public class MyInputProcessor extends InputAdapter{
	public boolean keyDown(int k) {
		if(k == Keys.Z) {
			MyInput.setKey(MyInput.BUTTON1, true);
		}
		if(k == Keys.X) {
			MyInput.setKey(MyInput.BUTTON2, true);
		}
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.SPACE) {
			if(MyContactLisenter.playerOnGround>0) {
				Player.playerBody.applyForceToCenter(0, 99999, true);
			}
		}
		
		return true;
	}
	

}
