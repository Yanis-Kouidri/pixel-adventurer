package gameengine.inventory.controller;

import gameengine.inventory.view.InventoryPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controller for listening key input to open/close the inventory menu
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryKeyController implements KeyListener {
    private final InventoryPanel inventoryPanel;

    public InventoryKeyController(InventoryPanel inventoryPanel) {
        this.inventoryPanel = inventoryPanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // When the player type 'e'
        if (e.getKeyChar() == 'e') {
            inventoryPanel.toggleInventoryVisibility(); // Show/hide the inventory
            // System.out.println("Key typed : " + e.getKeyCode()); // To debug

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Not used here
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used here
    }
}
