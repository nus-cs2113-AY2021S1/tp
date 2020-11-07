package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author Jingming517
class PrintTimelineCommandTest {

    @Test
    void printTimelineCommand_wrongCommand_exceptionThrown() {
        PrintTimelineCommand printTimelineCommand = new PrintTimelineCommand("print timeline");
        CalendarList calendarList = new CalendarList();
        try {
            printTimelineCommand.execute(calendarList, null);
        } catch (Exception e) {
            assertEquals("invalid command", e.getMessage());
        }
    }
}