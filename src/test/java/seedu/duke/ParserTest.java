package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.calendar.CalendarList;
import seedu.duke.command.Command;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    //Three part name for a test: methodUnderTest_inputGiven_expectedOutput
    CalendarList calendarList;
    Storage storage;
    @Test
    void handleUserInput_helpCommand_returnsAllAvailableCommands() throws DukeException {
        String input = "help";
        String actualOutput;
        String expectedOutput =("List of available commands:\n"
                + "1. todo <task description>\n"
                + "2. deadline <task description> /by ddMMyy\n"
                + "3. activity <activity description> <venue> /at ddMMyy\n"
                + "4. exam <module code> <venue> /at ddMMyy HHmm\n"
                + "5. lecture <module code> <venue> /at ddMMyy HHmm\n"
                + "6. tutorial <module code> <venue> /at ddMMyy HHmm\n"
                + "7. lab <module code> <venue> /at ddMMyy HHmm\n"
                + "8. done <task number>\n"
                + "9. delete <task number>\n"
                + "10. find <keyword>\n"
                + "11. print list\n"
                + "12. print events\n"
                + "13. print timeline\n"
                + "14. print progress"
        );
        Parser testParser = new Parser();
        Command c = new Command("help") {
            @Override
            public void execute(CalendarList calendarList, Storage storage) throws DukeException {

            }
        };
        //assertEquals(expectedOutput, c.execute(calendarList, storage));
    }
}