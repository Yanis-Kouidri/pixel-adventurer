package gameengine.characters.model;

import gameengine.utils.model.Coordinates;

import static gameengine.Physics.GRAVITY;
import static gameengine.Physics.SPEED;

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

	public void moveRight() {
		float newPosition = coordinates.getX() + SPEED;
		coordinates.setX(newPosition);
	}

	public void moveLeft() {
		float newPosition = coordinates.getX() - SPEED;
		coordinates.setX(newPosition);
	}

	// rename Jump ?
	public void moveUp() {
		gravitySpeed += GRAVITY;
		float newPosition = coordinates.getY() - SPEED + gravitySpeed;
		coordinates.setY(newPosition);
	}
	@Override
	public String toString() {
		return coordinates.toString();
	}
}
