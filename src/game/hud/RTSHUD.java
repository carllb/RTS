package game.hud;

import game.Display;
import game.DrawUtils;
import game.Perspective;
import game.World;
import game.collision.BoundingBox;
import game.gameObjects.GameObject;
import game.gameObjects.Wall;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RTSHUD implements HUD, MouseListener {

	World world;
	Perspective perspective;
	HUDTabList tabs;
	ArrayList<HUDButton> buttons = new ArrayList<HUDButton>();
	ArrayList<GameObject> selectedUnits = new ArrayList<GameObject>();
	private Point2D mouse;
	Display display;
	boolean rDown = false;
	Point2D rStart;
	private MouseClicked mc = null;
	GameObject mouseObject;


	public RTSHUD(Perspective p, World w, Display display) {
		perspective = p;
		world = w;
		this.display = display;
		display.addMouseListener(this);
	}

	@Override
	public void paintHUD(Graphics g3) {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).paint(g3);
		}
		drawSelection(g3);
		if(mouseObject != null){
			mouseObject.setLocation((int) mouse.getX(),(int)  mouse.getY());			
			mouseObject.render(g3);
		}
	}

	void drawSelection(Graphics g) {
		if (rDown && mouse != null) {
			g.setColor(Color.GREEN);
			g.drawRect((int) rStart.getX(), (int) rStart.getY(),
					(int) (-rStart.getX() + mouse.getX()),
					(int) (-rStart.getY() + mouse.getY()));
			g.drawRect((int) mouse.getX(), (int) mouse.getY(),
					(int) (rStart.getX() - mouse.getX()),
					(int) (rStart.getY() - mouse.getY()));
			g.drawRect((int) mouse.getX(), (int) rStart.getY(),
					(int) (rStart.getX() - mouse.getX()),
					(int) (-rStart.getY() + mouse.getY()));
			g.drawRect((int) rStart.getX(), (int) mouse.getY(),
					(int) (-rStart.getX() + mouse.getX()),
					(int) (rStart.getY() - mouse.getY()));
		}
	}

	@Override
	public void tick() {
		mouse = display.getMousePosition();
		for (int i = 0; i < buttons.size(); i++) {
			HUDButton b = buttons.get(i);
			if (mouse != null && mouse.getX() >= b.getX()
					&& mouse.getX() <= b.getX() + b.getWidth()) {
				if (mouse.getY() >= b.getY()
						&& mouse.getY() <= b.getY() + b.getHeight()) {
					b.setOver(true);
				} else {
					b.setOver(false);
				}
			} else {
				b.setOver(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1) {
			if(mc !=null ){
				Point point = new Point((int) mouse.getX(), (int) mouse.getY());
				pointReletiveToLevel(point);
				mc.mouseClicked(point.x, point.y);
				mc = null;
				return;
			}
			//boolean button = false;
			for (int i = 0; i < buttons.size(); i++) {
				HUDButton b = buttons.get(i);
				if (mouse.getX() >= b.getX()
						&& mouse.getX() <= b.getX() + b.getWidth()) {
					if (mouse.getY() >= b.getY()
							&& mouse.getY() <= b.getY() + b.getHeight()) {
						b.clicked();
						return;
					}
				}
			}
			world.unSelectAllObjects();
			selectedUnits.clear();
			Point p = new Point((int) mouse.getX(), (int) mouse.getY());
			pointReletiveToLevel(p);
			BoundingBox box = new BoundingBox(1, 1,p.x , p.y);
			List<BoundingBox> cols = world.getCollisionWorld().allColisions(box);
			if(cols.size() > 0){
				cols.get(0).getGameObject().selected = true;
				selectedUnits.add(cols.get(0).getGameObject());
				world.singleSelected = true;
			}
			world.getCollisionWorld().remove(box);			
		}
	}

	public void addButton(HUDButton button) {
		buttons.add(button);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("down");
		if (e.getButton() == 1 && !isMouseBusy()) {
			rDown = true;
			if(mouse == null)
				return;
			rStart = new Point((int) mouse.getX(), (int) mouse.getY());
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println("up");
		if (e.getButton() == 1) {
			if (rDown) {
				selectUnits();
			}
			rDown = false;
		}
		
		if(e.getButton() == 3){
			levelClicked();
		}
	}

	void selectUnits() {
		if(mouse == null){
			return;
		}
		
		int width = (int) (mouse.getX() - rStart.getX());
		int height = (int) (mouse.getY() - rStart.getY());
		
		if(width==0 && height==0){
			return;
		}
			
		
		world.unSelectAllObjects();
		selectedUnits.clear();
	
		int w = 0, h = 0;
		
	//	sx = (int) mouse.getX() + (width<0?width:0);
	//	sy = (int) mouse.getY() + (height<0?height:0);
		w = Math.abs(width);
		h = Math.abs(height);
		
		int x = (int) ((mouse.getX() - width/2) - display.getWidth() / 2) + perspective.x;
		int y = (int) ((mouse.getY() - height/2) - display.getHeight() / 2) + perspective.y;
		
		BoundingBox selectionBox = null;
		selectionBox = new BoundingBox(w, h, x, y);
	//	GameObject wall = new Wall(selectionBox);
	//	world.addGameObject(wall);
		List<BoundingBox> boxes = world.getCollisionWorld().allColisions(
				selectionBox);
		for (int i = 0; i < boxes.size(); i++) {
				selectedUnits.add(boxes.get(i).getGameObject());
				boxes.get(i).getGameObject().selected = true;
		}
		world.singleSelected = (boxes.size() == 1);
		world.getCollisionWorld().remove(selectionBox);
	}
	
	void levelClicked(){
		if(mouse == null)
			return;
		for(int i=0;i<selectedUnits.size();i++){
			Point2D point = new Point((int) mouse.getX(), (int) mouse.getY());
			pointReletiveToLevel(point);
			selectedUnits.get(i).levelClicked((int) point.getX(),(int) point.getY());
			
		}
	}
	
	public void pointReletiveToLevel(Point2D in){
		in.setLocation(in.getX() - display.getWidth()/2 + perspective.x, in.getY() - display.getHeight()/2 + perspective.y);
	}

	public boolean isMouseBusy() {
		return (mc !=null);
	}

	public void setMouseBusy(MouseClicked mc) {
		this.mc = mc;
	}
	
	public void placeObjectOnMouse(GameObject go){
		mouseObject = go;
		if(mouseObject != null)
			mouseObject.removeFromCollisionWorld();
	}
	
	
}
