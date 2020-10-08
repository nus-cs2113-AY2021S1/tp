package seedu.financeit.manualtracker;

import seedu.financeit.common.exceptions.ObjectNotFoundException;
import seedu.financeit.ui.TablePrinter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class LedgerList {
    private ArrayList<Ledger> ledgers = new ArrayList<>();

    public LedgerList() {

    }

    public void addLedger(Ledger ledger) {
        this.ledgers.add(ledger);
    }

    public int getLedgersSize() {
        return this.ledgers.size();
    }

    public Ledger getLedgerFromIndex(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= this.getLedgersSize()) {
            throw new IndexOutOfBoundsException(String.format("%s [%d]", "Ledger", index));
        }
        return this.ledgers.get(index);
    }

    public Ledger getLedgerFromDate(LocalDateTime dateTime) throws ObjectNotFoundException {
        for (Ledger i : this.ledgers) {
            if (i.dateTime.equals(dateTime)) {
                return i;
            }
        }
        throw new ObjectNotFoundException(String.format("%s [%s]", "Ledger", dateTime));
    }

    public void removeLedger(LocalDateTime dateTime) throws ObjectNotFoundException {
        Ledger removedLedger = getLedgerFromDate(dateTime);
        this.removeLedger(removedLedger);
        System.out.println(String.format("Ledger Removed: %s", removedLedger));
    }

    public void removeLedger(int index) throws IndexOutOfBoundsException {
        Ledger removedLedger = getLedgerFromIndex(index);
        this.removeLedger(removedLedger);
        System.out.println(String.format("Ledger Removed: %s", removedLedger));
    }

    public void removeLedger(Ledger ledger) {
        this.ledgers.remove(ledger);
    }

    public Ledger getLedgerByIndex(int index) {
        return this.ledgers.get(index);
    }

    public void printList() {
        TablePrinter.setTitle("List of Ledgers");
        TablePrinter.addRow("Ledger Number;Ledger Date");
        if (this.getLedgersSize() == 0) {
            TablePrinter.addRow("No ledgers created;               ");
        } else {
            for (int i = 0; i < this.getLedgersSize(); i++) {
                TablePrinter.addRow(String.format("%s;%s", i + 1, this.ledgers.get(i)));
            }
        }
        TablePrinter.printList();
    }
}
