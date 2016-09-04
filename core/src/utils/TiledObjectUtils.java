package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import static utils.Constants.PPM;

public class TiledObjectUtils {

	public static void bindObjectsToTheWorld(World world, MapObjects objects){
		for(MapObject object : objects){
			Shape shape;
			
			if(object instanceof PolylineMapObject){
				shape = createPolyline((PolylineMapObject) object);
			} else {
				continue;
			}
			
			Body body;
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.StaticBody;
			body = world.createBody(bodyDef);
			body.createFixture(shape, 1.0f);
			shape.dispose();
		}
	}
	
	public static void pointPlayerHisPlace(Body player, RectangleMapObject object){
//		Rectangle rect = object.getRectangle();
//		player.getPosition().x = (rect.getX() + rect.getWidth()/2)/PPM;
//		player.getPosition().y = (rect.getY() + rect.getHeight()/2 - Gdx.graphics.getHeight())/PPM;
	}

	private static ChainShape createPolyline(PolylineMapObject polyline){
		float[] vertices = polyline.getPolyline().getTransformedVertices();
		Vector2[] worldVector = new Vector2[vertices.length / 2];
		
		for(int i = 0; i < worldVector.length; i++){
			worldVector[i] = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
		}
		
		ChainShape cs = new ChainShape();
		cs.createChain(worldVector);
		return cs;
	}
	
}
