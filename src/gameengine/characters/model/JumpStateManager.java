package gameengine.characters.model;

public class JumpStateManager {
	
	public static boolean manager(Entity entity, boolean spaceKeyPressed) {
		boolean canJump = false;
		
		switch(entity.getJumpingState()) {
			case ON_THE_FLOOR : 
				if(spaceKeyPressed) {
					
				}
		}
		
		return canJump;
	}
}
