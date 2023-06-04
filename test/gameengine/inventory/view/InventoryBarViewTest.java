package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.Item;
import gameengine.inventory.model.NotEmptyPlaceException;

import javax.swing.*;
import java.awt.*;

/**
 * Test class to test the view of the inventory bar in a test Jframe
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryBarViewTest {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Inventory frame testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        // Create the inventory panel
        InventoryBar inventoryBar = new InventoryBar();

        Inventory inventoryExample = new Inventory(10);

        ImageIcon potionsIcon = new ImageIcon("test/gameassets/item/potions.png");
        ImageIcon crabIcon = new ImageIcon("test/gameassets/item/crab.png");
        ImageIcon fishingRodIcon = new ImageIcon("test/gameassets/item/fishing-rod.png");
        ImageIcon troutIcon = new ImageIcon("test/gameassets/item/trout.png");


        Item potionItem = new Item("Potions", new JLabel(potionsIcon));
        Item crabItem = new Item("Crab", new JLabel(crabIcon));
        Item fishRodItem = new Item("Fishing", new JLabel(fishingRodIcon));
        Item troutItem = new Item("Trout", new JLabel(troutIcon));

        try {
            inventoryExample.add(0, potionItem);
            inventoryExample.add(2, crabItem);
            inventoryExample.add(4, fishRodItem);
            inventoryExample.add(6, troutItem);


        } catch (NotEmptyPlaceException e) {
            throw new RuntimeException(e);
        }

        inventoryBar.displayInventory(inventoryExample);

        frame.add(inventoryBar);


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryBarViewTest::createAndShowGUI);
    }
}
