package game;

import game.collision.BoundingBox;
import game.gameObjects.Block;
import game.gameObjects.GameObject;
import game.gameObjects.Turret;
import game.hud.HUDButton;
import game.hud.HUDButtonClicked;
import game.hud.MouseClicked;
import game.hud.RTSHUD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class SimpleRTS implements Game{

	
	public static GameManager gm;
	
	public static void main(String[] args) {
		gm = new GameManager(new SimpleRTS(), 60);
		gm.startGame();
	}
	
	static Perspective perspective;
	static World world;
	KeyHandler input;
	KeyMapping map;
	AddObject obj = new AddObject();
	File keyMapFile = new File("keymap");
	Display display;
	static RTSHUD hud;
	
	public SimpleRTS(){
		input = new KeyHandler();		
		
	}

	@Override
	public void init() {
		if(keyMapFile.exists()){
			try {
				loadKeyMap();
			} catch (Exception e) {				
				e.printStackTrace();
				createKeyMap();
			}
		}else{
			createKeyMap();
		}
		perspective = new Perspective(input,map);
		world = new World(500, 500);
		display = new Display(world, perspective);
		display.setVisible(true);
		display.addKeyListener(input);
		hud = new RTSHUD(perspective, world, display);
		display.setHUD(hud);
		loadandtest();
	}
	
	void loadandtest(){
		HUDButton addBlock = new HUDButton("Add Block", 100,50, 200, 200, new HUDButtonClicked() {
			
			@Override
			public void buttonClicked() {
				MouseClicked mc = new MouseClicked() {
					
					@Override
					public void mouseClicked(int x, int y) {
						GameObject o = new Block( new BoundingBox(20,20,x, y));
						if(world.getCollisionWorld().allColisions(o.getBounds()).size() == 0){
							world.addGameObject(o);							
						}
						hud.placeObjectOnMouse(null);
					}
				};
				
				hud.setMouseBusy(mc);
				hud.placeObjectOnMouse(new Block( new BoundingBox(20, 20, 0, 0)));
			}
		});
		hud.addButton(addBlock);
		
	}
	
	
	@Override
	public void tick() {	
		world.tick();
		perspective.tick();
		hud.tick();
		obj.tick();
		display.repaint();
		display.setTitle("FPS: " + gm.FPS);
	}
	

	@Override
	public void end() {
		
	}
	
	
	void loadKeyMap() throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(keyMapFile));
		map = (KeyMapping) ois.readObject();
		ois.close();
	}
	void createKeyMap(){
		map = new KeyMapping(input);
	}
}
