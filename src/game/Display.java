package game;


import game.hud.HUD;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Display extends JFrame{
	
	BufferedImage image;
	BufferedImage rImage;
	World world;	
	Graphics g2;
	Graphics g3;
	Perspective perspective;
	HUD hud;
	
	
	private double oldX = -1;
	private double oldY = -1;
	private double tween = 1;
	
	public Display(World world, Perspective p){
		super("Game");
		setSize(640, 480);
		setResizable(false);		
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-getWidth()/2,Toolkit.getDefaultToolkit().getScreenSize().height/2-getHeight()/2);
		this.world = world;
		perspective = p;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setHUD(HUD hud){
		this.hud = hud;
	}
	
	
	@Override
	public void paint(Graphics g) {
		//super.paint(g2);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		image = new BufferedImage(world.getWidth(),world.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
		rImage = new BufferedImage(getWidth(), getHeight(),BufferedImage.TYPE_3BYTE_BGR);
		g2 = image.getGraphics();
		g2.drawRect(1, 1, image.getWidth()-2, image.getHeight()-2);
		g3 = rImage.getGraphics();
		((Graphics2D) g2).setRenderingHints(rh);
		world.render(g2);
		//g.clearRect(0, 0, getWidth(), getHeight());
		g3.setColor(Color.green);
		
		oldX = oldX + (-perspective.x + getWidth()/2 - oldX) * tween;
		oldY = oldY + (-perspective.y + getHeight()/2 - oldY) * tween;
		
		g3.drawImage(image, (int) oldX, (int) oldY, null);
		
		if(hud != null){
		//	System.out.println("paint hud");
			hud.paintHUD(g3);
			
		}
		g.drawImage(rImage, 0, 0, null);
		
	}
	long startT = 0;
	long endT = 0;
	int calculateFPS(){
		endT = System.currentTimeMillis();
		long diff = endT - startT;
		if(diff == 0)
			return -1;
		int fps = (int) (1000f/diff);
		startT = System.currentTimeMillis();
		return fps;
		
	}
	
}
