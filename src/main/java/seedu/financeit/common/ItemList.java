package seedu.financeit.common;

import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.ui.UiManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class ItemList<T extends Item> {

    protected ArrayList<T> items = new ArrayList<>();

    public ItemList() {
    }

    public void addItem(T item) throws DuplicateInputException {
        checkDuplicates(item);
        item.setIndex(this.getItemsSize());
        this.items.add(item);
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                String.format("%s is added to the list!", item.getName()));
    }

    public int getItemsSize() {
        return this.items.size();
    }

    public T getItemFromIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.getItemsSize()) {
            throw new IndexOutOfBoundsException();
        }
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

    public T getLedgerByIndex(int index) {
        return this.items.get(index);
    }

    public abstract void printList(String... itemName);
}
