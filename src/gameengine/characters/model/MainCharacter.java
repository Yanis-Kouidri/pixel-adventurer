package gameengine.characters.model;

/**
 * The Character class is the class for the main character. It's a singleton because only one main character can be create in a game.
 * @author n7student
 *
 */
public class MainCharacter extends Entity{
	
	private static MainCharacter INSTANCE = null;		//the instance of the main character
	
	/**
	 * a constructor.
	 */
	private MainCharacter() {
		super();
	}
	
	/**
	 * a constructor taking spawn coordinates.
	 * @param coordX
	 * @param coordY
	 */
	private MainCharacter(int coordX, int coordY) {
		super(coordX, coordY);
	}
	
	/**
	 * a method to create the instance.
	 * @return Character
	 */
	public static MainCharacter createInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MainCharacter();
		}
		
		return INSTANCE;
	}
	
	/**
	 * a method to create the instance if doesn't exist with spawn coordinates.
	 * @param coordX
	 * @param coordY
	 * @return Character
	 */
	public static MainCharacter createInstance(int coordX, int coordY) {
		if(INSTANCE == null) {
			INSTANCE = new MainCharacter(coordX, coordY);
		}
		
		return INSTANCE;
	}
	
	/**
	 * a method returning the instance of the mainCharacter.
	 * @return Character
	 */
	public static MainCharacter getInstance() {
		return INSTANCE;
	}
}
