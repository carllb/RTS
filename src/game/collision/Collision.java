package game.collision;

public class Collision {

	private int x,y;
	private BoundingBox first, second;
	private boolean xAxis;
	private boolean yAxis;
	
	public Collision(int x, int y, BoundingBox first, BoundingBox second, boolean xAxis,boolean yAxis){
		this.x = x;
		this.y = y;
		this.first = first;
		this.second = second;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BoundingBox getFirst() {
		return first;
	}

	public BoundingBox getSecond() {
		return second;
	}

	public boolean isxAxis() {
		return xAxis;
	}

	public boolean isyAxis() {
		return yAxis;
	}	
	
}
