package seedu.financeit.datatrackers.manualtracker;

import seedu.financeit.data.ItemList;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.ui.TablePrinter;

import java.time.LocalDate;

public class LedgerList extends ItemList {
    public LedgerList() {

    }

    /**
     * Given a date, returns the zero-based index of the ledger for that date.
     *
     * @param date Date of ledger
     * @return Zero-based index of ledger
     * @throws ItemNotFoundException When there is no ledger for that date
     */
    public int getIndexFromDate(LocalDate date) throws ItemNotFoundException {
        for (int i = 0; i < super.getItemsSize(); i++) {
            Ledger ledger = (Ledger) super.items.get(i);
            if (ledger.getDate().equals(date)) {
                return i;
            }
        }
        throw new ItemNotFoundException();
    }

    @Override
    public void printList() {
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
