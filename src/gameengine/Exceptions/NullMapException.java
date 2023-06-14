package gameengine.Exceptions;

/**
 * An exception caused by the lack of setUp of the map in the class Collision
 * @author Thomas Gruner
 *
 */
public class NullMapException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * NullMapException constructor
	 * @param message
	 */
	public NullMapException(String message) {
		super(message);
	}
}
