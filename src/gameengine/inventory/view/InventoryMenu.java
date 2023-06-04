package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;

import java.awt.*;

public class InventoryMenu extends InventoryPanel {

    /**
     * This constant defines the number of squares in the inventory menu
     */
    private final static int NB_OF_ITEMS_DISPLAY_IN_MENU = 40;

    /**
     * The number of rows for the inventory bar (One because it's a bar)
     */
    private final static int NB_OF_ROWS = 4;
    private final static int NB_OF_COLS = NB_OF_ITEMS_DISPLAY_IN_MENU / NB_OF_ROWS;


    public InventoryMenu() {
        super();
        this.setLayout(new GridLayout(NB_OF_ROWS,
                NB_OF_COLS ));
        this.setVisible(false); // By default, the inventory is hide.

    }

    public void displayInventory(Inventory inventoryToDisplay) {
        super.displayInventory(inventoryToDisplay, NB_OF_ITEMS_DISPLAY_IN_MENU);
    }
}
