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
    void execute_eventWithoutDate_doNotPrintCalendarForEvent() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        // Add personal event without date
        String personalInput = "personal to finish project";
        new AddCommand(personalInput).execute(data, ui, storage);
        printWriter.println("You have successfully added this event to your list!");
        printWriter.println("[P][X] to finish project");

        // Execute calendar command
        CalendarCommand.parse("").execute(data, ui, storage);
        printWriter.println("Calendar has 0 dates to display");
        printWriter.println("1 event not on the calendar because it has no date and time");
        printWriter.println("---------------------------------------------------------------------------------------");
        printWriter.println("End of calendar");
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}