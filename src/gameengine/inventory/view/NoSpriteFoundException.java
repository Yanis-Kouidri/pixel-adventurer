package gameengine.inventory.view;

/**
 * This exception occurs when you try to get a sprite of a non-existing item.
 *
 */
public class NoSpriteFoundException extends Exception {
    public NoSpriteFoundException() {
        super();
    }

    public NoSpriteFoundException(String message) {
        super(message);
    }

}
