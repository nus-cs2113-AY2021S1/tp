package seedu.financeit.goaltracker;

import seedu.financeit.manualtracker.subroutine.Entry;

public class TotalGoal {
    Entry entryData = new Entry();
    private String entryCategory;

    public void setTotalGoal(){
        entryCategory = entryData.getCategory();

    }
}
