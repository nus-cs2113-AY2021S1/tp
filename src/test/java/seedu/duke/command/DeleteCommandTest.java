package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {

    @Test
    void deleteCommand_invalidCommand_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("-lalala");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("delete", e.getMessage());
        }
    }

    @Test
    void deleteCommand_taskNumberIsNotANumber_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("-tesjk2");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("delete", e.getMessage());
        }
    }

    @Test
    void deleteCommand_eventNumberIsNotANumber_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("-e12jkm");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("delete", e.getMessage());
        }
    }

    @Test
    void deleteCommand_taskNumberMoreThanTasks_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("-t1");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid task action", e.getMessage());
        }
    }

    @Test
    void deleteCommand_negativeTaskNumber_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("-t-2");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid task action", e.getMessage());
        }
    }

    @Test
    void deleteCommand_taskNumberEqualsZero_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("-t0");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid task action", e.getMessage());
        }
    }

    @Test
    void deleteCommand_eventNumberMoreThanEvents_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("-e1");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid event action", e.getMessage());
        }
    }

    @Test
    void deleteCommand_negativeEventNumber_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("-e-2");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid event action", e.getMessage());
        }
    }

    @Test
    void deleteCommand_eventNumberEqualsZero_exceptionThrown() {
        DeleteCommand deleteCommand = new DeleteCommand("-e0");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            deleteCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid event action", e.getMessage());
        }
    }
}