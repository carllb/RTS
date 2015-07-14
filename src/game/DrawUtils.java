package game;

import java.awt.Graphics;

public class DrawUtils {

	public static void DrawRectangleAboutPoint(int x, int y, int w, int h, Graphics g){
		g.drawRect(x-(w/2), y-(h/2), w, h);
	}
	
}
