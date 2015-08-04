package game.hud;

import java.awt.Color;
import java.awt.Graphics;

public class HUDButton {

	private boolean over;
	private String text;
	private int width, height, x, y;
	private HUDButtonClicked hbc;

	public HUDButton(String text, int width, int height, int x, int y, HUDButtonClicked clickListener) {
		this.text = text;
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		hbc = clickListener;
	}

	public HUDButton() {
		this.text = "";
		this.height = 0;
		this.width = 0;
		x = 0;
		y = 0;
	}

	public void paint(Graphics g) {
		if (over) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		int w = g.getFontMetrics().stringWidth(text);		
		int sx = (width - w)/2; //centers the string
		g.drawString(text, sx + x, height/2 + y);

	}

	void clicked(){
		hbc.buttonClicked();
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int w, int h){
		width = w;
		height = h;
	}

	public void setOver(boolean over) {
		this.over = over;
	}
	
	public boolean getOver(){
		return over;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}

}
