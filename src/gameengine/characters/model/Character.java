package gameengine.characters.model;

/**
 * The Character class is the class for the main character. It's a singleton because only one main character can be create in a game.
 * @author thomas
 *
 */
public class Character extends Entity {
	
	private static Character instance = null;		//the instance of the main character
	
	/**
	 * a constructor.
	 */
	private Character() {
		super();
	}
	
	/**
	 * a constructor taking spawn coordinates.
	 * @param coordX
	 * @param coordY
	 */
	private Character(int coordX, int coordY) {
		super(coordX, coordY);
	}
	
	/**
	 * a method to create the instance.
	 * @return Character
	 */
	public static Character createInstance() {
		if(instance == null) {
			instance = new Character();
		}
		
		return instance;
	}
	
	/**
	 * a method to create the instance if doesn't exist with spawn coordinates.
	 * @param coordX
	 * @param coordY
	 * @return Character
	 */
	public static Character createInstance(int coordX, int coordY) {
		if(instance == null) {
			instance = new Character(coordX, coordY);
		}
		
		return instance;
	}
	
	/**
	 * a method returning the instance of the mainCharacter.
	 * @return Character
	 */
	public static Character getInstance() {
		return instance;
	}
	
	@Override
	public void moveLeft() {
		if(Collisions.left(getHitBox()) == CollisionType.NONE) {
			super.moveLeft();			
		}
	}
	
	@Override
	public void moveRight() {
		if(Collisions.right(getHitBox()) == CollisionType.NONE) {
			super.moveRight();			
		}
	}
	
	@Override
	public void moveUp() {
		if(Collisions.top(getHitBox()) == CollisionType.NONE) {
			super.moveUp();			
		}
	}
}
