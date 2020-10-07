package seedu.financeit.manualtracker;

import seedu.financeit.utils.Printer;

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

    public Ledger getLedgerFromDate(LocalDateTime dateTime) {
        for (Ledger i : this.ledgers) {
            if (i.dateTime.equals(dateTime)) {
                return i;
            }
        }
        return null;
    }

    public void removeLedger(LocalDateTime dateTime) {
        Ledger removedLedger = getLedgerFromDate(dateTime);
        if (removedLedger == null) {
            System.out.println("No ledger found. Try again!");
        } else {
            this.removeLedger(removedLedger);
            System.out.println(String.format("Ledger Removed: %s", removedLedger));
        }
    }

    public void removeLedger(Ledger ledger) {
        this.ledgers.remove(ledger);
    }

    public Ledger getLedgerByIndex(int index) {
        return this.ledgers.get(index);
    }

    public void printList() {
        Printer.setTitle("List of Ledgers");
        Printer.addRow("Ledger Number;Ledger Date");
        if (this.getLedgersSize() == 0) {
            Printer.addRow("No ledgers created               ");
        } else {
            for (int i = 0; i < this.getLedgersSize(); i++) {
                Printer.addRow(String.format("%s;%s", i + 1, this.ledgers.get(i)));
            }
        }
        Printer.printList();
    }
}
