package gameengine.application.model;

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

    /**
     * Constructs a new camera based on a map and coordinates to place the initial camera position
     * @param x in pixels
     * @param y in pixels
     * @param m
     */
    Camera(int x, int y, Map m){
        coordinates = new Coordinates(x,y);
        map = m;
    }

    /**
     * Constructs a camera on a map at its middle.
     * @param m the map
     */
    public Camera(Map m){
        map = m;
        Coordinates spawnPoint = m.getSpawnPoint();
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
        coordinates.setY(getY() - Physics.NB_DEPLACEMENT_BLOCK);

    }
}
