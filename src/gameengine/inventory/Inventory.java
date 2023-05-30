package gameengine.inventory;

public class Inventory {

    // Attributes :
    private final Item[] content;
    private final int numberOfPlace;

    // Constructor :
    public Inventory(int numberOfPlace) {
        this.content = new Item[numberOfPlace];
        this.numberOfPlace = numberOfPlace;
    }

    // Methods :

    /**
     * @return The total number of places of the inventory
     */
    public int getNumberOfPlace() {
        return numberOfPlace;
    }

    /**
     * @return The number of object present in the inventory
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

    public boolean isFull() {
        return getNumberOfItems() == this.numberOfPlace;
    }

    public boolean isEmpty() {
        return getNumberOfItems() == 0;
    }

    public void remove(int indexPlace) throws NothingToRemoveException, ArrayIndexOutOfBoundsException {
        //Checking if indexPlace get in parameter is relevant
        relevantIndex(indexPlace, this.numberOfPlace);

        // Checking if this indexPlace is no already empty
        if (this.content[indexPlace] == null) {
            throw new NothingToRemoveException("This inventory space is already empty");
        }
        // Removing item
        this.content[indexPlace] = null;
    }

    public int getFirstEmptyPlace() throws InventoryFullException {
        // If inventory is full, there is no empty place
        if (this.isFull()) {
            throw new InventoryFullException();
        }

        int emptyPlaceNumber = 0;
        int counter = 0;

        for (Item oneItem: this.content) {
            if (oneItem != null){
                emptyPlaceNumber = counter;
            }
            counter++;
        }
        return emptyPlaceNumber;
    }

    public void add(int indexPlace, Item newItem) throws ArrayIndexOutOfBoundsException, NotEmptyPlaceException {
        //Checking if indexPlace get in parameter is relevant
        relevantIndex(indexPlace, this.numberOfPlace);

        // Checking if this indexPlace is empty
        if (this.content[indexPlace] != null) {
            throw new NotEmptyPlaceException("This inventory space is already taken by an item");
        }

        content[indexPlace] = new Item(newItem.getName(), newItem.getSprite());

    }

    /**
     * To get an item of the inventory by his index (his place in the inventory)
     * @param indexPlace The number of the place that you want to take his item
     * @return A copy of the item wanted or null if it's empty
     * @throws ArrayIndexOutOfBoundsException Exception throw if you specify an indexPlace grater than inventory length
     */
    public Item getItemByIndex(int indexPlace) throws ArrayIndexOutOfBoundsException {
        //Checking if indexPlace get in parameter is relevant
        relevantIndex(indexPlace, this.numberOfPlace);

        if (this.content[indexPlace] == null) {
            return null;
        } else {
            return new Item(this.content[indexPlace].getName(), this.content[indexPlace].getSprite());
        }
    }

    /**
     * A static private function to check is an index place passed in arg is relevant compare to the inventory length
     * @param indexToCheck The index that you want to check if it's relevant compare to the number of place
     * @param numberOfPlace The number of place of the inventory
     * @throws ArrayIndexOutOfBoundsException exception throws if index is irrelevant
     */
    private static void relevantIndex(int indexToCheck, int numberOfPlace) throws ArrayIndexOutOfBoundsException {
        if (indexToCheck > numberOfPlace - 1) {
            throw new ArrayIndexOutOfBoundsException("This index place is out of inventory bound");
        }

    }

}
