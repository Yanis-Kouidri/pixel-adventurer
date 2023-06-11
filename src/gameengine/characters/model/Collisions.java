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
	
	public static CollisionType left(HitBox hitBox) {
		CollisionType collisionTopLeft, collisionMiddleLeft, collisionBottomLeft, collision = CollisionType.NONE;
		
		collisionTopLeft = topLeftSide(hitBox);
		collisionMiddleLeft = middleLeftSide(hitBox);
		collisionBottomLeft = bottomLeftSide(hitBox);
		
		collision = collisionManager(collisionTopLeft, collisionMiddleLeft, collisionBottomLeft);
		
		return collision;
	}
	
	public static CollisionType right(HitBox hitBox) {
		CollisionType collisionTopRight, collisionMiddleRight, collisionBottomRight, collision = CollisionType.NONE;
		
		collisionTopRight = topRightSide(hitBox);
		collisionMiddleRight = middleRightSide(hitBox);
		collisionBottomRight = bottomRightSide(hitBox);
		
		collision = collisionManager(collisionTopRight, collisionMiddleRight, collisionBottomRight);
		
		return collision;
	}
	
	public static CollisionType bottom(HitBox hitBox) {
		CollisionType collisionBottomLeft, collisionBottomMiddle, collisionBottomRight, collision = CollisionType.NONE;
		
		collisionBottomLeft = floorLeftSide(hitBox);
		collisionBottomMiddle = floorMiddleSide(hitBox);
		collisionBottomRight = floorRightSide(hitBox);
		
		collision = collisionManager(collisionBottomLeft, collisionBottomMiddle, collisionBottomRight);
		
		return collision;
	}
	
	public static CollisionType top(HitBox hitBox) {
		CollisionType collisionTopLeft, collisionTopMiddle, collisionTopRight, collision = CollisionType.NONE;
		
		collisionTopLeft = headLeftSide(hitBox);
		collisionTopMiddle = headMiddleSide(hitBox);
		collisionTopRight = headRightSide(hitBox);
		
		collision = collisionManager(collisionTopLeft, collisionTopMiddle, collisionTopRight);
		
		return collision;
	}
	
	private static CollisionType collisionManager(CollisionType a, CollisionType b, CollisionType c) {
		CollisionType collision = CollisionType.NONE;
		
		if(a == CollisionType.SOLID | b == CollisionType.SOLID || c == CollisionType.SOLID) {
			collision = CollisionType.SOLID;
			
			//TODO : ENLEVER
			System.out.println("++++++++++++++++++ Collision");
		}
		else {
			//mettre ici le code relatif à la gestion d'une collision de type lyquide
		}
		
		return collision;
	}
	
	private static CollisionType topLeftSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.ceilFloatToInt(hitBox.getX() - Physics.NB_DEPLACEMENT_BLOCK);
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY());
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType middleLeftSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.ceilFloatToInt(hitBox.getX() - Physics.NB_DEPLACEMENT_BLOCK);
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + (hitBox.getHeight() / 2));
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType bottomLeftSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.ceilFloatToInt(hitBox.getX() - Physics.NB_DEPLACEMENT_BLOCK);
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + hitBox.getHeight());
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType topRightSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + Physics.NB_DEPLACEMENT_BLOCK);
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY());
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType middleRightSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + Physics.NB_DEPLACEMENT_BLOCK);
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + (hitBox.getHeight() / 2));
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType bottomRightSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + Physics.NB_DEPLACEMENT_BLOCK);
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + hitBox.getHeight());
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType floorLeftSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX());
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + Physics.NB_DEPLACEMENT_BLOCK);
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType floorMiddleSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + (hitBox.getWidth() / 2));
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + Physics.NB_DEPLACEMENT_BLOCK);
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType floorRightSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + hitBox.getWidth());
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() + Physics.NB_DEPLACEMENT_BLOCK);
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType headLeftSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX());
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() - Physics.NB_DEPLACEMENT_BLOCK);
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType headMiddleSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + (hitBox.getWidth() / 2));
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() - Physics.NB_DEPLACEMENT_BLOCK);
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	private static CollisionType headRightSide(HitBox hitBox) {
		CollisionType collision = CollisionType.NONE;
		int xPositionToCheck = Utils.truncateFloatToInt(hitBox.getX() + hitBox.getWidth());
		int yPositionToCheck = Utils.truncateFloatToInt(hitBox.getY() - Physics.NB_DEPLACEMENT_BLOCK);
		
		if(collisionDetected(xPositionToCheck, yPositionToCheck)) {
			collision = CollisionType.SOLID;
		}
		
		return collision;
	}
	
	
}
