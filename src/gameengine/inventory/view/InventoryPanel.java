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

    /**
     * The default texture for an Item who hasn't one
     */
    private final static ImageIcon ITEM_TEXTURE_NOT_FOUND = new ImageIcon(
            "src/gameassets/item/unknown_item.png");


    public InventoryPanel() {
        super();
    }

    /**
     * This method adds to the Panel all the inventory items
     * and puts empty square when there is no item
     * @param inventoryToDisplay The inventory that you want to display (to draw)
     * @param nbOfItem The number of items to display
     */
    protected void displayInventory(Inventory inventoryToDisplay, int nbOfItem,
                                    ItemsView itemsSprite) {
        //TODO replace displayInventory with paintComponent
        this.removeAll(); // Clear the inventory bar before adding new items sprite

        // Creation of border
        Border border = BorderFactory.createLineBorder(Color.BLACK,
                SQUARE_BORDER_THICKNESS);


        for (int i = 0; i < nbOfItem; i++) {
            // Get the i th item
            Item currentItem = inventoryToDisplay.getItemByIndex(i);
            JLabel itemSpaceSprite;
            JLabel itemSpaceName;

            if (currentItem != null) { // If there is an item at i th position, display it :

                try { // Get the sprite bound to the currentItem
                    itemSpaceSprite =  itemsSprite.getSprite(currentItem.getName());
                } catch (NoSpriteFoundException e) {
                    // If there is no sprite :
                    itemSpaceSprite = new JLabel(ITEM_TEXTURE_NOT_FOUND);
                }

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
        }
        revalidate();
        repaint();
    }

    /**
     * A method to hide/show the inventory panel
     */
    public void toggleInventoryVisibility() {
        boolean isVisible = this.isVisible();
        this.setVisible(!isVisible);
    }

}
