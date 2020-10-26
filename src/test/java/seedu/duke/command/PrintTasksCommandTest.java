package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintTasksCommandTest {

    @Test
    void printTasksCommand_wrongCommandWithoutS_exceptionThrown() {
        PrintTasksCommand printTasksCommand = new PrintTasksCommand("print task");
        CalendarList calendarList = new CalendarList();
        try {
            printTasksCommand.execute(calendarList, null);
        } catch (Exception e) {
            assertEquals("invalid command", e.getMessage());
        }
    }

    @Test
    void printTasksCommand_wrongCommandWithExtraNumber_exceptionThrown() {
        PrintTasksCommand printTasksCommand = new PrintTasksCommand("print tasks 5");
        CalendarList calendarList = new CalendarList();
        try {
            printTasksCommand.execute(calendarList, null);
        } catch (Exception e) {
            assertEquals("invalid command", e.getMessage());
        }
    }
}