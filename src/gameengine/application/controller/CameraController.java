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

    GameLayerPanel gameLayerPanel;

    public boolean rightPressed, leftPressed, upPressed, downPressed;

    public CameraController(Camera cam, GameLayerPanel glp){
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
        if (code == KeyEvent.VK_SPACE) {
            upPressed = true;
//            logger.info("Press " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = true;
//            logger.info("Press " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
//            logger.info("Press " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_S) {
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
        if (code == KeyEvent.VK_SPACE) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
    }

    /**
     * Updates the camera model based on which key was pressed by the user
     */
    public void update() {
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
