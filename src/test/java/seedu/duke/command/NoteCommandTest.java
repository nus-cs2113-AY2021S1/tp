package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.InvalidListException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NoteCommandTest {
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
    public void execute_withWrongIndex_invalidIndexException() throws DukeException {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input);
        addPersonalEvent.execute(data, ui, storage);

        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);

        assertThrows(InvalidIndexException.class, () -> {
            NoteCommand testNotesWithInvalidIndex = new NoteCommand("personal; 7");
            testNotesWithInvalidIndex.execute(data, ui, storage);
        });
    }

    @Test
    public void execute_withWrongList_invalidListException() throws DukeException {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input);
        addPersonalEvent.execute(data, ui, storage);

        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);

        assertThrows(InvalidListException.class, () -> {
            NoteCommand testNotesWithInvalidIndex = new NoteCommand("persona; 1");
            testNotesWithInvalidIndex.execute(data, ui, storage);
        });
    }

    @Test
    public void execute_withoutListTypeorIndex_wrongNumberOfArgumentsException() throws DukeException {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input);
        addPersonalEvent.execute(data, ui, storage);


        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);

        assertThrows(WrongNumberOfArgumentsException.class, () -> {
            NoteCommand testNotesWithMissingArguments = new NoteCommand("personal");
            testNotesWithMissingArguments.execute(data, ui, storage);
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
            NoteCommand testNotesWithInvalidIndex = new NoteCommand("personal; a");
            testNotesWithInvalidIndex.execute(data, ui, storage);
        });
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
