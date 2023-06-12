package gameengine.characters.model;

import gameengine.map.model.Map;
import gameengine.utils.model.HitBox;
import gameengine.utils.model.Physics;
import gameengine.utils.model.Utils;

/**
 * the Collisions class implements and define all methods specific to the collision detection
 * @author n7student
 *
 */
public class Collisions {
	
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
	 * detect a collision on the left side of an entity
	 * @param hitBox
	 * @return CollisionType
	 */
	public static CollisionType left(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		
		collision = lateral(true, hitBox);
		
		return collision;
	}
	
	/**
	 * detect a collision on the right side of an entity
	 * @param hitBox
	 * @return CollisionType
	 */
	public static CollisionType right(HitBox hitBox) {
		CollisionType  collision = CollisionType.NONE;
		
		collision = lateral(false, hitBox);
		
		return collision;
	}
	
	/**
	 * detect a collision on the bottom side of an entity
	 * @param hitBox
	 * @return CollisionType
	 */
	public static CollisionType bottom(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		
		collision = vertical(false, hitBox);
		
		return collision;
	}
	
	/**
	 * detect a collision on the top side of an entity
	 * @param hitBox
	 * @return CollisionType
	 */
	public static CollisionType top(HitBox hitBox) {
		CollisionType  collision = CollisionType.NONE;

		collision = vertical(true, hitBox);
		
		return collision;
	}
	
	/**
	 * get the tile position on the x axis
	 * @param leftSide if the tile position is for the left side check set this parameter to true
	 * @param hitBox
	 * @return int the x position of the tile
	 */
	private static int getXTilePosition (boolean leftSide, HitBox hitBox) {
		return leftSide ? 
				Utils.truncateFloatToInt(hitBox.getX() - Physics.NB_DEPLACEMENT_BLOCK) : 
					Utils.truncateFloatToInt(hitBox.getX() + hitBox.getWidth() + Physics.NB_DEPLACEMENT_BLOCK - Physics.MINIMUM_BLOC_DEPLACEMENT);
	}
	
	/**
	 * get the tile position on the y axis
	 * @param topSide if the tile position is for the top side check set this parameter to true
	 * @param hitBox
	 * @return int the y position of the tile
	 */
	private static int getYTilePosition (boolean topSide, HitBox hitBox) {
		return topSide ? 
				Utils.truncateFloatToInt(hitBox.getY() - Physics.NB_DEPLACEMENT_BLOCK) : 
					Utils.truncateFloatToInt(hitBox.getY() + Physics.NB_DEPLACEMENT_BLOCK);
	}
	
	/**
	 * this method regroup the collision management for the left and right side by checking if a collision will appear on the next movement
	 * @param leftSide if the collision detection is for the left side set this parameter to true
	 * @param hitBox
	 * @return CollisionType
	 */
	private static CollisionType lateral(boolean leftSide, HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = getXTilePosition(leftSide, hitBox);
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY());
		
		for(int i = 0; i < 3; i++) {
			switch(i) {
			case 1 : yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + (hitBox.getHeight() / 2));
			break;
			
			case 2 : yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + hitBox.getHeight() - Physics.MINIMUM_BLOC_DEPLACEMENT);
			break;
			}
			
			if(collision == CollisionType.NONE && collisionDetected(xPositionToCheck, yPositionToCheck)) {
				collision = CollisionType.SOLID;
			}
		}
		
		return collision;
	}
	
	/**
	 * this method regroup the collision management for the top and bottom side by checking if a collision will appear on the next movement
	 * @param leftSide if the collision detection is for the top side set this parameter to true
	 * @param hitBox
	 * @return CollisionType
	 */
	private static CollisionType vertical(boolean topSide, HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX());
		int yPositionToCheck = getYTilePosition(topSide, hitBox);
		
		for(int i = 0; i < 3; i++) {
			switch(i) {
			case 1 : xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + (hitBox.getWidth() / 2));
			break;
			
			case 2 : xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + hitBox.getWidth() - Physics.MINIMUM_BLOC_DEPLACEMENT);
			break;
			}
			
			if(collision == CollisionType.NONE && collisionDetected(xPositionToCheck, yPositionToCheck)) {
				collision = CollisionType.SOLID;
			}
		}
		
		return collision;
	}
	
}
