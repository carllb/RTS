package game.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import game.collision.BoundingBox;

public class Wall extends GameObject{

	public Wall(BoundingBox bounds) {
		super(bounds.getX(), bounds.getY(), bounds);		
	}
	
	Color color = Color.green;

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.drawRect(x - bounds.getW()/2, y - bounds.getH()/2, bounds.getW(), bounds.getH());
	}
	
	public void setColor(Color c){
		this.color = c;
	}
	
}
