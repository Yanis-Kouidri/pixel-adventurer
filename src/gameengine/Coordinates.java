package gameengine;

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
}
