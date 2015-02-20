package physics;

public class Vector {

	private float x, y;
	private float mag, radDir;

	public Vector(float mag, float radDir)
	{
		this.mag = mag;
		this.radDir = radDir;
		x = (float) (mag * Math.cos(radDir));
		y = (float) (mag * Math.sin(radDir));
	}
 
	
	
	public float getMag() {
		return mag;
	}

	public float getRadDir() {
		return radDir;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}


}
