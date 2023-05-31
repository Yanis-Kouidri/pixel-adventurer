package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;

import java.awt.*;

public class InventoryBar extends InventoryPanel {

    /**
     * This constant defines the number of squares in the inventory bar
     */
    private final static int NB_OF_ITEMS_DISPLAY_IN_BAR = 10;

    /**
     * The number of rows for the inventory bar (One because it's a bar)
     */
    private final static int NB_OF_ROWS = 1;


    public InventoryBar() {
        super();
        this.setLayout(new GridLayout(NB_OF_ROWS, NB_OF_ITEMS_DISPLAY_IN_BAR));

    }


    /**
     * @param inventoryToDisplay The inventory that you want to display
     */
    public void displayInventory(Inventory inventoryToDisplay) {
        super.displayInventory(inventoryToDisplay, NB_OF_ITEMS_DISPLAY_IN_BAR);
    }
}
