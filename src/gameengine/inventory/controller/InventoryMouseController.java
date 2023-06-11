package gameengine.inventory.controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryMouseController extends MouseAdapter {
    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel source = (JLabel) e.getSource();

    }
}
