package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.EventAddErrorException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void execute_emptyInputForAddCommand_EventAddErrorException() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);
        String addInput = "";
        assertThrows(EventAddErrorException.class, () -> {
            AddCommand addCommand = new AddCommand(addInput);
            addCommand.execute(data, ui, storage);
        });
    }

    @Test
    void execute_emptyDescriptionForZoomEvent_printEmptyDescriptionError() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);
        String addInput = "zoom;   ; 16/10/20; 4pm";

        Command addCommand = new AddCommand(addInput);
        addCommand.execute(data, ui, storage);

        assertEquals("This event has an empty description!" + System.lineSeparator(),
                outputStreamCaptor.toString());

    }

    @Test
    void execute_timetableEvent_printEventAdded() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        // Add timetable event to data
        String addInput = "timetable; Math class; math building; 17/10/2000; 1300";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(data, ui, storage);

        assertEquals("You have successfully added this event to your list!" + System.lineSeparator()
                        + "[T][X] Math class, Location: "
                        + "math building on 2000-10-17, 13:00" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void execute_invalidZoomEvent_printZoomEventError() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        // Add zoom event with incorrect number of parameters to data
        String addInput = "zoom; class meeting; zoom.sg; 17/10/2000";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(data, ui, storage);

        assertEquals("Incorrect number of parameters for Zoom event!" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void execute_invalidPersonalEventTiming_printTimeParseError() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        String addInput = "personal; meeting; 16/9/2020; 18:89 PM";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(data, ui, storage);

        assertEquals("Something is wrong with the time!" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void execute_invalidPersonalEventDate_printDateParseError() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        String addInput = "personal; meeting; 35/9/2020; 4 PM";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(data, ui, storage);

        assertEquals("Something is wrong with the date!" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}