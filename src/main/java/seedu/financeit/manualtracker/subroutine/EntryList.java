package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.ui.TablePrinter;

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

    public Entry getEntryFromIndex(int index){
        return this.entries.get(index);
    }

    public Entry getEntryFromDateTime(LocalDateTime dateTime) {
        for (Entry i : this.entries) {
            if (i.getDateTime().equals(dateTime)) {
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

    public void removeEntry(int index) {
        Entry removedEntry = this.getEntryFromIndex(index);
        if (removedEntry == null) {
            System.out.println("No entry found. Try again!");
        } else {
            this.removeEntry(removedEntry);
            System.out.println(String.format("Ledger Removed:"));
            TablePrinter.addRow("Auto;Entry Type;Category;Amount;Time;Description                    ");
            TablePrinter.addRow(removedEntry.toString());
            TablePrinter.printList();
        }
    }

    public void removeEntry(Entry entry) {
        this.entries.remove(entry);
    }

    public Entry getLedgerByIndex(int index) {
        return this.entries.get(index);
    }

    public void printList(String ledgerDate) {
        TablePrinter.setTitle(String.format("List of Entries for Ledger [%s]", ledgerDate));
        TablePrinter.addRow("Entry Number;Auto;Entry Type;Category;Amount;Time;Description                    ");
        if (this.getEntriesSize() == 0) {
            TablePrinter.addRow("No entries created               ");
        } else {
            for (int i = 0; i < this.getEntriesSize(); i++) {
                TablePrinter.addRow(String.format("%s;%s", i + 1, this.entries.get(i)));
            }
        }
        TablePrinter.printList();
    }
}
