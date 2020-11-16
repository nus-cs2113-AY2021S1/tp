package seedu.financeit.datatrackers.recurringtracker.comparators;

import seedu.financeit.datatrackers.recurringtracker.RecurringEntry;

import java.util.Comparator;

public class SortByDay implements Comparator<RecurringEntry> {

    public int compare(RecurringEntry a, RecurringEntry b) {
        return a.getDay() - b.getDay();
    }
}
