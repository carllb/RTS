package game.gameObjects;

import java.awt.Color;
import java.awt.Graphics;



public class Pointer extends GameObject{

	int xp,yp;
	GameObject player;
	
	public Pointer(int x, int y, GameObject player) {
		super(x, y, null);
		this.player = player;
	}
	
	@Override
	public void tick() {
		double dist = Math.sqrt(Math.pow(player.x - x, 2) + Math.pow(player.y - y,2));
		xp = (int) (((float)(player.x - x)/dist)*20);
		yp = (int) (((float)(player.y - y)/dist)*20);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.PINK);
		g.drawOval(x-20, y-20, 40, 40);
		g.setColor(Color.blue);
		//System.out.println(xp + " " + yp);
		g.drawLine(x, y, xp + x, yp + y);
		
	}
}
