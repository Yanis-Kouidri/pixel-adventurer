package gameengine.application.controller;

import gameengine.application.model.Camera;
import gameengine.application.view.GameLayerPanel;
import gameengine.characters.controller.CharacterController;
import gameengine.map.controller.MapController;
import gameengine.map.model.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CameraController implements KeyListener {
    Camera camera;
    CharacterController characterController;

    GameLayerPanel gameLayerPanel;

    public boolean rightPressed, leftPressed, upPressed;

    public CameraController(Camera cam, CharacterController c, MapController m, GameLayerPanel glp){
        camera = cam;
        CharacterController characterController = c;
        gameLayerPanel = glp;
    }
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
//            logger.info("Release " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = false;
//            logger.info("Release " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
//            logger.info("Release " + e.getKeyChar());
        }
    }


    public void update() {
        if (upPressed) {
            camera.moveUp();
            System.out.println("Moving up !");
            gameLayerPanel.getMapPanel().moveMap(0, (int) camera.getY());
//            logger.info(mainCharacter.toString());
        }
        if (leftPressed) {
            camera.moveLeft();
            System.out.println("Moving left !");
            gameLayerPanel.getMapPanel().moveMap((int) camera.getX(), 0);
//            logger.info(mainCharacter.toString());
        }
        if (rightPressed) {
            camera.moveRight();
            gameLayerPanel.getMapPanel().moveMap((int) camera.getX(), 0);
//            logger.info(mainCharacter.toString());
        }
    }
}
