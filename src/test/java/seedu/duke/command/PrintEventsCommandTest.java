package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintEventsCommandTest {

    @Test
    void printEventsCommand_wrongCommandWithoutS_exceptionThrown() {
        PrintEventsCommand printEventsCommand = new PrintEventsCommand("print event");
        CalendarList calendarList = new CalendarList();
        try {
            printEventsCommand.execute(calendarList, null);
        } catch (Exception e) {
            assertEquals("invalid command", e.getMessage());
        }
    }

    @Test
    void printEventsCommand_wrongCommandWithExtraNumber_exceptionThrown() {
        PrintEventsCommand printEventsCommand = new PrintEventsCommand("print events 5");
        CalendarList calendarList = new CalendarList();
        try {
            printEventsCommand.execute(calendarList, null);
        } catch (Exception e) {
            assertEquals("invalid command", e.getMessage());
        }
    }
}