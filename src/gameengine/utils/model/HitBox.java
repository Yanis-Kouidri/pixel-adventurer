package gameengine.utils.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Logger;

/**
 * the HitBox class represent the HitBox for an entity
 * @author n7student
 *
 */
public class HitBox {
	private float x, y, width, height;		//coordinates for the hitBox
	private final Logger logger = Logger.getLogger(String.valueOf(this.getClass()));	
	/**
	 * Constructor taking coordinates and size of the hitBox
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public HitBox(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Costructor taking coordinates of the hitBox
	 * @param x
	 * @param y
	 */
	public HitBox(float x, float y){
		this.x = x;
		this.y = y;
		width = 1.0f;
		height = 1.0f;
	}
	
	/**
	 * x getter
	 * @return x
	 */
	public float  getX() {
		return x;
	}
	
	/**
	 * y getter
	 * @return y
	 */
	public float  getY() {
		return y;
	}
	
	/**
	 * width getter
	 * @return width
	 */
	public float  getWidth() {
		return width;
	}
	
	/**
	 * height getter
	 * @return height
	 */
	public float  getHeight() {
		return height;
	}
	
	/**
	 * update des coordonnées, ainsi que de la taille de la hitbox
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void updateHitBox(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
//	public void drawHitBox(Graphics g) {
//		g.setColor(Color.CYAN);
//		g.fillRect((int) x * Constants.BLOCK_LENGHT, (int) y * Constants.BLOCK_LENGHT, (int) width * Constants.BLOCK_LENGHT, (int) height * Constants.BLOCK_LENGHT);
//		//TODO : logs
//	}
}
