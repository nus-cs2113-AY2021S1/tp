package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ListCommand;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void storageLoadAll_LoadFilesFromDirectory_onlyPersonalFilesLoaded() throws DukeException {
        Storage store = new Storage("storagetester");
        UserData data = new UserData();
        Ui ui = new Ui();
        store.loadAll(data);
        String inputString = "personal";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, store);

        assertEquals("Here is a list of your Personal events:" + System.lineSeparator()
                        + "1. [P][✕] stuff on 2010-01-01, 12:00" + System.lineSeparator()
                        + "   Repeated weekly for 4 times." + System.lineSeparator()
                        + "2. [P][✓] birthday celebration on 2010-01-01, 12:00" + System.lineSeparator()
                        + "3. [P][✕] others" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());

        outputStreamCaptor.reset();

        inputString = "zoom";
        listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, store);

        assertEquals("Here is a list of your Zoom events:" + System.lineSeparator()
                        + "1. [Z][✕] math, Link: www.zoom.com/blah" + System.lineSeparator()
                        + "   Repeated daily for 4 times." + System.lineSeparator()
                        + "2. [Z][✓] computing, Link: www.zoom.com/hello" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());

        outputStreamCaptor.reset();

        inputString = "timetable";
        listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, store);

        assertEquals("Here is a list of your Timetable events:" + System.lineSeparator()
                        + "1. [T][✕] math, Location: S17 on 2010-01-01, 12:00" + System.lineSeparator()
                        + "   Repeated monthly for 4 times." + System.lineSeparator()
                        + "2. [T][✓] computing, Location: COM2 on 2010-01-01, 12:00" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

}