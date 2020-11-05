package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.InvalidListException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NoteCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Nested
    class TestCreateNote {

        @BeforeEach
        public void setUp() {
            String extractInput = "Meeting is held in NUS COM 2 \r\n Attire is black t-shirt \r\n noteend \r\n";
            ByteArrayInputStream inStream = new ByteArrayInputStream(extractInput.getBytes());
            System.setIn(inStream);
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        void execute_CreateNote_printSuccessfulCreate() throws DukeException {
            UserData data = new UserData();
            Ui ui = new Ui();
            Storage storage = new Storage("data", ui);
            String input = "personal sleep";
            Command addPersonalEvent = new AddCommand(input);
            addPersonalEvent.execute(data, ui, storage);

            NoteCommand testCreateNote = new NoteCommand("personal; 1");
            testCreateNote.execute(data, ui, storage);
            StringWriter expectedStringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(expectedStringWriter);
            printWriter.println("You have successfully added this event to your list!");
            printWriter.println("[P][X] sleep");
            printWriter.println("Please type in your notes."
                    + " To stop note taking, ensure that you are in a new line"
                    + " and type 'noteend', and press enter");
            printWriter.println("You have successfully written the note for this event!");
            printWriter.println("[P][X] sleep");
            printWriter.println("Meeting is held in NUS COM 2");
            printWriter.println("Attire is black t-shirt");
            printWriter.close();
            String expected = expectedStringWriter.toString();
            String actual = outputStreamCaptor.toString();
            String[] remove = actual.split("---------");

            StringWriter actualString = new StringWriter();
            PrintWriter printWriterActual = new PrintWriter(actualString);
            for (int i = 0; i < remove.length; i++) {
                if (i % 2 == 0) {
                    printWriterActual.println(remove[i].trim());
                }

            }
            String withoutTimestamp = actualString.toString();
            assertEquals(expected, withoutTimestamp);
        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }

    @Nested
    class TestWrongIndex {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

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

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }

    @Nested
    class TestWithWrongList {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        @BeforeEach
        public void setUp() {
            System.setOut(new PrintStream(outputStreamCaptor));
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

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }

    @Nested
    class TestWithoutListTypeOrIndex {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        @BeforeEach
        public void setUp() {
            System.setOut(new PrintStream(outputStreamCaptor));
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

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }

    @Nested
    class TestIndexIsNotaNumber {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        @BeforeEach
        public void setUp() {
            System.setOut(new PrintStream(outputStreamCaptor));
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

}
