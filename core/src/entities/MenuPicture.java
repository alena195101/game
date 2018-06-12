package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MenuPicture extends Actor{
	TextureAtlas atlas;
	public Animation<TextureRegion> runningAnimation;
	float stateTime = 0;
	TextureRegion currentFrame;
	
	public MenuPicture() {
		atlas = new TextureAtlas(Gdx.files.internal("assets/image/cat.txt"));
		runningAnimation =  new Animation<TextureRegion>(0.09f, atlas.findRegions("step"), PlayMode.LOOP);
	}
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = runningAnimation.getKeyFrame(stateTime);	
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(currentFrame, 120,50, 80,40);

	}

}
