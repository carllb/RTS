package game.hud;

import game.Display;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class HUDTabList {
	ArrayList<HUDTab> tabs = new ArrayList<HUDTab>();
	ArrayList<HUDButton> tabButtons = new ArrayList<HUDButton>();
	int currentTab = 0;
	Display display;
	FontMetrics fm = null;

	public HUDTabList(Display display) {
		this.display = display;
	}

	public void addTab(HUDTab ht) {
		tabs.add(ht);
	}

	public void removeTab(HUDTab ht) {
		tabs.remove(ht);
	}

	void update() {
		tabButtons.clear();
		int lenSum = 0;
		for(int i=0;i<tabs.size();i++){
			//make the length of the button based on the length of the name
			//TabButton b = new TabButton(tabs.get(i).name,);
			if(fm == null)
				return;
			int length = fm.stringWidth(tabs.get(i).name);
			TabButton tb = new TabButton(tabs.get(i).name, length + 10, 30, lenSum, display.getHeight()*4/5,i);
			lenSum += length;
			tabButtons.add(tb);
		}
	}

	public void render(Graphics g) {
		for(HUDButton b : tabButtons){
			b.paint(g);
		}
		if(tabs.size() > 0){
			tabs.get(currentTab).render();
		}
	}

	class TabButton extends HUDButton implements HUDButtonClicked{	
		int index;
		public TabButton(String tabName, int width , int height, int x, int y, int index) {
			super(tabName, width, height, x, y, null);
			setClickListener(this);
		}
		
		@Override
		public void paint(Graphics g) {
			fm = g.getFontMetrics();
			if (getOver()) {
				g.setColor(Color.GREEN);
			} else if(tabButtons.get(currentTab) == this) {
				g.setColor(Color.BLUE);
			} else {
				g.setColor(Color.WHITE);
			}
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.BLACK);
			int w = fm.stringWidth(getText());
			int sx = (getWidth() - w) / 2; // centers the string
			g.drawString(getText(), sx + getX(), getHeight() / 2 + getY());
		}

		@Override
		public void buttonClicked() {
			currentTab = index;			
		}
	}
}
