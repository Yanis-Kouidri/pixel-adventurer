package gameengine.application.model;

import gameengine.characters.controller.CharacterController;
import gameengine.characters.model.Character;
import gameengine.characters.model.Entity;
import gameengine.map.model.Map;
import gameengine.utils.model.Constants;
import gameengine.utils.model.Coordinates;
import gameengine.utils.model.Physics;

import static gameengine.utils.model.Physics.GRAVITY;
import static gameengine.utils.model.Physics.NB_DEPLACEMENT_BLOCK;

/**
 * Implements the logic on how the view of the player will be placed at.
 */
public class Camera {
    Coordinates coordinates; // The top-left point of the camera
    Map map; // The map on which the camera will be placed.
    Entity character; // Follows this character
    Boolean followPlayer; // Tells if the camera will lock on the player or not.

    /**
     * Will construct a camera using the map and the character.
     * If the lockOnPlayer boolean value is true, it will follow by default the player
     * and will lock on him. Else the camera will be placed in the middle of the map and
     * will have to be controlled manually.
     * @param c the player
     * @param m the map
     * @param lockOnPlayer lock-on
     */
    public Camera(Entity c, Map m, Boolean lockOnPlayer){
        followPlayer = lockOnPlayer;
        character = c;
        map = m;
        if(followPlayer){
            setToPlayer();
        }
        else{
            centerMap();
        }
    }

    /**
     * Retrieves the x of the camera's location
     * @return x
     */
    public int getX(){
        return (int) coordinates.getX();
    }

    /**
     * Retrieves the y of the camera's location
     * @return y
     */
    public void setY(int y){
        coordinates.setY(y);
    }
    /**
     * Retrieves the x of the camera's location
     * @return x
     */
    public void setX(int x){
        coordinates.setX(x);
    }

    /**
     * Retrieves the y of the camera's location
     * @return y
     */
    public int getY(){
        return (int) coordinates.getY();
    }

    /**
     * Returns if the lock-on is activated
     * @return the lock-on status
     */
    public Boolean getFollowPlayer() {
        return followPlayer;
    }

    /**
     * Modify the lock-on
     * @param followPlayer new status
     */
    public void setFollowPlayer(Boolean followPlayer) {
        this.followPlayer = followPlayer;
    }

    /**
     * Moves the camera's coordinates upwards. The number of pixels is a constant
     * and can be found in utils.model.Physics
     */
    public void moveUp() {
        float newPosition = coordinates.getY() - NB_DEPLACEMENT_BLOCK + GRAVITY;
        coordinates.setY(newPosition);
    }

    /**
     * Moves the camera's coordinates to the right. The number of pixels is a constant
     * and can be found in utils.model.Physics
     */
    public void moveRight() {
        coordinates.setX(getX() + Physics.NB_DEPLACEMENT_BLOCK);
    }

    /**
     * Moves the camera's coordinates to the left.The number of pixels is a constant
     * and can be found in utils.model.Physics
     */
    public void moveLeft() {
        coordinates.setX(getX() - Physics.NB_DEPLACEMENT_BLOCK);

    }
    /**
     * Moves the camera's coordinates downwards.The number of pixels is a constant
     * and can be found in utils.model.Physics
     */
    public void moveDown() {
        coordinates.setY(getY() + Physics.NB_DEPLACEMENT_BLOCK);

    }

    /**
     * Updates the new camera's coordinates to put the character in the middle of the screen.
     * Used by default when constructing the camera with the player.
     */
    public void setToPlayer(){
            Coordinates playerCoordinates = character.getCoordinates();
            // Gets the middle point of the map at ground-level.

            int x = (int)playerCoordinates.getX() + Constants.SPRITE_DIM/2;
            int y = (int)playerCoordinates.getY() + Constants.SPRITE_DIM/2;

            // Offsets the coordinates so that the middle point of the map is the middle point
            // of the camera
            x = x - Constants.SCREEN_WIDTH/2;
            y = y - Constants.SCREEN_HEIGHT/2;

            coordinates = new Coordinates(x,y);
    }

    /**
     * Centers the camera in the center of the map.
     * Used by default when constructing the camera with a map.
     */
    public void centerMap(){
        Coordinates spawnPoint = map.getSpawnPoint();
        // Gets the middle point of the map at ground-level.
        int x = (int) (spawnPoint.getX()*Constants.BLOCK_LENGHT);
        int y = (int) (spawnPoint.getY()*Constants.BLOCK_LENGHT);
        System.out.println("Le milieu de la map = " + x + "," + y);

        // Offsets the coordinates so that the middle point of the map is the middle point
        // of the camera
        x = x - Constants.SCREEN_WIDTH/2;
        y = y - Constants.SCREEN_HEIGHT/2;

        coordinates = new Coordinates(x,y);
        System.out.println("La camera se trouve = " + x + "," + y);
    }
}
