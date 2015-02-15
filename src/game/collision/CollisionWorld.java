package game.collision;

import java.io.Serializable;
import java.util.ArrayList;



public class CollisionWorld implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7530593257284815527L;
	ArrayList<BoundingBox> boundingBoxes = new ArrayList<BoundingBox>();

	public void addBoundingBox(BoundingBox bb) {
		boundingBoxes.add(bb);
	}

	public void remove(BoundingBox box) {
		boundingBoxes.remove(box);
	}

	public BoundingBox isColliding(BoundingBox bb) {
		for (int i = 0; i < boundingBoxes.size(); i++) {
			BoundingBox other = boundingBoxes.get(i);
			if (bb.checkCollison(other) && other != bb) {
				return other;
			}
		}
		return null;
	}
}
