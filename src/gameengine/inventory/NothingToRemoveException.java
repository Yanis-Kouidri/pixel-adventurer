package gameengine.inventory;

/**
 * Exception throws when you try to remove an item from the inventory but the place is already empty.
 */
public class NothingToRemoveException extends Exception {
    public NothingToRemoveException() {
        super();
    }

    public NothingToRemoveException(String message) {
        super(message);
    }
}
