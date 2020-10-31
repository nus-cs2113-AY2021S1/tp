package seedu.financeit.datatrackers.recurringtracker;

import seedu.financeit.data.Item;
import seedu.financeit.data.ItemList;
import seedu.financeit.datatrackers.recurringtracker.comparators.SortByDay;
import seedu.financeit.ui.TablePrinter;

//@@author Artemis-Hunt
public class RecurringEntryList extends ItemList {

    @Override
    public void addItem(Item item) {
        super.addItemAndSort(item, new SortByDay());
    }

    @Override
    public void printList() {
        TablePrinter.setTitle(String.format("List of Recurring entries"));
        TablePrinter.addRow("No.;Day;Description;Expenditure amount;Income amount;"
                + "Duration;Payment type;Notes                    ");
        if (super.items.isEmpty()) {
            TablePrinter.addRow("No entries created");
        } else {
            for (int i = 0; i < super.items.size(); i++) {
                RecurringEntry entry = (RecurringEntry) super.items.get(i);
                TablePrinter.addRow(String.format("%s;%s", i + 1, entry));
            }
        }
        TablePrinter.printList();
    }

}

