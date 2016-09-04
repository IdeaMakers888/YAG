package we.can.change;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static utils.Constants.PPM;
import we.can.change.screens.GameLevelOne;
import we.can.change.screens.MainScreen;

public class YetAnotherGame extends Game implements ContactListener{
    private Viewport viewport;
    private OrthographicCamera camera;

    private Box2DDebugRenderer box2DDebugRenderer;
    private World world;
    private Body player;
    
    private static final int ONE_WORLD_METER = 32; // 32 pixels = 1 meter
    
    private boolean jump;

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    public void update(float deltaTime) {
        world.step(1 / 60f, 6, 2);
        
        cameraUpdate(deltaTime);
        inputUpdate(deltaTime);
    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        world = new World(new Vector2(0, -98f), false);
        
        world.setContactListener(this);
        
        box2DDebugRenderer = new Box2DDebugRenderer();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

        super.setScreen(new GameLevelOne(this, camera));
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        super.render();
        getScreen().render(Gdx.graphics.getDeltaTime());
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
    }

    public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Body getPlayer() {
		return player;
	}
	
	@Override
    public void dispose() {
        super.dispose();
        box2DDebugRenderer.dispose();
        world.dispose();
    }

    public Body createPlayer() {
        Body pBody;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(400/PPM, 600/PPM);
        def.fixedRotation = false;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(64/2/PPM, 64/2/PPM);

        pBody.createFixture(shape, 1f);
        shape.dispose();
        player = pBody;
        return pBody;
    }
    
    public void cameraUpdate(float deltaTime){
    	Vector3 position = camera.position;
    	float playersX = player.getPosition().x * PPM;
    	if(player != null) {
    		float lerp = 0.75f;
    		
    		if(playersX - Gdx.graphics.getWidth()/2 <= 0 && playersX <= Gdx.graphics.getWidth()/2){
    			lerp = 0;
    		} else if(playersX > ((GameLevelOne) getScreen()).getMapWidth() - Gdx.graphics.getWidth()/2 
    				&& playersX >= ((GameLevelOne) getScreen()).getMapWidth() - Gdx.graphics.getWidth()/2 - 1)
    			lerp = 0;
    		
    		position.x += (playersX - position.x) * lerp * deltaTime;
    		if(Math.round(position.x) - position.x < 0.5)
    			position.x += Math.round(position.x) - position.x - 0.5;
    	}
    	
    	camera.position.set(position);
    	
    	camera.update();
    	
    	if(getScreen() instanceof GameLevelOne)
        	((GameLevelOne) getScreen()).getMapRenderer().setView(camera);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
    
    public void inputUpdate(float deltaTime){
    	
    	
    	if(player == null)
    		return;
    	
    	Vector2 pos = player.getPosition();
    	pos.x = pos.x * PPM;

    	if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
    		player.applyForceToCenter(0, 1800, false);
    	} 
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) pos.x -= 250 * Gdx.graphics.getDeltaTime();
	    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) pos.x += 250 * Gdx.graphics.getDeltaTime();

	    if(pos.x < 64) pos.x = 64;
	    if(pos.x > ((GameLevelOne) getScreen()).getMapWidth() - 64) pos.x = ((GameLevelOne) getScreen()).getMapWidth() - 64;

	    player.setTransform(pos.x / PPM, pos.y, 0);
		
//		if(jump) {			
//			jump = false;
//			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//				player.setLinearVelocity(vel.x, 0);			
//				System.out.println("jump before: " + player.getLinearVelocity());
//				player.setTransform(pos.x, pos.y + 0.01f, 0);
//				player.applyLinearImpulse(0, 30, pos.x, pos.y,false);			
//				System.out.println("jump, " + player.getLinearVelocity());				
//			}
//		}					
    }

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
}
