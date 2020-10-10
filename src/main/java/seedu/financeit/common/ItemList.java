package seedu.financeit.common;

import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class ItemList<T extends Item> extends ParamHandler {
    protected ArrayList<T> itemQueue = new ArrayList<>();
    protected ArrayList<T> items = new ArrayList<>();

    public ItemList() {
    }

    public void addItem(T item) throws DuplicateInputException {
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

    public T getItemFromIndex(int index) {
        return this.items.get(index);
    }

    public void checkDuplicates(Item item) throws DuplicateInputException {
        for (T i : items) {
            if (i.equals(item)) {
                throw new DuplicateInputException(item.getName());
            }
        }
    }

    public T getItemFromDateTime(LocalDateTime dateTime) throws ItemNotFoundException {
        for (T i : this.items) {
            if (i.getDateTime().equals(dateTime)) {
                return i;
            }
        }
        throw new ItemNotFoundException();
    }

    public void removeItem(T item) {
        this.items.remove(item);
    }

    public abstract void printList(String... itemName);
}
