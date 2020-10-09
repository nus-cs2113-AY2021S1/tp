package seedu.financeit.common;

import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.ObjectNotFoundException;
import seedu.financeit.manualtracker.Ledger;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class List<T extends Ledger, Entry> {

    protected ArrayList<T> items = new ArrayList<>();

    public List() {
    }

    public void addItem(T item) {
        this.items.add(item);
    }

    public int getItemsSize() {
        return this.items.size();
    }

    public T getItemFromIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.getItemsSize()) {
            throw new IndexOutOfBoundsException(String.format("%s [%d]", "Ledger", index));
        }
        return this.items.get(index);
    }

    public void checkDuplicates(LocalDateTime dateTime) throws DuplicateInputException {
        for (T i : items) {
            if (i.getDateTime().equals(dateTime)) {
                throw new DuplicateInputException(String.format("Ledger [%s]", dateTime));
            }
        }
    };

    public T getItemFromDateTime(LocalDateTime dateTime) throws ObjectNotFoundException {
        for (T i : this.items) {
            if (i.getDateTime().equals(dateTime)) {
                return i;
            }
        }
        throw new ObjectNotFoundException(String.format("%s [%s]", "Ledger", dateTime));
    }

    public void removeLedger(Ledger ledger) {
        this.items.remove(ledger);
    }

    public T getLedgerByIndex(int index) {
        return this.items.get(index);
    }

    public abstract void printList(String... itemName) ;
}
