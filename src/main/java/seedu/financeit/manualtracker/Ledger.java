package seedu.financeit.manualtracker;

import seedu.financeit.manualtracker.subroutine.Entry;
import seedu.financeit.parser.DateTimeManager;
import seedu.financeit.parser.InputParser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ledger {
    ArrayList<Entry> entryList = new ArrayList<>();
    DateTimeManager dateTimeManager;
    LocalDateTime dateTime;
    String defaultDateTimeFormat = "date";

    public Ledger(String rawDate) {
        this.setDate(rawDate);
    }

    public void addEntry(Entry entry) {
        this.entryList.add(entry);
    }

    public void setDate(String rawDate){
        this.dateTime = LocalDateTime.parse(InputParser.parseRawDateTime(rawDate, "date"));
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
