package gameengine.characters.model;

import gameengine.utils.model.Coordinates;

import static gameengine.utils.model.Physics.GRAVITY;
import static gameengine.utils.model.Physics.NB_DEPLACEMENT_BLOCK;

/**
 * Entity is the abstract class where all characters are inherited from. ex : mainCharacter, ennemies, animals, etc.
 * @author n7student
 *
 */
public abstract class Entity{
	private Coordinates coordinates;		//the coordinates of the entity

	private float gravitySpeed = 0;
	// speed = number block per seconds
	/**
	 * a constructor.
	 */
	public Entity() {
		coordinates = new Coordinates();
	}
	
	/**
	 * a constructor taking spawn coordinates.
	 * @param coordX
	 * @param coordY
	 */
	public Entity(int coordX, int coordY) {
		coordinates = new Coordinates(coordX, coordY);
	}
	
	/**
	 * a method to get the entity coordinates.
	 * @return Coordinates
	 */
	public final Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * a method to move the entity to the right of NB_DEPLACEMENT_BLOCK
	 */
	public void moveRight() {
		float newPosition = coordinates.getX() + NB_DEPLACEMENT_BLOCK;
		coordinates.setX(newPosition);
	}

	/**
	 * a method to move the entity to the left of NB_DEPLACEMENT_BLOCK
	 */

	public void moveLeft() {
		float newPosition = coordinates.getX() - NB_DEPLACEMENT_BLOCK;
		coordinates.setX(newPosition);
	}

	/**
	 * a method to jump the entity from NB_DEPLACEMENT_BLOCK
	 */
	public void moveUp() {
		gravitySpeed += GRAVITY;
		float newPosition = coordinates.getY() - NB_DEPLACEMENT_BLOCK + gravitySpeed;
		coordinates.setY(newPosition);
	}

	@Override
	public String toString() {
		return coordinates.toString();
	}
}