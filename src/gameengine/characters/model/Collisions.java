package gameengine.characters.model;

import gameengine.map.model.Map;
import gameengine.utils.model.HitBox;
import gameengine.utils.model.Utils;

/**
 * the Collisions class implements and define all methods specific to the collision detection
 * @author n7student
 *
 */
public class Collisions {
	
	private static final float MINIMUM_COLLISION_DETECTION_LENGTH = 0.1f;		//the minimum lenght that can be detected on both side
	private static Map map;														//the current map
	
	/**
	 * a method to set up the map
	 * WARNING : this method need to be call again only if the map is changing
	 * @param map
	 */
	public static void setMap(Map map) {
		Collisions.map = map;
	}
	
	/**
	 * a method that detect if the tile is solid or not
	 * @param xPositionToCheck
	 * @param yPositionToCheck
	 * @return boolean
	 */
	private static boolean collisionDetected(int xPositionToCheck, int yPositionToCheck) {
		return map.getTileAtPos(xPositionToCheck, yPositionToCheck).getCollisionBehaviour();
	}
	
	/**
	 * a method to detect a collision on the left side of the hitBox
	 * @param hitBox
	 * @return boolean
	 */
	public static boolean leftSide(HitBox hitBox) {
		boolean collision = false;
		int xPositionToCheck = Utils.convertToPixel(hitBox.getX() - MINIMUM_COLLISION_DETECTION_LENGTH);
		int yPositionToCheck = Utils.convertToPixel(hitBox.getX());
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = true;
		}
		
		return collision;
	}
	
}
