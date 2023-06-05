package gameengine.application.model;

import gameengine.application.view.GameLayerPanel;
import gameengine.map.model.Map;
import gameengine.map.view.MapPanel;
import gameengine.utils.model.Constants;
import gameengine.utils.model.Coordinates;
import gameengine.utils.model.Physics;

public class Camera {
    Coordinates coordinates;
    float speed;
    Map map;

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
        int x = Constants.MAP_LENGTH/2;
        int y = m.getGroundLevel(x/Constants.BLOCK_LENGHT - 1);
        //offsetting the coordinates
        x = x - Constants.SCREEN_WIDTH/2;
        y = y - Constants.SCREEN_HEIGHT/2;
        coordinates = new Coordinates(x,y);
        System.out.println("Le milieu de la map = " + x + "," + y);
    }

    public int getX(){
        return (int) coordinates.getX();
    }
    public int getY(){
        return (int) coordinates.getY();
    }

    public void moveUp() {
        coordinates.setY(getY() - Physics.NB_DEPLACEMENT_BLOCK);
    }

    public void moveRight() {
        coordinates.setX(getX() + Physics.NB_DEPLACEMENT_BLOCK);
    }

    public void moveLeft() {
        coordinates.setX(getX() - Physics.NB_DEPLACEMENT_BLOCK);

    }
}
