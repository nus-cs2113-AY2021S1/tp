package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddInfoCommandTest {

    @Test
    void addInfoCommand_wrongCommand_exceptionThrown() {
        AddInfoCommand addInfoCommand = new AddInfoCommand("/a1");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            addInfoCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid add info", e.getMessage());
        }
    }

    @Test
    void addInfoCommand_eventNumberIsNotANumber_exceptionThrown() {
        AddInfoCommand addInfoCommand = new AddInfoCommand("/are - hello");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            addInfoCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid add info", e.getMessage());
        }
    }

    @Test
    void addInfoCommand_eventNumberMoreThanEvents_exceptionThrown() {
        AddInfoCommand addInfoCommand = new AddInfoCommand("/a1 - hello");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            addInfoCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid event action", e.getMessage());
        }
    }
}