package gameengine.characters.model;

import gameengine.Coordinates;

/**
 * Entity is the abstract class where all characters are inherited from. ex : mainCharacter, ennemies, animals, etc.
 * @author n7student
 *
 */
public abstract class Entity{
	private Coordinates coordinates;		//the coordinates of the entity
	
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
}
