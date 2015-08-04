package game;

import java.awt.geom.Point2D;

public class Perspective {

	public int x, y;

	KeyHandler keys;
	KeyMapping map;
	public final static int SPEED = 10;

	public Perspective(KeyHandler input, KeyMapping map) {
		keys = input;
		this.map = map;
	}

	public void tick() {
		//System.out.println("Stuff");
		if (keys.isKeyDown(map.getKey("UP"))) {
			y -= SPEED;
		}
		if (keys.isKeyDown(map.getKey("DOWN"))) {
			y += SPEED;
		}
		if (keys.isKeyDown(map.getKey("LEFT"))) {
			x -= SPEED;
		}
		if (keys.isKeyDown(map.getKey("RIGHT"))) {
			x += SPEED;
		}
	}	

}
