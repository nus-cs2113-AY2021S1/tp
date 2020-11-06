package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidExtractCommandException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExtractCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Nested
    class TestEmptyTextSubject {
        public void setUp() {
            String extractInput = "  \r\n extractend \r\n";
            ByteArrayInputStream inStream = new ByteArrayInputStream(extractInput.getBytes());
            System.setIn(inStream);
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        void execute_emptyTextSubjectOfEvent_InvalidExtractCommandException() throws DukeException {
            UserData data = new UserData();
            Ui ui = new Ui();
            Storage storage = new Storage("data", ui);
            String extractInput = ";";
            assertThrows(InvalidExtractCommandException.class, () -> {
                ExtractCommand extractCommand = new ExtractCommand(extractInput);
                extractCommand.execute(data, ui, storage);
            });
        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }

    @Nested
    class TestEmptyTextBody {
        @BeforeEach
        public void setUp() {
            String extractInput = "  \r\n extractend \r\n";
            ByteArrayInputStream inStream = new ByteArrayInputStream(extractInput.getBytes());
            System.setIn(inStream);
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        void execute_emptyTextBodyOfEvent_InvalidExtractCommandException() throws DukeException {
            UserData data = new UserData();
            Ui ui = new Ui();
            Storage storage = new Storage("data", ui);
            String extractInput = "quiz; ";
            assertThrows(InvalidExtractCommandException.class, () -> {
                ExtractCommand extractCommand = new ExtractCommand(extractInput);
                extractCommand.execute(data, ui, storage);
            });
        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }

    @Nested
    class TestPersonalEventNoDateNoTime {
        @BeforeEach
        public void setUp() {
            String extractInput = "Hi class, please note there will be a meeting soon \r\n extractend \r\n";
            ByteArrayInputStream inStream = new ByteArrayInputStream(extractInput.getBytes());
            System.setIn(inStream);
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        void execute_CreatePersonalEventWithNoDateOrTime_printSuccessfulAdd() throws DukeException {
            UserData data = new UserData();
            Ui ui = new Ui();
            Storage storage = new Storage("data", ui);

            String extractInput = "Meeting;";
            Command extractCommand = new ExtractCommand(extractInput);
            extractCommand.execute(data, ui, storage);
            assertEquals("Copy and paste or enter the body of the text you want to extract from!"
                            + System.lineSeparator()
                            + "At the end of your text, press enter to go to the next line, enter 'extractend' "
                            + "with no quotation marks and press enter once more." + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "No date detected for this text body!" + System.lineSeparator()
                            + "Since no date was detected in the text body, "
                            + "the personal event will only contain the description." + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][X] Meeting" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }


    @Nested
    class TestPersonalEventDateOnly {
        @BeforeEach
        public void setUp() {
            String extractInput = "Hi class, please note there will be a makeup lesson on 17th nov"
                    + " and 32 jan \r\n extractend \r\n";
            ByteArrayInputStream inStream = new ByteArrayInputStream(extractInput.getBytes());
            System.setIn(inStream);
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        void execute_CreatePersonalEventWithDateOnly_printSuccessfulAdd() throws DukeException {
            UserData data = new UserData();
            Ui ui = new Ui();
            Storage storage = new Storage("data", ui);

            String extractInput = "CS2113T Makeup Lesson;";
            Command extractCommand = new ExtractCommand(extractInput);
            extractCommand.execute(data, ui, storage);
            assertEquals("Copy and paste or enter the body of the text you want to extract from!"
                            + System.lineSeparator()
                            + "At the end of your text, press enter to go to the next line, enter 'extractend' "
                            + "with no quotation marks and press enter once more." + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "One date detected and chosen: 2020-11-17" + System.lineSeparator()
                            + "No timing detected for this text body!" + System.lineSeparator()
                            + "Since no time was detected in the text body, "
                            + "the personal event will only contain the description and the date."
                            + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][X] CS2113T Makeup Lesson on 2020-11-17" + System.lineSeparator(),
                    outputStreamCaptor.toString());

        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }


    @Nested
    class TestPersonalEventOneDateAndTime {
        @BeforeEach
        public void setUp() {
            String extractInput = "Hi class, please note there will be a quiz \r\n on oct 5 2020 "
                    + "at 4pm \r\n extractend \r\n";
            ByteArrayInputStream inStream = new ByteArrayInputStream(extractInput.getBytes());
            System.setIn(inStream);
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        void execute_CreatePersonalEventOneDateAndTime_printSuccessfulAdd() throws DukeException {
            UserData data = new UserData();
            Ui ui = new Ui();
            Storage storage = new Storage("data", ui);
            Command extractCommand = new ExtractCommand("CG2271 Quiz;");
            extractCommand.execute(data, ui, storage);
            assertEquals("Copy and paste or enter the body of the text you want to extract from!"
                            + System.lineSeparator()
                            + "At the end of your text, press enter to go to the next line, enter 'extractend' "
                            + "with no quotation marks and press enter once more." + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "One date detected and chosen: 2020-10-05" + System.lineSeparator()
                            + "One timing detected and chosen: 16:00" + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][X] CG2271 Quiz on 2020-10-05, 16:00" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }


    @Nested
    class TestPersonalEventMultipleDateAndTime {
        @BeforeEach
        public void setUp() {
            String extractInput = "Hi class, please note there will be a quiz \r\n "
                    + "on jan 30 or may 15 at 06:00 am or 7pm \r\n extractend \r\n 2 \r\n 2 \r\n";
            ByteArrayInputStream inStream = new ByteArrayInputStream(extractInput.getBytes());
            System.setIn(inStream);
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        void execute_CreatePersonalEventMultipleDateAndTime_printSuccessfulAdd() throws DukeException {
            UserData data = new UserData();
            Ui ui = new Ui();
            Storage storage = new Storage("data", ui);
            Command extractCommand = new ExtractCommand("GEH quiz;");
            extractCommand.execute(data, ui, storage);
            assertEquals("Copy and paste or enter the body of the text you want to extract from!"
                            + System.lineSeparator()
                            + "At the end of your text, press enter to go to the next line, enter 'extractend' "
                            + "with no quotation marks and press enter once more." + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "We have detected 2 dates in this text body!" + System.lineSeparator()
                            + "Please select the date you want for this event from the list below!"
                            + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "1. 2020-01-30" + System.lineSeparator()
                            + "2. 2020-05-15" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "We have detected 2 timings in this text body!" + System.lineSeparator()
                            + "Please select the time you want for this event from the list below!"
                            + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "1. 06:00" + System.lineSeparator()
                            + "2. 19:00" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][X] GEH quiz on 2020-05-15, 19:00" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }

    @Nested
    class TestZoomEventLinkOnly {
        @BeforeEach
        public void setUp() {
            String extractInput = "The seminar will be held via Zoom. You may tune in using this link:\n"
                    + "\n" + "https://nus-sg.zoom.us/j/9290988107?pwd=dDZSQ0lTa0loaTRPc1F1d01hbWVFZz09"
                    + " \r\n extractend \r\n";
            ByteArrayInputStream inStream = new ByteArrayInputStream(extractInput.getBytes());
            System.setIn(inStream);
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @Test
        void execute_CreateZoomEventLinkOnly_printSuccessfulAdd() throws DukeException {
            UserData data = new UserData();
            Ui ui = new Ui();
            Storage storage = new Storage("data", ui);
            Command extractCommand = new ExtractCommand("Seminar;");
            extractCommand.execute(data, ui, storage);
            assertEquals("Copy and paste or enter the body of the text you want to extract from!"
                            + System.lineSeparator()
                            + "At the end of your text, press enter to go to the next line, enter 'extractend' "
                            + "with no quotation marks and press enter once more." + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "One zoom link detected and chosen:"
                            + " https://nus-sg.zoom.us/j/9290988107?pwd=dDZSQ0lTa0loaTRPc1F1d01hbWVFZz09"
                            + System.lineSeparator()
                            + "No date detected for this text body!" + System.lineSeparator()
                            + "Since no date was detected in the text body,"
                            + " the zoom event will only contain the description and zoom link."
                            + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[Z][X] Seminar, Link:"
                            + " https://nus-sg.zoom.us/j/9290988107?pwd=dDZSQ0lTa0loaTRPc1F1d01hbWVFZz09"
                            + System.lineSeparator(),
                    outputStreamCaptor.toString());
        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }
    }
}