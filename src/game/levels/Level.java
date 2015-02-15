package game.levels;

import game.gameObjects.GameObject;

import java.awt.Point;
import java.util.List;

//this is an interface called level
public interface Level {
	//this is  a list
	public List<GameObject> getObjects();	
	
	public int getHeight();
	public int getWidth();
}
