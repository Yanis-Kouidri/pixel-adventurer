package gameengine.inventory;

/**
 * Exception throws when you try to add an item to the inventory but the place is already taken by another item
 */
public class NotEmptyPlaceException extends Exception {
    public NotEmptyPlaceException() {
        super();
    }

    public NotEmptyPlaceException(String message) {
        super(message);
    }
}