package seedu.financeit.common;

import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class ItemList extends ParamHandler {
    protected ArrayList<Item> itemQueue = new ArrayList<>();
    protected ArrayList<Item> items = new ArrayList<>();
    protected int indexToModify;

    public ItemList() {
    }

    public void addItem(Item item) throws DuplicateInputException {
        //checkDuplicates(item);
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

    public void setItemQueue(CommandPacket packet)
        throws InsufficientParamsException, ItemNotFoundException, ConflictingItemReference {
        handleParams(packet);
    }

    public Item getItemQueue() {
        Item output =  this.itemQueue.remove(0);
        itemQueue.clear();
        return output;
    }

    public int getItemsSize() {
        return this.items.size();
    }

    /**
     * Provides a reference to the specified item at the index. Requires index to have been
     * parsed from user-input, else it will attempt to access index null or
     * previously stored index, causing bugs
     *
     * @throws IndexOutOfBoundsException - If no index has been parsed yet and it attempts to access index null
     */
    public Item getItemAtIndex() {
        return this.items.get(indexToModify);
    }

    public Item getItemAtIndex(int index) {
        return this.items.get(index);
    }

    public void checkDuplicates(Item item) throws DuplicateInputException {
        for (Item i : items) {
            if (i.equals(item)) {
                throw new DuplicateInputException(item.getName());
            }
        }
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * Removes the specified item at the index. Requires index to have been
     * parsed from user-input, else it will attempt to access index null or
     * previously stored index, causing bugs
     */
    public void removeItemAtIndex() {
        this.items.remove(indexToModify);
    }

    //Manually specify input in the code, if necessary
    public void removeItemAtIndex(int index) {
        this.items.remove(index);
    }

    public abstract void printList(String... itemName);
}
