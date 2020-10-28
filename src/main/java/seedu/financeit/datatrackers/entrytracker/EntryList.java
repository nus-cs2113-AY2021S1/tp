package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.data.ItemList;
import seedu.financeit.datatrackers.manualtracker.Ledger;
import seedu.financeit.ui.TablePrinter;

public class EntryList extends ItemList {
    Ledger ledger;

    public EntryList(Ledger ledger) {
        this.setLedger(ledger);
    }

    public void addEntry(Entry entry) {
        entry.setLedger(this.ledger);
        this.addItem(entry);
    }


    @Override
    public void printList() {
        TablePrinter.setTitle(String.format("List of Entries for Ledger [%s]", this.ledger));
        TablePrinter.addRow("Entry Number;Entry Type;Category;Amount;Time;Description                    ");
        if (super.getItemsSize() == 0) {
            TablePrinter.addRow("No entries created               ");
        } else {
            for (int i = 0; i < super.getItemsSize(); i++) {
                TablePrinter.addRow(String.format("%s;%s", i + 1, super.items.get(i)));
            }
        }
        TablePrinter.printList();
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }
}

