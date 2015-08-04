package game.collision;

import game.gameObjects.GameObject;

import java.io.Serializable;

public class BoundingBox implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8859735710667237970L;
	int w,h;
	int x,y;
	private GameObject go;
	
	public BoundingBox(int w, int h, int x, int y){
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;
	}
	
	public void move(int nx, int ny){
		this.x = nx;
		this.y = ny;
	}
	
	public boolean checkCollison(BoundingBox other){		
		int xdiff = Math.abs(other.x - x);
		int ydiff = Math.abs(other.y - y);
		int maxX = w/2 + other.w/2;
		int maxY = h/2 + other.h/2;
		if(maxX > xdiff && maxY > ydiff){
			return true;
			
		}
		return false;
	}
	
	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void changeSize(int w, int h){
		this.w = w;
		this.h = h;
	}
	
	public void setGameObject(GameObject gameobject){
		go = gameobject;
	}
	
	public GameObject getGameObject(){
		return go;
	}
}
