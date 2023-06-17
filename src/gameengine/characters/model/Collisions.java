package gameengine.characters.model;

import gameengine.Exceptions.NullMapException;
import gameengine.map.model.Map;
import gameengine.utils.model.HitBox;
import gameengine.utils.model.Physics;
import gameengine.utils.model.Utils;

/**
 * the Collisions class implements and define all methods specific to the collision detection
 * @author Thomas Gruner
 *
 */
public class Collisions {
	
	private static Map map;														//the current map
	private static final int HITBOX_SURFACE_CHECK_COLLISION_POINTS_NUMBER = 3;	//the number of check that need to be done (ex : to check the left side, you need to check the left/top + left/middle + left/bottom side)
	
	
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
		if(map == null) {
			throw new NullMapException("ERROR : The map is not set for the Collision class");
		} else {
			return map.getTileAtPos(xPositionToCheck, yPositionToCheck).getCollisionBehaviour();			
		}
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
	public static CollisionType bottom(HitBox hitBox, float gravitySpeed) {
		CollisionType collision = CollisionType.NONE;
		
		collision = vertical(false, hitBox, gravitySpeed);
		
		return collision;
	}
	
	/**
	 * detect a collision on the top side of an entity
	 * @param hitBox
	 * @return CollisionType
	 */
	public static CollisionType top(HitBox hitBox, float gravitySpeed) {
		CollisionType  collision = CollisionType.NONE;

		collision = vertical(true, hitBox, gravitySpeed);
		
		return collision;
	}
	
	/**
	 * get the tile position on the x axis
	 * @param leftSide if the tile position is for the left side check set this parameter to true
	 * @param hitBox
	 * @return int the x position of the tile
	 */
	private static int getXTilePosition (boolean leftSide, HitBox hitBox) {
		int tilePosition = 0;
		if (leftSide) {
			//left side : the tile to check is at the hitBox X position minus the number of deplacement for a block
			tilePosition = Utils.truncateFloatToInt(hitBox.getX() - Physics.NB_DEPLACEMENT_BLOCK);
		} else {
			//right side : the tile to check is at the hitBox X position, plus the width of the hitBox, plus the number of deplacement for a block, minus the delta value (ex : 1.7 {<-- hitBox X pos} + 2.0 {<-- hitBox width} + 0.3 = 4.0, which is not the position of the block we want to check)
			tilePosition = Utils.truncateFloatToInt(hitBox.getX() + hitBox.getWidth() + Physics.NB_DEPLACEMENT_BLOCK - Physics.DELTA);
		}
		return tilePosition;
	}
	
	/**
	 * get the tile position on the y axis
	 * @param topSide if the tile position is for the top side check set this parameter to true
	 * @param hitBox
	 * @return int the y position of the tile
	 */
	private static int getYTilePosition (boolean topSide, HitBox hitBox, float gravitySpeed) {
		int tilePosition = 0;
		if (topSide) {
			//top side : the tile to check is at the hitBox Y position minus the number of deplacement for a block
			tilePosition = Utils.truncateFloatToInt(hitBox.getY() - gravitySpeed- Physics.NB_DEPLACEMENT_BLOCK);
		} else {
			//bottom side : the tile to check is at the hitBox Y position, plus the height of the hitBox, plus the number of deplacement for a block, minus the minimum block deplacement (ex : 1.7 {<-- hitBox Y pos} + 2.0 {<-- hitBox height} + 0.3 = 4.0, which is not the position of the block we want to check)
			tilePosition = Utils.truncateFloatToInt(hitBox.getY() + hitBox.getHeight() + gravitySpeed + Physics.NB_DEPLACEMENT_BLOCK - Physics.DELTA);
		}
		
		return tilePosition; 
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
		//for the right/left top side
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY());
		
		for (int i = 0; i < HITBOX_SURFACE_CHECK_COLLISION_POINTS_NUMBER; i++) {
			switch(i) {
			
			case 1 : yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + (hitBox.getHeight() / 2));									//for the right/left middle side
			break;
			
			case 2 : yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + hitBox.getHeight() - Physics.DELTA);	//for the right/left bottom side
			break;
			}
			
			//if a collision has already been detected, it is useless to check for collisions again
			if (collision == CollisionType.NONE && collisionDetected(xPositionToCheck, yPositionToCheck)) {
				collision = CollisionType.SOLID;
			}
		}
		
		return collision;
	}
	
	/**
	 * this method regroup the collision management for the top and bottom side by checking if a collision will appear on the next movement
	 * @param topSide if the collision detection is for the top side set this parameter to true
	 * @param hitBox
	 * @return CollisionType
	 */
	private static CollisionType vertical(boolean topSide, HitBox hitBox, float gravitySpeed) {
		CollisionType collision = CollisionType.NONE;
		//for the top/bottom left side
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX());
		int yPositionToCheck = getYTilePosition(topSide, hitBox, gravitySpeed);
		
		for (int i = 0; i < HITBOX_SURFACE_CHECK_COLLISION_POINTS_NUMBER; i++) {
			switch(i) {
			case 1 : xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + (hitBox.getWidth() / 2));									//for the top/bottom middle side
			break;
			
			case 2 : xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + hitBox.getWidth() - Physics.DELTA);	//for the top/bottom right side
			break;
			}
			
			//if a collision has already been detected, it is useless to check for collisions again
			if (collision == CollisionType.NONE && collisionDetected(xPositionToCheck, yPositionToCheck)) {
				collision = CollisionType.SOLID;
			}
		}
		
		return collision;
	}
	
}
