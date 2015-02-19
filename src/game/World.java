package game;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import game.collision.CollisionWorld;
import game.gameObjects.GameObject;
import game.levels.Level;


public class World implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5657915675211099554L;
	ArrayList<GameObject> objects = AddObject.objects;
	CollisionWorld cWorld;
	int width,height;

	public World(int width, int height) {
		cWorld = new CollisionWorld();
		this.width = width;
		this.height = height;
	}

	public void render(Graphics g) {

		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
	public void tick(){
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).tick();
		}
	}

	public void addGameObject(GameObject o) {
		o.setWorld(this);
		objects.add(o);
	}

	public void removeGameObject(GameObject object) {
		objects.remove(object);
		object.removedFromWorld();
	}

	public CollisionWorld getCollisionWorld() {
		return cWorld;
	}
	
	public void removeAllObjects(){
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).removedFromWorld();
			objects.remove(i);
		}
	}
	
	public void loadLevel(Level l){
		removeAllObjects();
		setWidth(l.getWidth());
		setHeight(l.getHeight());
		for(int i = 0; i < l.getObjects().size(); i ++){
			addGameObject(l.getObjects().get(i));
		}
		
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public ArrayList<GameObject> getObjects(){
		return objects;
	}
	
}
