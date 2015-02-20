package game.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import game.collision.BoundingBox;
import physics.Vector;

public class Bullet extends GameObject{
	
	public Bullet(int x, int y) {
		super(x, y, null);
		setVel(new Vector(20, (float) (2 * Math.PI / 3)));
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, 2, 2);
	}

}
