package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;

import java.awt.*;

/**
 * The inventory bar represents a short view of the 10 first items in the inventory
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryBar extends InventoryPanel {

    /**
     * This constant defines the number of squares in the inventory bar
     */
    public final static int NB_OF_ITEMS_DISPLAY_IN_BAR = 10;

    /**
     * The number of rows for the inventory bar (One because it's a bar)
     */
    public final static int NB_OF_ROWS = 1;


    public InventoryBar(Inventory inventoryToDisplay, ItemsView texturePack) {
        super(inventoryToDisplay, NB_OF_ITEMS_DISPLAY_IN_BAR, texturePack);
        this.setLayout(new GridLayout(NB_OF_ROWS, NB_OF_ITEMS_DISPLAY_IN_BAR));
    }

}
