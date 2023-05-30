package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.InventoryFullException;
import gameengine.inventory.model.Item;
import gameengine.inventory.model.NotEmptyPlaceException;

import javax.swing.*;
import java.awt.*;

public class InventoryViewTest {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Inventory frame testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        // Create the inventory panel
        InventoryBar inventoryBar = new InventoryBar();

        Inventory inventoryExample = new Inventory(10);

        ImageIcon coeur = new ImageIcon("coeur.png");

        Item firstItem = new Item("Coeur", new JLabel(coeur));
        try {
            inventoryExample.add(inventoryExample.getFirstEmptyPlace(), firstItem);
        } catch (NotEmptyPlaceException | InventoryFullException e) {
            throw new RuntimeException(e);
        }

        inventoryBar.displayInventory(inventoryExample);

        frame.add(inventoryBar);


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryViewTest::createAndShowGUI);
    }
}
