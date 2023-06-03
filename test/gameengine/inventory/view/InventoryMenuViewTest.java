package gameengine.inventory.view;

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
        JFrame frame = new JFrame("Inventory frame testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new FlowLayout());

        // Create the inventory menu panel
        InventoryMenu inventoryMenu = new InventoryMenu();

        Inventory inventoryExample = new Inventory(INVENTORY_SIZE);

        ImageIcon potionsIcon = new ImageIcon("test/gameassets/item/potions.png");
        ImageIcon crabIcon = new ImageIcon("test/gameassets/item/crab.png");
        ImageIcon fishingRodIcon = new ImageIcon("test/gameassets/item/fishing-rod.png");
        ImageIcon troutIcon = new ImageIcon("test/gameassets/item/trout.png");


        Item potionItem = new Item("Potions", new JLabel(potionsIcon));
        Item crabItem = new Item("Crab", new JLabel(crabIcon));
        Item fishRodItem = new Item("Fishing", new JLabel(fishingRodIcon));
        Item troutItem = new Item("Trout", new JLabel(troutIcon));

        Item[] offerItem = {potionItem, crabItem, fishRodItem, troutItem};

        for (int i = 0; i < NB_OF_ITEM_TO_ADD ; i ++) {
            try {
                int itemToAdd = randomGenerator.nextInt(offerItem.length + 1);
                if (itemToAdd < 4) {
                    inventoryExample.add(i, offerItem[itemToAdd]);
                }
            } catch (NotEmptyPlaceException e) { // If the same place is choose twice
                System.out.println("That's ok, this exception may append in this test" + e.getMessage());
            }

        }

        inventoryMenu.displayInventory(inventoryExample);

        frame.add(inventoryMenu);


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryMenuViewTest::createAndShowGUI);
    }
}

