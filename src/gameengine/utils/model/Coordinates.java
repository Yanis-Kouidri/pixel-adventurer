package gameengine.utils.model;

public class Coordinates {
	private int x, y;
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinates() {
		x = y = 0;
	}
	
	final public int getX() {
		return x;
	}
	
	final public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
