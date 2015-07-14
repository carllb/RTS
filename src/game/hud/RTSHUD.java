package game.hud;

import game.Display;
import game.DrawUtils;
import game.Perspective;
import game.World;
import game.collision.BoundingBox;
import game.collision.SelectableBoundingBox;
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
	ArrayList<HUDButton> buttons = new ArrayList<HUDButton>();
	private Point2D mouse;
	Display display;
	boolean rDown = false;
	Point2D rStart;

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

	void drawSelectionAroundBoxes(Graphics g) {
		g.setColor(Color.GREEN);
		for (int i = 0; i < world.getCollisionWorld().getBoundingBoxes().size(); i++) {
			BoundingBox b = world.getCollisionWorld().getBoundingBoxes().get(i);
			if ((b instanceof SelectableBoundingBox)) {
				if (((SelectableBoundingBox) b).getSelected()) {
					DrawUtils.DrawRectangleAboutPoint(b.getX(), b.getY(), b.getW() + 2, b.getH() + 2, g);
				}
			}
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
			for (int i = 0; i < buttons.size(); i++) {
				HUDButton b = buttons.get(i);
				if (mouse.getX() >= b.getX()
						&& mouse.getX() <= b.getX() + b.getWidth()) {
					if (mouse.getY() >= b.getY()
							&& mouse.getY() <= b.getY() + b.getHeight()) {
						b.clicked();
					}
				}
			}
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
		if (e.getButton() == 1) {
			rDown = true;
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
	}

	void selectUnits() {
		System.out.println("select Units");
	/*	BoundingBox selectionBox = new BoundingBox(
				(int) (mouse.getX() - rStart.getX()) + perspective.x,
				(int) (mouse.getY() - rStart.getY()) + perspective.y,
				(int) (rStart.getX() + (mouse.getX() - rStart.getX()) / 2),
				(int) (rStart.getX() + (mouse.getX() - rStart.getX()) / 2));
	*/	
		int width = (int) (mouse.getX() - rStart.getX());
		int height = (int) (mouse.getY() - rStart.getY());
		int x = (int) ((mouse.getX() - width/2) - display.getWidth() / 2) + perspective.x;
		int y = (int) ((mouse.getY() - height/2) - display.getHeight() / 2) + perspective.y;
		
		BoundingBox selectionBox = new BoundingBox(width, height, x, y);
		GameObject wall = new Wall(selectionBox);
		world.addGameObject(wall);
		List<BoundingBox> boxes = world.getCollisionWorld().allColisions(
				selectionBox);
		for (int i = 0; i < boxes.size(); i++) {
			if (boxes.get(i) instanceof SelectableBoundingBox) {
				((SelectableBoundingBox) boxes.get(i)).select();
				System.out.println("stuff");
			}
		}
	}
}
