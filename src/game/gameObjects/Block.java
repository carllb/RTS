package game.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import game.collision.BoundingBox;

public class Block extends GameObject{

	public Block(BoundingBox bounds) {
		super(bounds.getX(), bounds.getY(), bounds);		
	}
	
	@Override
	public void render(Graphics g) {	
		g.setColor(Color.WHITE);
		g.drawRect(x-bounds.getW()/2, y-bounds.getH()/2, bounds.getW(), bounds.getH());
	}

}
