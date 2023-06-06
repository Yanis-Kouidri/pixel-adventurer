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
	 * a method to detect a collision on the top & bottom left side of the hitBox
	 * @param hitBox
	 * @return CollisionType
	 */
	public static CollisionType leftSide(HitBox hitBox) {
		CollisionType collision = CollisionType.AIR;
		int xPositionToCheck = Utils.convertToPixel(hitBox.getX() - MINIMUM_COLLISION_DETECTION_LENGTH);		//the left side of the entity
		int yTopPositionToCheck = Utils.convertToPixel(hitBox.getY() - hitBox.getHeight());						//the top side of the entity
		int yBottomPositionToCheck = Utils.convertToPixel(hitBox.getY() - hitBox.getHeight());					//the bottom side of the entity
		
		// TODO : Discuter avec Cedric sur comment reconnaître les différents blocs, et ajouter un collisionTypeManager qui retournera le bon type en fonction du bloc.
		//a solid bloc is detected in the top/bottom left corner of the hitbox
		if(collisionDetected(xPositionToCheck, yTopPositionToCheck) || collisionDetected(xPositionToCheck, yBottomPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	/**
	 * a method to detect a collision on the top & bottom right side of the hitBox
	 * @param hitBox
	 * @return CollisionType
	 */
	public static CollisionType rightSide(HitBox hitBox) {
		CollisionType collision = CollisionType.AIR;
		int xPositionToCheck = Utils.convertToPixel(hitBox.getX() + hitBox.getWidth() + MINIMUM_COLLISION_DETECTION_LENGTH);		//the right side of the entity
		int yTopPositionToCheck = Utils.convertToPixel(hitBox.getY());																//the top side of the entity
		int yBottomPositionToCheck = Utils.convertToPixel(hitBox.getY() - hitBox.getHeight());										//the bottom side of the entity
		
		// TODO : Discuter avec Cedric sur comment reconnaître les différents blocs, et ajouter un collisionTypeManager qui retournera le bon type en fonction du bloc.
		//a solid bloc is detected in the top/bottom right corner of the hitbox
		if(collisionDetected(xPositionToCheck, yTopPositionToCheck) || collisionDetected(xPositionToCheck, yBottomPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	/**
	 * a method to detect a collision on the top left & right side of the hitBox
	 * @param hitBox
	 * @return CollisionType
	 */
	public static CollisionType topSide(HitBox hitBox) {
		CollisionType collision = CollisionType.AIR;
		int xLeftPositionToCheck = Utils.convertToPixel(hitBox.getX());										//the left side of the entity
		int xRightPositionToCheck = Utils.convertToPixel(hitBox.getX() + hitBox.getWidth());				//the right side of the entity
		int yPositionToCheck = Utils.convertToPixel(hitBox.getY() + MINIMUM_COLLISION_DETECTION_LENGTH);	//the top side of the entity
		
		// TODO : Discuter avec Cedric sur comment reconnaître les différents blocs, et ajouter un collisionTypeManager qui retournera le bon type en fonction du bloc.
		//a solid bloc is detected in the top left/right corner of the hitbox
		if(collisionDetected(xLeftPositionToCheck, yPositionToCheck) || collisionDetected(xRightPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	/**
	 * a method to detect a collision on the bottom left & right side of the hitBox
	 * @param hitBox
	 * @return CollisionType
	 */
	public static CollisionType bottomSide(HitBox hitBox) {
		CollisionType collision = CollisionType.AIR;
		int xLeftPositionToCheck = Utils.convertToPixel(hitBox.getX());															//the left side of the entity
		int xRightPositionToCheck = Utils.convertToPixel(hitBox.getX() + hitBox.getWidth());									//the right side of the entity
		int yPositionToCheck = Utils.convertToPixel(hitBox.getY() - hitBox.getHeight() - MINIMUM_COLLISION_DETECTION_LENGTH);	//the bottom side of the entity
		
		// TODO : Discuter avec Cedric sur comment reconnaître les différents blocs, et ajouter un collisionTypeManager qui retournera le bon type en fonction du bloc.
		//a solid bloc is detected in the bottom left/right corner of the hitbox
		if(collisionDetected(xLeftPositionToCheck, yPositionToCheck) || collisionDetected(xRightPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
}
