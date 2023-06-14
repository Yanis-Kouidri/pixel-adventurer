package gameengine.inventory.model;

/**
 * Exception throws when you try to get an empty place but the inventory is full.
 * @author Yanis Kouidri
 * @version 0.1
 */
public class InventoryFullException extends Exception {

    public InventoryFullException() {
        super();
    }

}
