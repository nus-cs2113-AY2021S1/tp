package seedu.financeit.common;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * List class that stores Item instances as its elements.
 * Extends ParamHandler so it is able to handle params from a CommandPacket.
 */
public abstract class ItemList extends ParamHandler {
    protected Item currItem;
    protected ArrayList<Item> items = new ArrayList<>();
    protected int indexToModify;

    public ItemList() {
    }

    public void addItem(Item item) {
        item.setIndex(this.getItemsSize());
        this.items.add(item);
    }

    /**
     * Add the given item and sort the list according to the given comparator.
     *
     * @param item - Item to be added
     * @param comparator - Comparator object to determine sort order
     */
    public void addItemAndSort(Item item, Comparator comparator) {
        this.items.add(item);
        items.sort(comparator);
    }

    public Item getCurrItem() {
        return this.currItem;
    }

    public void setCurrItem(Item item) {
        this.currItem = item;
    }

    public Item popCurrItem() {
        Item tempItem = this.currItem;
        this.currItem = null;
        return tempItem;
    }

    public int getItemsSize() {
        return this.items.size();
    }

    /**
     * Provides a reference to the specified item at the zero-based index.
     * Requires index to have been parsed from user-input, else it will
     * attempt to access index null or previously stored index, causing bugs
     *
     * @throws IndexOutOfBoundsException If no index has been parsed yet and
     *                                   it attempts to access index null
     */
    public Item getItemAtIndex() throws IndexOutOfBoundsException {
        return this.items.get(indexToModify);
    }

    //Manually specify index in the code, if necessary
    public Item getItemAtIndex(int index) throws IndexOutOfBoundsException {
        return this.items.get(index);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * Removes the specified item at the index. Requires index to have been
     * parsed from user-input, else it will attempt to access index null or
     * previously stored index, causing bugs
     *
     * @throws IndexOutOfBoundsException If no index has been parsed yet and
     *                                   it attempts to access index null
     */
    public void removeItemAtIndex() throws IndexOutOfBoundsException {
        this.items.remove(indexToModify);
    }

    //Manually specify index in the code, if necessary
    public void removeItemAtIndex(int index) throws IndexOutOfBoundsException {
        this.items.remove(index);
    }

    /**
     * Prints all items that are in the ItemList instance.
     */
    public abstract void printList();
}
