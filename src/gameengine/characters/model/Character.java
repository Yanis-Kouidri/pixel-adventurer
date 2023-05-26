package gameengine.characters.model;

/**
 * The Character class is the class for the main character. It's a singleton because only one main character can be create in a game.
 * @author n7student
 *
 */
public class Character extends Entity{
	
	private static Character INSTANCE = null;		//the instance of the main character
	
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
		if(INSTANCE == null) {
			INSTANCE = new Character();
		}
		
		return INSTANCE;
	}
	
	/**
	 * a method to create the instance if doesn't exist with spawn coordinates.
	 * @param coordX
	 * @param coordY
	 * @return Character
	 */
	public static Character createInstance(int coordX, int coordY) {
		if(INSTANCE == null) {
			INSTANCE = new Character(coordX, coordY);
		}
		
		return INSTANCE;
	}
	
	/**
	 * a method returning the instance of the mainCharacter.
	 * @return Character
	 */
	public static Character getInstance() {
		return INSTANCE;
	}
}
