package gameengine.application.controller;

import gameengine.application.model.Camera;
import gameengine.application.view.GameLayerPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Defines how the camera will be modified based on the user's input
 * @author Eric YU
 */
public class CameraController implements KeyListener {
    Camera camera; // The camera model which will be modified

    GameLayerPanel gameLayerPanel; // The panel which will be reflected by the changes.

    public boolean rightPressed, leftPressed, upPressed, downPressed; //

    public CameraController(Camera cam, GameLayerPanel glp) {
        camera = cam;
        gameLayerPanel = glp;
    }

    /**
     * NOT IMPLEMENTED
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Trigger the key pressed key and assign one action for that
     * @param e the key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
//            logger.info("Press " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
//            logger.info("Press " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
//            logger.info("Press " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
//            logger.info("Press " + e.getKeyChar());
        }

    }

    /**
     * Trigger the key released key and assign one action for that
     * @param e the key released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_K){
            camera.setFollowPlayer(!camera.getFollowPlayer());
            if (camera.getFollowPlayer()){
                System.out.println("Locking camera");
            }
            else{
                System.out.println("Unlocking camera");

            }
        }
    }

    /**
     * Updates the camera's position.
     * If the camera follows the player the update will change the camera's coordinates according to the player position.
     * If the camera is unlocked, it will be moved depending on which key was pressed by the user
     */
    public void update() {
        if (camera.getFollowPlayer()){
            camera.setToPlayer();
        }
        else{
            if (upPressed) {
                camera.moveUp();
            }
            if (leftPressed) {
                camera.moveLeft();
            }
            if (rightPressed) {
                camera.moveRight();
            }
            if (downPressed) {
                camera.moveDown();
            }
        }

    }
}
