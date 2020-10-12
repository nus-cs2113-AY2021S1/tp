package seedu.financeit.common;

import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;

import java.util.ArrayList;

/**
 * List class that stores Item instances as its elements.
 * Extends ParamHandler so it is able to handle params from a CommandPacket.
 */
public abstract class ItemList extends ParamHandler {
    protected Item currItem;
    protected ArrayList<Item> items = new ArrayList<>();

    public ItemList() {
    }

    public void addItem(Item item) {
        item.setIndex(this.getItemsSize());
        this.items.add(item);
    }

    public void setCurrItemFromPacket(CommandPacket packet)
        throws InsufficientParamsException, ItemNotFoundException {
        handleParams(packet);
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

    public Item getItemFromIndex(int index) {
        return this.items.get(index);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * Prints all items that are in the ItemList instance.
     */
    public abstract void printList();
}
