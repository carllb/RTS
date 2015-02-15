package game.levels;

import game.Display;
import game.collision.BoundingBox;
import game.gameObjects.GameObject;
import game.gameObjects.Wall;

import java.util.ArrayList;
import java.util.List;

public class TestLevel implements Level {

	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	
	public TestLevel(Display display){
		BoundingBox floor = new BoundingBox(display.getWidth(), 40, display.getWidth()/2, display.getHeight()-40);
		BoundingBox roof = new BoundingBox(display.getWidth(), 40, display.getWidth()/2, 40);
		BoundingBox wallLeft = new BoundingBox(40, display.getHeight(), 0, display.getHeight()/2);
		BoundingBox wallRight = new BoundingBox(40, display.getHeight(), display.getWidth(), display.getHeight()/2);
		objects.add(new Wall(floor));
		objects.add(new Wall(roof));
		objects.add(new Wall(wallLeft));
		objects.add(new Wall(wallRight));
	}
	
	
	@Override
	public List<GameObject> getObjects() {		
		return objects;
	}
	
	@Override
	public int getHeight() {	
		return 480;
	}
	
	public int getWidth(){
		return 640;
	}

}
