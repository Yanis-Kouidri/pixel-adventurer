package gameengine.utils.model;

public class Coordinates {
	private float x, y;
	
	public Coordinates(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinates() {
		x = y = 0;
	}
	
	final public float getX() {
		return x;
	}
	
	final public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "X=" + x + " Y=" + y;
	}
}
