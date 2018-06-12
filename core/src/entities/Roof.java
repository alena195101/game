package entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Roof extends Actor{
	private World world;
	Body body;
	public Roof (World world,float x) {
		this.world = world;
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		bdef.position.set(0, 500);
		bdef.type = BodyType.StaticBody;
		body = world.createBody(bdef);
		shape.setAsBox(x, 20);
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("roof");
	}

}
