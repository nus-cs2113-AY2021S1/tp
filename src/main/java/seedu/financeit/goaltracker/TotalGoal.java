package seedu.financeit.goaltracker;

import seedu.financeit.common.Constants;
import seedu.financeit.manualtracker.subroutine.Entry;

public class TotalGoal {
    Entry entryData = new Entry();
    private Constants.EntryType entryCategory;

    public void setTotalGoal() {
        entryCategory = entryData.getEntryType();
        System.out.println(entryCategory);


    }
}
