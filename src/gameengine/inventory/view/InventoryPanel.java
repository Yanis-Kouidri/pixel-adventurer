package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.Item;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InventoryPanel extends JPanel {

    /**
     * The size, in pixel of a square in the inventory bar
     */
    private final static int INVENTORY_SQUARE_SIZE = 50;

    /**
     * The thickness of the square's border
     */
    private final static int SQUARE_BORDER_THICKNESS = 2;


    public InventoryPanel() {
        super();
    }

    protected void displayInventory(Inventory inventoryToDisplay, int nbOfItem) {
        this.removeAll(); // Clear the inventory bar before adding new items sprite

        // Creation of border
        Border border = BorderFactory.createLineBorder(Color.BLACK,
                SQUARE_BORDER_THICKNESS);


        for (int i = 0; i < nbOfItem; i++) {
            // Get the i th item
            Item currentItem = inventoryToDisplay.getItemByIndex(i);
            JLabel itemSpaceSprite;

            if (currentItem != null) { // If there is an item at i th position, display it :

                itemSpaceSprite = currentItem.getSprite();

            } else { // Adding an empty case.

                itemSpaceSprite = new JLabel();
                itemSpaceSprite.setBackground(Color.WHITE);
                itemSpaceSprite.setOpaque(true);

            }

            itemSpaceSprite.setPreferredSize(new Dimension(INVENTORY_SQUARE_SIZE,
                    INVENTORY_SQUARE_SIZE));
            itemSpaceSprite.setBorder(border);

            // Adding newly creating item or empty
            this.add(itemSpaceSprite);

            revalidate();
            repaint();
        }
    }



}
