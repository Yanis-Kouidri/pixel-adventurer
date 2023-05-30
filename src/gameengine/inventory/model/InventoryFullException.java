package gameengine.inventory.model;

/**
 * Exception throws when you try to get an empty place but the inventory is full.
 */
public class InventoryFullException extends Exception {

    public InventoryFullException() {
        super();
    }

    public InventoryFullException(String message) {
        super(message);
    }
}
