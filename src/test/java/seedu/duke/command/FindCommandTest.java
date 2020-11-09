package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {

    @Test
    void findCommand_emptyKeywordWithoutSpace_exceptionThrown() {
        FindCommand findCommand = new FindCommand("/f");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            findCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("missing keyword", e.getMessage());
        }
    }

    @Test
    void findCommand_emptyKeywordWithSpace_exceptionThrown() {
        FindCommand findCommand = new FindCommand("/f ");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            findCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("missing keyword", e.getMessage());
        }
    }

    @Test
    void findCommand_wrongCommand_exceptionThrown() {
        FindCommand findCommand = new FindCommand("/feruidfmkj");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            findCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("find", e.getMessage());
        }
    }

    @Test
    void findCommand_wrongCommandNoKeyword_exceptionThrown() {
        FindCommand findCommand = new FindCommand("/feruidfmkj ");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            findCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("find", e.getMessage());
        }
    }

    @Test
    void findCommand_wrongCommandWithKeyword_exceptionThrown() {
        FindCommand findCommand = new FindCommand("/feruidfmkj haha");
        CalendarList calendarList = new CalendarList();
        Storage storage = null;
        try {
            findCommand.execute(calendarList, storage);
        } catch (Exception e) {
            assertEquals("find", e.getMessage());
        }
    }
}