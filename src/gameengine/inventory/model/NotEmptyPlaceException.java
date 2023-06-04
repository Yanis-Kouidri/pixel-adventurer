package gameengine.inventory.model;

/**
 * Exception throws when you try to add an item to the inventory but the place is already taken by another item
 * @author Yanis Kouidri
 * @version 0.1
 */
public class NotEmptyPlaceException extends Exception {
    public NotEmptyPlaceException() {
        super();
    }

    public NotEmptyPlaceException(String message) {
        super(message);
    }
}
