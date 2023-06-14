package gameengine.inventory.view;

import gameengine.inventory.controller.InventoryKeyController;
import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.Item;
import gameengine.inventory.model.NotEmptyPlaceException;

import javax.swing.*;
import java.awt.*;

/**
 * This class aim to test the view of items name when the mouse is over
 *
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryNameDisplayingTest {
    private static final int INVENTORY_SIZE = 40;

    /**
     * A test to display the inventory in a frame
     */
    public static void createAndShowGUI() {
        // Creation on the frame :
        JFrame frame = new JFrame("Inventory item name frame testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new FlowLayout());

        // Create the ItemsView
        ItemsView texturePack = new ItemsView();



        // Creation of the inventory himself (model)
        Inventory inventoryExample = new Inventory(INVENTORY_SIZE);

        // Creation of icons for items
        ImageIcon potionsIcon = new ImageIcon("test/gameassets/item/potions.png");
        ImageIcon crabIcon = new ImageIcon("test/gameassets/item/crab.png");
        ImageIcon fishingRodIcon = new ImageIcon("test/gameassets/item/fishing-rod.png");
        ImageIcon troutIcon = new ImageIcon("test/gameassets/item/trout.png");

        //

        // Creation of items and adding of texture
        Item potionItem = new Item("Potions");
        texturePack.addItem("Potions", potionsIcon);

        Item crabItem = new Item("Crab");
        texturePack.addItem("Crab", crabIcon);

        Item fishRodItem = new Item("Fishing");
        texturePack.addItem("Fishing", fishingRodIcon);

        Item troutItem = new Item("Trout");
        texturePack.addItem("Trout", troutIcon);

        Item magicWand = new Item("Magic Wand");

        Item[] offerItem = {potionItem, crabItem, fishRodItem, troutItem, magicWand};

        // Filling the inventory randomly
        for (int i = 0; i < offerItem.length ; i ++) {
            try {
                inventoryExample.add(i, offerItem[i]);

            } catch (NotEmptyPlaceException e) { // If the same place is choose twice
                System.err.println("That not should append" + e.getMessage());
            }
        }

        // Create the inventory menu panel
        InventoryMenu inventoryMenu = new InventoryMenu(inventoryExample);

        // Draw the inventory in the JPanel :
        inventoryMenu.displayInventory(texturePack);

        // Add the inventory to the frame
        frame.add(inventoryMenu);

        // Make the JFrame appears
        frame.setVisible(true);

        // Adding a controller to the frame to record keyTyping for open/close the inventory
        InventoryKeyController controller = new InventoryKeyController(inventoryMenu);
        frame.addKeyListener(controller);
        frame.setFocusable(true);
        frame.requestFocus();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryNameDisplayingTest::createAndShowGUI);
    }
}

