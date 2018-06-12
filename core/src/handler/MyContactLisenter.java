package handler;

import java.time.LocalDate;
import java.time.LocalTime;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

import states.LevelsScreen;
import states.Play;
import states.ResultScreen;
import states.RunCat;

public class MyContactLisenter implements ContactListener{
	public FileHandle file;
	public static int playerOnGround=1;
	public static boolean end=false;
	public static Array<Body> removeBodies = new Array<Body>();
	//when 2 texsture start to cintact
	@Override
	public void beginContact(Contact cont) {
		Fixture fa = cont.getFixtureA();
		Fixture fb = cont.getFixtureB();
		file = Gdx.files.local("assets/log.txt");
		
		if (fa.getUserData()=="player" && fb.getUserData()=="ground" ||
				fa.getUserData()=="ground" && fb.getUserData()=="player") {
			playerOnGround++;
			System.out.println(playerOnGround);
		}
		if (fa.getUserData()=="player" && fb.getUserData()=="bomb" ||
				fa.getUserData()=="bomb" && fb.getUserData()=="player") {
				Play.bombs.play(1.0f);
				Variables.life--;
				if (Variables.life > -1) {
					if (Variables.level==1) {
						RunCat.cat.changeScreen(new Play("level1",Variables.life));
					}else RunCat.cat.changeScreen(new Play("level2",Variables.life));
				}else if (Variables.level==1) {
					RunCat.cat.changeScreen(new ResultScreen("LOSE","level1"));
					file.writeString("Lose in level 1    "+LocalDate.now()+" "+LocalTime.now()+"\n", true);
				}else {
					RunCat.cat.changeScreen(new ResultScreen("LOSE","level2"));
					file.writeString("Lose in level 2     "+LocalDate.now()+" "+LocalTime.now()+"\n", true);
				}
				
		}
		if (fa.getUserData()=="player" && fb.getUserData()=="crystal" ||
				fa.getUserData()=="crystal" && fb.getUserData()=="player") {
			Variables.score++;
			if (fa.getUserData()=="crystal") {
				removeBodies.add(fa.getBody());
			}
			else {
				removeBodies.add(fb.getBody());
			}
		}
		if (fa.getUserData()=="player" && fb.getUserData()=="wall" ||
				fa.getUserData()=="wall" && fb.getUserData()=="wall") {
			if(Variables.level==1) {
				if (Variables.score > 18) {
					RunCat.cat.changeScreen(new ResultScreen("WON","level1"));
					file.writeString("Win in level 1    "+LocalDate.now()+" "+LocalTime.now()+"\n", true);
				}else {
					RunCat.cat.changeScreen(new ResultScreen("LOSE","level1"));
					file.writeString("Lose in level 1   "+LocalDate.now()+" "+LocalTime.now()+"\n", true);
				}
			}else if (Variables.score >1) {
				System.out.println(RunCat.cat.toString());
					RunCat.cat.changeScreen(new ResultScreen("WON","level2"));
					file.writeString("Win in"+LocalDate.now()+" "+LocalTime.now()+"\n", true);
				}else {
					RunCat.cat.changeScreen(new ResultScreen("LOSE","level2"));
					file.writeString("Lose in"+LocalDate.now()+" "+LocalTime.now()+"\n", true);
			}
				}
				
			
		}
		

	
	//when 2 texture no longer contact
	@Override
	public void endContact(Contact cont) {
		Fixture fa = cont.getFixtureA();
		Fixture fb = cont.getFixtureB();
		
		if (fa.getUserData()=="player" && fb.getUserData()=="ground" ||
				fa.getUserData()=="ground" && fb.getUserData()=="player") {
			playerOnGround--;
			System.out.println(playerOnGround);
	}
	}
//	public Array<Body> getBodies() { return removeBodies; }
	
	@Override
	public void preSolve(Contact cont, Manifold mf) {
	}
	@Override
	public void postSolve(Contact cont, ContactImpulse cimp) {
	}

}
