package seedu.financeit.common;

import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;

import java.util.ArrayList;

public abstract class ItemList extends ParamHandler {
    protected ArrayList<Item> itemQueue = new ArrayList<>();
    protected ArrayList<Item> items = new ArrayList<>();

    public ItemList() {
    }

    public void addItem(Item item) throws DuplicateInputException {
        checkDuplicates(item);
        item.setIndex(this.getItemsSize());
        this.items.add(item);
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

    public Item getItemFromIndex(int index) {
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

    public abstract void printList(String... itemName);
}
