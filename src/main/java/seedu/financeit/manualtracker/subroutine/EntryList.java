package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.List;
import seedu.financeit.ui.TablePrinter;

public class EntryList extends List {

    @Override
    public void printList(String ledgerDate) {
        TablePrinter.setTitle(String.format("List of Entries for Ledger [%s]", ledgerDate));
        TablePrinter.addRow("Entry Number;Auto;Entry Type;Category;Amount;Time;Description                    ");
        if (super.getItemsSize() == 0) {
            TablePrinter.addRow("No entries created               ");
        } else {
            for (int i = 0; i < super.getItemsSize(); i++) {
                TablePrinter.addRow(String.format("%s;%s", i + 1, super.items.get(i)));
            }
        }
        TablePrinter.printList();
    }
}

