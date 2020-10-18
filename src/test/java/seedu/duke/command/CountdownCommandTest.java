package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.*;

class CountdownCommandTest {

    @Test
    void execute_exceptionThrown() {
        CountdownCommand countdownCommand = new CountdownCommand("countdown abs");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            countdownCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid countdown", e.getMessage());
        }
    }
}