package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeadlineCommandTest {
    UserData data = new UserData();
    Ui ui = new Ui();
    Storage storage = new Storage("data", ui);

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_withDate_printDeadline() throws DukeException {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input);
        addPersonalEvent.execute(data, ui, storage);

        DeadlineCommand testDeadlineWithDateOnly = new DeadlineCommand("1; 7/10/20");
        testDeadlineWithDateOnly.execute(data, ui, storage);
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.println("You have successfully added this event to your list!");
        printWriter.println("[P][✕] sleep");
        printWriter.println("You have successfully updated the deadline for this event!");
        printWriter.println("[P][✕] sleep on 2020-10-07");
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected,
                outputStreamCaptor.toString());
    }

    @Test
    public void execute_withDateAndTime_printDeadline() throws DukeException {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input);
        addPersonalEvent.execute(data, ui, storage);

        DeadlineCommand testDeadlineWithDateOnly = new DeadlineCommand("1; 7/10/20; 11:20 PM");
        testDeadlineWithDateOnly.execute(data, ui, storage);
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.println("You have successfully added this event to your list!");
        printWriter.println("[P][✕] sleep");
        printWriter.println("You have successfully updated the deadline for this event!");
        printWriter.println("[P][✕] sleep on 2020-10-07, 23:20");
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected,
                outputStreamCaptor.toString());
    }

    @Test
    public void execute_withoutDateandTime_wrongNumberOfArgumentsException() throws DukeException {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input);
        addPersonalEvent.execute(data, ui, storage);


        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);

        assertThrows(WrongNumberOfArgumentsException.class, () -> {
            DeadlineCommand testDeadlineWithoutDateandTime = new DeadlineCommand("1");
            testDeadlineWithoutDateandTime.execute(data, ui, storage);
        });
    }

    @Test
    public void execute_withInvalidIndex_invalidIndexException() throws DukeException {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input);
        addPersonalEvent.execute(data, ui, storage);


        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);

        assertThrows(InvalidIndexException.class, () -> {
            DeadlineCommand testDeadlineWithInvalidIndex = new DeadlineCommand("0; 7/10/20; 11:20 PM");
            testDeadlineWithInvalidIndex.execute(data, ui, storage);
        });
    }

    @Test
    public void execute_withIndexIsNotaNumber_wrongNumberFormatException() throws DukeException {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input);
        addPersonalEvent.execute(data, ui, storage);


        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);

        assertThrows(WrongNumberFormatException.class, () -> {
            DeadlineCommand testDeadlineWithInvalidIndex = new DeadlineCommand("a; 7/10/20; 11:20 PM");
            testDeadlineWithInvalidIndex.execute(data, ui, storage);
        });
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}