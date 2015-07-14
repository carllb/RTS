package game.collision;

import game.ClickInstruction;

public class SelectableBoundingBox extends BoundingBox{

	private ClickInstruction clickInstruction;
	private boolean selected = false;
	
	public SelectableBoundingBox(int w, int h, int x, int y, ClickInstruction ci) {
		super(w, h, x, y);	
		clickInstruction = ci;
	}
	
	public void select(){
		selected = true;
	}
	
	public void unSelect(){
		selected = false;
	}
	
	public boolean getSelected(){
		return selected;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100958872392802117L;

}
