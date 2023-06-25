package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;
import gameengine.inventory.model.InventoryObserver;
import gameengine.inventory.model.Item;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * A generic class for Inventory displaying
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryPanel extends JPanel implements InventoryObserver {

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

    private final Inventory inventoryToDisplay;
    private final int nbOfItem;
    private final ItemsView texturePack;

    public InventoryPanel(Inventory inventoryToDisplay, int nbOfItem, ItemsView texturePack) {
        super();
        this.inventoryToDisplay = inventoryToDisplay;
        this.nbOfItem = nbOfItem;
        this.texturePack = texturePack;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

    }

    /**
     * This method adds to the Panel all the inventory items
     * and puts empty square when there is no item.
     */
    public void displayInventory() {
        this.removeAll(); // Clear the inventory bar before adding new items sprite

        // Creation of the border
        Border border = BorderFactory.createLineBorder(Color.BLACK,
                SQUARE_BORDER_THICKNESS);


        for (int i = 0; i < nbOfItem; i++) {
            // Get the i th item
            Item currentItem = inventoryToDisplay.getItemByIndex(i);
            JLabel itemSpaceSprite;

            if (currentItem != null) { // If there is an item at the i th position,
                // display it :

                try { // Get the sprite bound to the currentItem
                    itemSpaceSprite =  texturePack.getSprite(currentItem.getName());
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

    @Override
    public void onItemAdded() {
        displayInventory();
    }
}
