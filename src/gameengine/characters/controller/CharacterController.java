package gameengine.characters.controller;

import gameengine.characters.model.MainCharacter;
import gameengine.characters.model.Entity;
import gameengine.characters.model.EntityJumpStateType;

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
        }
        if (code == KeyEvent.VK_Q) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
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
    }

    /**
     * update model depending to the controller
     */
    public void update() {
        if (upPressed) {
        	if(mainCharacter.getJumpingState() != EntityJumpStateType.GOING_DOWN) {
        		//if the main character is on the ground or he is actually jumping, then he can continue going up
        		mainCharacter.jump();       		
        	} else {
        		//if he is already going down, then he can't jump again until he touch the ground
        		mainCharacter.fallingCheck(); 
        	}
        } else {
        	//if the space bar is not pressed, then reset the speed only one (in case the main character wasn't touching the ground), and fall
        	mainCharacter.resetGravitySpeedOnce();
        	mainCharacter.fallingCheck(); 
        }
        if (leftPressed) { 
            mainCharacter.moveLeft();
        }
        if (rightPressed) {
            mainCharacter.moveRight();
        }

    }
}
