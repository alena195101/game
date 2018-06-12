package entities;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;

import states.Play;

public class Cristal extends Actor{
	 private Sprite sprite = null;
	 private Body body;
	float x,y;
	public Cristal(World world, float x, float y ) {
		TextureAtlas txAtlas = new TextureAtlas(Gdx.files.internal("assets/image/menu.txt"));
		sprite = new Sprite(txAtlas.findRegion("crystal"));
		this.x = x;
		this.y = y;
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		bdef.type = BodyType.StaticBody;
		bdef.position.set(x,y);
		CircleShape cshape = new CircleShape();
		cshape.setRadius(8);
		fdef.shape = cshape;
		fdef.isSensor = true;
		body = world.createBody(bdef);
		body.createFixture(fdef).setUserData("crystal");
		Play.Crystals.put(body, this);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (sprite!=null) {
			float x1 = x-Player.playerBody.getPosition().x+192;
			float y1 = y-Player.playerBody.getPosition().y+142;
			sprite.setPosition(x1, y1);
			sprite.draw(batch);
		}
	}
		

}
