package entities;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;



public class Player extends Actor{
	World world;
	public static Body playerBody;
	Sprite sprite;
	public Animation<TextureRegion> runningAnimation;
	private TextureAtlas txAtlas;
	float stateTime = 0;
	TextureRegion currentFrame;
	
	public Player (World world) {
		this.world = world;
		BodyDef bdef = new BodyDef();
		bdef.gravityScale = 1.6f;
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		bdef.position.set(100, 50);
		bdef.type = BodyType.DynamicBody;
		playerBody = world.createBody(bdef);
		shape.setAsBox(40, 20);
		fdef.shape = shape;
		playerBody.createFixture(fdef).setUserData("player");
		MassData mass = new MassData();
		mass.mass = 5;
		playerBody.setMassData(mass);
		
		txAtlas = new TextureAtlas(Gdx.files.internal("assets/image/cat.txt"));
		runningAnimation = 
				new Animation<TextureRegion>(0.09f, txAtlas.findRegions("step"), PlayMode.LOOP);

		
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = runningAnimation.getKeyFrame(stateTime);
		playerBody.setLinearVelocity(50f, playerBody.getLinearVelocity().y);
		//playerBody.setGravityScale(1.8f);
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(currentFrame, 160,130, 80, 40);

	}
	
	
	
}
