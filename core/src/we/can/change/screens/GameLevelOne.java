package we.can.change.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

import utils.TiledObjectUtils;
import we.can.change.YetAnotherGame;
import we.can.change.screens.interfaces.GameMap;

public class GameLevelOne extends GameMap implements Screen {
	
	private final YetAnotherGame game;
	private final OrthographicCamera camera;
	private OrthogonalTiledMapRenderer mapRenderer;
	private TiledMap map; 
	
	public GameLevelOne(YetAnotherGame game, OrthographicCamera camera){
		super();
		this.game = game;
		this.camera = camera;
		map = new TmxMapLoader().load("maps/1024x.tmx");
		
		setMap(map);
		
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		mapRenderer.setView(camera);
		TiledObjectUtils.bindObjectsToTheWorld(game.getWorld(), map.getLayers().get("collision_border").getObjects());
		TiledObjectUtils.pointPlayerHisPlace(game.createPlayer(), (RectangleMapObject) map.getLayers().get("player_spawn").getObjects().get(0)); 
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	public OrthogonalTiledMapRenderer getMapRenderer() {
		return mapRenderer;
	}

	public void setMapRenderer(OrthogonalTiledMapRenderer mapRenderer) {
		this.mapRenderer = mapRenderer;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mapRenderer.render();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		mapRenderer.dispose();
		// TODO Auto-generated method stub

	}

}
