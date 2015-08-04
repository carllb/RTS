package game.gameObjects;

import game.World;
import game.collision.BoundingBox;
import game.collision.CollisionWorld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import physics.Vector;

public class GameObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9134781521929979767L;
	protected int x, y;
	protected int xInc = 0, yInc = 0;
	BoundingBox bounds;
	transient World world;
	public boolean selected = false;

	Vector vel;
	public int tickCount;
	public GameObject(int x, int y, BoundingBox bounds) {
		this.x = x;
		this.y = y;
		this.bounds = bounds;
		if(bounds != null){
			bounds.setGameObject(this);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.drawChars("NO render".toCharArray(), 0, "NO render".length(), x, y);
	}


	public void incrimentStep(int x, int y) {
		xInc += x;
		yInc += y;
	}

	public void setVel(Vector v)
	{
		vel = v;
	}

	public void tick() {
		tickCount++;
		if(bounds == null){
			x += xInc;
			y += yInc;
			xInc = 0;
			yInc = 0;
			return;
		}
		if(xInc == 0 && yInc == 0){
			return;
		}
		bounds.move(x + xInc, y + yInc);
		BoundingBox box = world.getCollisionWorld().isColliding(bounds);
		if (box == null) {
			if (!(vel == null))
			{
				xInc = (int) vel.getX();
				yInc = (int) vel.getY();
			}
			x += xInc;
			y += yInc;
		} else if (xInc != 0 || yInc != 0) {

			xInc = 0;
			yInc = 0;
			bounds.move(x, y);
		} else {
			// do nothing
		}
		//		x += xInc;
		//		y += yInc;
		xInc = 0;
		yInc = 0;
	}

	public void setWorld(World world) {
		this.world = world;
		if (bounds != null && world.getCollisionWorld() != null)
		//	System.out.println("Gameobject.java: added bounds");
			world.getCollisionWorld().addBoundingBox(bounds);
	}

	public void removedFromWorld() {
		if (bounds != null)
			world.getCollisionWorld().remove(bounds);
	}

	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Point getLocation(){
		return new Point(x,y);
	}

	public BoundingBox getBounds(){
		return bounds;
	}
	
	public void levelClicked(int mx, int my){
		
	}
	
	public void removeFromCollisionWorld(){
		if(world != null)
			world.getCollisionWorld().remove(bounds);
	}

}
