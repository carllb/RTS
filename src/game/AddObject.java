package game;
import java.util.ArrayList;

import game.collision.BoundingBox;
import game.gameObjects.*;
import game.hud.HUDButton;
import game.hud.HUDButtonClicked;

public class AddObject {

	static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	ArrayList<GameObject> read = new ArrayList<GameObject>();

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
		/*
		int x, y;
		for (int i = 0; i < objects.size(); i++)
		{
			read.add(objects.get(i));
		}
		for (GameObject o: read)
		{
			if (o instanceof Turret)
			{
				if (((Turret) o).onTick())
				{
					x = (int) o.getLocation().getX();
					y = (int) o.getLocation().getY();
					SimpleRTS.world.addGameObject(new Bullet (x, y, new BoundingBox(2, 2, x, y)));
				}
			}
		}*/
	}


	public void tick()
	{
		hudListenSpawn();
		keyListenSpawn();
		periodicSpawn();
	}

}
