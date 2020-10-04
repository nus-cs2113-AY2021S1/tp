package seedu.financeit.utils;

import seedu.financeit.manualtracker.Ledger;

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
        for (int i = 0; i < this.getSizeOfList(); i++) {
            System.out.println(String.format("[%d] %s", i + 1, this.list.get(i)));
        }
    }

}
