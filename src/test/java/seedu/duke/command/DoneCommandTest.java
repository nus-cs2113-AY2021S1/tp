package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.*;

class DoneCommandTest {

    @Test
    void doneCommand_invalidCommand_exceptionThrown() {
        DoneCommand doneCommand = new DoneCommand("done4   urehjsd");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            doneCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("done", e.getMessage());
        }
    }

    @Test
    void doneCommand_taskNumberMoreThanTasks_exceptionThrown() {
        DoneCommand doneCommand = new DoneCommand("done 544");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            doneCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid task action", e.getMessage());
        }
    }

    @Test
    void doneCommand_negativeTaskNumber_exceptionThrown() {
        DoneCommand doneCommand = new DoneCommand("done-544");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            doneCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid task action", e.getMessage());
        }
    }

    @Test
    void doneCommand_taskNumberEqualsZero_exceptionThrown() {
        DoneCommand doneCommand = new DoneCommand("done0");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            doneCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("invalid task action", e.getMessage());
        }
    }

}