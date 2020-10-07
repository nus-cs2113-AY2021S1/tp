package seedu.financeit.manualtracker;

import seedu.financeit.utils.Printer;

import java.util.ArrayList;

public class LedgerList {
    private ArrayList<Ledger> list = new ArrayList<>();

    public LedgerList() {

    }

    public void addLedger(Ledger ledger) {
        this.list.add(ledger);
    }

    public int getSizeOfList() {
        return this.list.size();
    }

    public void removeLedger(Ledger ledger) {
        this.list.remove(ledger);
    }

    public Ledger getLedgerByIndex(int index) {
        return this.list.get(index);
    }

    public void printList() {
        Printer.setTitle("List of Ledgers");
        Printer.addRow("Ledger Number;Ledger Date");
        for (int i = 0; i < this.getSizeOfList(); i++) {
            Printer.addRow(String.format("%s;%s", i + 1, this.list.get(i)));
        }
        Printer.printList();
    }
}
