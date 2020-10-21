package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrioritizeCommandTest {

    @Test
    void prioritizeCommand_messageIndex_exceptionThrown() {
        PrioritizeCommand prioritizeCommand = new PrioritizeCommand("*t kdjah");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            prioritizeCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("prioritize", e.getMessage());
        }
    }

    @Test
    void prioritizeCommand_negativeIndex_exceptionThrown() {
        PrioritizeCommand prioritizeCommand = new PrioritizeCommand("*t -1");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            prioritizeCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid task action", e.getMessage());
        }
    }

    @Test
    void prioritizeCommand_outOfBoundIndex_exceptionThrown() {
        PrioritizeCommand prioritizeCommand = new PrioritizeCommand("*t 1");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            prioritizeCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid task action", e.getMessage());
        }
    }
}
