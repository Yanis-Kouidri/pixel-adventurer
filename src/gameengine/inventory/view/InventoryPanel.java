package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.Item;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * A generic class for Inventory displaying
 * @author Yanis Kouidri
 * @version 0.1
 */
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

    /**
     * This method add to the Panel all the inventory items and put empty square when there is no item
     * @param inventoryToDisplay The inventory that you want to display (to draw)
     * @param nbOfItem The number of items to display
     */
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

            // Defining the size of each place in the inventory menu
            itemSpaceSprite.setPreferredSize(new Dimension(INVENTORY_SQUARE_SIZE,
                    INVENTORY_SQUARE_SIZE));

            // Setting a border to each square for a better visual
            itemSpaceSprite.setBorder(border);

            // Adding newly creating item or empty
            this.add(itemSpaceSprite);

            revalidate();
            repaint();
        }
    }

    /**
     * A method to hide/show the inventory panel
     */
    public void toggleInventoryVisibility() {
        boolean isVisible = this.isVisible();
        this.setVisible(!isVisible);
    }



}