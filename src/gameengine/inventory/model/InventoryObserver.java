package gameengine.inventory.model;

/**
 * An observer to update the view when an item is added to the inventory
 * @author Yanis Kouidri
 * @version 0.1
 */
public interface InventoryObserver {
    /**
     * Action to perform when an item is added to the inventory
     */
    void onItemAdded();

}
