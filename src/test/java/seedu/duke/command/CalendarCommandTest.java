package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarCommandTest {
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
        Storage storage = new Storage("data");

        // Add zoom events to data
        String zoomInput1 = "zoom Math class; zoom.com; 09/10/2000; 1300";
        String zoomInput2 = "zoom Math class 2; zoom.com; 09/10/2000; 1500";
        Command addCommand = new AddCommand(zoomInput1);
        addCommand.execute(data, ui, storage);
        addCommand = new AddCommand(zoomInput2);
        addCommand.execute(data, ui, storage);

        // Execute calendar command
        Command command = CalendarCommand.parse("");
        command.execute(data, ui, storage);
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
