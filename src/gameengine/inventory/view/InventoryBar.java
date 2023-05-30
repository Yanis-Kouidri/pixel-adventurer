package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.Item;

import javax.swing.*;
import java.awt.*;

public class InventoryBar extends InventoryPanel {

    private final static int NUMBER_OF_ITEMS_DISPLAY_IN_BAR = 10;

    public InventoryBar() {
        super();
        //this.setSize(100,40);
        this.setLayout(new GridLayout(1,NUMBER_OF_ITEMS_DISPLAY_IN_BAR));

    }

    public void displayInventory(Inventory inventoryToDisplay) {
        //this.removeAll(); // Clear the inventory bar before adding new items sprite

        for (int i = 0; i < NUMBER_OF_ITEMS_DISPLAY_IN_BAR; i++) {
            // Get the i th item
            Item currentItem = inventoryToDisplay.getItemByIndex(i);

            if (currentItem != null) { // If there is an item at i th position, display it :

                JLabel itemSprite = currentItem.getSprite();
                //itemSprite.setPreferredSize(new Dimension(40,40));
                System.out.println(currentItem.getName());
                //FIXME We don't see the png

                itemSprite.setOpaque(true);
                //itemSprite.setVisible(true);

                this.add(itemSprite);

            } else {

                JLabel emptyPlace = new JLabel();
                emptyPlace.setBackground(Color.GRAY);
                emptyPlace.setPreferredSize(new Dimension(40,40));
                emptyPlace.setOpaque(true);
                this.add(emptyPlace); // Adding an empty case.
            }

            revalidate();
            repaint();
        }
    }
}
