package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {

    @Test
    void findCommand_emptyKeyword_exceptionThrown() {
        FindCommand findCommand = new FindCommand("/f");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            findCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("keyword not found", e.getMessage());
        }
    }

    @Test
    void findCommand_noSuchKeyword_exceptionThrown() {
        FindCommand findCommand = new FindCommand("/feruidfmkj");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            findCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("keyword not found", e.getMessage());
        }
    }
}