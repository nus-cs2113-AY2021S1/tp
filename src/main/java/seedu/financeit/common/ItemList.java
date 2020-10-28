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
    protected int indexToModify = -1;

    public ItemList() {
    }

    public void addItem(Item item) {
        int index = this.getItemsSize();
        item.setIndex(index);
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

    public int findIndexOfItem(Item item) {
        return this.items.indexOf(item);
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * Provides a reference to the specified item at the zero-based index.
     * Requires index to have been parsed from user-input, else it will
     * attempt to access index -1.
     *
     * @throws IndexOutOfBoundsException If no index has been parsed yet and
     *                                   it attempts to access index -1.
     */
    public Item getItemAtIndex() throws IndexOutOfBoundsException {
        assert this.indexToModify >= 0;
        int index = this.indexToModify;
        return this.items.get(index);
    }

    //Manually specify index in the code, if necessary
    public Item getItemAtIndex(int index) throws IndexOutOfBoundsException {
        assert index < items.size();
        return this.items.get(index);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * Removes the specified item at the zero-based index. Requires index to
     * have been parsed from user-input, else it will attempt to access index -1.
     *
     * @throws IndexOutOfBoundsException If no index has been parsed yet and
     *                                   it attempts to access index -1.
     */
    public void removeItemAtIndex() throws IndexOutOfBoundsException {
        assert this.indexToModify >= 0;
        this.items.remove(this.indexToModify);
    }

    /**
     * Removes the specified item at the zero-based index.
     *
     * @throws IndexOutOfBoundsException If invalid index is passed as argument
     */
    public void removeItemAtIndex(int index) throws IndexOutOfBoundsException {
        assert index < this.items.size();
        this.items.remove(index);
    }

    /**
     * Prints all items that are in the ItemList instance.
     */
    public abstract void printList();
}
