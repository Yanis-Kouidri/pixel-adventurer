package gameengine.inventory.controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * To show item name (In progress)
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryMouseController extends MouseAdapter {
    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel source = (JLabel) e.getSource();

    }
}
