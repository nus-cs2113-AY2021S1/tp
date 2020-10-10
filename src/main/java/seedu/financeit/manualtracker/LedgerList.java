package seedu.financeit.manualtracker;

import seedu.financeit.common.ItemList;
import seedu.financeit.ui.TablePrinter;

public class LedgerList extends ItemList {

    @Override
    public void printList(String... ledgerName) {
        TablePrinter.setTitle("List of Ledgers");
        TablePrinter.addRow("Ledger Number;Ledger Date");
        if (super.getItemsSize() == 0) {
            TablePrinter.addRow("No ledgers created;               ");
        } else {
            for (int i = 0; i < super.getItemsSize(); i++) {
                TablePrinter.addRow(String.format("%s;%s", i + 1, this.items.get(i)));
            }
        }
        TablePrinter.printList();
    }
}
