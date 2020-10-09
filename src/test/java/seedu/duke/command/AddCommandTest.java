package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void execute_timetableEvent_printEventAdded() {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data");

        // Add timetable event to data
        String timetableInput = "timetable Math class; math building; 17/10/2000; 1300";
        Command addCommand = new AddCommand(timetableInput);
        addCommand.execute(data, ui, storage);

        assertEquals("You have successfully added this event to your list!" + System.lineSeparator()
                        + "[T][✕] Math class, Location: "
                        + "math building on 2000-10-17, 13:00" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void execute_invalidPersonalEvent_printZoomEventError() {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data");

        // Add zoom event with incorrect number of parameters to data
        String zoomInput = "zoom class meeting; zoom.sg; 17/10/2000";
        Command addCommand = new AddCommand(zoomInput);
        addCommand.execute(data, ui, storage);

        assertEquals("Incorrect number of parameters for Zoom event!" + System.lineSeparator()
                        + "Error adding Zoom event, please try again!" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}