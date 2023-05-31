package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.Item;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InventoryBar extends InventoryPanel {

    private final static int NUMBER_OF_ITEMS_DISPLAY_IN_BAR = 10;

    public InventoryBar() {
        super();
        //this.setSize(100,40);
        this.setLayout(new GridLayout(1,NUMBER_OF_ITEMS_DISPLAY_IN_BAR));

    }

    public void displayInventory(Inventory inventoryToDisplay) {
        this.removeAll(); // Clear the inventory bar before adding new items sprite

        // Creation of border
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);


        for (int i = 0; i < NUMBER_OF_ITEMS_DISPLAY_IN_BAR; i++) {
            // Get the i th item
            Item currentItem = inventoryToDisplay.getItemByIndex(i);
            JLabel itemSpaceSprite;

            if (currentItem != null) { // If there is an item at i th position, display it :

                itemSpaceSprite = currentItem.getSprite();
                itemSpaceSprite.setPreferredSize(new Dimension(40,40));

            } else { // Adding an empty case.

                itemSpaceSprite = new JLabel();
                itemSpaceSprite.setBackground(Color.GRAY);

                itemSpaceSprite.setPreferredSize(new Dimension(40,40));
                itemSpaceSprite.setOpaque(true);

            }
            itemSpaceSprite.setBorder(border);
            this.add(itemSpaceSprite);

            revalidate();
            repaint();
        }
    }
}
