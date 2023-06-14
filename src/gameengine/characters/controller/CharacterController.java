package gameengine.characters.controller;

import gameengine.characters.model.MainCharacter;
import gameengine.characters.model.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Logger;

public class CharacterController implements KeyListener {

    // Change type to Character (modifiy instance methode in CHaracter)

    private final Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
    private MainCharacter mainCharacter;

    public boolean rightPressed, leftPressed, upPressed;
    public CharacterController(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
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
            logger.info("Press " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = true;
            logger.info("Press " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            logger.info("Press " + e.getKeyChar());
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
            logger.info("Release " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = false;
            logger.info("Release " + e.getKeyChar());
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
            logger.info("Release " + e.getKeyChar());
        }
    }

    /**
     * update model depending to the controller
     */
    public void update() {
        if (upPressed) {
            mainCharacter.moveUp();
            logger.info(mainCharacter.toString());
        }
        if (leftPressed) {
            mainCharacter.moveLeft();
            logger.info(mainCharacter.toString());
        }
        if (rightPressed) {
            mainCharacter.moveRight();
            logger.info(mainCharacter.toString());
        }

    }
}
