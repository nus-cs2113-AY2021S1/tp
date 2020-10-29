package seedu.financeit.datatrackers.recurringtracker;

import seedu.financeit.data.Item;
import seedu.financeit.data.ItemList;
import seedu.financeit.datatrackers.recurringtracker.comparators.SortByDay;
import seedu.financeit.ui.TablePrinter;

import java.util.ArrayList;

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

    /**
     * Returns an ArrayList of all RecurringEntry with
     * day of month between X and Y  (both inclusive)
     * i.e. Y >= day >= X (inequality)
     *
     * @param startDay - X
     * @param endDay - Y
     * @return ArrayList of RecurringEntry
     */
    public ArrayList<RecurringEntry> getEntriesFromDayXtoY(int startDay, int endDay) {
        ArrayList<RecurringEntry> entries = new ArrayList<>();
        for (Item item : super.items) {
            RecurringEntry entry = (RecurringEntry) item;
            int dayOfEntry = entry.getDay();
            if (dayOfEntry >= startDay && dayOfEntry <= endDay) {
                entries.add(entry);
            }
        }
        return entries;
    }

}

