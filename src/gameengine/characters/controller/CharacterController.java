package gameengine.characters.controller;

import gameengine.characters.model.Character;
import gameengine.characters.model.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterController implements KeyListener {

    // Change type to Character (modifiy instance methode in CHaracter)
    private Entity mainCharacter;

    public boolean rightPressed, leftPressed, upPressed;
    public CharacterController(Entity mainCharacter) {
        this.mainCharacter = mainCharacter;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

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

    public void update() {
        if (upPressed) {
            mainCharacter.moveUp();
        }
        if (leftPressed) {
            mainCharacter.moveLeft();
        }
        if (rightPressed) {
            mainCharacter.moveRight();
        }
    }
}
