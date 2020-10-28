package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidExtractCommandException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExtractCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void execute_emptyTextSubjectOfEvent_InvalidExtractCommandException() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);
        String extractInput = ";";
        assertThrows(InvalidExtractCommandException.class, () -> {
            ExtractCommand extractCommand = new ExtractCommand(extractInput);
            extractCommand.execute(data, ui, storage);
        });
    }

    @Test
    void execute_emptyTextBodyOfEvent_InvalidExtractCommandException() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);
        String extractInput = "quiz; ";
        assertThrows(InvalidExtractCommandException.class, () -> {
            ExtractCommand extractCommand = new ExtractCommand(extractInput);
            extractCommand.execute(data, ui, storage);
        });
    }

    @Test
    void execute_CreateEventWithNoDateOrTime_printSucessfulAdd() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        String extractInput = "Meeting; There will be a meeting on tuesday at 16.00";
        Command extractCommand = new ExtractCommand(extractInput);
        extractCommand.execute(data, ui, storage);
        assertEquals("No dates detected for this text body!" + System.lineSeparator()
                        + "Since no date was detected in the text body, "
                        + "the personal event will only contain the description." + System.lineSeparator()
                        + "You have successfully added this event to your list!" + System.lineSeparator()
                        + "[P][✕] Meeting" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void execute_CreateEventWithDateOnly_printSuccessfulAdd() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        String extractInput = "CS2113T Makeup Lesson;"
                + " Please note there will be a make up lesson on 17th November and 32 Dec";
        Command extractCommand = new ExtractCommand(extractInput);
        extractCommand.execute(data, ui, storage);
        assertEquals("One date detected and chosen: 2020-11-17" + System.lineSeparator()
                        + "No time slots detected for this text body!" + System.lineSeparator()
                        + "Since no time detected in text body, "
                        + "the personal event will only have the date and description." + System.lineSeparator()
                        + "You have successfully added this event to your list!" + System.lineSeparator()
                        + "[P][✕] CS2113T Makeup Lesson on 2020-11-17" + System.lineSeparator(),
                outputStreamCaptor.toString());

    }

    @Test
    void execute_CreateEvent_printSuccessfulAdd() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        // Add timetable event to data
        String extractInput = "CG2271 Quiz; Hi class, please note there will be a quiz on Oct 05 2020 at 4pm";

        /*InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("2 \n".getBytes());
        System.setIn(in);*/
        Command extractCommand = new ExtractCommand(extractInput);
        extractCommand.execute(data, ui, storage);
        assertEquals("One date detected and chosen: 2020-10-05" + System.lineSeparator()
                        + "One time slot detected and chosen: 16:00" + System.lineSeparator()
                        + "You have successfully added this event to your list!" + System.lineSeparator()
                        + "[P][✕] CG2271 Quiz on 2020-10-05, 16:00" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }



    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}