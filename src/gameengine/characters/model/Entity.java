package gameengine.characters.model;

import gameengine.utils.model.Coordinates;
import gameengine.utils.model.HitBox;
import gameengine.utils.model.Physics;
import gameengine.utils.model.Utils;

import static gameengine.utils.model.Physics.GRAVITY;
import static gameengine.utils.model.Physics.NB_DEPLACEMENT_BLOCK;

import gameengine.Exceptions.UnvalidMovementDistanceException;

/**
 * Entity is the abstract class where all characters are inherited from. ex : mainCharacter, ennemies, animals, etc.
 * @author Thomas Gruner
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
		float newPosition = hitBox.getX() + NB_DEPLACEMENT_BLOCK;
		coordinates.setX(newPosition);
		updateHitBox();
	}
	
	/**
	 * a method to move the entity to the right when a collision is detected to the maximum it can
	 * @throws UnvalidMovementDistanceException 
	 */
	public void moveRightOnCollision() throws UnvalidMovementDistanceException {
		float xPos = hitBox.getX();
		float newPosition = (float) Utils.ceilFloatToInt(xPos);
		float distance = newPosition - xPos;
		
		if(distance > Physics.NB_DEPLACEMENT_BLOCK) {
			throw new UnvalidMovementDistanceException(distance, xPos, hitBox.getY());
		}
		else {
			coordinates.setX(newPosition);
			updateHitBox();			
		}
		
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
	 * a method to move the entity to the left when a collision is detected to the maximum it can
	 * @throws UnvalidMovementDistanceException 
	 */
	public void moveLeftOnCollision() throws UnvalidMovementDistanceException {
		float xPos = hitBox.getX();
		float newPosition = (float) Utils.truncateFloatToInt(xPos);
		float distance = xPos - newPosition;
		
		if(distance > Physics.NB_DEPLACEMENT_BLOCK) {
			throw new UnvalidMovementDistanceException(distance, xPos, hitBox.getY());
		}
		else {
			coordinates.setX(newPosition);
			updateHitBox();			
		}
	}

	/**
	 * a method to make the entity jump from NB_DEPLACEMENT_BLOCK
	 */
	public void moveUp() {
		gravitySpeed += GRAVITY;
		float newPosition = coordinates.getY() - NB_DEPLACEMENT_BLOCK + gravitySpeed;
		coordinates.setY(newPosition);
		updateHitBox();
	}
	
	/**
	 * a method to make the entity jump when a collision is detected to the maximum it can
	 * @throws UnvalidMovementDistanceException 
	 */
	public void moveUpOnCollision() throws UnvalidMovementDistanceException {
		float yPos = hitBox.getY();
		float newPosition = (float) Utils.truncateFloatToInt(yPos);
		float distance = yPos - newPosition;
		
		if(distance > Physics.NB_DEPLACEMENT_BLOCK) {
			throw new UnvalidMovementDistanceException(distance, yPos, hitBox.getY());
		}
		else {
			coordinates.setY(newPosition);
			updateHitBox();		
		}
	}

	@Override
	public String toString() {
		return coordinates.toString();
	}
}
