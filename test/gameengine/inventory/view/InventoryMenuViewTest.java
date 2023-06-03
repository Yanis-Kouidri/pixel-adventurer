package gameengine.inventory.view;

import gameengine.inventory.controller.InventoryKeyController;
import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.Item;
import gameengine.inventory.model.NotEmptyPlaceException;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class InventoryMenuViewTest {

    private static final int INVENTORY_SIZE = 40;

    private static final Random randomGenerator = new Random();
    private static final int NB_OF_ITEM_TO_ADD = randomGenerator.nextInt(INVENTORY_SIZE);
    public static void createAndShowGUI() {
        // Creation on the frame :
        JFrame frame = new JFrame("Inventory frame testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new FlowLayout());


        // Create the inventory menu panel
        InventoryMenu inventoryMenu = new InventoryMenu();

        // Creation of the inventory himself (model)
        Inventory inventoryExample = new Inventory(INVENTORY_SIZE);

        // Creation of icons for items
        ImageIcon potionsIcon = new ImageIcon("test/gameassets/item/potions.png");
        ImageIcon crabIcon = new ImageIcon("test/gameassets/item/crab.png");
        ImageIcon fishingRodIcon = new ImageIcon("test/gameassets/item/fishing-rod.png");
        ImageIcon troutIcon = new ImageIcon("test/gameassets/item/trout.png");

        // Creation of items with icons
        Item potionItem = new Item("Potions", new JLabel(potionsIcon));
        Item crabItem = new Item("Crab", new JLabel(crabIcon));
        Item fishRodItem = new Item("Fishing", new JLabel(fishingRodIcon));
        Item troutItem = new Item("Trout", new JLabel(troutIcon));

        Item[] offerItem = {potionItem, crabItem, fishRodItem, troutItem};

        // Filling the inventory randomly
        for (int i = 0; i < NB_OF_ITEM_TO_ADD ; i ++) {
            try {
                int itemToAdd = randomGenerator.nextInt(offerItem.length + 1);
                if (itemToAdd < offerItem.length) {
                    inventoryExample.add(i, offerItem[itemToAdd]);
                }
            } catch (NotEmptyPlaceException e) { // If the same place is choose twice
                System.out.println("That's ok, this exception may append in this test" + e.getMessage());
            }
        }

        // Draw the inventory in the JPanel :
        inventoryMenu.displayInventory(inventoryExample);

        // Add the inventory to the frame
        frame.add(inventoryMenu);


        frame.setVisible(true);

        // Adding a controller to the frame to record keyTyping for open/close the inventory
        InventoryKeyController controller = new InventoryKeyController(inventoryMenu);
        frame.addKeyListener(controller);
        frame.setFocusable(true);
        frame.requestFocus();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryMenuViewTest::createAndShowGUI);
    }
}

