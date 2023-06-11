package gameengine.characters.model;

import gameengine.utils.model.Coordinates;
import gameengine.utils.model.HitBox;
import static gameengine.utils.model.Physics.GRAVITY;
import static gameengine.utils.model.Physics.NB_DEPLACEMENT_BLOCK;

/**
 * Entity is the abstract class where all characters are inherited from. ex : mainCharacter, ennemies, animals, etc.
 * @author n7student
 *
 */
public abstract class Entity{
	private Coordinates coordinates;		//the coordinates of the entity
	private float width, height;		//la taille de l'entité par rapport à la taille d'un bloc
	private HitBox hitBox;					//la hitBox de l'entité
	private float gravitySpeed = 0;			// speed = number block per seconds
	
	
	/**
	 * a constructor.
	 */
	public Entity() {
		width = 1;
		height = 1;
		coordinates = new Coordinates();
		initHitBox();
	}
	
	/**
	 * a constructor taking spawn coordinates.
	 * @param coordX
	 * @param coordY
	 */
	public Entity(float coordX, float coordY) {
		width = 1;
		height = 1;
		coordinates = new Coordinates(coordX, coordY);
		initHitBox();
	}
	
	/**
	 * a constructor taking spawn coordinates and the size of the entity.
	 * @param coordX
	 * @param coordY
	 * @param width
	 * @param height
	 */
	public Entity(float coordX, float coordY, float width, float height) {
		this.width = width;
		this.height = height;
		coordinates = new Coordinates(coordX, coordY);
		initHitBox();
	}
	
	/**
	 * permit to set a new location for the entity
	 * @param coordX
	 * @param coordY
	 */
	public void setLocation(float coordX, float coordY) {
		coordinates.setX(coordX);
		coordinates.setY(coordY);
		
		updateHitBox();
	}
	
	/**
	 * initialize the HitBox 
	 */
	private void initHitBox() {
		hitBox = new HitBox(coordinates.getX(), coordinates.getY(), width, height);
	}
	
	/**
	 * the hitBox getter
	 * @return HitBox
	 */
	public HitBox getHitBox() {
		return hitBox;
	}
	
	/**
	 * a method to update the hitBox coordinates
	 */
	protected void updateHitBox() {
		hitBox.updateHitBox(coordinates.getX(), coordinates.getY(), width, height);
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
		updateHitBox();
	}

	/**
	 * a method to move the entity to the left of NB_DEPLACEMENT_BLOCK
	 */
	public void moveLeft() {
		float newPosition = coordinates.getX() - NB_DEPLACEMENT_BLOCK;
		coordinates.setX(newPosition);
		updateHitBox();
	}

	/**
	 * a method to jump the entity from NB_DEPLACEMENT_BLOCK
	 */
	public void moveUp() {
		gravitySpeed += GRAVITY;
		float newPosition = coordinates.getY() - NB_DEPLACEMENT_BLOCK + gravitySpeed;
		coordinates.setY(newPosition);
		updateHitBox();
	}

	@Override
	public String toString() {
		return coordinates.toString();
	}
}
