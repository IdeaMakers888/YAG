package we.can.change.screens.interfaces;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class GameMap {
	
	private TiledMap map;
	private MapProperties prop;
	private int mapWidth, mapHeight;
	
	public GameMap(TiledMap map){
		this.map = map;
		calculateSize();
	}

	
	public GameMap(){
	}
	
	public void setMap(TiledMap map){
		this.map = map;
		calculateSize();
	}
	
	private void calculateSize(){
		prop = map.getProperties();
	        int mapBlocksCountHorizontal = prop.get("width", Integer.class);
	        int mapBlocksCountVertical = prop.get("height", Integer.class);
	        int tilePixelWidth = prop.get("tilewidth", Integer.class);
	        int tilePixelHeight = prop.get("tileheight", Integer.class);
	
	        mapWidth = mapBlocksCountHorizontal * tilePixelWidth;
	        mapHeight = mapBlocksCountVertical * tilePixelHeight;
	}

	public TiledMap getMap() {
		return map;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}
	
	
}
