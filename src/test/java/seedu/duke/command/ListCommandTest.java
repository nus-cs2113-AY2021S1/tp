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

    @Test
    void execute_noArguments_listEventListNames() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        System.setOut(new PrintStream(outputStreamCaptor));
        Command listCommand = ListCommand.parse("");
        listCommand.execute(data, ui, storage);
        assertEquals("Following are all existing lists:" + System.lineSeparator()
                + "Personal" + System.lineSeparator()
                + "Timetable" + System.lineSeparator()
                + "Zoom" + System.lineSeparator()
                + "'list All' will list all existing lists." + System.lineSeparator(),
                outputStreamCaptor.toString());
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
        assertEquals("Here is a list of your Zoom events:" + System.lineSeparator()
                        + "1. [Z][X] Math class, Link: zoom.com on 2000-10-09, 13:00" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void execute_repeatEvent_listRepeatEvents() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        String personalInput = "personal; party; 09/10/2000; 1300";
        Command addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        String repeatInput = "personal; 1; monthly; 4;";
        Command repeatCommand = RepeatCommand.parse(repeatInput);
        repeatCommand.execute(data, ui, storage);

        // Execute list command
        String inputString = "all";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, storage);
        assertEquals("Here is a list of your Personal events:" + System.lineSeparator()
                        + "1. [P][X] party on 2000-10-09, 13:00 is also on:" + System.lineSeparator()
                        + "    1. 2000-11-09 13:00 [X]" + System.lineSeparator()
                        + "    2. 2000-12-09 13:00 [X]" + System.lineSeparator()
                        + "    3. 2001-01-09 13:00 [X]" + System.lineSeparator()
                        + "    4. 2001-02-09 13:00 [X]" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator()
                        + "You have no Timetable events!" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator()
                        + "You have no Zoom events!" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}