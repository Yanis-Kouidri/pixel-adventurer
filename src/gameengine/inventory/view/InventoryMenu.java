package gameengine.inventory.view;

import gameengine.inventory.model.Inventory;

import java.awt.*;

/**
 * The inventory menu is a panel that shows the whole inventory with icon for items
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryMenu extends InventoryPanel {

    /**
     * This constant defines the number of squares in the inventory menu
     */
    public final static int NB_OF_ITEMS_DISPLAY_IN_MENU = 40;

    /**
     * The number of rows for the inventory bar
     */
    public final static int NB_OF_ROWS = 4;

    /**
     * The number of columns defines by the number of rows and the nb of items to display
     */
    public final static int NB_OF_COLS = NB_OF_ITEMS_DISPLAY_IN_MENU / NB_OF_ROWS;


    /**
     * Construction of the InventoryMenu defines by nb of rows and columns
     */
    public InventoryMenu(Inventory inventoryToDisplay, ItemsView texturePack) {
        super(inventoryToDisplay, NB_OF_ITEMS_DISPLAY_IN_MENU, texturePack);
        this.setLayout(new GridLayout(NB_OF_ROWS,
                NB_OF_COLS ));
        this.setVisible(false); // By default, the inventory is hide.

    }


}
