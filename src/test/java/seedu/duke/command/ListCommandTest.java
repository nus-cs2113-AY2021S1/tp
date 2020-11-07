package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void execute_zoomEvent_listZoomEvents() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        // Add zoom event to data
        String zoomInput = "zoom; Math class; zoom.com; 09/10/2000; 1300";
        Command addCommand = new AddCommand(zoomInput);
        addCommand.execute(data, ui, storage);

        // Execute list command
        String inputString = "zoom";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, storage);
        assertEquals("You have successfully added this event to your list!" + System.lineSeparator()
                        + "[Z][X] Math class, Link: "
                        + "zoom.com on 2000-10-09, 13:00" + System.lineSeparator()
                        + "Here is a list of your Zoom events:" + System.lineSeparator()
                        + "1. [Z][X] Math class, Link: zoom.com on 2000-10-09, 13:00" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }


    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}