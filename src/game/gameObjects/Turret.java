package game.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Timer;

import game.collision.BoundingBox;

public class Turret extends GameObject implements TickSpawn{
	int [] xs = new int[3];
	int [] ys = new int[3];
	
	public Turret(int x, int y, BoundingBox bounds) {
		super(bounds.getX(), bounds.getY(), bounds);
		xs[0] = x; xs[1] =  x + 5; xs[2] = x + 10;
		ys[0] = y + 10; ys[1] = y; ys[2] = x + 10;
		
	}
	
	@Override
	public void render(Graphics g) {
		int [] xs = {x, x + 5 , x + 10};
		int [] ys = {y + 10, y , y + 10};
		g.setColor(Color.GREEN);
		g.fillPolygon(new Polygon(xs, ys, 3));
	}

	@Override
	public boolean onTick() {
		if (super.tickCount % 10 == 0)
			return true;
		return false;
	}
	
	
	
	

}
