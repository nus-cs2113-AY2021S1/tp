package seedu.financeit.utils;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntry;
import seedu.financeit.datatrackers.recurringtracker.RecurringTracker;
import seedu.financeit.datatrackers.recurringtracker.RecurringTrackerTest;
import seedu.financeit.testutil.TestUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ReminderGeneratorTest {
    CommandPacket testPacket;

    void createEntriesFromDay1To15() {
        RecurringTrackerTest test = new RecurringTrackerTest();
        test.handleNewEntry_noMissingParams_entriesCreated();
        for (int i = 1; i <= test.ENTRIES_TO_CREATE; i++) {
            testPacket = TestUtil.createCommandPacket("edit",
                    new String[]{"/id", "/day"},
                    new String[]{String.valueOf(i), String.valueOf(i)});
            RecurringTracker.handleEditEntry(testPacket);
        }
    }

    @Test
    public void filterEntriesToRemind_positiveTestOne_correctEntriesReturned() {
        final int numEntriesToRemind = 4;
        createEntriesFromDay1To15();
        ArrayList<RecurringEntry> entriesToRemind = ReminderGenerator.filterEntriesToRemind(29, 30, 4, true);
        assertEquals(numEntriesToRemind, entriesToRemind.size());
        for (int i = 0; i < numEntriesToRemind; i++) {
            assertEquals(i + 1, entriesToRemind.get(i).getDay());
        }
    }

    @Test
    public void filterEntriesToRemind_positiveTestTwo_correctEntriesReturned() {
        final int numEntriesToRemind = 0;
        createEntriesFromDay1To15();
        ArrayList<RecurringEntry> entriesToRemind = ReminderGenerator.filterEntriesToRemind(17, 30, 22, false);
        assertEquals(numEntriesToRemind, entriesToRemind.size());
    }

}
