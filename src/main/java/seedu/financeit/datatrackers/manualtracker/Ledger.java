package seedu.financeit.datatrackers.manualtracker;

import seedu.financeit.data.DateTimeItem;
import seedu.financeit.datatrackers.entrytracker.EntryList;

import java.time.LocalDate;

public class Ledger extends DateTimeItem {
    protected LocalDate date = null;
    public EntryList entryList = new EntryList(this);

    public Ledger() {
        super();
        super.setDefaultDateTimeFormat("date");
    }

    @Override
    public String getName() {
        return String.format("Ledger %d : [ %s ]", this.index + 1,
            this.dateTimeOutputManager.getSingleDateFormatted("date"));
    }

    @Override
    public boolean equals(Object object) {
        Ledger entry = (Ledger) object;
        return (this.getDate().equals(entry.getDate()));
    }

    @Override
    public String toString() {
        return String.format("%s", this.getDate());
    }
}
