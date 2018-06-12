package states;


import java.util.IdentityHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;

import entities.Cristal;
import entities.Player;
import entities.Roof;
import entities.Wall;

import handler.MyContactLisenter;
import handler.MyInputProcessor;
import handler.Variables;



public class Play implements Screen{
	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	private Array<Cristal> cristals;
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	private float tileSize;
	private Stage stage;
	private String level;
	TextureAtlas txAtlas;
	public static IdentityHashMap<Body, Cristal> Crystals = new IdentityHashMap<Body, Cristal>();
	private 	BitmapFont font;
	
	private Music music;
	public Sound cristal;
	public static Sound bombs;
	private float width;
	
	//life
	private int life=3;
	private Sprite sprite;
	private SpriteBatch batch;
	

	public Play(String str, int life) {
		this.level = str;
		this.life = life;
	}

	@Override
	public void show() {
		Variables.score =0;
		world = new World(new Vector2(0,-50),true);
		//music
		bombs = Gdx.audio.newSound(Gdx.files.internal("assets/music/hit.wav"));
		cristal = Gdx.audio.newSound(Gdx.files.internal("assets/music/crystal.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("assets/music/Street Party.mp3"));
		music.setVolume(0.3f); 
		music.setLooping(true);
		music.play();
		//life
		batch = new SpriteBatch();
		txAtlas = new TextureAtlas(Gdx.files.internal("assets/image/cat.txt"));
		sprite =txAtlas.createSprite("life");
		sprite.setBounds(150,539, 25, 25);
		
		Variables.cl = new MyContactLisenter();;
		world.setContactListener(Variables.cl);
		b2dr = new Box2DDebugRenderer();
		//stage
		stage = new Stage(new FillViewport(Variables.V_WIDTH, Variables.V_HEIGHT));
		stage.addActor(new Player(world));
		createTitles();			
		createCristals();
		createBobms();
		stage.addActor(new Wall(world,width));
		stage.addActor(new Roof(world,width));
		//System.out.println(tileSize);
		//camera
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Variables.V_WIDTH, Variables.V_HEIGHT);
		//input listener
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("assets/font/PFAgoraSlabPro Bold.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
		p.color = Color.WHITE;
		p.size = 80;
		p.borderColor = Color.BROWN;
		p.borderWidth = 5;
		p.shadowOffsetX = 8;
		p.shadowOffsetY = 8;
	
		font = gen.generateFont(p);
		font.getData().setScale(0.15f);
		
	} 
	
	public void update(float dt) {	
		world.step(dt, 6, 2);

	//	bodies.clear();
	}

	@Override
	public void render(float delta) {
		
		
		Gdx.gl.glClearColor( 1, 1, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		world.step(1/60f, 4, 4);
		stage.act(delta);
		stage.draw();
		
		//floor
		tmr.setView(b2dCam);
		tmr.render();
		//box2d debug render
		//b2dr.render(world, b2dCam.combined);
		
		b2dCam.position.x = Player.playerBody.getPosition().x;
		b2dCam.position.y = Player.playerBody.getPosition().y;
		b2dCam.update();
		
		//life
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		
		if(MyContactLisenter.removeBodies!=null) {
			for (Body b:MyContactLisenter.removeBodies) {
				cristal.play(1.0f);
				world.destroyBody(b);
				Crystals.get(b).remove();
				Crystals.remove(b);
				
			}
			MyContactLisenter.removeBodies.clear();
		}
		
		stage.getBatch().begin();
		font.draw(stage.getBatch(), Variables.score+"/"+cristals.size, 20 , Variables.V_HEIGHT-20);
		font.draw(stage.getBatch(), ": "+life, 90 ,  Variables.V_HEIGHT-20);
		stage.getBatch().end();
	}


	
	TiledMapTileLayer layer;
	private void createCristals() {
		cristals = new Array<Cristal>();
		
		// get all crystals in "crystals" layer,
		// create bodies for each, and add them
		// to the crystals list
		MapLayer ml = tileMap.getLayers().get("crystal");
		if(ml == null) return;
		Group g = new Group();
		for(MapObject mo : ml.getObjects()) {
			float x = (Float) mo.getProperties().get("x");
			float y = (Float) mo.getProperties().get("y");
			Cristal c = new Cristal(world,x,y);
			c.setBounds(x, y, 16, 16);
			g.addActor(c);
			cristals.add(c);
			
		}
		stage.addActor(g);
		
	}
	
	private void createTitles() {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		//load tilmap level
		tileMap = new TmxMapLoader().load("assets/maps/"+level+".tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get("block");
				
		tileSize = layer.getTileWidth();
		
		width = layer.getWidth()*layer.getTileWidth();
	//go through all cells level
		for(int row = 0; row < layer.getHeight(); row++) {
			for(int col = 0; col<layer.getWidth(); col++) {
				Cell cell = layer.getCell(col, row);
				if (cell == null) continue;
				if (cell.getTile() == null) continue;
				//create body
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f)*tileSize, (row + 0.5f )*tileSize);
				PolygonShape cShape = new PolygonShape();
				cShape.setAsBox(tileSize/2, tileSize/2);
				fdef.friction = 0;
				fdef.shape = cShape;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef).setUserData("ground");
				}
		}
		
	}
	private void createBobms() {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		//load tilmap level
		tileMap = new TmxMapLoader().load("assets/maps/"+level+".tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get("bomb");
				
		tileSize = layer.getTileWidth();
		
		width = layer.getWidth()*layer.getTileWidth();
	//go through all cells level
		for(int row = 0; row < layer.getHeight(); row++) {
			for(int col = 0; col<layer.getWidth(); col++) {
				Cell cell = layer.getCell(col, row);
				if (cell == null) continue;
				if (cell.getTile() == null) continue;
				//create body
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f)*tileSize, (row + 0.5f )*tileSize);
				PolygonShape cShape = new PolygonShape();
				cShape.setAsBox(tileSize/2, tileSize/2);
				fdef.friction = 0;
				fdef.shape = cShape;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef).setUserData("bomb");
				}
		}
		
	}



	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		music.pause(); 
		this.dispose();
		cristal.dispose();
		bombs.dispose();
		music.dispose();
	}

	@Override
	public void dispose() {
		cristal.dispose();
		music.dispose();
		
	}

	
}
