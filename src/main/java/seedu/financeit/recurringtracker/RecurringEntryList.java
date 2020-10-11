package seedu.financeit.recurringtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.ItemList;
import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.ui.TablePrinter;

public class RecurringEntryList extends ItemList {
    public RecurringEntryList() {

    }

    @Override
    public void printList(String... itemName) {

    }

    public void printList() {
        TablePrinter.setTitle(String.format("List of Recurring entries"));
        TablePrinter.addRow("Day;Description;Expenditure amount;Income amount;Payment;Notes                    ");
        if (getItemsSize() == 0) {
            TablePrinter.addRow("No entries created               ");
        } else {
            for (int i = 0; i < getItemsSize(); i++) {
                TablePrinter.addRow(String.format("%s;%s", i + 1, items.get(i)));
            }
        }
        TablePrinter.printList();
    }

    @Override
    public boolean isValidItem() {
        return false;
    }

    @Override
    public void handleSingleParam(CommandPacket packet, String paramType) throws ParseFailParamException,
        ItemNotFoundException, ConflictingItemReference {
    }
}
