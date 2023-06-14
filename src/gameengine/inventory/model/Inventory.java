package gameengine.inventory.model;

/**
 * This class defines the player inventory
 * @author Yanis Kouidri
 * @version 0.1
 */

public class Inventory {

    // Attributes :

    /**
     * An array of Items that represent the player inventory
     */
    private final Item[] content;

    /**
     * The fix number of spots in an inventory
     */
    private final int numberOfPlace;

    private InventoryObserver observer;

    // Constructor :

    /**
     * @param numberOfPlace The number of available places in the inventory
     */
    public Inventory(int numberOfPlace) {
        if (numberOfPlace <= 0) {
            throw new IllegalArgumentException("Inventory number of place must be positive");
        }
        this.content = new Item[numberOfPlace];
        this.numberOfPlace = numberOfPlace;
        this.observer = null;
    }

    // Methods :

    /**
     * @return The total number of places in the inventory
     */
    public int getNumberOfPlace() {
        return numberOfPlace;
    }

    /**
     * @return The number of object presents in the inventory
     */
    public int getNumberOfItems() {
        int nbOfItems = 0;
        for (Item oneItem: this.content ) {
            if (oneItem != null) {
                nbOfItems++;
            }
        }
        return nbOfItems;
    }

    /**
     * Add an observer for an inventory
     * @param observer observer to add
     */
    public void setObserver(InventoryObserver observer) {
        this.observer = observer;
    }

    /**
     * Notify the observer that the inventory is updated
     */
    private void notifyObservers() {
        if (observer != null) { // If an observer is set, notify its
            observer.onItemAdded();
        }
    }

    /**
     * @return True, if all the inventory places are occupied, if not, return false
     */
    public boolean isFull() {
        return getNumberOfItems() == this.numberOfPlace;
    }

    /**
     * @return True, there is no item in the inventory, false if not.
     */
    public boolean isEmpty() {
        return getNumberOfItems() == 0;
    }


    /**
     * Remove an item from inventory
     * @param indexPlace The place of the inventory that you want to remove the item
     * @throws NothingToRemoveException Throws if the place is empty
     * @throws ArrayIndexOutOfBoundsException Throws if the indexPlace are irrelevant
     */
    public void remove(int indexPlace) throws NothingToRemoveException, ArrayIndexOutOfBoundsException {
        //Checking if indexPlace get in parameter is relevant
        relevantIndex(indexPlace, this.numberOfPlace);

        // Checking if this indexPlace is no already empty
        if (this.content[indexPlace] == null) {
            throw new NothingToRemoveException("This inventory space is already empty");
        }
        // Removing item
        this.content[indexPlace] = null;
        notifyObservers();
    }

    /**
     * To get the first empty place of the inventory
     * Useful if you don't know how to place an item
     * @return The index of the first empty place in the inventory
     * @throws InventoryFullException Throws if the inventory is full
     */
    public int getFirstEmptyPlace() throws InventoryFullException {
        // If the inventory is full, there is no empty place
        if (this.isFull()) {
            throw new InventoryFullException();
        }

        int emptyPlaceNumber = 0;
        int counter = 0;

        // Look for the first null value
        for (Item oneItem: this.content) {
            if (oneItem == null){
                emptyPlaceNumber = counter;
                break; // I found an empty place
            }
            counter++;
        }
        return emptyPlaceNumber;
    }

    /**
     * Add an item to the inventory by specifying an index
     * @param indexPlace The number of the place to insert the new item
     * @param newItem The item to add to the inventory
     * @throws ArrayIndexOutOfBoundsException Throws if the indexPlace is irrelevant
     * @throws NotEmptyPlaceException Throws if the indexPlace specify is not empty
     * (there is already an item at this place).
     */
    public void add(int indexPlace, Item newItem) throws ArrayIndexOutOfBoundsException,
            NotEmptyPlaceException {
        //Checking if indexPlace get in parameter is relevant
        relevantIndex(indexPlace, this.numberOfPlace);

        // Checking if this indexPlace is empty
        if (this.content[indexPlace] != null) {
            throw new NotEmptyPlaceException("The inventory space number " + indexPlace
                    + " is already taken by an item");
        }
        content[indexPlace] = new Item(newItem.getName(), newItem.getDescription());
        notifyObservers();
    }

    /**
     * Add an item at the first empty place
     * @param newItem The item to add to the inventory
     * @throws ArrayIndexOutOfBoundsException Throws if the indexPlace is irrelevant
     * @throws InventoryFullException The inventory is full
     */
    public void add(Item newItem) throws ArrayIndexOutOfBoundsException,
            InventoryFullException {
        try {
            add(this.getFirstEmptyPlace(), newItem);
        } catch (NotEmptyPlaceException e) {
            System.err.println("That should not happened");
        }
    }

    /**
     * To get an item of the inventory by his index (his place in the inventory)
     * @param indexPlace The number of the place that you want to take his item
     * @return A copy of the item wanted or null if it's empty
     * @throws ArrayIndexOutOfBoundsException Exception throw if you specify an indexPlace grater than inventory length
     */
    public Item getItemByIndex(int indexPlace) throws ArrayIndexOutOfBoundsException {
        // Checking if indexPlace get in parameter is relevant
        relevantIndex(indexPlace, this.numberOfPlace);

        if (this.content[indexPlace] == null) {
            return null;
        } else {
            return new Item(this.content[indexPlace].getName(), this.content[indexPlace].getDescription());
        }
    }

    /**
     * A static private function to check is an index place passed in arg is relevant compare to the inventory length
     * @param indexToCheck The index that you want to check if it is relevant compares to the number of places
     * @param numberOfPlace The number of places in the inventory
     * @throws ArrayIndexOutOfBoundsException exception throws if index is irrelevant
     */
    private static void relevantIndex(int indexToCheck, int numberOfPlace) throws ArrayIndexOutOfBoundsException {
        if (indexToCheck > numberOfPlace - 1 || indexToCheck < 0) {
            throw new ArrayIndexOutOfBoundsException("The index place " + indexToCheck + " is out of inventory bound");
        }
    }
}
