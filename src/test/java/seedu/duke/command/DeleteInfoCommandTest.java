package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.*;

class DeleteInfoCommandTest {

    @Test
    void deleteInfoCommand_missingCommand_exceptionThrown() {
        DeleteInfoCommand deleteInfoCommand = new DeleteInfoCommand("/-");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteInfoCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid delete info", e.getMessage());
        }
    }

    @Test
    void deleteInfoCommand_eventNumberIsNotANumber_exceptionThrown() {
        DeleteInfoCommand deleteInfoCommand = new DeleteInfoCommand("/-a2");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteInfoCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid delete info", e.getMessage());
        }
    }

    @Test
    void deleteInfoCommand_eventNumberIsNotValid_exceptionThrown() {
        DeleteInfoCommand deleteInfoCommand = new DeleteInfoCommand("/-999a2");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteInfoCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid event action", e.getMessage());
        }
    }

}