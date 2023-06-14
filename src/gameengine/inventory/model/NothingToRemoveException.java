package gameengine.inventory.model;

/**
 * Exception throws when you try to remove an item from the inventory but the place is already empty.
 * @author Yanis Kouidri
 * @version 0.1
 */
public class NothingToRemoveException extends Exception {
    public NothingToRemoveException() {
        super();
    }

    public NothingToRemoveException(String message) {
        super(message);
    }
}
