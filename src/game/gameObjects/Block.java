package game.gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import game.collision.BoundingBox;

public class Block extends GameObject{

	public Block(BoundingBox bounds) {
		super(bounds.getX(), bounds.getY(), bounds);	
		targetx = bounds.getX();
		targety = bounds.getY();
	}
	
	int targetx,targety;
	public static final int SPEED = 5;
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(x-bounds.getW()/2, y-bounds.getH()/2, bounds.getW(), bounds.getH());
		if(selected){
			g.setColor(Color.blue);
			g.fillOval(x-bounds.getW()/2, y-bounds.getH()/2, bounds.getW(), bounds.getH());
		}
	}
	@Override
	public void tick() {
		float xdis = targetx - x;
		float ydis = targety - y;
		float dis = (float) Math.sqrt(Math.pow(xdis, 2) + Math.pow(ydis, 2));
		if(dis < 10){
			xdis = 0;
			ydis = 0;
		}
		float xT = (xdis/dis);
		float yT = (ydis/dis);
		xInc = (int) (SPEED*xT);
		yInc = (int) (SPEED*yT);
	//	System.out.println("block.java tick " + xdis + " " + ydis);
		super.tick();
	}
	@Override
	public void levelClicked(int mx, int my) {
		targetx = mx;
		targety = my;
		//System.out.println("Block.java  clicked");
	}
}
