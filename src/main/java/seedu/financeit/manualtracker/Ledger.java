package seedu.financeit.manualtracker;

import seedu.financeit.manualtracker.subroutine.Entry;
import seedu.financeit.parser.DateTimeManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ledger {
    ArrayList<Entry> entryList = new ArrayList<>();
    DateTimeManager dateTimeManager;
    LocalDateTime dateTime;
    String defaultDateTimeFormat = "date";

    public Ledger(LocalDateTime dateTime) {
        this.setDate(dateTime);
    }

    public void addEntry(Entry entry) {
        this.entryList.add(entry);
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.dateTimeManager = new DateTimeManager(dateTime);
    }

    public String getDate() {
        return this.dateTimeManager.getDateFormatted(this.defaultDateTimeFormat);
    }

    @Override
    public String toString() {
        return this.getDate();
    }
}
