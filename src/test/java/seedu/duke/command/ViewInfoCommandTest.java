package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewInfoCommandTest {

    @Test
    void viewInfoCommand_wrongCommand_exceptionThrown() {
        ViewInfoCommand viewInfoCommand = new ViewInfoCommand("/v");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            viewInfoCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid view info", e.getMessage());
        }
    }

    @Test
    void viewInfoCommand_eventNumberIsNotANumber_exceptionThrown() {
        ViewInfoCommand viewInfoCommand = new ViewInfoCommand("/v4redf4");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            viewInfoCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid view info", e.getMessage());
        }
    }

    @Test
    void viewInfoCommand_eventNumberMoreThanEvents_exceptionThrown() {
        ViewInfoCommand viewInfoCommand = new ViewInfoCommand("/v1");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            viewInfoCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid event action", e.getMessage());
        }
    }
}