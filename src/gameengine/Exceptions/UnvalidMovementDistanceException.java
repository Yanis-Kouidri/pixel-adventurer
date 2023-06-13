package gameengine.Exceptions;

import gameengine.utils.model.Physics;

/**
 * an exception provocated by the wrong distance estimation that need to be browse after a collision detection
 * @author n7student
 *
 */
public class UnvalidMovementDistanceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UnvalidMovementDistanceException(float distance, float entityXpos, float entityYpos) {
		super(message(distance, entityXpos, entityYpos));
	}
	
	private static String message(float distance, float entityXpos, float entityYpos) {
		return "EXCEPTION : the distance to browse is higher than the authorized distance (" + Physics.NB_DEPLACEMENT_BLOCK + " blocks)  ==>  dist = " + distance + " at position x = " + entityXpos + ", y = " + entityYpos;
	}

}
