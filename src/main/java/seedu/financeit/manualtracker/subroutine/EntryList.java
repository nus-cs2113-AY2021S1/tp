package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.utils.Printer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EntryList {
    private ArrayList<Entry> entries = new ArrayList<>();

    public EntryList() {

    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public int getEntriesSize() {
        return this.entries.size();
    }

    public Entry getEntryFromDateTime(LocalDateTime dateTime) {
        for (Entry i : this.entries) {
            if (i.dateTime.equals(dateTime)) {
                return i;
            }
        }
        return null;
    }

    public void removeEntry(LocalDateTime dateTime) {
        Entry removedEntry = this.getEntryFromDateTime(dateTime);
        if (removedEntry == null) {
            System.out.println("No entry found. Try again!");
        } else {
            this.removeEntry(removedEntry);
            System.out.println(String.format("Ledger Removed: %s", removedEntry));
        }
    }

    public void removeEntry(Entry entry) {
        this.entries.remove(entry);
    }

    public Entry getLedgerByIndex(int index) {
        return this.entries.get(index);
    }

    public void printList() {
        Printer.setTitle("List of Ledgers");
        Printer.addRow("Entry Number;Entry Type;Category;Time;Description                            ");
        if (this.getEntriesSize() == 0) {
            Printer.addRow("No entries created               ");
        } else {
            for (int i = 0; i < this.getEntriesSize(); i++) {
                Printer.addRow(String.format("%s;%s", i + 1, this.entries.get(i)));
            }
        }
        Printer.printList();
    }
}
