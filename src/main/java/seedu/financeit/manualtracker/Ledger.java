package seedu.financeit.manualtracker;

import seedu.financeit.utils.DateTimeManager;
import seedu.financeit.utils.InputParser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ledger {
    ArrayList<Entry> entryList = new ArrayList<>();
    DateTimeManager dateTimeManager;
    String defaultDateTimeFormat = "date";

    public Ledger(String rawDate) {
        LocalDateTime dateTime = LocalDateTime.parse(InputParser.parseRawDateTime(rawDate, "date"));
        this.dateTimeManager = new DateTimeManager(dateTime);
    }

    public void addEntry(Entry entry) {
        this.entryList.add(entry);
    }

    public String getDate() {
        return this.dateTimeManager.getDateFormatted(this.defaultDateTimeFormat);
    }

    @Override
    public String toString() {
        return this.getDate();
    }
}
