package game;

import game.collision.BoundingBox;
import game.gameObjects.Block;
import game.gameObjects.GameObject;
import game.hud.HUDButton;
import game.hud.HUDButtonClicked;
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
	
	Perspective perspective;
	World world;
	KeyHandler input;
	KeyMapping map;
	File keyMapFile = new File("keymap");
	Display display;
	RTSHUD hud;
	
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
		world = new World(5000, 5000);
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
				GameObject o = new Block( new BoundingBox(20,20,perspective.x, perspective.y));
				world.addGameObject(o);
			}
		});
		hud.addButton(addBlock);
	}
	
	
	@Override
	public void tick() {	
		world.tick();
		perspective.tick();
		hud.tick();
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
