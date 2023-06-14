package gameengine.characters.model;

import gameengine.exceptions.UnvalidMovementDistanceException;

/**
 * The Character class is the class for the main character. It's a singleton because only one main character can be create in a game.
 * @author Thomas Gruner
 *
 */
public class MainCharacter extends Entity {
	
	private static MainCharacter instance = null;		//the instance of the main character
	
	/**
	 * a constructor.
	 */
	private MainCharacter() {
		super();
	}

	/**
	 * a constructor taking spawn coordinates.
	 * @param width
	 * @param height
	 */
	private MainCharacter(float width, float height) {
		super(width, height);
	}
	
	/**
	 * a constructor taking spawn coordinates and a size.
	 * @param coordX
	 * @param coordY
	 * @param width
	 * @param height
	 */
	private MainCharacter(float coordX, float coordY, float width, float height) {
		super(coordX, coordY, width, height);
	}
	
	/**
	 * a method to create the instance.
	 * @return Character
	 */
	public static MainCharacter createInstance() {
		if(instance == null) {
			instance = new MainCharacter();
		}
		
		return instance;
	}
	
	/**
	 * a method to create the instance if doesn't exist with spawn coordinates.
	 * @param coordX
	 * @param coordY
	 * @return Character
	 */
	public static MainCharacter createInstance(float width, float height) {
		if(instance == null) {
			instance = new MainCharacter(width, height);
		}
		
		return instance;
	}
	
	/**
	 * a method to create the instance if doesn't exist with spawn coordinates and a size.
	 * @param coordX
	 * @param coordY
	 * @param width
	 * @param height
	 * @return Character
	 */
	public static MainCharacter createInstance(float coordX, float coordY, float width, float height) {
		if(instance == null) {
			instance = new MainCharacter(coordX, coordY, width, height);
		}
		
		return instance;
	}
	
	/**
	 * a method returning the instance of the mainCharacter.
	 * @return Character
	 */
	public static MainCharacter getInstance() {
		return instance;
	}
	
	/**
	 * a method call when the main character need to move left
	 */
	@Override
	public void moveLeft() {
		System.out.println(this);
		if(Collisions.left(getHitBox()) == CollisionType.NONE) {
			super.moveLeft();			
		} else {
			try {
				super.moveLeftOnCollision();
			} catch (UnvalidMovementDistanceException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * a method call when the main character need to move right
	 */
	@Override
	public void moveRight() {
		System.out.println(this);
		if(Collisions.right(getHitBox()) == CollisionType.NONE) {
			super.moveRight();			
		} else {
			try {
				super.moveRightOnCollision();
			} catch (UnvalidMovementDistanceException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * a method call when the main character need to jump
	 */
	@Override
	public void jump() {
		if(Collisions.top(getHitBox()) == CollisionType.NONE) {
			super.jump();			
		} else {
			try {
				super.moveUpOnCollision();
			} catch (UnvalidMovementDistanceException e) {
				e.printStackTrace();
			}
		}
	}
}
