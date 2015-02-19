package game;
import java.util.ArrayList;

import game.collision.BoundingBox;
import game.gameObjects.*;
import game.hud.HUDButton;
import game.hud.HUDButtonClicked;

public class AddObject {
	
	static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public void hudListenSpawn()
	{
		HUDButton addBlock = new HUDButton("Add Block", 100,50, 200, 200, new HUDButtonClicked() {
			@Override
			public void buttonClicked() {
				GameObject o = new Block( new BoundingBox(20,20, SimpleRTS.perspective.x, SimpleRTS.perspective.y));
				SimpleRTS.world.addGameObject(o);
			}
		});
		
		HUDButton turret = new HUDButton("Add Turret", 100,50, 310, 200, new HUDButtonClicked() {
			@Override
			public void buttonClicked() {
				BoundingBox b = new BoundingBox(20,20, SimpleRTS.perspective.x, SimpleRTS.perspective.y);
				GameObject p = new Turret(b.getX(), b.getY(), b);
				SimpleRTS.world.addGameObject(p);
			}
		});
		SimpleRTS.hud.addButton(addBlock);
		SimpleRTS.hud.addButton(turret);
	}
	
	
	
	public void keyListenSpawn(){}
	
	public void periodicSpawn()
	{
		
	}
	
	
	public void tick()
	{
		hudListenSpawn();
		keyListenSpawn();
		periodicSpawn();
	}
	
}
