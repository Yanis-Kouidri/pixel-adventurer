package gameengine.utils.model;

/**
 * a class containing constants about the world interractions
 * @author Thomas Gruner
 *
 */
public class Physics {

    private Physics() {}

    public static final float GRAVITY = 0.08f;						//the gravity constant 

    public static final float NB_DEPLACEMENT_BLOCK = 0.3f;			//the number of block that an entity can move per deplacement
    
    public static final float DELTA = 0.01f;						//delta is used as a security value for the collision check in a particular case

}
