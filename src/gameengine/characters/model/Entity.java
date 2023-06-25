package gameengine.characters.model;

import gameengine.map.model.Map;
import gameengine.utils.model.Coordinates;
import gameengine.utils.model.HitBox;
import gameengine.utils.model.Physics;
import gameengine.utils.model.Utils;


import static gameengine.utils.model.Physics.GRAVITY;
import static gameengine.utils.model.Physics.NB_DEPLACEMENT_BLOCK;

import java.util.ArrayList;
import java.util.List;

import gameengine.exceptions.UnvalidMovementDistanceException;

/**
 * Entity is the abstract class where all characters are inherited from. ex : mainCharacter, ennemies, animals, etc.
 * @author Thomas Gruner
 *
 */
public abstract class Entity{
	private Coordinates coordinates;			//the coordinates of the entity
	private float width, height;				//the entity width and height
	private HitBox hitBox;						//the entity hitbox
	private float gravitySpeed = 0.0f;			// speed = number block per seconds
	private static List<Entity> instances = new ArrayList<>();		//a list containing every entity instances
	private EntityJumpStateType actualJumpState;					//the actual state of the entity that permit it or not to jump
	private boolean gravityReset;				//a boolean used to know if the entity gravity speed has already been reset 
	
	
	/**
	 * a constructor.
	 */
	public Entity() {
		width = 1;
		height = 1;
		coordinates = new Coordinates();
		initHitBox();
		
		actualJumpState = EntityJumpStateType.ON_THE_FLOOR;
		
		instances.add(this);
	}
	
	/**
	 * a constructor taking spawn coordinates.
	 * @param coordX
	 * @param coordY
	 */
	public Entity(float width, float height) {
		this.width = width;
		this.height = height;
		coordinates = new Coordinates();
		initHitBox();
		
		actualJumpState = EntityJumpStateType.ON_THE_FLOOR;
		
		instances.add(this);
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

		actualJumpState = EntityJumpStateType.ON_THE_FLOOR;
		
		instances.add(this);
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
	 * a method to get the entity coordinates in pixels.
	 * @return Coordinates
	 */
	public final Coordinates getCoordinates() {
		return coordinates;
	}
	
	/**
	 * a method to get all instances of entities
	 * @return List<Entity>
	 */
	public static List<Entity> getInstances(){
		return instances;
	}
	
	/**
	 * a method to reset the gravity speed
	 */
	public void resetGravitySpeed() {
		gravitySpeed = 0.0f;
	}
	
	/**
	 * a method to reset the gravity speed
	 */
	public void resetGravitySpeedOnce() {
		if(gravityReset) {
			gravitySpeed = 0.0f;
			gravityReset = false;
		}
	}
	
	/**
	 * getter of the gravity speed
	 * @return float
	 */
	public float getGravitySpeed() {
		return gravitySpeed;
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
	 * a method that return the actual entity jumping state
	 * @return EntityJumpStateType
	 */
	public EntityJumpStateType getJumpingState() {
		return actualJumpState;
	}
	
	public void setJumpingState(EntityJumpStateType state) {
		actualJumpState = state;
	}

	/**
	 * a method to make the entity isJumping from NB_DEPLACEMENT_BLOCK
	 */
	public void jump() {
		if(NB_DEPLACEMENT_BLOCK - gravitySpeed > 0) {
			actualJumpState = EntityJumpStateType.GOING_UP;
			float newPosition = coordinates.getY() - NB_DEPLACEMENT_BLOCK + gravitySpeed;
			coordinates.setY(newPosition);
			updateHitBox();
			gravitySpeed += GRAVITY;			
		} else {
			actualJumpState = EntityJumpStateType.GOING_DOWN;
			resetGravitySpeed();
		}			
	}
	
	/**
	 * a method to make the entity isJumping when a collision is detected to the maximum it can
	 * @throws UnvalidMovementDistanceException 
	 */
	public void jumpOnCollision() throws UnvalidMovementDistanceException {
		float yPos = hitBox.getY();
		float newPosition = (float) Utils.ceilFloatToInt(yPos);
		float distance = newPosition - yPos;
		
		if(distance > Physics.NB_DEPLACEMENT_BLOCK) {
			throw new UnvalidMovementDistanceException(distance, yPos, hitBox.getY());
		}
		else {
			coordinates.setY(newPosition);
			updateHitBox();		
		}
		
		//if a collision is detected on a jump, the entity is going down
		actualJumpState = EntityJumpStateType.GOING_DOWN;
		
		//reset of the gravity speed when a collision is detected
		resetGravitySpeed();
	}
	
	/**
	 * a method to make the entity fall 
	 * @throws UnvalidMovementDistanceException 
	 */
	private void fall() {
		System.out.println(this);
		float newPosition = coordinates.getY() + NB_DEPLACEMENT_BLOCK + gravitySpeed;
		coordinates.setY(newPosition);
		updateHitBox();		
		gravitySpeed += GRAVITY;
	}
	
	/**
	 * a method to make the entity fall when a collision is detected to the maximum it can
	 * @throws UnvalidMovementDistanceException 
	 */
	public void fallOnCollision() throws UnvalidMovementDistanceException {
		System.out.println(this);
		float yPos = hitBox.getY();
		float newPosition = (float) Utils.ceilFloatToInt(yPos);
		// TODO : enlever si pas utile
		float distanceFromTheGround = Utils.truncateFloatToInt(newPosition - yPos);
		
		if(distanceFromTheGround > Physics.NB_DEPLACEMENT_BLOCK) {
			throw new UnvalidMovementDistanceException(distanceFromTheGround, yPos, hitBox.getY());
		}
		else {
			coordinates.setY(newPosition);
			updateHitBox();	
		}
		gravitySpeed = 0.0f;
	}
	
	/**
	 * a method that return true if the character is not on the ground and reset the gravity
	 * @return boolean
	 */
	private boolean notOnTheGround() {
		boolean notOnTheGround = true;
		
		if(hitBox.getY() % 1 <= Physics.DELTA) {
			notOnTheGround = false;
			gravityReset = true;
			resetGravitySpeed();
		}
		
		return notOnTheGround;
	}

	/**
	 * a method to check if an entity need to fall
	 */
	public void fallingCheck() {
		
		if(Collisions.bottom(getHitBox(), gravitySpeed) == CollisionType.NONE) {
			//no collision, so the entity is falling
			actualJumpState = EntityJumpStateType.GOING_DOWN;
			fall();
		} else {
			if(notOnTheGround()) {
				try {
					//a collision detected, but not on the ground yet, the entity is still going down
					actualJumpState = EntityJumpStateType.GOING_DOWN;
					fallOnCollision();
				} catch (UnvalidMovementDistanceException e) {
					e.printStackTrace();
					System.out.println();
				}				
			}
			else {
				//if the entity is on the ground, then it's state is on the floor
				actualJumpState = EntityJumpStateType.ON_THE_FLOOR;
			}
		}
		
	}
	
	@Override
	public String toString() {
		return coordinates.toString() + "gravity speed = " + gravitySpeed;
	}

	/**
	 * a method to set the spawn coordinates of an entity
	 * @param m
	 */
	public void setSpawn(Map m){
		coordinates.setX((float) (m.getSpawnPoint().getX() - 0.5));
		coordinates.setY((float) ((m.getSpawnPoint().getY()-2) - 0.5));
		System.out.println("> Position of Player : ("+coordinates.getX()+","+coordinates.getY()+")");
		updateHitBox();
	}
	
}
